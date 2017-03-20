package ru.zhilin.example.openapi;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Properties;

import javax.sql.DataSource;

import ru.zhilin.example.model.entity.DocumentEntity;
import ru.zhilin.example.model.entity.DocumentItemEntity;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
@ComponentScan("ru.zhilin.example")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public DataSource dataSource() {
        return new DriverManagerDataSource("jdbc:h2:~/test", "sa", "");
    }

    @Bean
    public FactoryBean<SessionFactory> sessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setAnnotatedClasses(DocumentEntity.class, DocumentItemEntity.class);

        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.hbm2ddl.auto", "create");
        sessionFactoryBean.setHibernateProperties(hibernateProperties);

        return sessionFactoryBean;
    }

    @Bean
    public PlatformTransactionManager transactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }
}
