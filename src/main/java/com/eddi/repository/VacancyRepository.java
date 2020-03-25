package com.eddi.repository;

import com.eddi.model.Vacancy;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface VacancyRepository extends CrudRepository<Vacancy, Integer> {
    public List<Vacancy> findByNameContaining(String name);
    public List<Vacancy> findByEmployerNameContaining(String employerName);
    public List<Vacancy> findByPublishedAtContaining(Date publishedAt);
    public List<Vacancy> findBySalaryContaining(int salary);
}
