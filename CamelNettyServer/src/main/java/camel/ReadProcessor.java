package camel;

import java.nio.charset.Charset;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpRequest;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.netty4.http.NettyHttpMessage;
import org.apache.camel.util.CaseInsensitiveMap;
import org.springframework.stereotype.Component;

@Component
public class ReadProcessor implements Processor {

    private static AtomicInteger counter = new AtomicInteger(0);
    private static ScheduledThreadPoolExecutor TIME_SERVICE = new ScheduledThreadPoolExecutor(2);

    static {
        TIME_SERVICE.scheduleAtFixedRate(() -> {
            System.out.println("Requests handled in second : " + counter);
            counter.set(0);
        }, 0, 1, TimeUnit.SECONDS);
    }

    @Override
    public void process(Exchange exchange) throws Exception {

        FullHttpRequest request = exchange.getIn(NettyHttpMessage.class).getHttpRequest();
        String ipLocal = exchange.getIn().getHeader("CamelNettyLocalAddress").toString();
        String ipRemote = exchange.getIn().getHeader("CamelNettyRemoteAddress").toString();
        System.out.println(ipLocal);
        System.out.println(ipRemote);


        counter.incrementAndGet();
        /*System.out.println (request.toString() + "\n");
        System.out.println (request.content().toString(Charset.forName("UTF-8")));
*/
    }
}
