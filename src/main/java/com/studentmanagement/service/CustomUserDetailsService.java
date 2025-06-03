package com.studentmanagement.service;

import com.studentmanagement.model.Student;
import com.studentmanagement.model.Teacher;
import com.studentmanagement.repository.StudentRepository;
import com.studentmanagement.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<GrantedAuthority> authorities = new ArrayList<>();

        // 优先查找教师
        Optional<Teacher> teacherOpt = teacherRepository.findByWorkId(username);
        if (teacherOpt.isPresent()) {
            Teacher teacher = teacherOpt.get();
            if (teacher.isAdmin()) {
                authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            } else {
                authorities.add(new SimpleGrantedAuthority("ROLE_TEACHER"));
            }
            return new User(teacher.getWorkId(), teacher.getPassword(), authorities);
        }

        // 再查找学生（注意：学号为数字，转为字符串比较）
        Optional<Student> studentOpt = studentRepository.findByNums(Long.parseLong(username));
        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();
            authorities.add(new SimpleGrantedAuthority("ROLE_STUDENT"));
            return new User(String.valueOf(student.getNums()), student.getPassword(), authorities);
        }

        // 用户不存在
        throw new UsernameNotFoundException("用户不存在: " + username);
    }
}
