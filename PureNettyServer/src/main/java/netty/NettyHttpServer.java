package netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import netty.handlers.HttpChannelHandler;

/**
 * Created by andrii on 1/6/18.
 */
public class NettyHttpServer {

    private ChannelFuture channel;
    private final EventLoopGroup masterGroup;
    private final EventLoopGroup slaveGroup;

    public NettyHttpServer() {
        masterGroup = new NioEventLoopGroup ();
        slaveGroup = new NioEventLoopGroup ();
    }

    public void start() // #1
    {
        Runtime.getRuntime ().addShutdownHook (new Thread () {
            @Override
            public void run() {
                shutdown ();
            }
        });

        try {
            // #3
            final ServerBootstrap bootstrap =
                    new ServerBootstrap ()
                            .group (masterGroup, slaveGroup)
                            .channel (NioServerSocketChannel.class)
                            //.localAddress(new InetSocketAddress(8088))
                            .childHandler (new ChannelInitializer<SocketChannel> () // #4
                            {
                                @Override
                                public void initChannel(final SocketChannel ch)
                                        throws Exception {
                                    ch.pipeline ().addLast ("codec", new HttpServerCodec ());
                                    ch.pipeline ().addLast ("aggregator",
                                            new HttpObjectAggregator (512 * 1024));
                                /*    ch.pipeline().addLast(
                                            new LoggingHandler(LogLevel.INFO));*/
                                    ch.pipeline ().addLast ("request", new HttpChannelHandler ());
                                }
                            })
                            .option (ChannelOption.SO_BACKLOG, 128)
                            // .option (EpollChannelOption.EPOLL_MODE, EpollMode.EDGE_TRIGGERED)
                            // .option (EpollChannelOption.SO_REUSEPORT, true)
                            .childOption (ChannelOption.SO_KEEPALIVE, true);
                           // .childOption (ChannelOption.TCP_NODELAY, true);
            channel = bootstrap.bind (8088).sync ();
        } catch (final InterruptedException e) {
        }
    }

    public void shutdown() // #2
    {
        slaveGroup.shutdownGracefully ();
        masterGroup.shutdownGracefully ();

        try {
            channel.channel ().closeFuture ().sync ();
        } catch (InterruptedException e) {
        }
    }

    public static void main(String[] args) {
        new NettyHttpServer ().start ();
    }

}
