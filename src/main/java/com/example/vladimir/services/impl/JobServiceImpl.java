package com.example.vladimir.services.impl;

import com.example.vladimir.models.Job;
import com.example.vladimir.repositories.JobRepository;
import com.example.vladimir.services.JobService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;

    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public boolean deleteJobById(Long id) {
        boolean result = false;

        if(jobRepository.findById(id).isPresent()){
            jobRepository.deleteById(id);
            result = true;
        }

        return result;
    }

    @Override
    public boolean updateJob(Long id, Job jobUpdate) {
        boolean result = false;

        if (jobRepository.findById(id).isPresent()) {
            Job job = getJobById(id);
            job.setTitle(jobUpdate.getTitle());
            job.setDescription(jobUpdate.getDescription());
            job.setMinSalary(jobUpdate.getMinSalary());
            job.setMaxSalary(jobUpdate.getMaxSalary());
            job.setLocation(jobUpdate.getLocation());

            createJob(job);
            result = true;
        }

        return result;
    }

    @Override
    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Job> findAllJobs() {
        return jobRepository.findAll();
    }
}
