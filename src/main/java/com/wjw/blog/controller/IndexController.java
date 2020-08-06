package com.wjw.blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wjw.blog.dto.BlogDetail;
import com.wjw.blog.dto.BlogShow;
import com.wjw.blog.service.BlogService;
import com.wjw.blog.service.TagService;
import com.wjw.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    BlogService blogService;

    @Autowired
    TypeService typeService;

    @Autowired
    TagService tagService;

    @GetMapping("/")
    public String index(Model model, @RequestParam(defaultValue = "1",value = "p") Integer pageNum) {
        PageHelper.startPage(pageNum, 10);
        List<BlogShow> blogs = blogService.getAllBlogShow();
        PageInfo<BlogShow> pageInfo = new PageInfo<>(blogs);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("types", typeService.getAllType());
        model.addAttribute("tags", tagService.getAllTag());
        model.addAttribute("recommends", blogService.getRecommendedBlog());
        return "index";
    }

    @GetMapping("/search")
    public String search(Model model, @RequestParam String query) {
        List<BlogShow> searchBlog = blogService.getSearchBlog(query);
        model.addAttribute("blogs", searchBlog);
        model.addAttribute("query", query);
        return "search";
    }

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id, Model model) {
        blogService.addViews(id);
        BlogDetail detailedBlog = blogService.getBlogDetail(id);
        model.addAttribute("blog", detailedBlog);
        return "blog";
    }

}
