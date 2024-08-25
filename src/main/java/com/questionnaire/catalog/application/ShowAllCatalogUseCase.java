package com.questionnaire.catalog.application;

import java.util.List;

import com.questionnaire.catalog.domain.entity.Catalog;
import com.questionnaire.catalog.domain.service.CatalogService;

public class ShowAllCatalogUseCase {
    private final CatalogService catalogService;

    public ShowAllCatalogUseCase (CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    public List<Catalog> execute() {
        return catalogService.getAllCatalogs();
    }
}
