package com.examly.springapp.service.impl;

import com.examly.springapp.model.VerificationLog;
import com.examly.springapp.repository.VerificationLogRepository;
import com.examly.springapp.service.VerificationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VerificationLogServiceImpl implements VerificationLogService {

    @Autowired
    private VerificationLogRepository repository;

    @Override
    public List<VerificationLog> getAllVerificationLogs() {
        return repository.findAll();
    }

    @Override
    public VerificationLog getVerificationLogById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public VerificationLog addVerificationLog(VerificationLog log) {
        return repository.save(log);
    }

    @Override
    public VerificationLog updateVerificationLog(Integer id, VerificationLog log) {

        VerificationLog existing = repository.findById(id).orElse(null);

        if (existing == null)
            return null;

        existing.setCertificateNumber(log.getCertificateNumber());
        existing.setVerifiedBy(log.getVerifiedBy());
        existing.setVerificationTime(log.getVerificationTime());
        existing.setVerificationStatus(log.getVerificationStatus());
        existing.setRemarks(log.getRemarks());

        return repository.save(existing);
    }

    @Override
    public void deleteVerificationLog(Integer id) {
        repository.deleteById(id);
    }
}