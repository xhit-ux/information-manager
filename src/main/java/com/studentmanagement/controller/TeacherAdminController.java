package com.studentmanagement.controller;

import com.studentmanagement.model.Teacher;
import com.studentmanagement.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/teachers")
public class TeacherAdminController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping
    public String getAllTeachers(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Teacher> teacherPage = teacherService.getAllTeachers(pageable);

        model.addAttribute("teachers", teacherPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", teacherPage.getTotalPages());

        return "admin/teachers"; // 使用 admin/teachers.html 页面
    }

    @PostMapping("/new")
    public String addTeacher(@ModelAttribute Teacher teacher,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        teacherService.saveTeacher(teacher);

        Pageable pageable = PageRequest.of(page, size);
        Page<Teacher> pages = teacherService.getAllTeachers(pageable);
        int totalPages = pages.getTotalPages();

        return "redirect:/admin/teachers?page=" + (totalPages - 1) + "&size=" + size;
    }

    @PostMapping("/{id}")
    public String deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
        return "redirect:/admin/teachers";
    }
}
