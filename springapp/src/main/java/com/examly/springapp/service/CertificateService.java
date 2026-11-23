package com.examly.springapp.service;

import java.util.List;

import com.examly.springapp.model.Certificate;

public interface CertificateService {

    List<Certificate> getAllCertificates();

    Certificate getCertificateById(Integer id);

    Certificate addCertificate(Certificate certificate);

    Certificate updateCertificate(Integer id, Certificate certificate);

    void deleteCertificate(Integer id);

    Certificate getCertificateByNumber(String certificateNumber);

}