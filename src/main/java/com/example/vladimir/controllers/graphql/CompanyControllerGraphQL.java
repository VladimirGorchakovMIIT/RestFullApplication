package com.example.vladimir.controllers.graphql;

import com.example.vladimir.models.Company;
import com.example.vladimir.models.Job;
import com.example.vladimir.models.Review;
import com.example.vladimir.services.impl.CompanyServiceImpl;
import com.example.vladimir.services.impl.JobServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CompanyControllerGraphQL {
    private final CompanyServiceImpl companyService;
    private final JobServiceImpl jobService;

    @QueryMapping
    public List<Company> getAllCompanies(){
        log.info("Вывод всех компаний");
        return companyService.getAllCompany();
    }

    @QueryMapping
    public Company getCompanyById(@Argument Long id){
        return companyService.getCompanyById(id);
    }

    @MutationMapping
    public Boolean deleteCompanyById(@Argument Long id){
        return companyService.deleteCompanyByid(id);
    }
}
