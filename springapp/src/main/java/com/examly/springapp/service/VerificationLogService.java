package com.examly.springapp.service;

import com.examly.springapp.model.VerificationLog;
import java.util.List;

public interface VerificationLogService {

    List<VerificationLog> getAllVerificationLogs();

    VerificationLog getVerificationLogById(Integer id);

    VerificationLog addVerificationLog(VerificationLog verificationLog);

    VerificationLog updateVerificationLog(Integer id, VerificationLog verificationLog);

    void deleteVerificationLog(Integer id);
}