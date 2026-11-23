package com.examly.springapp.service;

import com.examly.springapp.model.Institution;
import java.util.List;

public interface InstitutionService {

    List<Institution> getAllInstitutions();

    Institution getInstitutionById(Integer id);

    Institution addInstitution(Institution institution);

    Institution updateInstitution(Integer id, Institution institution);

    void deleteInstitution(Integer id);
}