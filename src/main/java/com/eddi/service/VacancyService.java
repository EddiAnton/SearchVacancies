package com.eddi.service;

import com.eddi.model.Employer;
import com.eddi.model.Salary;
import com.eddi.model.Vacancy;
import com.eddi.model.VacancyList;
import com.eddi.repository.EmployerRepository;
import com.eddi.repository.SalaryRepository;
import com.eddi.repository.VacancyRepository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class VacancyService {
    @Autowired
    private VacancyRepository vacancyRepository;

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private SalaryRepository salaryRepository;

    public void saveEmployer(Employer employer) {
        employerRepository.save(employer);
    }

    public void saveSalary(Salary salary) {
        salaryRepository.save(salary);
    }

    public void saveVacancy(Vacancy vacancy) {
        saveEmployer(vacancy.getEmployer());
        saveSalary(vacancy.getSalary());
        vacancyRepository.save(vacancy);

        System.out.println(vacancy);
    }

    public List<Vacancy> getAllVacancy() {
        return StreamSupport
                .stream(Spliterators.spliteratorUnknownSize(vacancyRepository.findAll().iterator(),
                        Spliterator.NONNULL), false)
                .collect(Collectors.toList());
    }

    public List<Employer> getAllEmployer() {
        return StreamSupport
                .stream(Spliterators.spliteratorUnknownSize(employerRepository.findAll().iterator(),
                        Spliterator.NONNULL), false)
                .collect(Collectors.toList());
    }

    public VacancyList getNewVacancies(String url) {
        String httpObject = null;
        try {
            httpObject = new HttpService().doHttpUrlConnectionAction(url);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(httpObject);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        VacancyList vacancyList = null;
        try {
            vacancyList = objectMapper.readValue(httpObject, VacancyList.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return vacancyList;
    }

    public List<Vacancy> findByNameContaining(String name) {
        return StreamSupport
                .stream(Spliterators.spliteratorUnknownSize(vacancyRepository.findByNameContaining(name).iterator(),
                        Spliterator.NONNULL), false)
                .collect(Collectors.toList());
    }

    public List<Vacancy> findByEmployerNameContaining(String employerName) {
        return StreamSupport
                .stream(Spliterators.spliteratorUnknownSize(vacancyRepository.findByEmployerNameContaining(employerName).iterator(),
                        Spliterator.NONNULL), false)
                .collect(Collectors.toList());
    }

    public List<Vacancy> findByPublishedAtContaining(String publishedAt) {
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(publishedAt);
        }
        catch (Exception e) {
            date = Date.from(Instant.ofEpochMilli(0));
        }
        return StreamSupport
                .stream(Spliterators.spliteratorUnknownSize(vacancyRepository.findByPublishedAtContaining(date).iterator(),
                        Spliterator.NONNULL), false)
                .collect(Collectors.toList());
    }

    public List<Vacancy> findBySalaryContaining(String salary) {
        Integer salaryInt = Integer.parseInt(salary);
        return StreamSupport
                .stream(Spliterators.spliteratorUnknownSize(vacancyRepository.findBySalaryContaining(salaryInt).iterator(),
                        Spliterator.NONNULL), false)
                .collect(Collectors.toList());
    }
}
