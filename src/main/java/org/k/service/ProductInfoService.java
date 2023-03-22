package org.k.service;

import com.github.pagehelper.PageInfo;
import org.k.dao.ProductInfo;

import java.util.List;

public interface ProductInfoService {
    List<ProductInfo> getAll();

    PageInfo<ProductInfo> splitPage(int pageNum,int pageSize);

    int create(ProductInfo productInfo);

    ProductInfo selectByKey(int pId);

    int update(ProductInfo productInfo);

    int deleteByKey(int pId);

    int deleteBatch(String[] strings);
}
