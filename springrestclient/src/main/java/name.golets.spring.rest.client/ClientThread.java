package name.golets.spring.rest.client;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class ClientThread implements Callable<Integer> {

    private static RestTemplate restTemplate = new RestTemplate ();

    private String name;

    public ClientThread(String name) {
        this.name = name;
    }

    @Override
    public Integer call() throws Exception {


        System.out.println (" Client : " + name + " started !");
        String url = "http://localhost:8080";

        String input = "Hello Netty";


        HttpHeaders headers = new HttpHeaders ();
        headers.setContentType (MediaType.TEXT_HTML);

        List<ResponseEntity<String>> respList = new ArrayList<> (16000);
        long startTime = System.nanoTime ();
        for (int i = 0; i < 500000; i++) {
            ResponseEntity<String> responseS = restTemplate.exchange (url, HttpMethod.GET, new HttpEntity<> (input, headers), String.class);
            respList.add (responseS);
        }

        long difference = System.nanoTime () - startTime;
        System.out.println (respList.size ());

        System.out.println (name + " Total execution time: " +
                String.format ("%d min, %d sec , %d mil",
                        TimeUnit.NANOSECONDS.toMinutes (difference),
                        TimeUnit.NANOSECONDS.toSeconds (difference),
                        TimeUnit.NANOSECONDS.toMillis (difference)
                ));

        return null;
    }
}
