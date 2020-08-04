package com.wjw.blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wjw.blog.dto.BlogShow;
import com.wjw.blog.entity.Type;
import com.wjw.blog.service.BlogService;
import com.wjw.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TypeShowController {

    @Autowired
    TypeService typeService;

    @Autowired
    BlogService blogService;


    @GetMapping("/types/{id}")
    public String types(Model model, @PathVariable Long id,
                          @RequestParam(value = "p", defaultValue = "1") Integer pageNum) {
        List<Type> types = typeService.getAllType();
        if(id == -1) {
            id = types.get(0).getId();
        }
        model.addAttribute("types", types);
        PageHelper.startPage(pageNum, 5);
        List<BlogShow> blogs = blogService.getAllByTypeId(id);
        PageInfo<BlogShow> pageInfo = new PageInfo<>(blogs);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("activeType", id);
        return "types";
    }

}
