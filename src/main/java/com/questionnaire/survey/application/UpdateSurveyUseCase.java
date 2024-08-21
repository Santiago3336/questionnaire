package com.questionnaire.survey.application;

import com.questionnaire.survey.domain.entity.Survey;
import com.questionnaire.survey.domain.service.SurveyService;

public class UpdateSurveyUseCase {
    private final SurveyService surveyService;

    public UpdateSurveyUseCase(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    public void execute(Survey survey) {
        surveyService.updateSurvey(survey);
    }
}
