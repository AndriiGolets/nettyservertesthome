package name.golets.spring.rest.client;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class ClientThread implements Callable<Integer> {

    private static final int TOTAL = 512;
    private static final int PER_ROUTE = 256;
    private String name;

    public ClientThread(String name) {
        this.name = name;
    }

    @Override
    public Integer call() throws Exception {


        System.out.println (" Client : " + name + " started !");
        String url = "http://localhost:8088";

        String input = "Hello Netty";

        Message message = new Message (input);

        RestTemplate restTemplate = new RestTemplate ();
        HttpClient httpClient = HttpClientBuilder.create ()
                .setMaxConnTotal (TOTAL)
                .setMaxConnPerRoute (PER_ROUTE)
                .build ();
        restTemplate.setRequestFactory (new HttpComponentsClientHttpRequestFactory (httpClient));

        HttpHeaders headers = new HttpHeaders ();
        List<Charset> acceptCharset = Collections.singletonList (StandardCharsets.UTF_8);
        headers.setAcceptCharset (acceptCharset);

        //  headers.setContentType (MediaType.TEXT_HTML);
        HttpEntity<Message> entity = new HttpEntity<> (message, headers);

        long startTime = System.nanoTime ();
        for (int i = 0; i < 500000; i++) {
            ResponseEntity<String> responseS = restTemplate.exchange (url, HttpMethod.POST, entity, String.class);
            // System.out.println (responseS.toString ());
        }

        long difference = System.nanoTime () - startTime;

        System.out.println (name + " Total execution time: " +
                String.format ("%d min, %d sec , %d mil",
                        TimeUnit.NANOSECONDS.toMinutes (difference),
                        TimeUnit.NANOSECONDS.toSeconds (difference),
                        TimeUnit.NANOSECONDS.toMillis (difference)
                ));

        return null;
    }

    class Message {

        private String message;

        Message(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
//<200 OK,Hello from Netty!,{connection=[keep-alive], content-type=[text/plain], content-length=[17]}>

/*

        LOGGER.info ("Starting server at " + tcpPort);

        Runtime.getRuntime ().addShutdownHook (new Thread () {
            @Override
            public void run() {
                try {
                    serverChannelFuture.channel ().closeFuture ().sync ();
                } catch (Exception e) {
                    LOGGER.error (e.getMessage (), e);
                }
                LOGGER.info ("Server at port: " + tcpPort + " Shutdown");
            }
        });

        serverChannelFuture = b.bind (tcpPort).sync ();
    }

  @Bean(destroyMethod = "shutdownGracefully")
    public NioEventLoopGroup bossGroup() {
        return new NioEventLoopGroup (bossCount);
    }

    @Bean(destroyMethod = "shutdownGracefully")
    public NioEventLoopGroup workerGroup() {
        return new NioEventLoopGroup (workerCount);
    }

* */