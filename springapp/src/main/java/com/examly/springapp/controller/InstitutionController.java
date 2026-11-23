package com.examly.springapp.controller;

import com.examly.springapp.model.Institution;
import com.examly.springapp.service.InstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/institutions")
@CrossOrigin("*")
public class InstitutionController {

    @Autowired
    private InstitutionService institutionService;

    @GetMapping
    public List<Institution> getAllInstitutions() {
        return institutionService.getAllInstitutions();
    }

    @GetMapping("/{id}")
    public Institution getInstitutionById(@PathVariable Integer id) {
        return institutionService.getInstitutionById(id);
    }

    @PostMapping
    public Institution addInstitution(@RequestBody Institution institution) {
        return institutionService.addInstitution(institution);
    }

    @PutMapping("/{id}")
    public Institution updateInstitution(@PathVariable Integer id,@RequestBody Institution institution) {
        return institutionService.updateInstitution(id,institution);
    }

    @DeleteMapping("/{id}")
    public String deleteInstitution(@PathVariable Integer id) {
        institutionService.deleteInstitution(id);
        return "Institution Deleted Successfully";
    }
}