package org.k.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.k.dao.ProductInfo;
import org.k.dao.ProductInfoExample;
import org.k.dao.vo.ProductInfoVo;

public interface ProductInfoMapper {
    long countByExample(ProductInfoExample example);

    int deleteByExample(ProductInfoExample example);

    int deleteByPrimaryKey(Integer pId);

    int insert(ProductInfo row);

    int insertSelective(ProductInfo row);

    List<ProductInfo> selectByExample(ProductInfoExample example);

    ProductInfo selectByPrimaryKey(Integer pId);

    int updateByExampleSelective(@Param("row") ProductInfo row, @Param("example") ProductInfoExample example);

    int updateByExample(@Param("row") ProductInfo row, @Param("example") ProductInfoExample example);

    int updateByPrimaryKeySelective(ProductInfo row);

    int updateByPrimaryKey(ProductInfo row);

    int deleteBatch(String[] temp);

    List<ProductInfo> selectCondition(ProductInfoVo productInfoVo);
}