package name.golets.netty.client;

import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.util.CharsetUtil;

import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * Created by andrii on 1/5/18.
 */
public class SceduleTask implements Runnable {

    private static ScheduledThreadPoolExecutor TIME_SERVICE = new ScheduledThreadPoolExecutor(2);

    private Channel ch;

    public SceduleTask(Channel ch) {
        this.ch = this.ch;
    }

    @Override
    public void run() {
        ch.writeAndFlush(Unpooled.copiedBuffer(" Netty rocks! \n" , CharsetUtil.UTF_8));
    }
}
