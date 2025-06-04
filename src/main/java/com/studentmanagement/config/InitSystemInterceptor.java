package com.studentmanagement.config;

import com.studentmanagement.repository.StudentRepository;
import com.studentmanagement.repository.TeacherRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class InitSystemInterceptor implements HandlerInterceptor {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull Object handler)
            throws Exception {
        boolean teacherEmpty = teacherRepository.count() == 0;
        boolean studentEmpty = studentRepository.count() == 0;

        String uri = request.getRequestURI();
        boolean isInitRequest = uri.startsWith("/init_system");

        if (teacherEmpty && studentEmpty && !isInitRequest) {
            response.sendRedirect("/init_system");
            return false;
        }
        return true;

    }
}
