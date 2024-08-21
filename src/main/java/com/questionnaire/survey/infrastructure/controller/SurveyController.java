package com.questionnaire.survey.infrastructure.controller;

import com.questionnaire.survey.domain.entity.Survey;
import com.questionnaire.survey.application.CreateSurveyUseCase;
import com.questionnaire.survey.application.DeleteSurveyUseCase;
import com.questionnaire.survey.application.UpdateSurveyUseCase;
import com.questionnaire.survey.application.ShowAllSurveyUseCase;
import com.questionnaire.survey.application.SearchSurveyUseCase;


import java.util.List;
import java.util.Optional;

public class SurveyController {
    private final CreateSurveyUseCase createSurveyUseCase;
    private final DeleteSurveyUseCase deleteSurveyUseCase;
    private final UpdateSurveyUseCase updateSurveyUseCase;
    private final ShowAllSurveyUseCase showAllSurveyUseCase;
    private final SearchSurveyUseCase searchSurveyUseCase;

    public SurveyController(CreateSurveyUseCase createSurveyUseCase,
                            DeleteSurveyUseCase deleteSurveyUseCase,
                            UpdateSurveyUseCase updateSurveyUseCase,
                            ShowAllSurveyUseCase showAllSurveyUseCase,
                            SearchSurveyUseCase searchSurveyUseCase) {
        this.createSurveyUseCase = createSurveyUseCase;
        this.deleteSurveyUseCase = deleteSurveyUseCase;
        this.updateSurveyUseCase = updateSurveyUseCase;
        this.showAllSurveyUseCase = showAllSurveyUseCase;
        this.searchSurveyUseCase = searchSurveyUseCase;
    }

    public void createSurvey(Survey survey) {
        createSurveyUseCase.execute(survey);
    }

    public void deleteSurvey(int id) {
        deleteSurveyUseCase.execute(id);
    }

    public void updateSurvey(Survey survey) {
        updateSurveyUseCase.execute(survey);
    }

    public List<Survey> getAllSurveys() {
        return showAllSurveyUseCase.execute();
    }

    public Optional<Survey> findSurveyById(int id) {
        return searchSurveyUseCase.execute(id);
    }
}
