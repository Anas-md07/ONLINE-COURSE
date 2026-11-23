package com.examly.springapp.service.impl;

import com.examly.springapp.model.CertificateTemplate;
import com.examly.springapp.repository.CertificateTemplateRepository;
import com.examly.springapp.service.CertificateTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CertificateTemplateServiceImpl implements CertificateTemplateService {

    @Autowired
    private CertificateTemplateRepository repository;

    @Override
    public List<CertificateTemplate> getAllCertificateTemplates() {
        return repository.findAll();
    }

    @Override
    public CertificateTemplate getCertificateTemplateById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public CertificateTemplate addCertificateTemplate(CertificateTemplate template) {
        return repository.save(template);
    }

    @Override
    public CertificateTemplate updateCertificateTemplate(Integer id, CertificateTemplate template) {

        CertificateTemplate existing = repository.findById(id).orElse(null);

        if (existing == null)
            return null;

        existing.setTemplateName(template.getTemplateName());
        existing.setTemplateType(template.getTemplateType());
        existing.setBackgroundImage(template.getBackgroundImage());
        existing.setLogo(template.getLogo());
        existing.setStatus(template.getStatus());

        return repository.save(existing);
    }

    @Override
    public void deleteCertificateTemplate(Integer id) {
        repository.deleteById(id);
    }
}