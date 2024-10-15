package com.example.vladimir.services;

import com.example.vladimir.models.Company;

import java.util.List;

public interface CompanyService {
    List<Company> getAllCompany();

    Company getCompanyById(Long id);

    boolean updateCompany(Long id, Company updateCompany);

    boolean deleteCompanyByid(Long id);

    void addNewCompany(Company company);
}
