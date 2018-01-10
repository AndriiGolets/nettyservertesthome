package name.golets.nettyserver.camel;

import io.netty.handler.codec.http.HttpRequest;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.netty4.http.NettyHttpMessage;
import org.springframework.stereotype.Component;

@Component
public class ReadProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {

       HttpRequest request = exchange.getIn (NettyHttpMessage.class).getHttpRequest ();
        System.out.println (request.toString ());

    }
}
