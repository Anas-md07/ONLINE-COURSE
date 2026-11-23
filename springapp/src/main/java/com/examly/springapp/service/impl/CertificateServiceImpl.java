package com.examly.springapp.service.impl;

import com.examly.springapp.model.Certificate;
import com.examly.springapp.repository.CertificateRepository;
import com.examly.springapp.service.CertificateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class CertificateServiceImpl implements CertificateService {

    @Autowired
    private CertificateRepository certificateRepository;

    @Override
    public List<Certificate> getAllCertificates() {
        return certificateRepository.findAll();
    }

    @Override
    public Certificate getCertificateById(Integer id) {
        return certificateRepository.findById(id).orElse(null);
    }

    @Override
    public Certificate addCertificate(Certificate certificate) {

        // Generate unique certificate number/token
        if (certificate.getCertificateNumber() == null ||
                certificate.getCertificateNumber().trim().isEmpty()) {

            String token = UUID.randomUUID()
                    .toString()
                    .replace("-", "")
                    .substring(0, 12);

            certificate.setCertificateNumber(token);
        }

        // Automatically set issue date
        if (certificate.getIssueDate() == null) {
            certificate.setIssueDate(LocalDate.now());
        }

        // Set default status
        if (certificate.getStatus() == null ||
                certificate.getStatus().trim().isEmpty()) {

            certificate.setStatus("COMPLETED");
        }

        return certificateRepository.save(certificate);
    }

    @Override
    public Certificate updateCertificate(Integer id, Certificate certificate) {

        Certificate existing =
                certificateRepository.findById(id).orElse(null);

        if (existing != null) {

            existing.setCertificateNumber(
                    certificate.getCertificateNumber()
            );

            existing.setStudentName(
                    certificate.getStudentName()
            );

            existing.setCourseName(
                    certificate.getCourseName()
            );

            existing.setIssueDate(
                    certificate.getIssueDate()
            );

            existing.setGrade(
                    certificate.getGrade()
            );

            existing.setStatus(
                    certificate.getStatus()
            );

            return certificateRepository.save(existing);
        }

        return null;
    }

    @Override
    public void deleteCertificate(Integer id) {
        certificateRepository.deleteById(id);
    }

    @Override
    public Certificate getCertificateByNumber(String certificateNumber) {

        return certificateRepository
                .findByCertificateNumber(certificateNumber)
                .orElse(null);
    }
}