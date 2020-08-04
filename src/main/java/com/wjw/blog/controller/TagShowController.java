package com.wjw.blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wjw.blog.dto.BlogShow;
import com.wjw.blog.entity.Tag;
import com.wjw.blog.entity.Type;
import com.wjw.blog.service.BlogService;
import com.wjw.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TagShowController {

    @Autowired
    TagService tagService;

    @Autowired
    BlogService blogService;

    @GetMapping("/tags/{id}")
    public String tags(Model model, @PathVariable Long id,
                        @RequestParam(value = "p", defaultValue = "1") Integer pageNum) {
        List<Tag> tags = tagService.getAllTag();
        if(id == -1) {
            id = tags.get(0).getId();
        }
        model.addAttribute("tags", tags);
        PageHelper.startPage(pageNum, 5);
        List<BlogShow> blogs = blogService.getAllByTagId(id);
        PageInfo<BlogShow> pageInfo = new PageInfo<>(blogs);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("activeTag", id);
        return "tags";
    }
}
