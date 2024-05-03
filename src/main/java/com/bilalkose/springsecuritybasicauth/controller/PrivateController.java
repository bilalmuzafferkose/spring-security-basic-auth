package com.bilalkose.springsecuritybasicauth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/private")
public class PrivateController {
    @GetMapping
    public String helloWorldPrivate() {
        return "Hello world from private";
    }

    //@PreAuthorize("hasRole('USER')")
    @GetMapping("/user")
    public String helloWorldUserPrivate() {
        return "Hello world user, from private";
    }

    //@PreAuthorize("hasRole('ADMIN')")
    //preAuthorize yerine Config kısmında authorizeHttpRequests ekledik
    @GetMapping("/admin")
    public String helloWorldAdminPrivate() { //fsk, username ve password doğru olursa, /admin'e ulaşmaya çalışırsa 403 forbidden dönecek
        return "Hello world admin, from private";
    }
}
