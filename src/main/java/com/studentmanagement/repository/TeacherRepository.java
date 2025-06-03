package com.studentmanagement.repository;

import com.studentmanagement.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    // 根据教职工号（workId）查找教师
    Optional<Teacher> findByWorkId(String workId);
}
