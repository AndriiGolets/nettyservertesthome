package netty.handlers;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HttpInitializerAutowired extends ChannelInitializer<SocketChannel> {

    private final HttpObjectAggregator aggregator;

    private final HttpServerCodec httpServerCodec;

    private final HttpChannelHandler httpChannelHandler;

    @Autowired
    public HttpInitializerAutowired(HttpObjectAggregator aggregator, HttpServerCodec httpServerCodec, HttpChannelHandler httpChannelHandler) {
        this.aggregator = aggregator;
        this.httpServerCodec = httpServerCodec;
        this.httpChannelHandler = httpChannelHandler;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline ().addLast (httpServerCodec);
        ch.pipeline ().addLast (aggregator);
        ch.pipeline ().addLast (httpChannelHandler);
        // ch.pipeline().addLast(new LoggingHandler(LogLevel.INFO));

    }

}
