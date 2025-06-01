package com.studentmanagement.controller;

import com.studentmanagement.model.Student;
import com.studentmanagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // 显示所有学生(分页)
    @GetMapping
    public String getAllStudents(
            @RequestParam(defaultValue = "0") int page, // 默认第一页
            @RequestParam(defaultValue = "10") int size, // 默认每页10条
            Model model) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Student> studentPage = studentService.getAllStudents(pageable);

        model.addAttribute("students", studentPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", studentPage.getTotalPages());

        return "index"; // 返回学生列表页面
    }

    // 添加学生
    @PostMapping("/new")
    public String addStudent(@ModelAttribute Student student,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        studentService.saveStudent(student);

        Pageable pageable = PageRequest.of(page, size);
        Page<Student> pages = studentService.getAllStudents(pageable);
        int totalPages = pages.getTotalPages();

        return "redirect:/students?page=" + (totalPages - 1) + "&size=" + pageable.getPageSize(); // 提交后重定向到学生列表页面
    }

    // 删除学生
    @PostMapping("/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "redirect:/students"; // 删除后重定向到学生列表
    }
}
