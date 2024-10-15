package com.example.vladimir.controllers;

import com.example.vladimir.models.Job;
import com.example.vladimir.repositories.JobRepository;
import com.example.vladimir.services.impl.JobServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/job")
@RequiredArgsConstructor
public class JobController {

    private final ObjectMapper objectMapper;
    private final JobServiceImpl jobServiceImpl;

    @GetMapping
    public String getAll() throws JsonProcessingException {
        return objectMapper.writeValueAsString(jobServiceImpl.findAllJobs());
    }

    @GetMapping("/{id}")
    public String get(@PathVariable("id") Long id) throws JsonProcessingException {
        return objectMapper.writeValueAsString(jobServiceImpl.getJobById(id));
    }

    @PostMapping
    public void add(@RequestBody Job job){
        log.info("Post row job");
        jobServiceImpl.createJob(job);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") Long id, @RequestBody Job job){
        log.info("Put row job");
        jobServiceImpl.updateJob(id, job);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        log.info("Delete row job");
        jobServiceImpl.deleteJobById(id);
    }
}
