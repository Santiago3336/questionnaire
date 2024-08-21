package com.questionnaire.survey.application;

import com.questionnaire.survey.domain.entity.Survey;
import com.questionnaire.survey.domain.service.SurveyService;

public class CreateSurveyUseCase {
        private final SurveyService surveyService;

        public CreateSurveyUseCase(SurveyService surveyService) {
            this.surveyService = surveyService;
        }

        public void execute(Survey survey) {
            surveyService.createSurvey(survey);
        }
}
