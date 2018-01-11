package boot;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class SimpleRestController {

    private static AtomicInteger counter = new AtomicInteger (0);
    private static ScheduledThreadPoolExecutor TIME_SERVICE = new ScheduledThreadPoolExecutor (2);

    static  {
        TIME_SERVICE.scheduleAtFixedRate (() -> {
            System.out.println ("Requests handled in second : " +  counter);
            counter.set (0);
        }, 0, 1, TimeUnit.SECONDS);
    }

    @RequestMapping("/")
    @ResponseBody
    public String test() {
        counter.incrementAndGet ();
        return "hello from netty";
    }
}
