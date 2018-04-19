package netty;


import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import org.apache.camel.CamelContext;
import org.apache.camel.component.netty4.NettyConsumer;
import org.apache.camel.component.netty4.NettyServerBootstrapConfiguration;
import org.apache.camel.component.netty4.http.HttpServerBootstrapFactory;
import org.apache.camel.component.netty4.http.HttpServerConsumerChannelFactory;
import org.apache.camel.component.netty4.http.NettyHttpConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class HttpServerBootstrapFactoryWithOptions extends SingleTCPNettyServerBootstrapFactoryWithOptions {

    private static final Logger LOG = LoggerFactory.getLogger(HttpServerBootstrapFactory.class);
    private final HttpServerConsumerChannelFactory channelFactory;
    private int port;
    private NettyServerBootstrapConfiguration bootstrapConfiguration;
    private boolean compatibleCheck;

    public HttpServerBootstrapFactoryWithOptions(HttpServerConsumerChannelFactory channelFactory) {
        this(channelFactory, true);
    }

    public HttpServerBootstrapFactoryWithOptions(HttpServerConsumerChannelFactory channelFactory, boolean compatibleCheck) {
        this.channelFactory = channelFactory;
        this.compatibleCheck = compatibleCheck;
    }

    @Override
    public void init(CamelContext camelContext, NettyServerBootstrapConfiguration configuration, ChannelInitializer<Channel> pipelineFactory) {
        super.init(camelContext, configuration, pipelineFactory);
        this.port = configuration.getPort();
        this.bootstrapConfiguration = configuration;

        LOG.info("BootstrapFactory on port {} is using bootstrap configuration: [{}]", port, bootstrapConfiguration.toStringBootstrapConfiguration());
    }

    public void addConsumer(NettyConsumer consumer) {
        if (compatibleCheck) {
            // when adding additional consumers on the same port (eg to reuse port for multiple routes etc) then the Netty server bootstrap
            // configuration must match, as its the 1st consumer that calls the init method, which configuration is used for the Netty server bootstrap
            // we do this to avoid mis configuration, so people configure SSL and plain configuration on the same port etc.

            // first it may be the same instance, so only check for compatibility of different instance
            if (bootstrapConfiguration != consumer.getConfiguration() && !bootstrapConfiguration.compatible(consumer.getConfiguration())) {
                throw new IllegalArgumentException("Bootstrap configuration must be identical when adding additional consumer: " + consumer.getEndpoint() + " on same port: " + port
                        + ".\n  Existing " + bootstrapConfiguration.toStringBootstrapConfiguration() + "\n       New " + consumer.getConfiguration().toStringBootstrapConfiguration());
            }
        }

        if (LOG.isDebugEnabled()) {
            NettyHttpConsumer httpConsumer = (NettyHttpConsumer) consumer;
            LOG.debug("BootstrapFactory on port {} is adding consumer with context-path {}", port, httpConsumer.getConfiguration().getPath());
        }

        channelFactory.addConsumer((NettyHttpConsumer) consumer);
    }

    @Override
    public void removeConsumer(NettyConsumer consumer) {
        if (LOG.isDebugEnabled()) {
            NettyHttpConsumer httpConsumer = (NettyHttpConsumer) consumer;
            LOG.debug("BootstrapFactory on port {} is removing consumer with context-path {}", port, httpConsumer.getConfiguration().getPath());
        }
        channelFactory.removeConsumer((NettyHttpConsumer) consumer);
    }

    @Override
    protected void doStart() throws Exception {
        LOG.debug("BootstrapFactory on port {} is starting", port);
        super.doStart();
    }

    @Override
    public void stop() throws Exception {
        // only stop if no more active consumers
        int consumers = channelFactory.consumers();
        if (consumers == 0) {
            LOG.debug("BootstrapFactory on port {} is stopping", port);
            super.stop();
        } else {
            LOG.debug("BootstrapFactory on port {} has {} registered consumers, so cannot stop yet.", port, consumers);
        }
    }

}