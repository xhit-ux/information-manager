package com.studentmanagement.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminHomeController {

    @GetMapping("/admin")
    public String adminIndex(Authentication auth) {
        return "admin/index"; // 正常管理员页面
    }

}
