package camel;

import org.apache.camel.util.jsse.KeyManagersParameters;
import org.apache.camel.util.jsse.KeyStoreParameters;
import org.apache.camel.util.jsse.SSLContextParameters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean("sslContextParameters")
    SSLContextParameters getSSLContextParameters(){
        KeyStoreParameters ksp = new KeyStoreParameters();
        ksp.setResource("/home/GAMELOFT/andrii.golets/work/jdk1.8.0_151/jre/lib/security/cacerts");
        ksp.setPassword("changeit");

        KeyManagersParameters kmp = new KeyManagersParameters();
        kmp.setKeyStore(ksp);
        kmp.setKeyPassword("changeit");


        SSLContextParameters scp = new SSLContextParameters();
        scp.setKeyManagers(kmp);
        return scp;
    }

}
