package ch03.config;

import ch03.spring.MemberDAO;
import ch03.spring.MemberPrinter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import(AppConf2.class)
@Configuration
public class AppConf1 {
    @Bean
    public MemberDAO memberDAO(){
        return new MemberDAO();
    }
    @Bean
    public MemberPrinter memberPrinter(){
        return new MemberPrinter();
    }
}
