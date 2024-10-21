package com.example.crudcourseproject.controller;
import com.example.crudcourseproject.model.Course;
import com.example.crudcourseproject.model.Student;
import com.example.crudcourseproject.repository.CourseRepository;
import com.example.crudcourseproject.repository.StudentRepository;
import com.example.crudcourseproject.service.StudentService;
import com.example.crudcourseproject.service.CourseService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class CourseController {

    @Autowired
    private CourseService courseService;

    // display list of courses
    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("listCourses", courseService.getAllCourses());

        return "index";
    }

    @RequestMapping(value = "/HelloWorld")
    public String helloWorld(Model model) {return "HelloWorld";}

    @GetMapping("/showNewCourseForm")
    public String showNewCourseForm(Model model) {
        //create model attribute to bind form data
        Course course = new Course();
        model.addAttribute("course", course);
        return "new_course";
    }

    @PostMapping("/saveCourse")
    public String saveCourse(@ModelAttribute("course") Course course) {
        // save course to database
        courseService.saveCourse(course);
        return "redirect:/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable (value = "id") long id, Model model) {
        // get course from the service
        Course course = courseService.getCourseById(id);

        // set course as model attribute to pre-populate the form
        model.addAttribute("course", course);
        return "update_course";
    }

    @GetMapping("/deleteCourse/{id}")
    public String deleteCourse(@PathVariable (value = "id") long id) {
        // call delete course method
        this.courseService.deleteCourseById(id);
        return "redirect:/";
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
                                @RequestParam ("sortField") String sortField,
                                @RequestParam ("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;
        Page<Course> page = courseService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Course> listCourses = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());


        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listCourses", listCourses);
        return "index";
    }
}
