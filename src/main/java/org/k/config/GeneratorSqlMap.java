package org.k.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.sql.visitor.functions.Char;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GeneratorSqlMap {
    static void generator() throws Exception{
        List<String> warnings= new ArrayList<String>();
        boolean overwrite=true;
        File configFile=new File("C:\\Users\\26591\\Documents\\tool\\Code\\Java\\ssm\\src\\main\\resources\\GeneratorConfig.xml");
        ConfigurationParser  configurationParser=new ConfigurationParser(warnings);
        Configuration configuration=configurationParser.parseConfiguration(configFile);
        DefaultShellCallback defaultShellCallback=new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator=new MyBatisGenerator(configuration,defaultShellCallback,warnings);
        myBatisGenerator.generate(null);
    }
}
