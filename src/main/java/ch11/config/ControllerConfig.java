package ch11.config;

import ch11.controller.LoginController;
import ch11.controller.RegisterController;
import ch11.controller.SurveyController;
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
}
