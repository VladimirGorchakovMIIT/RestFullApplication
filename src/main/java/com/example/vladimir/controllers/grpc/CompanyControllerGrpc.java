package com.example.vladimir.controllers.grpc;

import com.example.vladimir.models.Company;
import com.example.vladimir.services.grpc.FormationChequeServiceImpl;
import com.example.vladimir.services.impl.CompanyServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/company/grpc")
@RequiredArgsConstructor
public class CompanyControllerGrpc {
    private final CompanyServiceImpl companyService;
    private final FormationChequeServiceImpl formationChequeService;

    @GetMapping("/{id}")
    public String getFormationCheque(@PathVariable("id")Long companyId){
        Company company = companyService.getCompanyById(companyId);
        String result = formationChequeService.getInformationCheque(company.getName(), company.getDescription(), company);

        formationChequeService.createQueue(company);

        return result;
    }
}
