package ch11.config;

import ch11.spring.ChangePasswordService;
import ch11.spring.MemberDAO;
import ch11.spring.MemberRegisterService;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class MemberConfig {
    @Bean(destroyMethod = "close")
    public DataSource dataSource(){
        DataSource dataSource = new DataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/mydb?characterEncoding=UTF-8&serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("1234");
        dataSource.setInitialSize(2);
        dataSource.setMaxActive(10);
        dataSource.setTestWhileIdle(true);
        dataSource.setMinEvictableIdleTimeMillis(60000 * 3);
        dataSource.setTimeBetweenEvictionRunsMillis(10 * 1000);
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager(){
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public MemberDAO memberDAO(){
        return new MemberDAO(dataSource());
    }

    @Bean
    public MemberRegisterService memberRegisterService(){
        return new MemberRegisterService();
    }

    @Bean
    public ChangePasswordService changePasswordService(){
        return new ChangePasswordService();
    }

}
