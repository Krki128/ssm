package org.k.controller;

import org.k.dao.Admin;
import org.k.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping()
public class AdminController {
    AdminService adminService;
    public AdminController(AdminService adminService) {
        this.adminService =adminService;
    }

    //@RequestMapping(value = "/login",method = RequestMethod.POST)
    @RequestMapping("/home")
    public String home(String username,Model model){
        model.addAttribute("username",username);
        return "main";
    }
    @RequestMapping("/fail")
    public String fail(String msg,Model model){
        model.addAttribute("msg",msg);
        return "login";
    }
/*    @GetMapping("/login")
    public String getLogin(){
        return "login";
    }
    @PostMapping("/login")
    public String login(String username, String password, Model model){
        Admin admin=adminService.login(username,password);
        if(admin!=null){
            model.addAttribute("admin",admin);
            return "redirect:main";
        }
        else{
            model.addAttribute("msg","login fail");
            return "forward:/login";
        }
    }*/
}
