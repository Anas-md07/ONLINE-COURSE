package com.examly.springapp.service.impl;

import com.examly.springapp.model.Course;
import com.examly.springapp.repository.CourseRepository;
import com.examly.springapp.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository repository;

    @Override
    public List<Course> getAllCourses() {
        return repository.findAll();
    }

    @Override
    public Course getCourseById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Course addCourse(Course course) {
        return repository.save(course);
    }

    @Override
    public Course updateCourse(Integer id, Course course) {

        Course existing = repository.findById(id).orElse(null);

        if (existing == null)
            return null;

        existing.setCourseName(course.getCourseName());
        existing.setCourseCode(course.getCourseCode());
        existing.setDescription(course.getDescription());
        existing.setDuration(course.getDuration());
        existing.setDepartment(course.getDepartment());

        return repository.save(existing);
    }

    @Override
    public void deleteCourse(Integer id) {
        repository.deleteById(id);
    }
}