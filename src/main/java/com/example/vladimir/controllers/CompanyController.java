package com.example.vladimir.controllers;

import com.example.vladimir.models.Company;
import com.example.vladimir.services.impl.CompanyServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/company")
public class CompanyController {
    private final CompanyServiceImpl companyService;
    private final ObjectMapper objectMapper;

    //Получение списка всех объектов Company
    @GetMapping
    public ResponseEntity<List<Company>> getAll() {
        return new ResponseEntity<>(companyService.getAllCompany(), HttpStatus.OK);
    }

    //Получение объекта из списка Company по его id
    @GetMapping("/{id}")
    public ResponseEntity<Company> get(@PathVariable("id") Long id) throws JsonProcessingException {
        return new ResponseEntity<>(companyService.getCompanyById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> add(@RequestBody Company company){
        companyService.addNewCompany(company);
        return new ResponseEntity<>("Company added successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable("id") Long id, @RequestBody Company company){
        companyService.updateCompany(id, company);
        return new ResponseEntity<>("Company update successfully" + companyService.updateCompany(id, company), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        companyService.deleteCompanyByid(id);
        return new ResponseEntity<>("Delete company successfully", HttpStatus.OK);
    }
}
