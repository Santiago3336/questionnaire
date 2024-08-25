package com.questionnaire.catalog.domain.service;

import java.util.List;
import java.util.Optional;

import com.questionnaire.catalog.domain.entity.Catalog;

public interface CatalogService {
    void createCatalog(Catalog catalog);
    Optional<Catalog> findCatalogById(int id);
    void updateCatalog(Catalog catalog);
    void deleteCatalog(int id);
    List<Catalog> getAllCatalogs();

    
}
