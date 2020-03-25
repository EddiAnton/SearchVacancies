package com.eddi.controller;

import com.eddi.model.Vacancy;
import com.eddi.service.VacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

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
    public String select() {
        return "view_vacancies";
    }

    @RequestMapping(value = "/view_vacancies")
    public String getAllVacancies(Model model) {
        model.addAttribute("vacancies", vacancyService.getAllVacancy());
        return "view_vacancies";
    }

    @RequestMapping(value = "/select_vacancies")
    public String updateVacanciesDatabase() {

        String hhUrl = "https://api.hh.ru/vacancies?area=1202&specialization=1";
        Vacancy vacancy = new VacancyService().getNewVacancy(hhUrl);
        vacancyService.save(vacancy);

        /*String hhUrl = "https://api.hh.ru/vacancies?area=1202&specialization=1";
        List<Vacancy> vacancyList = new VacancyService().getNewVacancy(hhUrl);
        for (Vacancy v: vacancyList) {
            vacancyService.save(v);
        }*/
        return "redirect:../";
    }
}
