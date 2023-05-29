package com.example.Security.Repository;

import com.example.Security.Entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Long> {
}
