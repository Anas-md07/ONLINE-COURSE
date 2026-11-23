package com.examly.springapp.service.impl;

import com.examly.springapp.model.CourseCompletion;
import com.examly.springapp.repository.CourseCompletionRepository;
import com.examly.springapp.service.CourseCompletionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseCompletionServiceImpl implements CourseCompletionService {

    @Autowired
    private CourseCompletionRepository repository;

    @Override
    public List<CourseCompletion> getAllCourseCompletions() {
        return repository.findAll();
    }

    @Override
    public CourseCompletion getCourseCompletionById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public CourseCompletion addCourseCompletion(CourseCompletion courseCompletion) {
        return repository.save(courseCompletion);
    }

    @Override
    public CourseCompletion updateCourseCompletion(Integer id, CourseCompletion courseCompletion) {

        CourseCompletion existing = repository.findById(id).orElse(null);

        if (existing == null)
            return null;

        existing.setCompletionDate(courseCompletion.getCompletionDate());
        existing.setGrade(courseCompletion.getGrade());
        existing.setVerifiedBy(courseCompletion.getVerifiedBy());
        existing.setCertificateIssued(courseCompletion.isCertificateIssued());

        return repository.save(existing);
    }

    @Override
    public void deleteCourseCompletion(Integer id) {
        repository.deleteById(id);
    }
}