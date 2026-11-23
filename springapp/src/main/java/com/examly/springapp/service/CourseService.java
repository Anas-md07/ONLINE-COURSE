package com.examly.springapp.service;

import com.examly.springapp.model.Course;
import java.util.List;

public interface CourseService {

    List<Course> getAllCourses();

    Course getCourseById(Integer id);

    Course addCourse(Course course);

    Course updateCourse(Integer id, Course course);

    void deleteCourse(Integer id);
}