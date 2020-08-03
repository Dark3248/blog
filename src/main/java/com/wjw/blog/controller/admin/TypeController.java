package com.wjw.blog.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wjw.blog.dto.BlogShow;
import com.wjw.blog.entity.Type;
import com.wjw.blog.service.BlogService;
import com.wjw.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/types")
    public String types(Model model, @RequestParam(value = "p", defaultValue = "1") Integer pageNum) {
        PageHelper.startPage(pageNum, 5);
        List<Type> allTypes = typeService.getAdminType();
        PageInfo<Type> pageInfo = new PageInfo<>(allTypes);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("types", typeService.getAllType());
        return "admin/types";
    }

    @GetMapping("/types/input")
    public String input() {
        return "admin/types-input";
    }

    @PostMapping("/types/add")
    public String add(Type type, RedirectAttributes attributes) {
        Type type1 = typeService.getTypeByName(type.getName());
        if(type1 != null) {
            attributes.addFlashAttribute("message", "不能添加重复的分类");
            return "redirect:/admin/types/input";
        }
        typeService.saveType(type);
        attributes.addFlashAttribute("message", "添加成功");
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable("id") Long id, Model model) {
        Type type = typeService.getType(id);
        System.out.println(type);
        model.addAttribute("type", type);
        return "admin/types-update";
    }

    @PostMapping("/types/update")
    public String editPost(Type type, RedirectAttributes attributes) {
        Type type1 = typeService.getTypeByName(type.getName());
        if(type1 != null) {
            attributes.addFlashAttribute("message", "不能添加重复的分类");
            return "redirect:/admin/types/input";
        }
        typeService.updateType(type);
        attributes.addFlashAttribute("message", "修改成功");
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable("id") Long id, RedirectAttributes attributes) {
        List<BlogShow> blogs = blogService.getAllByTypeId(id);
        if(blogs.size() != 0) {
            attributes.addFlashAttribute("message", "该分类下仍存在博客， 请修改博客分类后进行删除");
            return "redirect:/admin/types";
        }
        typeService.deleteType(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/types";
    }

}
