package name.golets.spring.rest.client;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by andrii on 1/6/18.
 */
public class SpringRestClient {

    private static ExecutorService executor = Executors.newFixedThreadPool (20);

    public static void main(String[] args) {

        List<ClientThread> threadList = new ArrayList<> ();

        for (int i = 0; i < 10; i++) {
            threadList.add (new ClientThread ("0" + i));
        }

        try {
            executor.invokeAll (threadList);
        } catch (InterruptedException e) {
            e.printStackTrace ();
        }

    }


}
