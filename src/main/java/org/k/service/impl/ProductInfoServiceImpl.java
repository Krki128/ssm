package org.k.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.k.dao.ProductInfo;
import org.k.dao.ProductInfoExample;
import org.k.dao.mapper.ProductInfoMapper;
import org.k.dao.vo.ProductInfoVo;
import org.k.service.ProductInfoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductInfoServiceImpl implements ProductInfoService {
    ProductInfoMapper productInfoMapper;

    public ProductInfoServiceImpl(ProductInfoMapper productInfoMapper) {
        this.productInfoMapper = productInfoMapper;
    }

    public List<ProductInfo> getAll() {
        return productInfoMapper.selectByExample(new ProductInfoExample());
    }

    public PageInfo<ProductInfo> splitPage(ProductInfoVo productInfoVo) {
        PageHelper.startPage(productInfoVo.getPageNum(),PAGE_SIZE);

        //ProductInfoExample productInfoExample=new ProductInfoExample();
        //productInfoExample.setOrderByClause("p_id desc");
        //List<ProductInfo> productInfoList= productInfoMapper.selectByExample(productInfoExample);
        List<ProductInfo> productInfoList=productInfoMapper.selectCondition(productInfoVo);

        return new PageInfo<ProductInfo>(productInfoList);
    }

    public int create(ProductInfo productInfo) {
        return productInfoMapper.insert(productInfo);
    }

    public ProductInfo selectByKey(int pId) {
        return  productInfoMapper.selectByPrimaryKey(pId);
    }

    public int update(ProductInfo productInfo) {
        return productInfoMapper.updateByPrimaryKey(productInfo);
    }

    public int deleteByKey(int pId) {
        return productInfoMapper.deleteByPrimaryKey(pId);
    }

    public int deleteBatch(String[] temp) {
        return productInfoMapper.deleteBatch(temp);
    }

    @Override
    public PageInfo<ProductInfo> selectCondition(ProductInfoVo productInfoVo) {

        return null;
    }


}
