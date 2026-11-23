package com.examly.springapp.repository;

import com.examly.springapp.model.CertificationRequirement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificationRequirementRepository extends JpaRepository<CertificationRequirement, Integer> {

}