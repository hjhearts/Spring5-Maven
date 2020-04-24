package ch08.main;

import ch08.config.AppCtx;
import ch08.spring.ChangePasswordService;
import ch08.spring.MemberNotFoundException;
import ch08.spring.WrongPasswordException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class MainForCPS {
    public static void main(String[] args) {
        AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);
        ChangePasswordService cps = ctx.getBean(ChangePasswordService.class);
        try{
            cps.changePassword("covid@covid.net", "1111", "1234");
        }catch (MemberNotFoundException nfe){
            System.out.println("회원이 존재하지 않습니다.");
        }catch(WrongPasswordException wpe){
            System.out.println("패스워드가 일치하지 않습니다.");
        }
        ctx.close();
    }
}
