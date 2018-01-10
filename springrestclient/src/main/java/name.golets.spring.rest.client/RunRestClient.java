package name.golets.spring.rest.client;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class RunRestClient implements CommandLineRunner{

    private static ExecutorService executor = Executors.newFixedThreadPool (4);

    public static void main(String[] args) {
        SpringApplication.run (RunRestClient.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {


        List<ClientThread> threadList = new ArrayList<> ();

        for (int i = 0; i < 1; i++) {
            threadList.add (new ClientThread ("0" + i));
        }

        try {
            executor.invokeAll (threadList);
        } catch (InterruptedException e) {
            e.printStackTrace ();
        }

    }
}
