package org.k.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

    public PageInfo<ProductInfo> splitPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        ProductInfoExample productInfoExample=new ProductInfoExample();
        productInfoExample.setOrderByClause("p_id desc");
        List<ProductInfo> productInfoList= productInfoMapper.selectByExample(productInfoExample);
        return new PageInfo<ProductInfo>(productInfoList);
    }
}
