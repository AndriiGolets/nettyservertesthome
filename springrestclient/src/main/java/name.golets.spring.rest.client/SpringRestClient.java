package name.golets.spring.rest.client;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

/**
 * Created by andrii on 1/6/18.
 */
public class SpringRestClient {

    private static RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {

        String url =  "http://localhost:8080";

        String input = "Hello Netty";


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_HTML);

        ResponseEntity<String> responseS = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(input, headers), String.class);

        System.out.println(responseS);
    }

}
