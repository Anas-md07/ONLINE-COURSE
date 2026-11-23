package com.examly.springapp.controller;

import com.examly.springapp.model.CertificateTemplate;
import com.examly.springapp.service.CertificateTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/templates")
@CrossOrigin("*")
public class CertificateTemplateController {

    @Autowired
    private CertificateTemplateService certificateTemplateService;

    @GetMapping
    public List<CertificateTemplate> getAllTemplates() {
        return certificateTemplateService.getAllCertificateTemplates();
    }

    @GetMapping("/{id}")
    public CertificateTemplate getTemplateById(@PathVariable Integer id) {
        return certificateTemplateService.getCertificateTemplateById(id);
    }

    @PostMapping
    public CertificateTemplate addTemplate(@RequestBody CertificateTemplate template) {
        return certificateTemplateService.addCertificateTemplate(template);
    }

    @PutMapping("/{id}")
    public CertificateTemplate updateTemplate(@PathVariable Integer id,@RequestBody CertificateTemplate template) {
        return certificateTemplateService.updateCertificateTemplate(id, template);
    }

    @DeleteMapping("/{id}")
    public String deleteTemplate(@PathVariable Integer id) {
        certificateTemplateService.deleteCertificateTemplate(id);
        return "Template Deleted Successfully";
    }
}