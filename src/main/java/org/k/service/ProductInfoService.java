package org.k.service;

import com.github.pagehelper.PageInfo;
import org.k.dao.ProductInfo;
import org.k.dao.vo.ProductInfoVo;

import java.util.List;

public interface ProductInfoService {
    int PAGE_SIZE=5;
    List<ProductInfo> getAll();

    PageInfo<ProductInfo> splitPage(ProductInfoVo productInfoVo);

    int create(ProductInfo productInfo);

    ProductInfo selectByKey(int pId);

    int update(ProductInfo productInfo);

    int deleteByKey(int pId);

    int deleteBatch(String[] temp);

    PageInfo<ProductInfo> selectCondition(ProductInfoVo productInfoVo);
}
