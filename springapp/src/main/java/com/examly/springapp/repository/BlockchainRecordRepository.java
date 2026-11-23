package com.examly.springapp.repository;

import com.examly.springapp.model.BlockchainRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlockchainRecordRepository extends JpaRepository<BlockchainRecord, Integer> {

}