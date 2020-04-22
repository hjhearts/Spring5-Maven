//package ch03.config;
//
//import ch03.spring.*;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class AppCtx {
//    @Bean
//    public MemberDAO memberDAO(){
//        return new MemberDAO();
//    }
//    @Bean
//    public MemberRegisterService memberRegisterService(){
//        return new MemberRegisterService(memberDAO());
//    }
//    @Bean
//    public ChangePasswordService changePasswordService(){
//        ChangePasswordService service = new ChangePasswordService();
//        service.setMemberDAO(memberDAO());
//        return service;
//    }
//    @Bean
//    public MemberPrinter memberPrinter(){
//        return new MemberPrinter();
//    }
//    @Bean
//    public MemberListPrinter memberListPrinter(){
//        return new MemberListPrinter(memberDAO(), memberPrinter());
//    }
//    @Bean
//    public MemberInfoPrinter memberInfoPrinter(){
//        MemberInfoPrinter memberInfoPrinter = new MemberInfoPrinter();
//        memberInfoPrinter.setMemberDAO(memberDAO());
//        memberInfoPrinter.setMemberPrinter(memberPrinter());
//        return memberInfoPrinter;
//    }
//    @Bean
//    public VersionPrinter versionPrinter(){
//        VersionPrinter versionPrinter = new VersionPrinter();
//        versionPrinter.setMajorVersion(5);
//        versionPrinter.setMinorVersion(0);
//        return versionPrinter;
//    }
//
//}
