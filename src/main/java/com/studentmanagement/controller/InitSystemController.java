package com.studentmanagement.controller;

import com.studentmanagement.model.Teacher;
import com.studentmanagement.repository.StudentRepository;
import com.studentmanagement.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class InitSystemController {

    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/init_system")
    public String showInitForm() {
        if (teacherRepository.count() > 0 || studentRepository.count() > 0) {
            return "redirect:/login";
        }
        return "init_system";
    }

    @PostMapping("/init_system")
    public String createAdmin(@RequestParam String workId,
            @RequestParam String name,
            @RequestParam String department,
            @RequestParam String title,
            @RequestParam String password) {
        Teacher admin = new Teacher();
        admin.setWorkId(workId);
        admin.setName(name);
        admin.setDepartment(department);
        admin.setTitle(title);
        admin.setAdmin(true);
        admin.setPassword(passwordEncoder.encode(password));
        teacherRepository.save(admin);

        return "redirect:/login";
    }
}
