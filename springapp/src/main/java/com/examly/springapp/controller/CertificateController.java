package com.examly.springapp.controller;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.Certificate;
import com.examly.springapp.service.CertificateService;

@RestController
@RequestMapping({"/api/certificates", "/certificates"})
@CrossOrigin("*")
public class CertificateController {

    @Autowired
    private CertificateService certificateService;

    @GetMapping
    public List<Certificate> getAllCertificates() {
        return certificateService.getAllCertificates();
    }

    @GetMapping("/{id}")
    public Certificate getCertificateById(@PathVariable Integer id) {
        return certificateService.getCertificateById(id);
    }

    @PostMapping
    public Certificate addCertificate(@RequestBody Certificate certificate) {
        return certificateService.addCertificate(certificate);
    }

    @PutMapping("/{id}")
    public Certificate updateCertificate(
            @PathVariable Integer id,
            @RequestBody Certificate certificate) {

        return certificateService.updateCertificate(id, certificate);
    }

    @DeleteMapping("/{id}")
    public String deleteCertificate(@PathVariable Integer id) {

        certificateService.deleteCertificate(id);

        return "Certificate Deleted Successfully";
    }

    @GetMapping(
            value = "/{certificateNumber}/download",
            produces = MediaType.APPLICATION_PDF_VALUE
    )
    public ResponseEntity<byte[]> downloadCertificate(
            @PathVariable String certificateNumber) {

        Certificate certificate =
                certificateService.getCertificateByNumber(certificateNumber);

        if (certificate == null) {
            return ResponseEntity.notFound().build();
        }

        byte[] pdf = createCertificatePdf(certificate);

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"certificate.pdf\""
                )
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }

    private byte[] createCertificatePdf(Certificate certificate) {

        String studentName =
                certificate.getStudentName() != null
                        ? certificate.getStudentName()
                        : "";

        String courseName =
                certificate.getCourseName() != null
                        ? certificate.getCourseName()
                        : "";

        String issueDate =
                certificate.getIssueDate() != null
                        ? certificate.getIssueDate().toString()
                        : "";

        String certificateNumber =
                certificate.getCertificateNumber() != null
                        ? certificate.getCertificateNumber()
                        : "";

        String content =
                "BT\n" +
                "/F1 24 Tf\n" +
                "150 700 Td\n" +
                "(CERTIFICATE OF COMPLETION) Tj\n" +
                "/F1 18 Tf\n" +
                "0 -60 Td\n" +
                "(" + escapePdfText(studentName) + ") Tj\n" +
                "/F1 14 Tf\n" +
                "0 -40 Td\n" +
                "(Course: " + escapePdfText(courseName) + ") Tj\n" +
                "0 -30 Td\n" +
                "(Issue Date: " + escapePdfText(issueDate) + ") Tj\n" +
                "0 -30 Td\n" +
                "(Certificate Number: " +
                escapePdfText(certificateNumber) +
                ") Tj\n" +
                "ET";

        String pdf =
                "%PDF-1.4\n" +
                "1 0 obj\n" +
                "<< /Type /Catalog /Pages 2 0 R >>\n" +
                "endobj\n" +
                "2 0 obj\n" +
                "<< /Type /Pages /Kids [3 0 R] /Count 1 >>\n" +
                "endobj\n" +
                "3 0 obj\n" +
                "<< /Type /Page /Parent 2 0 R " +
                "/MediaBox [0 0 612 792] " +
                "/Resources << /Font << /F1 4 0 R >> >> " +
                "/Contents 5 0 R >>\n" +
                "endobj\n" +
                "4 0 obj\n" +
                "<< /Type /Font /Subtype /Type1 /BaseFont /Helvetica >>\n" +
                "endobj\n" +
                "5 0 obj\n" +
                "<< /Length " + content.length() +
                " >>\n" +
                "stream\n" +
                content +
                "\nendstream\n" +
                "endobj\n" +
                "xref\n" +
                "0 6\n" +
                "0000000000 65535 f \n" +
                "0000000009 00000 n \n" +
                "0000000058 00000 n \n" +
                "0000000115 00000 n \n" +
                "0000000274 00000 n \n" +
                "0000000344 00000 n \n" +
                "trailer\n" +
                "<< /Size 6 /Root 1 0 R >>\n" +
                "startxref\n" +
                "500\n" +
                "%%EOF";

        return pdf.getBytes(StandardCharsets.UTF_8);
    }

    private String escapePdfText(String text) {

        return text
                .replace("\\", "\\\\")
                .replace("(", "\\(")
                .replace(")", "\\)");
    }
}