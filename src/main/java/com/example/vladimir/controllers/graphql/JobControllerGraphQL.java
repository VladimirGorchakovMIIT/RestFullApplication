package com.example.vladimir.controllers.graphql;

import com.example.vladimir.models.Company;
import com.example.vladimir.models.Job;
import com.example.vladimir.services.impl.CompanyServiceImpl;
import com.example.vladimir.services.impl.JobServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class JobControllerGraphQL {
    private final JobServiceImpl jobService;
    private final CompanyServiceImpl companyService;

    @QueryMapping
    public List<Job> getAllJobs(){
        return jobService.findAllJobs();
    }

    @QueryMapping
    public Job getJobById(@Argument Long id){
        return jobService.getJobById(id);
    }

    @MutationMapping
    public Boolean deleteJobById(@Argument Long id){
        return jobService.deleteJobById(id);
    }

    @MutationMapping
    public String createJob(@Argument JobInput jobInput, @Argument Long companyId){
        Company company = companyService.getCompanyById(companyId);
        Job job = new Job(jobInput.title, jobInput.description, jobInput.minSalary, jobInput.maxSalary, jobInput.location, company);
        jobService.createJob(job);

        return "Creating new job";
    }

    @MutationMapping
    public String updateJob(@Argument Long jobId, @Argument JobInput jobInput, @Argument Long companyId){
        Company company = companyService.getCompanyById(companyId);
        Job job = new Job(jobInput.title, jobInput.description, jobInput.minSalary, jobInput.maxSalary, jobInput.location, company);
        jobService.updateJob(jobId, job);

        return "Job was updating";
    }

    private record JobInput(String title, String description, String minSalary, String maxSalary, String location){}
}
