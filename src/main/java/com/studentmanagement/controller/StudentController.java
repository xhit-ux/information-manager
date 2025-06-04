package com.studentmanagement.controller;

import com.studentmanagement.model.Student;
import com.studentmanagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/students")
    public String viewStudentInfo(Authentication auth, Model model) {
        String username = auth.getName(); // 学生学号
        Long studentNum = Long.parseLong(username);

        Student student = studentRepository.findByNums(studentNum)
                .orElseThrow(() -> new RuntimeException("找不到学生信息：" + studentNum));

        model.addAttribute("student", student);
        return "students"; // 渲染 students.html
    }
}
