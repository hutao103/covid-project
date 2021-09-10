package com.htht.pro;

import java.util.Properties;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.Ordered;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.interceptor.TransactionInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import redis.clients.jedis.JedisPoolConfig;

/**
 * Application
 * @author Administrator
 *
 */
@SpringBootApplication
@EnableTransactionManagement
@ComponentScan(basePackages = "com.htht.pro")
@ServletComponentScan(basePackages = "com.htht.pro")
@MapperScan("com.htht.pro.mapper")
public class Application extends WebMvcConfigurerAdapter{
	
	private static Logger logger = LoggerFactory.getLogger(Application.class);
	
	
	// dataBase  Connection  
	@Bean(destroyMethod = "close")
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource dataSource() {
        return new org.apache.tomcat.jdbc.pool.DataSource();
    }
	
	// MyBatis
    @Bean(name = "sqlSessionFactory")
    public org.apache.ibatis.session.SqlSessionFactory sqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new  SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setConfigLocation(resolver.getResource("classpath:sqlMapConfig.xml"));
        return sqlSessionFactoryBean.getObject();
    }
    
    // Transaction
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }
    
    // Transaction
    @Bean(name = "transactionInterceptor")
    public TransactionInterceptor transactionInterceptor(PlatformTransactionManager platformTransactionManager) {
        TransactionInterceptor transactionInterceptor = new TransactionInterceptor();
        // Transaction manager
        transactionInterceptor.setTransactionManager(platformTransactionManager);
        Properties transactionAttributes = new Properties();
        // add
        transactionAttributes.setProperty("insert*","PROPAGATION_REQUIRED,-Throwable");
        transactionAttributes.setProperty("save*","PROPAGATION_REQUIRED,-Throwable");
        transactionAttributes.setProperty("add*","PROPAGATION_REQUIRED,-Throwable");
        // update
        transactionAttributes.setProperty("update*","PROPAGATION_REQUIRED,-Throwable");
        transactionAttributes.setProperty("modify*","PROPAGATION_REQUIRED,-Throwable");
        transactionAttributes.setProperty("copy*","PROPAGATION_REQUIRED,-Throwable");
        transactionAttributes.setProperty("move*","PROPAGATION_REQUIRED,-Throwable");
        // delete
        transactionAttributes.setProperty("del*","PROPAGATION_REQUIRED,-Throwable");
        //query
        transactionAttributes.setProperty("*","PROPAGATION_SUPPORTS,-Throwable,readOnly");
        transactionInterceptor.setTransactionAttributes(transactionAttributes);
        return transactionInterceptor;
    }
    
    // transaction
    @Bean
    public BeanNameAutoProxyCreator transactionAutoProxy() {
        BeanNameAutoProxyCreator transactionAutoProxy = new BeanNameAutoProxyCreator();
        transactionAutoProxy.setProxyTargetClass(true);
        transactionAutoProxy.setBeanNames("*Service", "*ServiceImpl");
        transactionAutoProxy.setInterceptorNames("transactionInterceptor");
        return transactionAutoProxy;
    }
	
    // redis configuration
    @Bean  
    @ConfigurationProperties(prefix="spring.redis")  
    public JedisPoolConfig getRedisConfig(){  
        JedisPoolConfig config = new JedisPoolConfig();  
        return config;  
    }  
    
    // Create JedisConnectionFactory
    @Bean(name = "jedisConnectionFactory")
    @ConfigurationProperties(prefix="spring.redis")  
    public JedisConnectionFactory getConnectionFactory(){  
        JedisConnectionFactory factory = new JedisConnectionFactory();  
        JedisPoolConfig config = getRedisConfig();  
        factory.setPoolConfig(config);  
        logger.info("JedisConnectionFactory bean init success.");  
        return factory;  
    }  
      
    // Create StringRedisTemplate
    @Bean  
    public RedisTemplate<?, ?> getRedisTemplate(){  
        RedisTemplate<?,?> template = new StringRedisTemplate(getConnectionFactory());  
        return template;  
    }
    
    //Interceptor
    @Override
	public void addInterceptors(InterceptorRegistry registry) {
    	//InterceptorRegistration registration = registry.addInterceptor(new AdminInterceptor());
    	//registration.addPathPatterns("/**");                      //all path

		super.addInterceptors(registry);
	}
    
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
    	registry.addViewController("/").setViewName("forward:/login");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        super.addViewControllers(registry);
    } 
    
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		logger.info("============= COVID Start Success =============");
	}
	
}
