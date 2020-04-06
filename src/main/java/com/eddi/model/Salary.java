package com.eddi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "salary")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Salary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int salary_id;

    @Column(name = "salary_from")
    private int from;

    @Column(name = "salary_to")
    private int to;

    @OneToOne(mappedBy = "salary")
    private Vacancy vacancy;

    public Salary() {}

    public int getSalary_id() {
        return salary_id;
    }

    public void setSalary_id(int salary_id) {
        this.salary_id = salary_id;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public Vacancy getVacancy() {
        return vacancy;
    }

    public void setVacancy(Vacancy vacancy) {
        this.vacancy = vacancy;
    }

    @Override
    public String toString() {
        return "Salary{" +
                "from=" + from +
                ", to=" + to +
                '}';
    }
}
