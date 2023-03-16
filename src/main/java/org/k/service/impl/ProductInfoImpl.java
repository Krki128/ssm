package org.k.service.impl;

import org.k.dao.ProductInfo;
import org.k.dao.ProductInfoExample;
import org.k.dao.mapper.ProductInfoMapper;
import org.k.service.ProductInfoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductInfoImpl implements ProductInfoService {
    ProductInfoMapper productInfoMapper;

    public ProductInfoImpl(ProductInfoMapper productInfoMapper) {
        this.productInfoMapper = productInfoMapper;
    }

    public List<ProductInfo> getAll() {
        return productInfoMapper.selectByExample(new ProductInfoExample());
    }
}
