package com.examly.springapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.BlockchainRecord;
import com.examly.springapp.repository.BlockchainRecordRepository;
import com.examly.springapp.service.BlockchainRecordService;

@Service
public class BlockchainRecordServiceImpl implements BlockchainRecordService {

    @Autowired
    private BlockchainRecordRepository repository;
    
    @Override
    public List<BlockchainRecord> getAllBlockchainRecords() {
        return repository.findAll();
    }

    @Override
    public BlockchainRecord getBlockchainRecordById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public BlockchainRecord addBlockchainRecord(BlockchainRecord record) {
        return repository.save(record);
    }

    @Override
    public BlockchainRecord updateBlockchainRecord(Integer id, BlockchainRecord record) {

        BlockchainRecord existing = repository.findById(id).orElse(null);

        if (existing == null)
            return null;

        existing.setCertificateHash(record.getCertificateHash());
        existing.setBlockchainNetwork(record.getBlockchainNetwork());
        existing.setTransactionHash(record.getTransactionHash());
        existing.setTransactionDate(record.getTransactionDate());
        existing.setStatus(record.getStatus());

        return repository.save(existing);
    }

    @Override
    public void deleteBlockchainRecord(Integer id) {
        repository.deleteById(id);
    }
}