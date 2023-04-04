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

    @RequestMapping("/")
    public String index(){
        return "redirect:/login";
    }

    //@RequestMapping(value = "/login",method = RequestMethod.POST)
    @RequestMapping("/home")
    public String home(String username,Model model){
        model.addAttribute("username",username);
        return "main";
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
            model.addAttribute("errmsg","login fail");
            return "forward:/login";
        }
    }*/
}
