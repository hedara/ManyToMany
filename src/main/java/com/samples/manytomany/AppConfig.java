package com.samples.manytomany;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by edara on 9/14/16.
 */
@Configuration
@ComponentScan(basePackages = {"com.samples.manytomany"})
@PropertySource(value = "classpath:app.properties")
public class AppConfig {
    @Autowired
    Environment env;
    @Bean
    public Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", env.getProperty("hibernate_driver_dialect"));
        //properties.setProperty("hibernate.show_sql", env.getProperty("hibernate_show_sql"));
        properties.setProperty("hibernate.format_sql", env.getProperty("hibernate_format_sql"));
        properties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hbm2ddl.auto"));
        return properties;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("db_driver_class"));
        dataSource.setUrl(env.getProperty("db_url"));
        dataSource.setUsername(env.getProperty("db_username"));
        dataSource.setPassword(env.getProperty("db_password"));
        return dataSource;
    }

    @Bean
    public EntityManagerFactory entityManagerFactoryBean(){
        LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();
        emfb.setDataSource(dataSource());
        emfb.setJpaProperties(hibernateProperties());
        emfb.setPackagesToScan("com.samples.manytomany.model");
        emfb.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        emfb.afterPropertiesSet();
        return emfb.getObject();
    }



}
