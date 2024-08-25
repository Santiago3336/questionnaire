package com.questionnaire.ui;

import com.questionnaire.catalog.application.CreateCatalogUseCase;
import com.questionnaire.catalog.application.UpdateCatalogUseCase;
import com.questionnaire.catalog.application.DeleteCatalogUseCase;
import com.questionnaire.catalog.application.ShowAllCatalogUseCase;
import com.questionnaire.catalog.application.SearchCatalogUseCase;
import com.questionnaire.catalog.infrastructure.controller.CatalogController;
import com.questionnaire.catalog.infrastructure.repository.CatalogRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CatalogUI extends JFrame {
    private JTextField catalogNameField;
    private JTextField catalogIdField;
    private JTextArea catalogDisplayArea;
    private CatalogController catalogController;

    public CatalogUI() {
        setTitle("Gestión de Catálogos");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        catalogNameField = new JTextField(20);
        catalogIdField = new JTextField(5);
        catalogDisplayArea = new JTextArea(10, 40);
        catalogDisplayArea.setEditable(false);

        // Initialize connection and repository
        Connection connection = createConnection();
        CatalogRepository catalogRepository = new CatalogRepository(connection);

        // Create use cases with the repository
        CreateCatalogUseCase createCatalogUseCase = new CreateCatalogUseCase(catalogRepository);
        UpdateCatalogUseCase updateCatalogUseCase = new UpdateCatalogUseCase(catalogRepository);
        DeleteCatalogUseCase deleteCatalogUseCase = new DeleteCatalogUseCase(catalogRepository);
        ShowAllCatalogUseCase showAllCatalogUseCase = new ShowAllCatalogUseCase(catalogRepository);
        SearchCatalogUseCase searchCatalogUseCase = new SearchCatalogUseCase(catalogRepository);

        // Initialize controller
        catalogController = new CatalogController(
            createCatalogUseCase,
            updateCatalogUseCase,
            deleteCatalogUseCase,
            showAllCatalogUseCase,
            searchCatalogUseCase,
            catalogNameField
        );

        // Panel de entrada
        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        inputPanel.add(new JLabel("ID del Catálogo:"));
        inputPanel.add(catalogIdField);
        inputPanel.add(new JLabel("Nombre del Catálogo:"));
        inputPanel.add(catalogNameField);

        // Botones
        JButton createButton = new JButton("Crear Catálogo");
        JButton updateButton = new JButton("Actualizar Catálogo");
        JButton deleteButton = new JButton("Eliminar Catálogo");
        JButton searchButton = new JButton("Buscar Catálogo");
        JButton showAllButton = new JButton("Mostrar Todos los Catálogos");

        // Listeners para los botones
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                catalogController.createCatalog();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int catalogId = Integer.parseInt(catalogIdField.getText());
                catalogController.updateCatalog(catalogId);
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int catalogId = Integer.parseInt(catalogIdField.getText());
                catalogController.deleteCatalog(catalogId);
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int catalogId = Integer.parseInt(catalogIdField.getText());
                catalogController.searchCatalog(catalogId);
            }
        });

        showAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                catalogController.showAllCatalogs(catalogDisplayArea);
            }
        });

        // Panel de botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(createButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(showAllButton);

        // Añadir componentes al frame
        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(catalogDisplayArea), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private Connection createConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/sourvey_jr", "root", "root");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CatalogUI catalogUI = new CatalogUI();
            catalogUI.setVisible(true);
        });
    }
}
