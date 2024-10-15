package com.example.vladimir.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "job")
public class Job extends BaseModel{

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "minSalary", nullable = false)
    private String minSalary;

    @Column(name = "maxSalary", nullable = false)
    private String maxSalary;

    @Column(name = "location", nullable = false)
    private String location;

    @ManyToOne
    private Company company;

    public Job() {}

    public Job(String title, String description, String minSalary, String maxSalary, String location, Company company) {
        this.title = title;
        this.description = description;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
        this.location = location;
        this.company = company;
    }
}
