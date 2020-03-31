package com.eddi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "vacancies")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Vacancy implements Serializable {
    private static final String PATTERN = "yyyy-MM-dd";
    Employer employer = new Employer();
    Salary salary = new Salary();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "published_at")
    private Date publishedAt;

    @Column(name = "employer_name")
    private String employerName;

    @Column(name = "salary_from")
    private int salaryFrom;

    @Column(name = "salary_to")
    private int salaryTo;

    public Vacancy() {}

    public Vacancy(String name, Date publishedAt, String employerName, int salaryFrom, int salaryTo) {
        this.name = name;
        this.publishedAt = publishedAt;
        this.employerName = employerName;
        this.salaryFrom = salaryFrom;
        this.salaryTo = salaryTo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublishedAt() {
        if (publishedAt != null) {
            return new SimpleDateFormat(PATTERN).format(publishedAt);
        }
        return "";
    }

    public void setPublishedAt(String publishedAt) {
        try {
            this.publishedAt = new SimpleDateFormat(PATTERN).parse(publishedAt);
        }catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getEmployerName() {
        return employer.getName();
    }

    public void setEmployerName(String employerName) {
        employer.setName(employerName);
    }

    public int getSalaryFrom() {
        return salary.getFrom();
    }

    public void setSalaryFrom(int salaryFrom) {
        salary.setFrom(salaryFrom);
    }

    public int getSalaryTo() {
        return salary.getTo();
    }

    public void setSalaryTo(int salaryTo) {
        salary.setTo(salaryTo);
    }
}
