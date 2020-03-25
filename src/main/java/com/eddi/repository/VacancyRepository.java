package com.eddi.repository;

import com.eddi.model.Vacancy;
import org.springframework.data.repository.CrudRepository;

public interface VacancyRepository extends CrudRepository<Vacancy, Integer> {

}
