package ch03.config;

import ch03.spring.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConf2 {
    @Autowired
    private MemberDAO memberDAO;
    @Autowired
    private MemberPrinter memberPrinter;

    @Bean
    public MemberRegisterService memberRegisterService(){
        return new MemberRegisterService(memberDAO);
    }

    @Bean
    public ChangePasswordService changePasswordService(){
        ChangePasswordService changePasswordService = new ChangePasswordService();
        changePasswordService.setMemberDAO(memberDAO);
        return changePasswordService;
    }

    @Bean
    public MemberListPrinter memberListPrinter(){
        return new MemberListPrinter(memberDAO, memberPrinter);
    }

    @Bean
    public MemberInfoPrinter memberInfoPrinter(){
        MemberInfoPrinter memberInfoPrinter = new MemberInfoPrinter();
        memberInfoPrinter.setMemberPrinter(memberPrinter);
        memberInfoPrinter.setMemberDAO(memberDAO);
        return memberInfoPrinter;
    }

    @Bean
    public VersionPrinter versionPrinter(){
        VersionPrinter versionPrinter = new VersionPrinter();
        versionPrinter.setMajorVersion(5);
        versionPrinter.setMinorVersion(0);
        return versionPrinter;
    }
}
