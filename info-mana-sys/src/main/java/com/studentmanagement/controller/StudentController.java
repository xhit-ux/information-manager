package com.studentmanagement.controller;

import com.studentmanagement.model.Student;
import com.studentmanagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // 显示所有学生
    @GetMapping
    public String getAllStudents(Model model) {
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "index"; // 返回 students/index.html
    }

    // 显示添加学生页面
    @GetMapping("/new")
    public String showStudentForm() {
        return "new"; // 返回 students/new.html
    }

    // 添加学生
    @PostMapping("/new")
    public String addStudent(@ModelAttribute Student student) {
        studentService.saveStudent(student);
        return "redirect:/students"; // 提交后重定向到学生列表页面
    }


    // 删除学生
    @PostMapping("/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "redirect:/students"; // 删除后重定向到学生列表
    }
}
