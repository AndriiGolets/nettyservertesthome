package netty.handlers;

import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.channel.socket.SocketChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:netty-server.properties")
public class HttpInitializer extends ChannelInitializer<SocketChannel> {

    @Value("${aggregator.size}")
    private int maxContentLength;

    private static final int KILOBYTE = 1024;

    private final HttpChannelHandler httpChannelHandler;

    @Autowired
    public HttpInitializer(HttpChannelHandler httpChannelHandler) {
        this.httpChannelHandler = httpChannelHandler;

    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline ().addLast (new HttpServerCodec ());
        ch.pipeline ().addLast (new HttpObjectAggregator (maxContentLength * KILOBYTE));
        // ch.pipeline().addLast(new LoggingHandler(LogLevel.INFO));
        ch.pipeline ().addLast (httpChannelHandler);
    }
}
