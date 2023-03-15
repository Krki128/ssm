package org.k.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(value = "org.k",excludeFilters =
    @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Controller.class}))
@EnableTransactionManagement
@Import({DaoConfig.class})
public class RootConfig {

}
