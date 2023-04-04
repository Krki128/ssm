package org.k.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.logging.log4j.Log4jImpl;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@PropertySource("classpath:jdbc.properties")
public class DaoConfig {
    @Value("${jdbc.driver}")
    private String driver;
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;

    @Bean
    public DataSource dataSource(){
        DruidDataSource dataSource =new DruidDataSource();

        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        //dataSource.setDriverClassName(driver);
        //dataSource.setUrl(url);
        dataSource.setUrl("jdbc:mysql://localhost:3306/ssm?useSSL=false&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true");
        dataSource.setUsername("root");
        //dataSource.setUsername(username);
        dataSource.setPassword("1226");
        //dataSource.setPassword(password);

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager platformTransactionManager(DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer mapperScannerConfigurer=
                new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("org.k.dao.mapper");
        return mapperScannerConfigurer;
    }


    @Bean
    public PageInterceptor pageInterceptor(){
        PageInterceptor pageInterceptor=new PageInterceptor();
        Properties properties=new Properties();
        properties.setProperty("helperDialect","mysql");
        properties.setProperty("reasonable","true");
        pageInterceptor.setProperties(properties);
        return pageInterceptor;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource,
             PageInterceptor pageInterceptor){
        SqlSessionFactoryBean sqlSessionFactoryBean=new SqlSessionFactoryBean();
        Configuration configuration=new Configuration();
        configuration.setLogImpl(Log4jImpl.class);
        sqlSessionFactoryBean.setTypeAliasesPackage("org.k.doa");
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{pageInterceptor});
        sqlSessionFactoryBean.setConfiguration(configuration);
        return sqlSessionFactoryBean;
    }
}
