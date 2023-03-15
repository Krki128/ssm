package org.k.service.impl;

import org.k.dao.Admin;
import org.k.dao.AdminExample;
import org.k.dao.mapper.AdminMapper;
import org.k.utils.MD5Util;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements org.k.service.AdminService {
    final
    AdminMapper adminMapper;

    public AdminServiceImpl(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    @Override
    public Admin login(String name, String password) {
        AdminExample adminExample=new AdminExample();
        adminExample.createCriteria().andANameEqualTo(name);
        List<Admin> adminList=adminMapper.selectByExample(adminExample);
        if(adminList.size()>0){
            Admin admin=adminList.get(0);
            String pwd= MD5Util.getMD5(password);
            if(pwd!=null&&pwd.equals(admin.getaPass())){
                return admin;
            }
        }
        return null;
    }
}
