package ch11.config;

import ch11.controller.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllerConfig {
    @Bean
    public RegisterController registerController(){
        return new RegisterController();
    }

    @Bean
    public SurveyController surveyController(){
        return new SurveyController();
    }

    @Bean
    public LoginController loginController(){
        return new LoginController();
    }

    @Bean
    public LogoutController logoutController(){
        return new LogoutController();
    }

    @Bean
    public ChangePwdController changePwdController(){
        return new ChangePwdController();
    }

    @Bean
    public MemberListController memberListController(){
        return new MemberListController();
    }

    @Bean
    public MemberDetailController memberDetailController(){
        return new MemberDetailController();
    }
/*
    @Bean
    public DataExceptionHandler dataExceptionHandler(){
        return new DataExceptionHandler();
    }

 */

    @Bean
    public RestMemberController restMemberController(){
        return new RestMemberController();
    }

    @Bean
    public ApiExceptionAdvice apiExceptionAdvice(){
        return new ApiExceptionAdvice();
    }
}
