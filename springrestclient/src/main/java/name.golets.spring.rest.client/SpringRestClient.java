package name.golets.spring.rest.client;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by andrii on 1/6/18.
 */
public class SpringRestClient {

    private static ExecutorService executor = Executors.newFixedThreadPool (4);

    public static void main(String[] args) {

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
