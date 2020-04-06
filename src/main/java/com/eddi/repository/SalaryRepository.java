package com.eddi.repository;

import com.eddi.model.Salary;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaryRepository extends CrudRepository<Salary, Integer> {
}
