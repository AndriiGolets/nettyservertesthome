package camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CamelNettyServer extends RouteBuilder{

    @Autowired
    private ReadProcessor readProcessor;



    @Override
    public void configure() throws Exception {

        from ("netty4-http:http://localhost:8088?keepAlive=true&ssl=true&sslContextParameters=#sslContextParameters")
                .process (readProcessor).process(new Processor () {
            public void process(Exchange exchange) throws Exception {
                exchange.getOut().setBody("Hello from netty");
            }
        });
    }
}
