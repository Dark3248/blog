package com.wjw.blog.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wjw.blog.entity.Tag;
import com.wjw.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/tags")
    public String tags(Model model, @RequestParam(value = "p", defaultValue = "1") Integer pageNum) {
        PageHelper.startPage(pageNum, 5);
        List<Tag> allTags = tagService.getAdminTag();
        PageInfo<Tag> pageInfo = new PageInfo<>(allTags);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/tags";
    }

    @GetMapping("/tags/input")
    public String input() {
        return "admin/tags-input";
    }

    @PostMapping("/tags/add")
    public String add(Tag tag, RedirectAttributes attributes) {
        Tag tag1 = tagService.getTagByName(tag.getName());
        if(tag1 != null) {
            attributes.addFlashAttribute("message", "不能添加重复的标签");
            return "redirect:/admin/tags/input";
        }
        tagService.saveTag(tag);
        attributes.addFlashAttribute("message", "添加成功");
        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable("id") Long id, Model model) {
        Tag tag = tagService.getTagById(id);
        model.addAttribute("tag", tag);
        return "admin/tags-update";
    }

    @PostMapping("/tags/update")
    public String editPost(Tag tag, RedirectAttributes attributes) {
        Tag tag1 = tagService.getTagByName(tag.getName());
        if(tag1 != null) {
            attributes.addFlashAttribute("message", "不能添加重复的标签");
            return "redirect:/admin/tags/input";
        }
        tagService.updateTag(tag);
        attributes.addFlashAttribute("message", "修改成功");
        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable("id") Long id, RedirectAttributes attributes) {
        tagService.deleteTag(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/tags";
    }

}
