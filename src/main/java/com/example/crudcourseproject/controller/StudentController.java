package com.example.crudcourseproject.controller;

import com.example.crudcourseproject.model.Course;
import com.example.crudcourseproject.model.Student;
import com.example.crudcourseproject.repository.CourseRepository;
import com.example.crudcourseproject.repository.StudentRepository;
import com.example.crudcourseproject.service.StudentService;
import com.example.crudcourseproject.service.CourseService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class StudentController {
    private static final Logger log = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @GetMapping("/studentList")
    public String viewStudentList(Model model) {
        model.addAttribute("listStudents", studentService.getAllStudents());

        return "student_list";
    }

    @GetMapping("/showNewStudentForm")
    public String showNewStudentForm(Model model) {
        // create model attribute to bind form data
        Student student = new Student();
        model.addAttribute("student", student);
        return "new_student";
    }

    @PostMapping("/saveStudent")
    public String saveStudent(@ModelAttribute("student") Student student) {
        // save student to database
        studentService.saveStudent(student);
        return "redirect:/studentList";
    }

    @RequestMapping(value = "/get/{sid}")
    public String getStudent(@PathVariable("sid") Long sid, Model model) {

        Optional<Student> findStudentId = studentRepository.findById(sid);

        model.addAttribute("title", "Data Student");
        model.addAttribute("courses", courseRepository.findAll());
        model.addAttribute("student", findStudentId);

        log.info("getStudentId() : " + sid);
        return "redirect:/studentList";
    }

    @GetMapping("/showStudFormForUpdate/{sid}")
    public String showStudFormForUpdate(@PathVariable(value = "sid") Long sid, Model model) {
        Student student = studentService.getStudentById(sid);
        List<Course> allCourses = courseService.getAllCourses();
        model.addAttribute("allCourses", allCourses);

        model.addAttribute("student", student);

        return "update_student";
    }

    @GetMapping("/deleteStudent/{sid}")
    public String deleteStudent(@PathVariable(value = "sid") Long sid, Model model) {
        this.studentService.deleteStudentById(sid);
        return "redirect:/studentList";
    }
}
