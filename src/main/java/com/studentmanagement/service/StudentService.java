package com.studentmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.studentmanagement.model.Student;
import com.studentmanagement.repository.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class StudentService {

    private final PasswordEncoder passwordEncoder;
    private StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, PasswordEncoder passwordEncoder) {
        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Page<Student> getAllStudents(Pageable pageable) {
        return studentRepository.findAll(pageable); // 使用 Pageable 进行分页查询
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Student saveStudent(Student student) {

        if (student.getId() != null) {
            // 是更新操作，保留原密码
            Student existingStudent = studentRepository.findById(student.getId()).orElse(null);
            if (existingStudent != null) {
                student.setPassword(existingStudent.getPassword());
            }
        } else {
            // 是新增操作，加密密码
            student.setPassword(passwordEncoder.encode(student.getPassword()));
        }

        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
