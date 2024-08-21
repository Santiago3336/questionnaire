package com.questionnaire.survey.application;

import java.util.Optional;

import com.questionnaire.survey.domain.entity.Survey;
import com.questionnaire.survey.domain.service.SurveyService;

public class SearchSurveyUseCase {
    private final SurveyService surveyService;

    public SearchSurveyUseCase(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    public Optional<Survey> execute(int id){
        return surveyService.findSurveyById(id);
    }
}
