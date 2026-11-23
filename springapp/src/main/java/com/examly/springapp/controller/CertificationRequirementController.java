package com.examly.springapp.controller;

import com.examly.springapp.model.CertificationRequirement;
import com.examly.springapp.service.CertificationRequirementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/requirements")
@CrossOrigin("*")
public class CertificationRequirementController {

    @Autowired
    private CertificationRequirementService certificationRequirementService;

    @GetMapping
    public List<CertificationRequirement> getAllRequirements() {
        return certificationRequirementService.getAllCertificationRequirements();
    }

    @GetMapping("/{id}")
    public CertificationRequirement getRequirementById(@PathVariable Integer id) {
        return certificationRequirementService.getCertificationRequirementById(id);
    }

    @PostMapping
    public CertificationRequirement addRequirement(@RequestBody CertificationRequirement requirement) {
        return certificationRequirementService.addCertificationRequirement(requirement);
    }

    @PutMapping("/{id}")
    public CertificationRequirement updateRequirement(@PathVariable Integer id,@RequestBody CertificationRequirement requirement) {
        return certificationRequirementService.updateCertificationRequirement(id, requirement);
    }

    @DeleteMapping("/{id}")
    public String deleteRequirement(@PathVariable Integer id) {
        certificationRequirementService.deleteCertificationRequirement(id);
        return "Requirement Deleted Successfully";
    }
}