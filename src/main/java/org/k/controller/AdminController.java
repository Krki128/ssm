package org.k.controller;

import org.k.dao.Admin;
import org.k.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
//@RequestMapping()
public class AdminController {
    AdminService adminService;
    public AdminController(AdminService adminService) {
        this.adminService =adminService;
    }

    /*@RequestMapping("/login")
    @GetMapping
    public String home(){
        return "login";
    }*/

    //@RequestMapping(value = "/login",method = RequestMethod.POST)
    @PostMapping("/login")
    public String login(String name, String pwd, Model model){
        Admin admin=adminService.login(name,pwd);
        if(admin!=null){
            model.addAttribute("admin",admin);
            return "main";
        }
        else{
            model.addAttribute("errmsg","login fail");
            return "forward:/";
        }
    }
}
