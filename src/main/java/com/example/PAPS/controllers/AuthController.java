package com.example.PAPS.controllers;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@RestController
//@RequestMapping("/")
public class AuthController {

//    @GetMapping
//    public void createTargetUrl(HttpServletResponse httpResponse) throws IOException {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//
//        switch (auth.getPrincipal().toString()) {
//            case ("HR@test.com"):
//                httpResponse.sendRedirect("/hr");
//                break;
//            case ("worker@test.com"):
//                httpResponse.sendRedirect("/worker");
//                break;
//            case ("director@test.com"):
//                httpResponse.sendRedirect("/director");
//                break;
//            case ("supplier-dep@test.com"):
//                httpResponse.sendRedirect("/supplier-d");
//                break;
//            case ("manager-spares@test.com"):
//                 httpResponse.sendRedirect("/manager-spares");
//                 break;
//            case ("manager-sells@test.com"):
//                httpResponse.sendRedirect("/manager-sells");
//                break;
//            case ("manager-service@test.com"):
//                httpResponse.sendRedirect("/manager-service");
//                break;
//            default: httpResponse.sendRedirect("/logout");
//        }
//    }
}
