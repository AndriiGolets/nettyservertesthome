package name.golets.spring.rest.client;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class RunRestClient implements CommandLineRunner {


    private static final int CLIENT_THREADS_NUMBER = 4;

    private static ExecutorService executor = Executors.newFixedThreadPool (CLIENT_THREADS_NUMBER);

    public static void main(String[] args) {
        SpringApplication.run (RunRestClient.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {


        List<ClientThread> threadList = new ArrayList<> ();

        for (int i = 0; i < CLIENT_THREADS_NUMBER; i++) {
            threadList.add (new ClientThread (i < 10 ? "0" + i : "" + i));
        }

        try {
            executor.invokeAll (threadList);
        } catch (InterruptedException e) {
            e.printStackTrace ();
        }

    }
}
