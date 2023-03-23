package org.k.service.impl;

import org.k.dao.Admin;
import org.k.dao.AdminExample;
import org.k.dao.mapper.AdminMapper;
import org.k.utils.MD5Util;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements org.k.service.AdminService {
    AdminMapper adminMapper;

    public AdminServiceImpl(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    public Admin login(String name, String password) {
        /*
        AdminExample adminExample=new AdminExample();
        adminExample.createCriteria().andANameEqualTo(name);
        List<Admin> adminList=adminMapper.selectByExample(adminExample);
        if(adminList.size()>0){
            Admin admin=adminList.get(0);
            if(password.equals(admin.getaPass())){
                return admin;
            }
        }
         */

        List<Admin> admins=adminMapper.selectAll();
        if (admins.size()>0){
            Admin admin=admins.get(0);
            //String md5Pwd=MD5Util.getMD5(password);
            //if (md5Pwd!=null&&md5Pwd.equals(admin.getaPass()))
            if(password.equals(admin.getaPass()))
                return admin;
        }
        return null;
    }
}
