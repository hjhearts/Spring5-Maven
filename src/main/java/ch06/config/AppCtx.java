package ch06.config;

import ch06.spring.Client;
import ch06.spring.Client2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppCtx {

    public Client client(){
        Client client = new Client();
        client.setHost("host");
        return client;
    }

    @Bean(initMethod = "connect", destroyMethod = "close", value = "cl2")
    public Client2 client2(){
        Client2 client2 = new Client2();
        client2.setHost("127.0.0.1");
        return client2;
    }
}
