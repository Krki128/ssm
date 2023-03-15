package org.k.service;

import org.k.dao.Admin;

public interface AdminService {
    Admin login(String name,String password);
}
