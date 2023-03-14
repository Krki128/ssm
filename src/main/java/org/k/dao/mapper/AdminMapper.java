package org.k.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.k.dao.pojo.Admin;
import org.k.dao.pojo.AdminExample;

public interface AdminMapper {
    long countByExample(AdminExample example);

    int deleteByExample(AdminExample example);

    int deleteByPrimaryKey(Integer aId);

    int insert(Admin row);

    int insertSelective(Admin row);

    List<Admin> selectByExample(AdminExample example);

    Admin selectByPrimaryKey(Integer aId);

    int updateByExampleSelective(@Param("row") Admin row, @Param("example") AdminExample example);

    int updateByExample(@Param("row") Admin row, @Param("example") AdminExample example);

    int updateByPrimaryKeySelective(Admin row);

    int updateByPrimaryKey(Admin row);
}