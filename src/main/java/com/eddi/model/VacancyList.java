package com.eddi.model;

import java.util.ArrayList;
import java.util.List;

public class VacancyList {
    private List<Vacancy> items;

    public VacancyList() {
        items = new ArrayList<>();
    }

    public VacancyList(List<Vacancy> items) {
        this.items = items;
    }

    public List<Vacancy> getItems() {
        return items;
    }

    public void setItems(List<Vacancy> items) {
        this.items = items;
    }
}
