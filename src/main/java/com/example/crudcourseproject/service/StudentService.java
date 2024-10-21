package com.example.crudcourseproject.service;

import com.example.crudcourseproject.model.Student;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();
    void saveStudent(Student student);
    Student getStudentById(long id);
    void deleteStudentById(long id);
    Page<Student> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
