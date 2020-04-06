package com.eddi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int vacancy_id;

    @Column(name = "name")
    private String name;

    @Column(name = "published_at")
    private Date publishedAt;

    @ManyToOne
    @JoinColumn(name = "employer_id")
    private Employer employer;

    @OneToOne
    @JoinColumn(name = "salary_id")
    private Salary salary;

    public Vacancy() {}

    public Vacancy(String name, Date publishedAt, Employer employer, Salary salary) {
        this.name = name;
        this.publishedAt = publishedAt;
        this.employer = employer;
        this.salary = salary;
    }

    public int getVacancy_id() {
        return vacancy_id;
    }

    public void setVacancy_id(int vacancy_id) {
        this.vacancy_id = vacancy_id;
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

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public Salary getSalary() {
        return salary;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Vacancy{" +
                "id=" + vacancy_id +
                ", name='" + name + '\'' +
                ", publishedAt=" + publishedAt +
                ", employer=" + employer +
                ", salary=" + salary +
                '}';
    }
}
