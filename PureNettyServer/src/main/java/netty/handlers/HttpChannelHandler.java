package netty.handlers;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


import java.security.MessageDigest;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Sharable
@Component
public class HttpChannelHandler extends ChannelInboundHandlerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpChannelHandler.class);

    private static AtomicInteger counter = new AtomicInteger (0);
    private static ScheduledThreadPoolExecutor TIME_SERVICE = new ScheduledThreadPoolExecutor (2);

    static  {
        TIME_SERVICE.scheduleAtFixedRate (() -> {
            LOGGER.info ("Requests handled in second : " +  counter);
            counter.set (0);
        }, 0, 1, TimeUnit.SECONDS);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        if (msg instanceof FullHttpRequest) {
            final FullHttpRequest request = (FullHttpRequest) msg;

            MessageDigest md = MessageDigest.getInstance("SHA-1");

            System.out.println(" -- " + request.content().writerIndex());
            System.out.println(" -- " +  ByteBufUtil.hashCode(request.content()));
            System.out.println(request.toString());
            counter.incrementAndGet ();
            final String responseMessage = "Hello from Netty!";
            ByteBuf byteBuf = Unpooled.copiedBuffer (responseMessage.getBytes ());

            FullHttpResponse response = new DefaultFullHttpResponse (
                    HttpVersion.HTTP_1_1,
                    HttpResponseStatus.OK,
                    byteBuf
            );
          //  byteBuf.release ();

            if (HttpUtil.isKeepAlive (request)) {
                response.headers ().set (
                        HttpHeaderNames.CONNECTION,
                        HttpHeaderValues.KEEP_ALIVE
                );
            }

            response.headers ().set (HttpHeaderNames.CONTENT_TYPE,
                    "text/plain");
            response.headers ().set (HttpHeaderNames.CONTENT_LENGTH,
                    responseMessage.length ());

            ctx.writeAndFlush (response);
        } else {
            super.channelRead (ctx, msg);
        }
    }


    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush ();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,
                                Throwable cause) throws Exception {
        ByteBuf byteBuf = Unpooled.copiedBuffer (cause.getMessage ().getBytes ());
        ctx.writeAndFlush (new DefaultFullHttpResponse (
                HttpVersion.HTTP_1_1,
                HttpResponseStatus.INTERNAL_SERVER_ERROR,
                byteBuf
        ));
        //byteBuf.release ();
    }
}
