package com.examly.springapp.service;

import com.examly.springapp.model.CertificateTemplate;
import java.util.List;

public interface CertificateTemplateService {

    List<CertificateTemplate> getAllCertificateTemplates();

    CertificateTemplate getCertificateTemplateById(Integer id);

    CertificateTemplate addCertificateTemplate(CertificateTemplate template);

    CertificateTemplate updateCertificateTemplate(Integer id, CertificateTemplate template);

    void deleteCertificateTemplate(Integer id);
}