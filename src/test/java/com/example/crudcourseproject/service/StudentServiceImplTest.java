package com.example.crudcourseproject.service;

import com.example.crudcourseproject.model.Student;
import com.example.crudcourseproject.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class StudentServiceImplTest {
    @Autowired
    private StudentRepository repository;

    @Test
    void getAllStudents() {
        List<Student> items = repository.findAll();
        assertEquals(2, items.size());
    }

    @Test
    public void testFindOne() {
        Student student = repository.findById(1L).get();

        assertEquals("Matt Nickolaus", student.getStudName());
    }
}
