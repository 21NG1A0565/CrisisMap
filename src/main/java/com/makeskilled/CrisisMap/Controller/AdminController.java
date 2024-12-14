package com.makeskilled.CrisisMap.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    
    @GetMapping("/userSignup")
    public String userSignup() {
        return "userSignup";
    }

    @GetMapping("/userSignin")
    public String userSignin() {
        return "userSignin";
    }

    @GetMapping("/")
    public String homePage() {
        return "index";
    }
}
