package camel;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CamelNettyServer extends RouteBuilder{

    @Autowired
    private ReadProcessor readProcessor;

    @Override
    public void configure() throws Exception {
        System.out.println (" ----- CamelNettyRouteStart ");
        from ("netty4-http:http://localhost:8088?keepAlive=true").process (readProcessor).transform ().constant ("Hello from Netty!");
    }
}
