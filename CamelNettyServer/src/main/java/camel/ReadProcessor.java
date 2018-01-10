package camel;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.netty4.http.NettyHttpMessage;
import org.springframework.stereotype.Component;

@Component
public class ReadProcessor implements Processor {

    private static AtomicInteger counter = new AtomicInteger (0);
    private static ScheduledThreadPoolExecutor TIME_SERVICE = new ScheduledThreadPoolExecutor (2);

    static {
        TIME_SERVICE.scheduleAtFixedRate (() -> {
            System.out.println ("Requests handled in second : " + counter);
            counter.set (0);
        }, 0, 1, TimeUnit.SECONDS);
    }

    @Override
    public void process(Exchange exchange) throws Exception {

        exchange.getIn (NettyHttpMessage.class).getHttpRequest ();
        counter.incrementAndGet ();
        // System.out.println (request.toString ());
    }

}
