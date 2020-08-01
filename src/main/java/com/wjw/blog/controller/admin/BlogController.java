package com.wjw.blog.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wjw.blog.dto.BlogQuery;
import com.wjw.blog.dto.BlogSearch;
import com.wjw.blog.dto.BlogUpdate;
import com.wjw.blog.entity.Blog;
import com.wjw.blog.entity.User;
import com.wjw.blog.service.BlogService;
import com.wjw.blog.service.TagService;
import com.wjw.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    BlogService blogService;

    @Autowired
    TypeService typeService;

    @Autowired
    TagService tagService;

    //    展示
    @GetMapping("/blogs")
    public String blogs(@RequestParam(value = "p", defaultValue = "1") Integer pageNum, Model model) {
        PageHelper.startPage(pageNum, 6);
        List<BlogQuery> blogQueryList = blogService.getAllBlog();
        PageInfo<BlogQuery> pageInfo = new PageInfo<>(blogQueryList);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("types", typeService.getAdminType());
        return "admin/blogs";
    }

    //    删除
    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/blogs";
    }

    //    去新增页面
    @GetMapping("/blogs/input")
    public String toAdd(Model model) {
        model.addAttribute("types", typeService.getAdminType());
        model.addAttribute("tags", tagService.getAdminTag());
        return "admin/blogs-input";
    }

    //    新增
    @PostMapping("/blogs/add")
    public String add(Blog blog, RedirectAttributes attributes, HttpSession session) {
        //填写用户id
        blog.setUserId(((User) session.getAttribute("user")).getId());
        //填写标签
        blog.setTags(tagService.getTagByString(blog.getTagIds()));
        blogService.saveBlog(blog);
        return "redirect:/admin/blogs";
    }

    //    查询
    @PostMapping("/blogs/search")
    public String search(BlogSearch blogSearch, Model model,
                         @RequestParam(value = "p", defaultValue = "1") Integer pageNum) {
        if(blogSearch.getTitle().length() == 0)
            blogSearch.setTitle(null);
        PageHelper.startPage(pageNum, 4);
        List<BlogQuery> list = blogService.getBlogBySearch(blogSearch);
        PageInfo<BlogQuery> pageInfo = new PageInfo<>(list);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("types", typeService.getAdminType());
        if(list.size() == 0)
            model.addAttribute("message", "查询无结果");
        else
            model.addAttribute("message", "查询成功");
        return "admin/blogs";
    }

    //  去修改页面
    @GetMapping("/blogs/{id}/update")
    public String toUpdate(@PathVariable Long id, Model model) {
        BlogUpdate blog =  blogService.getBlog(id);
        model.addAttribute("blog", blog);
        model.addAttribute("types", typeService.getAdminType());
        model.addAttribute("tags", tagService.getAdminTag());
        return "admin/blogs-update";
    }

    //  修改
    @PostMapping("/blogs/update")
    public String update(BlogUpdate blog, RedirectAttributes attributes) {
        blogService.updateBlog(blog);
        attributes.addFlashAttribute("message", "修改成功");
        return "redirect:/admin/blogs";
    }
}
