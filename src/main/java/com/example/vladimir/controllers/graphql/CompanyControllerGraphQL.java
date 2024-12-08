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
        return companyService.getAllCompany();
    }

    @QueryMapping
    public Company getCompanyById(@Argument Long id){
        return companyService.getCompanyById(id);
    }

    @MutationMapping
    public String createCompany(@Argument CompanyInput companyInput){
        String result = "Была создана новая компания";

        Company company = new Company(companyInput.name, companyInput.description, new ArrayList<>(), new ArrayList<>());
        companyService.addNewCompany(company);

        return result;
    }
    @MutationMapping
    public String updateCompany(@Argument Long companyId, @Argument CompanyInput companyInput){
        String result = "Компания была успешно добавлена";

        Company company = companyService.getCompanyById(companyId);

        company.setName(companyInput.name);
        company.setDescription(companyInput.description);

        companyService.updateCompany(companyId, company);

        return result;
    }

    @MutationMapping
    public Boolean deleteCompanyById(@Argument Long id){
        return companyService.deleteCompanyByid(id);
    }


    private record CompanyInput(String name, String description){}
}
