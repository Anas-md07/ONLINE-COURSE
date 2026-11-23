package com.examly.springapp.service.impl;

import com.examly.springapp.model.CertificationRequirement;
import com.examly.springapp.repository.CertificationRequirementRepository;
import com.examly.springapp.service.CertificationRequirementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CertificationRequirementServiceImpl implements CertificationRequirementService {

    @Autowired
    private CertificationRequirementRepository repository;

    @Override
    public List<CertificationRequirement> getAllCertificationRequirements() {
        return repository.findAll();
    }

    @Override
    public CertificationRequirement getCertificationRequirementById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public CertificationRequirement addCertificationRequirement(CertificationRequirement requirement) {
        return repository.save(requirement);
    }

    @Override
    public CertificationRequirement updateCertificationRequirement(Integer id, CertificationRequirement requirement) {

        CertificationRequirement existing = repository.findById(id).orElse(null);

        if (existing == null)
            return null;

        existing.setMinimumGrade(requirement.getMinimumGrade());
        existing.setCompletionCriteria(requirement.getCompletionCriteria());
        existing.setTimeLimit(requirement.getTimeLimit());
        existing.setPrerequisites(requirement.getPrerequisites());
        existing.setRemarks(requirement.getRemarks());

        return repository.save(existing);
    }

    @Override
    public void deleteCertificationRequirement(Integer id) {
        repository.deleteById(id);
    }
}