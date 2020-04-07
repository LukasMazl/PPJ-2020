package cz.mazl.tul.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import java.util.Properties;

@Configuration
@ConfigurationProperties(prefix = "cz.mazl.tul.hibernate")
@Profile("prod")
public class HibernateConfiguration {

    private static final String KEY_HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String KEY_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    private static final String KEY_HIBERNATE_HDM2DDL_AUTO = "hibernate.hbm2ddl.auto";

    @Bean(name="entityManagerFactory")
    @Autowired
    public LocalSessionFactoryBean localSessionFactoryBean(DriverManagerDataSource dataSource, HibernateProperties hibernateProperties) {
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();

        Properties hibernateProp = localSessionFactoryBean.getHibernateProperties();
        hibernateProp.setProperty(KEY_HIBERNATE_DIALECT, hibernateProperties.getDialect());
        hibernateProp.setProperty(KEY_HIBERNATE_SHOW_SQL, "true");
        hibernateProp.setProperty(KEY_HIBERNATE_HDM2DDL_AUTO, "update");

        localSessionFactoryBean.setPackagesToScan("cz.mazl.tul.entity.db");
        localSessionFactoryBean.setDataSource(dataSource);
        return localSessionFactoryBean;
    }

    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        dataSource.setUrl("jdbc:oracle:thin:@192.168.99.100:1521/xe");
        dataSource.setUsername("system");
        dataSource.setPassword("oracle");
        return dataSource;
    }
}
