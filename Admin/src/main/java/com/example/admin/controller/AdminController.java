package com.example.admin.controller;


import com.example.lib.dto.AdminDto;
import com.example.lib.models.Admin;
import com.example.lib.service.impl.AdminServiceImpl;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class AdminController {
    private final AdminServiceImpl adminService;

    @GetMapping("/login")
    public String loginForm(){
        return "login";
    }

    @GetMapping("/register")
    public String registerForm(Model model,HttpSession session){
        model.addAttribute("adminDto",new AdminDto());
        return "register";
    }

    @GetMapping("/forgot-password")
    public String forgotPassword(){
        return "forgot-password";
    }

    @GetMapping("/index")
    public String home(){
        return "index";
    }

    @PostMapping("/register-new")
    public String addAdmin(@Valid @ModelAttribute("adminDto") AdminDto adminDto,
                           BindingResult result,
                           Model model){

        try{

            if(result.hasErrors()){
                model.addAttribute("adminDto",adminDto);
                return "register";
            }

            Admin admin= adminService.findByUsername(adminDto.getUsername());
            if(admin!=null){
                model.addAttribute("adminDto",adminDto);
                model.addAttribute("mailError","Your email has been register!");
                return "register";
            }

            if(adminDto.getPassword().equals(adminDto.getPasswordConfirm())){
                adminService.save(adminDto);
                model.addAttribute("adminDto",adminDto);
                model.addAttribute("success","Register successfully!");

            }else {
                model.addAttribute("adminDto",adminDto);
                model.addAttribute("passwordError","Password not correct!");

                return "register";
            }




        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("errors","The server has been wrong!");
        }


        return "register";


    }
}
