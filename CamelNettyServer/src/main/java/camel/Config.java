package camel;


import netty.HttpServerBootstrapFactoryWithOptions;
import netty.SingleTCPNettyServerBootstrapFactoryWithOptions;
import org.apache.camel.component.netty4.NettyServerBootstrapConfiguration;
import org.apache.camel.component.netty4.SingleTCPNettyServerBootstrapFactory;
import org.apache.camel.component.netty4.http.HttpServerInitializerFactory;
import org.apache.camel.component.netty4.http.handlers.HttpServerMultiplexChannelHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.HashMap;


@Configuration
public class Config {

    private final int bossCount = 1;
    private final int workerCount = 8;
    private final int backlog = 128;
    private final int timeout = 3000;

    @Bean("bootstrapConfiguration")
    public NettyServerBootstrapConfiguration bootstrapConfiguration() {

        NettyServerBootstrapConfiguration bootstrapConfiguration = new NettyServerBootstrapConfiguration();


//        bootstrapConfiguration.setWorkerGroup(workerGroupEpoll());
//        bootstrapConfiguration.setBossGroup(bossGroupEpoll());

        //HttpServerInitializerFactory

        bootstrapConfiguration.setWorkerCount(workerCount);
       // bootstrapConfiguration.setNettyServerBootstrapFactory(new SingleTCPNettyServerBootstrapFactory());

        bootstrapConfiguration.setBossCount(bossCount);
        bootstrapConfiguration.setNativeTransport(true);
        bootstrapConfiguration.setConnectTimeout(3000);
        bootstrapConfiguration.setReuseAddress(false);
        bootstrapConfiguration.setOptions(new HashMap<String, Object>(){{

            /*put(ChannelOption.CONNECT_TIMEOUT_MILLIS.name(), timeout);
            put(ChannelOption.SO_KEEPALIVE.name(), timeout);*/
            put("option.child.SO_KEEPALIVE", false);

        }});
        bootstrapConfiguration.setNettyServerBootstrapFactory(new HttpServerBootstrapFactoryWithOptions(new HttpServerMultiplexChannelHandler()));

        return bootstrapConfiguration;
    }

    /*@Lazy
    @Bean(destroyMethod = "shutdownGracefully")
    public EventLoopGroup bossGroupEpoll() {
        return new EpollEventLoopGroup(bossCount);
    }

    @Lazy
    @Bean(destroyMethod = "shutdownGracefully")
    public EventLoopGroup workerGroupEpoll() {
        return new EpollEventLoopGroup(workerCount);
    }*/

  /*  @Bean("sslContextParameters")
    SSLContextParameters getSSLContext( ){
        KeyStoreParameters keyStoreParameters = new KeyStoreParameters();
        // Change this path to point to your truststore/keystore as jks files
        keyStoreParameters.setResource ("/home/GAMELOFT/andrii.golets/work/jdk1.8.0_151/jre/lib/security/cacerts");
        keyStoreParameters.setPassword ("changeit");

        KeyManagersParameters keyManagersParameters = new KeyManagersParameters();
        keyManagersParameters.setKeyStore(keyStoreParameters);
        keyManagersParameters.setKeyPassword("changeit");

        TrustManagersParameters trustManagersParameters = new TrustManagersParameters();
        trustManagersParameters.setKeyStore(keyStoreParameters);

        SSLContextParameters sslContextParameters = new SSLContextParameters();
        sslContextParameters.setKeyManagers(keyManagersParameters);
        sslContextParameters.setTrustManagers(trustManagersParameters);

        return sslContextParameters;
    }*/

}
