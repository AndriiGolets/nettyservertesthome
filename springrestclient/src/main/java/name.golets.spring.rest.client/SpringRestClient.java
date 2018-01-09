package name.golets.spring.rest.client;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by andrii on 1/6/18.
 */
public class SpringRestClient {

    private static RestTemplate restTemplate = new RestTemplate ();

    public static void main(String[] args) {

        String url = "http://localhost:8080";

        String input = "Hello Netty";


        HttpHeaders headers = new HttpHeaders ();
        headers.setContentType (MediaType.TEXT_HTML);


        List<ResponseEntity<String>> respList = new ArrayList<> (16000);
        long startTime = System.nanoTime ();
        for (int i = 0; i < 16000; i++) {
            ResponseEntity<String> responseS = restTemplate.exchange (url, HttpMethod.GET, new HttpEntity<> (input, headers), String.class);
            respList.add (responseS);
        }
        long difference = System.nanoTime () - startTime;
        System.out.println (respList.size ());

        System.out.println ("Total execution time: " +
                String.format ("%d min, %d sec , %d mil",
                        TimeUnit.NANOSECONDS.toMinutes (difference),
                        TimeUnit.NANOSECONDS.toSeconds (difference),
                        TimeUnit.NANOSECONDS.toMillis (difference)
                ));

    }

}
