package ch04.config;

import ch04.spring.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.format.DateTimeFormatter;

@Configuration
public class AppCtx{
    @Bean
    public MemberDAO memberDAO(){
        return new MemberDAO();
    }

    @Bean
    public MemberRegisterService memberRegisterService(){
        return new MemberRegisterService(memberDAO());
    }

    @Bean
    public ChangePasswordService changePasswordService(){
        return new ChangePasswordService();
    }

    @Bean
    @Qualifier("printer")
    public MemberPrinter memberPrinter(){
        return new MemberPrinter();
    }

    @Bean
    public MemberInfoPrinter memberInfoPrinter(){
        return new MemberInfoPrinter();
    }

    @Bean
    public MemberListPrinter memberListPrinter(){
        return new MemberListPrinter();
    }

    @Bean
    public VersionPrinter versionPrinter(){
        VersionPrinter versionPrinter = new VersionPrinter();
        versionPrinter.setMajorVersion(5);
        versionPrinter.setMinorVersion(0);
        return versionPrinter;
    }


}