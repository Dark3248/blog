package com.wjw.blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wjw.blog.dto.BlogShow;
import com.wjw.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    BlogService blogService;

    @GetMapping("/")
    public String index(Model model, @RequestParam(defaultValue = "1",value = "p") Integer pageNum) {
        PageHelper.startPage(pageNum, 6);
        List<BlogShow> blogs = blogService.getAllBlogShow();
        for(BlogShow blog : blogs) {
            System.out.println(blog);
        }
        PageInfo<BlogShow> pageInfo = new PageInfo<>(blogs);
        model.addAttribute("pageInfo", pageInfo);
        return "index";
    }

}
