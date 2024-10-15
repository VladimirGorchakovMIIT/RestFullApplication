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
    public void deleteJobById(Long id) {
        jobRepository.deleteById(id);
    }

    @Override
    public boolean updateJob(Long id, Job jobUpdate) {
        boolean result = false;

        if (jobRepository.findById(id).isPresent()) {
            createJob(jobUpdate);
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
