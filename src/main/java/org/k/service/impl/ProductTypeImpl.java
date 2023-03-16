package org.k.service.impl;

import org.k.dao.ProductType;
import org.k.dao.ProductTypeExample;
import org.k.dao.mapper.ProductTypeMapper;
import org.k.service.ProductTypeService;

import java.util.List;

public class ProductTypeImpl implements ProductTypeService {
    ProductTypeMapper productTypeMapper;

    public ProductTypeImpl(ProductTypeMapper productTypeMapper) {
        this.productTypeMapper = productTypeMapper;
    }

    public List<ProductType> getAllType() {
        return productTypeMapper.selectByExample(new ProductTypeExample());
    }
}
