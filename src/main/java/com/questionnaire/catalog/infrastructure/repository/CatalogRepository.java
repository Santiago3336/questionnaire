package com.questionnaire.catalog.infrastructure.repository;

import com.questionnaire.catalog.domain.entity.Catalog;
import com.questionnaire.catalog.domain.service.CatalogService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CatalogRepository implements CatalogService {
    private final Connection connection;

    public CatalogRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void createCatalog(Catalog catalog) {
        String sql = "INSERT INTO catalogs (name) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, catalog.getName());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    catalog.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Catalog> findCatalogById(int id) {
        String query = "SELECT * FROM catalogs WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Catalog catalog = new Catalog(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                    );
                    return Optional.of(catalog);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void updateCatalog(Catalog catalog) {
        String sql = "UPDATE catalogs SET name = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, catalog.getName());
            statement.setInt(2, catalog.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCatalog(int id) {
        String sql = "DELETE FROM catalogs WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Catalog> getAllCatalogs() {
        List<Catalog> catalogs = new ArrayList<>();
        String query = "SELECT * FROM catalogs";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Catalog catalog = new Catalog(
                    resultSet.getInt("id"),
                    resultSet.getString("name")
                );
                catalogs.add(catalog);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return catalogs;
    }
}
