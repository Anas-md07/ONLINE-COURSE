package com.examly.springapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.Certificate;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, Integer> {

    Optional<Certificate> findByCertificateNumber(String certificateNumber);
}