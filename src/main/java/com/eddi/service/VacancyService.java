package com.eddi.service;

import com.eddi.model.Vacancy;
import com.eddi.repository.VacancyRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class VacancyService {
    @Autowired
    private VacancyRepository vacancyRepository;

    public void save(Vacancy vacancy) {
        vacancyRepository.save(vacancy);

        System.out.println(vacancy);
    }

    public List<Vacancy> getAllVacancy() {
        return StreamSupport
                .stream(Spliterators.spliteratorUnknownSize(vacancyRepository.findAll().iterator(),
                        Spliterator.NONNULL), false)
                .collect(Collectors.toList());
    }

    public Vacancy getNewVacancy(String url) {
        String httpObject = null;
        try {
            httpObject = new HttpService().doHttpUrlConnectionAction(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Vacancy vacancy = null;
        try {
            vacancy = objectMapper.readValue(httpObject, Vacancy.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return vacancy;
    }
}
