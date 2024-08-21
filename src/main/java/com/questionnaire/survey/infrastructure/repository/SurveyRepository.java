package com.questionnaire.survey.infrastructure.repository;

import com.questionnaire.survey.domain.entity.Survey;
import com.questionnaire.survey.domain.service.SurveyService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SurveyRepository implements SurveyService {
    private final Connection connection;

    public SurveyRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void createSurvey(Survey survey) {
        String sql = "INSERT INTO surveys (description, name) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, survey.getDescription());
            statement.setString(2, survey.getName());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    survey.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Survey> findSurveyById(int id) {
        String sql = "SELECT id, description, name FROM surveys WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Survey survey = new Survey(
                        resultSet.getInt("id"),
                        resultSet.getString("description"),
                        resultSet.getString("name")
                    );
                    return Optional.of(survey);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Survey> getAllSurveys() {
        List<Survey> surveys = new ArrayList<>();
        String sql = "SELECT id, description, name FROM surveys";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Survey survey = new Survey(
                    resultSet.getInt("id"),
                    resultSet.getString("description"),
                    resultSet.getString("name")
                );
                surveys.add(survey);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return surveys;
    }

    @Override
    public void updateSurvey(Survey survey) {
        String sql = "UPDATE surveys SET description = ?, name = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, survey.getDescription());
            statement.setString(2, survey.getName());
            statement.setInt(3, survey.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteSurvey(int id) {
        String sql = "DELETE FROM surveys WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
