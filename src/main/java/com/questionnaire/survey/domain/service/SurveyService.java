package com.questionnaire.survey.domain.service;

import com.questionnaire.survey.domain.entity.Survey;

import java.util.List;
import java.util.Optional;

public interface SurveyService {
    void createSurvey(Survey survey);
    Optional<Survey> findSurveyById(int id);
    List<Survey> getAllSurveys();
    void updateSurvey(Survey survey);
    void deleteSurvey(int id);
}
