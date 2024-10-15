package com.example.vladimir.services.impl;

import com.example.vladimir.models.Company;
import com.example.vladimir.repositories.CompanyRepository;
import com.example.vladimir.services.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;

    @Override
    public void addNewCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public List<Company> getAllCompany() {
        return companyRepository.findAll();
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElseThrow();
    }

    @Override
    public boolean updateCompany(Long id, Company updateCompany) {
        boolean result = false;
        Optional<Company> companyOptional = companyRepository.findById(id);

        if (companyOptional.isPresent()) {
            Company companyToUpdate = companyOptional.get();
            companyToUpdate.setDescription(updateCompany.getDescription());
            companyToUpdate.setName(updateCompany.getName());
            companyToUpdate.setJobs(updateCompany.getJobs());
            companyRepository.save(companyToUpdate);
            result = true;
        }

        return result;
    }

    @Override
    public boolean deleteCompanyByid(Long id) {
        boolean result = false;

        if(companyRepository.findById(id).isPresent()){
            companyRepository.deleteById(id);
            result = true;
        }

        return result;
    }

}
