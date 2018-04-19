package camel;


import org.apache.camel.builder.RouteBuilder;

import org.apache.camel.component.http4.HttpComponent;

import org.apache.camel.util.jsse.KeyStoreParameters;
import org.apache.camel.util.jsse.SSLContextParameters;
import org.apache.camel.util.jsse.TrustManagersParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CamelNettyServer extends RouteBuilder {

    @Autowired
    private ReadProcessor readProcessor;


    @Override
    public void configure() throws Exception {

        from("netty4-http:http://localhost:8080?bootstrapConfiguration=#bootstrapConfiguration").process(readProcessor);



    }

}
