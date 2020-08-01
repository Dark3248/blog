package com.wjw.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
//    @GetMapping("/{id}/{name}")
//    public String index(@PathVariable Integer id, @PathVariable String name) {
//        return "index";
//    }

    @GetMapping("/blog")
    public String blog() {
        return "admin/types";
    }

}
