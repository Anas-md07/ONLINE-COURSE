package com.examly.springapp.controller;

import com.examly.springapp.model.CourseCompletion;
import com.examly.springapp.service.CourseCompletionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coursecompletions")
@CrossOrigin("*")
public class CourseCompletionController {

    @Autowired
    private CourseCompletionService courseCompletionService;

    @GetMapping
    public List<CourseCompletion> getAllCourseCompletions() {
        return courseCompletionService.getAllCourseCompletions();
    }

    @GetMapping("/{id}")
    public CourseCompletion getCourseCompletionById(@PathVariable Integer id) {
        return courseCompletionService.getCourseCompletionById(id);
    }

    @PostMapping
    public CourseCompletion addCourseCompletion(@RequestBody CourseCompletion completion) {
        return courseCompletionService.addCourseCompletion(completion);
    }

    @PutMapping("/{id}")
    public CourseCompletion updateCourseCompletion(@PathVariable Integer id,
                                                   @RequestBody CourseCompletion completion) {
        return courseCompletionService.updateCourseCompletion(id, completion);
    }

    @DeleteMapping("/{id}")
    public String deleteCourseCompletion(@PathVariable Integer id) {
        courseCompletionService.deleteCourseCompletion(id);
        return "Course Completion Deleted Successfully";
    }
}