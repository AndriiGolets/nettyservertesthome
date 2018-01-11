package netty.config;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

import netty.handlers.HttpInitializer;
import netty.handlers.HttpInitializerAutowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;


@Configuration
@ComponentScan("netty")
@PropertySource("classpath:netty-server.properties")
public class SpringConfig {

    private static final int KILOBYTE = 1024;

    @Value("${boss.thread.count}")
    private int bossCount;

    @Value("${worker.thread.count}")
    private int workerCount;

    @Value("${tcp.port}")
    private int tcpPort;

    @Value("${so.keepalive}")
    private boolean keepAlive;

    @Value("${so.backlog}")
    private int backlog;

    @Value("${aggregator.size}")
    private int maxContentLength;

    @Autowired
    private HttpInitializer httpInitializer;

    @Autowired
    private HttpInitializerAutowired httpInitializerAutowired;


    @SuppressWarnings("unchecked")
    @Bean(name = "serverBootstrap")
    public ServerBootstrap bootstrap() {

        ServerBootstrap b = new ServerBootstrap ();

        b.group (bossGroup (), workerGroup ())
                .channel (NioServerSocketChannel.class)
                //.childHandler (httpInitializer);
                .childHandler (httpInitializerAutowired);

        for (ChannelOption option : tcpChannelOptions ().keySet ()) {
            b.option (option, tcpChannelOptions ().get (option));
        }

        return b;
    }

    @Bean(destroyMethod = "shutdownGracefully")
    public NioEventLoopGroup bossGroup() {
        return new NioEventLoopGroup (bossCount);
    }

    @Bean(destroyMethod = "shutdownGracefully")
    public NioEventLoopGroup workerGroup() {
        return new NioEventLoopGroup (workerCount);
    }

    @Bean
    public InetSocketAddress tcpPort() {
        return new InetSocketAddress (tcpPort);
    }

    @Bean
    public Map<ChannelOption<?>, Object> tcpChannelOptions() {
        Map<ChannelOption<?>, Object> options = new HashMap<ChannelOption<?>, Object> ();
        options.put (ChannelOption.SO_KEEPALIVE, keepAlive);
        options.put (ChannelOption.SO_BACKLOG, backlog);
        return options;
    }

    @Bean
    public HttpServerCodec httpServerCodec() {
        return new HttpServerCodec ();
    }

    @Bean
    public HttpObjectAggregator httpObjectAggregator() {
        return new HttpObjectAggregator (maxContentLength * KILOBYTE);
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer ();
    }
}
