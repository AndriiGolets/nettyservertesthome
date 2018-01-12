package netty.config;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

import netty.handlers.HttpInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;


@Configuration
@PropertySource("classpath:netty-server.properties")
public class SpringConfig {

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


    private final HttpInitializer httpInitializer;

    @Autowired
    public SpringConfig(HttpInitializer httpInitializer) {
        this.httpInitializer = httpInitializer;
    }

    @SuppressWarnings("unchecked")
    @Bean(name = "serverBootstrap")
    public ServerBootstrap bootstrap() {

        ServerBootstrap b = new ServerBootstrap ();

        b.group (bossGroup (), workerGroup ())
                .channel (NioServerSocketChannel.class)
                .childHandler (httpInitializer);

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
    public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer ();
    }
}
