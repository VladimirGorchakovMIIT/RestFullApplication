package com.example.vladimir.repositories;

import com.example.vladimir.models.Company;
import org.hibernate.boot.model.convert.spi.JpaAttributeConverterCreationContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
