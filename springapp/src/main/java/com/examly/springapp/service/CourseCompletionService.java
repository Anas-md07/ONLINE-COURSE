package com.examly.springapp.service;

import com.examly.springapp.model.CourseCompletion;
import java.util.List;

public interface CourseCompletionService {

    List<CourseCompletion> getAllCourseCompletions();

    CourseCompletion getCourseCompletionById(Integer id);

    CourseCompletion addCourseCompletion(CourseCompletion completion);

    CourseCompletion updateCourseCompletion(Integer id, CourseCompletion completion);

    void deleteCourseCompletion(Integer id);
}