package ch06.main;

import ch06.config.AppCtx;
import ch06.spring.Client;
import ch06.spring.Client2;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class Main {
    public static void main(String[] args) {
        AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);
        Client2 client = ctx.getBean("cl2", Client2.class);
        client.send();
        ctx.close();
    }
}
