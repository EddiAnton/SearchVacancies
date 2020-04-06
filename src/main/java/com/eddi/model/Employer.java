package com.eddi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.List;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "employers")
public class Employer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int employer_id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "employer")
    private List<Vacancy> vacancies;

    public Employer() {}

    public Employer(int employer_id, String name) {
        this.employer_id = employer_id;
        this.name = name;
    }

    public int getEmployer_id() {
        return employer_id;
    }

    public void setEmployer_id(int employer_id) {
        this.employer_id = employer_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String employerName) {
        this.name = employerName;
    }

    @Override
    public String toString() {
        return "Employer{" +
                "id=" + employer_id +
                ", name='" + name + '\'' +
                '}';
    }
}
