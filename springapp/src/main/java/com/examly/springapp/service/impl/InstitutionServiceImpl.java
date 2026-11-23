package com.examly.springapp.service.impl;

import com.examly.springapp.model.Institution;
import com.examly.springapp.repository.InstitutionRepository;
import com.examly.springapp.service.InstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstitutionServiceImpl implements InstitutionService {

    @Autowired
    private InstitutionRepository repository;

    @Override
    public List<Institution> getAllInstitutions() {
        return repository.findAll();
    }

    @Override
    public Institution getInstitutionById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Institution addInstitution(Institution institution) {
        return repository.save(institution);
    }

    @Override
    public Institution updateInstitution(Integer id, Institution institution) {

        Institution existing = repository.findById(id).orElse(null);

        if (existing == null)
            return null;

        existing.setInstitutionName(institution.getInstitutionName());
        existing.setAddress(institution.getAddress());
        existing.setEmail(institution.getEmail());
        existing.setContactNumber(institution.getContactNumber());
        existing.setWebsite(institution.getWebsite());

        return repository.save(existing);
    }

    @Override
    public void deleteInstitution(Integer id) {
        repository.deleteById(id);
    }
}