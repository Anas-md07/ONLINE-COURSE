package com.examly.springapp.controller;

import com.examly.springapp.model.VerificationLog;
import com.examly.springapp.service.VerificationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/verificationlogs")
@CrossOrigin("*")
public class VerificationLogController {

    @Autowired
    private VerificationLogService verificationLogService;

    @GetMapping
    public List<VerificationLog> getAllVerificationLogs() {
        return verificationLogService.getAllVerificationLogs();
    }

    @GetMapping("/{id}")
    public VerificationLog getVerificationLogById(@PathVariable Integer id) {
        return verificationLogService.getVerificationLogById(id);
    }

    @PostMapping
    public VerificationLog addVerificationLog(@RequestBody VerificationLog verificationLog) {
        return verificationLogService.addVerificationLog(verificationLog);
    }

    @PutMapping("/{id}")
    public VerificationLog updateVerificationLog(@PathVariable Integer id,@RequestBody VerificationLog verificationLog) {
        return verificationLogService.updateVerificationLog(id,verificationLog);
    }

    @DeleteMapping("/{id}")
    public String deleteVerificationLog(@PathVariable Integer id) {
        verificationLogService.deleteVerificationLog(id);
        return "Verification Log Deleted Successfully";
    }
}