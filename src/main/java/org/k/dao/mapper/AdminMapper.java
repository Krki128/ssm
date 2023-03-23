package org.k.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.k.dao.Admin;
import org.k.dao.AdminExample;

public interface AdminMapper {
    long countByExample(AdminExample example);

    int deleteByExample(AdminExample example);

    int deleteByPrimaryKey(Integer aId);

    int insert(Admin row);

    int insertSelective(Admin row);

    List<Admin> selectByExample(AdminExample example);

/*    @Results(id = "adminMap",value = {
            @Result(id = true,column = "a_id",property = "aId"),
            @Result(column = "a_name",property = "aName"),
            @Result(column = "a_pass",property = "aPass")
    })
    @Select("select * from admin")*/
    List<Admin> selectAll();

    Admin selectByPrimaryKey(Integer aId);

    int updateByExampleSelective(@Param("row") Admin row, @Param("example") AdminExample example);

    int updateByExample(@Param("row") Admin row, @Param("example") AdminExample example);

    int updateByPrimaryKeySelective(Admin row);

    int updateByPrimaryKey(Admin row);
}