package org.k.service.impl;

import org.k.dao.ProductType;
import org.k.dao.ProductTypeExample;
import org.k.dao.mapper.ProductTypeMapper;
import org.k.service.ProductTypeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ProductTypeServiceImpl")
public class ProductTypeServiceImpl implements ProductTypeService {
    ProductTypeMapper productTypeMapper;

    public ProductTypeServiceImpl(ProductTypeMapper productTypeMapper) {
        this.productTypeMapper = productTypeMapper;
    }

    public List<ProductType> getAllType() {
        return productTypeMapper.selectByExample(new ProductTypeExample());
    }
}
