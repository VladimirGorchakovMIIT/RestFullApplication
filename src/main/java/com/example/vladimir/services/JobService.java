package com.example.vladimir.services;

import com.example.vladimir.models.Job;

import java.util.List;

public interface JobService {

    void createJob(Job job);

    boolean deleteJobById(Long id);

    boolean updateJob(Long id, Job jobUpdate);

    Job getJobById(Long id);

    List<Job> findAllJobs();

}
