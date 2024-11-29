package com.example.vladimir.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
    @JsonIgnore
    @JoinColumn(name = "company_id", referencedColumnName = "id")
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
