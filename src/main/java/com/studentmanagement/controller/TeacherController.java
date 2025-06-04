package com.studentmanagement.controller;

import com.studentmanagement.model.Student;
import com.studentmanagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/teachers")
public class TeacherController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public String getAllStudents(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Student> studentPage = studentService.getAllStudents(pageable);

        model.addAttribute("students", studentPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", studentPage.getTotalPages());

        return "teachers"; // 使用 teacher.html 页面
    }

    @PostMapping("/new")
    public String addStudent(@ModelAttribute Student student,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        studentService.saveStudent(student);

        Pageable pageable = PageRequest.of(page, size);
        Page<Student> pages = studentService.getAllStudents(pageable);
        int totalPages = pages.getTotalPages();

        return "redirect:/teachers?page=" + (totalPages - 1) + "&size=" + size;
    }

    @PostMapping("/update")
    public String updateStudent(@ModelAttribute Student student) {
        studentService.saveStudent(student);
        return "redirect:/teachers";
    }

    @PostMapping("/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "redirect:/teachers";
    }
}
