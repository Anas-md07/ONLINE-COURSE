package com.examly.springapp.service;

import com.examly.springapp.model.CertificationRequirement;
import java.util.List;

public interface CertificationRequirementService {

    List<CertificationRequirement> getAllCertificationRequirements();

    CertificationRequirement getCertificationRequirementById(Integer id);

    CertificationRequirement addCertificationRequirement(CertificationRequirement requirement);

    CertificationRequirement updateCertificationRequirement(Integer id, CertificationRequirement requirement);

    void deleteCertificationRequirement(Integer id);
}