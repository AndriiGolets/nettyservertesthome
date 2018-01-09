package name.golets.netty.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * Created by andrii on 1/5/18.
 */
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    private static ScheduledThreadPoolExecutor TIME_SERVICE = new ScheduledThreadPoolExecutor(2);

    private static void startScheduleTask(final Channel channel){
        TIME_SERVICE.scheduleAtFixedRate(() ->
                channel.writeAndFlush(Unpooled.copiedBuffer(
                        " Netty rocks! \n" , CharsetUtil.UTF_8)),0,1, TimeUnit.SECONDS);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        startScheduleTask(ctx.channel());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext,
                                ByteBuf in) throws Exception {
        System.out.println("Client received: " + in.toString(CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
