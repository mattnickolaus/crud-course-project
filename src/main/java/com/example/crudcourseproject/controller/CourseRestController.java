package com.example.crudcourseproject.controller;

import com.example.crudcourseproject.model.Course;
import com.example.crudcourseproject.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class CourseRestController {
    @Autowired
    CourseService courseService;

    @RequestMapping(value = "/course/", method = RequestMethod.GET)
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> course = courseService.getAllCourses();
        if (course.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

}
