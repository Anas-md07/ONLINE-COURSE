package com.examly.springapp.service;

import com.examly.springapp.model.BlockchainRecord;
import java.util.List;

public interface BlockchainRecordService {

    List<BlockchainRecord> getAllBlockchainRecords();

    BlockchainRecord getBlockchainRecordById(Integer id);

    BlockchainRecord addBlockchainRecord(BlockchainRecord record);

    BlockchainRecord updateBlockchainRecord(Integer id, BlockchainRecord record);

    void deleteBlockchainRecord(Integer id);
}