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

      /*  configureSslForHttp4();
        //configureSslForJetty();*/

        from ("https4://0.0.0.0:8088q=ssl&throwExceptionOnFailure=false")
                .process (readProcessor).process (exchange -> exchange.getOut ().setBody ("Hello from netty"));
    }

   /* private void configureSslForHttp4()
    {
        KeyStoreParameters trust_ksp = new KeyStoreParameters();
        trust_ksp.setResource("/home/GAMELOFT/andrii.golets/work/jdk1.8.0_151/jre/lib/security/cacerts");
        trust_ksp.setPassword("changeit");

        TrustManagersParameters trustp = new TrustManagersParameters();
        trustp.setKeyStore(trust_ksp);

        SSLContextParameters scp = new SSLContextParameters();
        scp.setTrustManagers(trustp);

        HttpComponent httpComponent = getContext().getComponent("https4", HttpComponent.class);
        httpComponent.setSslContextParameters(scp);
    }*/

   /* private void configureSslForJetty()
    {
        KeyStoreParameters ksp = new KeyStoreParameters();
        ksp.setResource("/home/GAMELOFT/andrii.golets/work/jdk1.8.0_151/jre/lib/security/cacerts");
        ksp.setPassword("changeit");

        KeyManagersParameters kmp = new KeyManagersParameters();
        kmp.setKeyStore(ksp);
        kmp.setKeyPassword("changeit");

        SSLContextParameters scp = new SSLContextParameters();
        scp.setKeyManagers(kmp);

        JettyHttpComponent jettyComponent = getContext().getComponent("jetty", JettyHttpComponent.class);
        jettyComponent.setSslContextParameters(scp);
    }*/

}
