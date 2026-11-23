package com.examly.springapp.controller;

import com.examly.springapp.model.BlockchainRecord;
import com.examly.springapp.service.BlockchainRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blockchain")
@CrossOrigin("*")
public class BlockchainRecordController {

    @Autowired
    private BlockchainRecordService blockchainRecordService;

    @GetMapping
    public List<BlockchainRecord> getAllRecords() {
        return blockchainRecordService.getAllBlockchainRecords();
    }

    @GetMapping("/{id}")
    public BlockchainRecord getRecordById(@PathVariable Integer id) {
        return blockchainRecordService.getBlockchainRecordById(id);
    }

    @PostMapping
    public BlockchainRecord addRecord(@RequestBody BlockchainRecord record) {
        return blockchainRecordService.addBlockchainRecord(record);
    }

    @PutMapping("/{id}")
    public BlockchainRecord updateRecord(@PathVariable Integer id,@RequestBody BlockchainRecord record) {
        return blockchainRecordService.updateBlockchainRecord(id, record);
    }

    @DeleteMapping("/{id}")
    public String deleteRecord(@PathVariable Integer id) {
        blockchainRecordService.deleteBlockchainRecord(id);
        return "Blockchain Record Deleted Successfully";
    }
}