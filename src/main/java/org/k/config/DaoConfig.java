package org.k.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
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
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager platformTransactionManager(DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer mapperScannerConfigurer=new MapperScannerConfigurer();
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
        sqlSessionFactoryBean.setTypeAliasesPackage("org.k.doa.pojo");
        sqlSessionFactoryBean.setDataSource(dataSource);

        sqlSessionFactoryBean.setPlugins(new Interceptor[]{pageInterceptor});

        return sqlSessionFactoryBean;
    }
}
