package org.k.listener;

import org.k.dao.ProductType;
import org.k.service.ProductTypeService;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;

public class ProductTypeListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {
        ApplicationContext applicationContext=
                WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
        ProductTypeService productTypeService=(ProductTypeService) applicationContext.getBean("ProductTypeServiceImpl");
        List<ProductType> productTypeList= productTypeService.getAllType();
        sce.getServletContext().setAttribute("typeList",productTypeList);
    }

}
