package com.eddi.controller;

import com.eddi.model.Vacancy;
import com.eddi.model.VacancyList;
import com.eddi.service.VacancyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/select_vacancies")
public class SelectVacancyController {

    @Autowired
    private VacancyService vacancyService;

    @RequestMapping
    public String mainPage() {
        return "select_vacancies";
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public String select(@RequestParam("name") String name,
                         @RequestParam("publishedAt")String publishedAt,
                         @RequestParam("employerName") String employerName,
                         @RequestParam("salary") String salary,
                         Model model) {
        List<Vacancy> allSelection = new ArrayList<>();
        List<Vacancy> listFindByNameContaining = new ArrayList<>();
        List<Vacancy> listFindByEmployerNameContaining = new ArrayList<>();
        List<Vacancy> listFindByPublishedAtContaining = new ArrayList<>();
        //List<Vacancy> listFindBySalaryContaining = new ArrayList<>();

        listFindByNameContaining = vacancyService.findByNameContaining(name);
        listFindByEmployerNameContaining = vacancyService.findByEmployerNameContaining(employerName);
        listFindByPublishedAtContaining = vacancyService.findByPublishedAtContaining(publishedAt);
        //listFindBySalaryContaining = vacancyService.findBySalaryContaining(salary);

        if(allSelection.size() > 0 && listFindByNameContaining.size() > 0) {
            allSelection.retainAll(listFindByNameContaining);
        }else {
            allSelection.addAll(listFindByNameContaining);
        }
        if(allSelection.size() > 0 && listFindByEmployerNameContaining.size() > 0) {
            allSelection.retainAll(listFindByEmployerNameContaining);
        }else {
            allSelection.addAll(listFindByEmployerNameContaining);
        }
        if(allSelection.size() > 0 && listFindByPublishedAtContaining.size() > 0) {
            allSelection.retainAll(listFindByPublishedAtContaining);
        }else {
            allSelection.addAll(listFindByPublishedAtContaining);
        }
        /*if(allSelection.size() > 0 && listFindBySalaryContaining.size() > 0) {
            allSelection.retainAll(listFindBySalaryContaining);
        }else {
            allSelection.addAll(listFindBySalaryContaining);
        }*/

        model.addAttribute("vacancies", allSelection);
        return "view_vacancies";
    }

    @RequestMapping(value = "/view_vacancies")
    public String getAllVacancies(Model model) {
        model.addAttribute("vacancies", vacancyService.getAllVacancy());
        model.addAttribute("employers", vacancyService.getAllEmployer());
        return "view_vacancies";
    }

    @RequestMapping(value = "/select_vacancies", method = RequestMethod.GET)
    public String updateVacanciesDatabase(Model model) {

        String hhUrl = "https://api.hh.ru/vacancies?area=1202&specialization=1";
        VacancyList vacancyList = new VacancyService().getNewVacancies(hhUrl);
        for (Vacancy v: vacancyList.getItems()) {
            vacancyService.save(v);
        }
        return "select_vacancies";
    }
}
