package ch03.config;

import ch03.spring.ChangePasswordService;
import ch03.spring.MemberDAO;
import ch03.spring.MemberRegisterService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppCtx {
    @Bean
    public MemberDAO memberDAO(){
        return new MemberDAO();
    }
    @Bean
    public MemberRegisterService memberRegisterService(){
        return new MemberRegisterService(memberDAO());
    }

    public ChangePasswordService changePasswordService(){
        ChangePasswordService service = new ChangePasswordService();
        service.setMemberDAO(memberDAO());
        return service;
    }
}
