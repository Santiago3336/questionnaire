package com.questionnaire.survey.application;

import java.util.List;

import com.questionnaire.survey.domain.entity.Survey;
import com.questionnaire.survey.domain.service.SurveyService;

public class ShowAllSurveyUseCase {
    private final SurveyService surveyService;

    public ShowAllSurveyUseCase(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    public List<Survey> execute() {
        return surveyService.getAllSurveys();
    }
}
