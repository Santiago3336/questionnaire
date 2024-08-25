package com.questionnaire.catalog.infrastructure.controller;

import com.questionnaire.catalog.application.CreateCatalogUseCase;
import com.questionnaire.catalog.application.UpdateCatalogUseCase;
import com.questionnaire.catalog.application.DeleteCatalogUseCase;
import com.questionnaire.catalog.application.ShowAllCatalogUseCase;
import com.questionnaire.catalog.application.SearchCatalogUseCase;
import com.questionnaire.catalog.domain.entity.Catalog;

import javax.swing.*;
import java.util.List;
import java.util.Optional;

public class CatalogController {
    private final CreateCatalogUseCase createCatalogUseCase;
    private final UpdateCatalogUseCase updateCatalogUseCase;
    private final DeleteCatalogUseCase deleteCatalogUseCase;
    private final ShowAllCatalogUseCase showAllCatalogUseCase;
    private final SearchCatalogUseCase searchCatalogUseCase;

    private JTextField catalogNameField;

    public CatalogController(CreateCatalogUseCase createCatalogUseCase,
                             UpdateCatalogUseCase updateCatalogUseCase,
                             DeleteCatalogUseCase deleteCatalogUseCase,
                             ShowAllCatalogUseCase showAllCatalogUseCase,
                             SearchCatalogUseCase searchCatalogUseCase,
                             JTextField catalogNameField) {
        this.createCatalogUseCase = createCatalogUseCase;
        this.updateCatalogUseCase = updateCatalogUseCase;
        this.deleteCatalogUseCase = deleteCatalogUseCase;
        this.showAllCatalogUseCase = showAllCatalogUseCase;
        this.searchCatalogUseCase = searchCatalogUseCase;
        this.catalogNameField = catalogNameField;
    }

    public void createCatalog() {
        String catalogName = catalogNameField.getText();
        if (!catalogName.isEmpty()) {
            Catalog catalog = new Catalog(catalogName);
            createCatalogUseCase.execute(catalog);
            JOptionPane.showMessageDialog(null, "Catálogo creado con éxito!");
        } else {
            JOptionPane.showMessageDialog(null, "El nombre del catálogo no puede estar vacío.");
        }
    }

    
    public void updateCatalog(int catalogId) {
        String catalogName = catalogNameField.getText();
        if (!catalogName.isEmpty()) {
            Catalog catalog = new Catalog(catalogId, catalogName);
            updateCatalogUseCase.execute(catalog);
            JOptionPane.showMessageDialog(null, "Catálogo actualizado con éxito!");
        } else {
            JOptionPane.showMessageDialog(null, "El nombre del catálogo no puede estar vacío.");
        }
    }

    
    public void deleteCatalog(int catalogId) {
        int confirmation = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que deseas eliminar este catálogo?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (confirmation == JOptionPane.YES_OPTION) {
            deleteCatalogUseCase.execute(catalogId);
            JOptionPane.showMessageDialog(null, "Catálogo eliminado con éxito!");
        }
    }

    public void searchCatalog(int catalogId) {
        Optional<Catalog> catalog = searchCatalogUseCase.execute(catalogId);
        if (catalog.isPresent()) {
            JOptionPane.showMessageDialog(null, "Catálogo encontrado: " + catalog.get().getName());
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró ningún catálogo con ese ID.");
        }
    }

    public void showAllCatalogs(JTextArea outputArea) {
        List<Catalog> catalogs = showAllCatalogUseCase.execute();
        StringBuilder catalogData = new StringBuilder();
        for (Catalog catalog : catalogs) {
            catalogData.append("ID: ").append(catalog.getId()).append(", Nombre: ").append(catalog.getName()).append("\n");
        }
        outputArea.setText(catalogData.toString());
    }
    
}
