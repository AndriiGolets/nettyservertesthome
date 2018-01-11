package netty.handlers;

import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.channel.socket.SocketChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HttpInitializer extends ChannelInitializer<SocketChannel> {

  /*  private final HttpObjectAggregator aggregator;

    private final HttpServerCodec httpServerCodec;

    private final HttpChannelHandler httpChannelHandler;

    @Autowired
    public HttpInitializer(HttpObjectAggregator aggregator, HttpServerCodec httpServerCodec, HttpChannelHandler httpChannelHandler) {
        this.aggregator = aggregator;
        this.httpServerCodec = httpServerCodec;
        this.httpChannelHandler = httpChannelHandler;
    }*/

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline ().addLast (new HttpServerCodec ());
        ch.pipeline ().addLast (new HttpObjectAggregator (512 * 1024));
        // ch.pipeline().addLast(new LoggingHandler(LogLevel.INFO));
        ch.pipeline ().addLast (new HttpChannelHandler ());
    }

}
