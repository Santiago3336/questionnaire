package com.questionnaire.ui;

import com.questionnaire.survey.application.CreateSurveyUseCase;
import com.questionnaire.survey.domain.entity.Survey;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SurveyUI extends JFrame {
    private final CreateSurveyUseCase createSurveyUseCase;

    private JTextField nameField;
    private JTextArea descriptionArea;
    private JButton submitButton;

    public SurveyUI(CreateSurveyUseCase createSurveyUseCase) {
        this.createSurveyUseCase = createSurveyUseCase;
        initialize();
    }

    private void initialize() {
        setTitle("Survey Management");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2));

        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField();

        JLabel descriptionLabel = new JLabel("Description:");
        descriptionArea = new JTextArea();

        submitButton = new JButton("Create Survey");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleCreateSurvey();
            }
        });

        add(nameLabel);
        add(nameField);
        add(descriptionLabel);
        add(new JScrollPane(descriptionArea));
        add(submitButton);
    }

    private void handleCreateSurvey() {
        String name = nameField.getText();
        String description = descriptionArea.getText();
        
        Survey survey = new Survey(0, description, name); // Assuming ID is generated by the database
        createSurveyUseCase.execute(survey);
        
        JOptionPane.showMessageDialog(this, "Survey created successfully!");
    }
}
