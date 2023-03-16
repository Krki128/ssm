package org.k.controller;

import org.k.dao.Admin;
import org.k.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class AdminController {
    AdminService adminService;
    public AdminController(AdminService adminService) {
        this.adminService =adminService;
    }

    @RequestMapping("/login")
    public String home(){
        return "login";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(String name, String pwd, Model model){
        Admin admin=adminService.login(name,pwd);
        if(admin!=null){
            model.addAttribute("admin",admin);
            return "main";
        }
        else{
            model.addAttribute("errmsg","login fail");
            return "login";
        }
    }
}
