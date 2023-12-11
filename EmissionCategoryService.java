package service;

import java.util.List;

import dao.EmissionCategoryDAO;
import entities.EmissionCategory;

public class EmissionCategoryService {

    private final EmissionCategoryDAO emissionCategoryDAO;

    public EmissionCategoryService() {
        this.emissionCategoryDAO = new EmissionCategoryDAO();
    }

    public List<EmissionCategory> getAllEmissionCategories() {
        return emissionCategoryDAO.getAllEmissionCategories();
    }

    public EmissionCategory getEmissionCategoryById(int id) {
        return emissionCategoryDAO.getEmissionCategoryById(id);
    }

    public EmissionCategory getEmissionCategoryByDescription(String categoryDescription) {
        return emissionCategoryDAO.getEmissionCategoryByDescription(categoryDescription);
    }

    public void createEmissionCategory(EmissionCategory emissionCategory) {
        emissionCategoryDAO.persist(emissionCategory);
    }

    public void updateEmissionCategory(int id, EmissionCategory updatedEmissionCategory) {
        EmissionCategory existingCategory = emissionCategoryDAO.getEmissionCategoryById(id);
        if (existingCategory != null) {
            updatedEmissionCategory.setId(existingCategory.getId());
            emissionCategoryDAO.merge(updatedEmissionCategory);
        }
    }

    public void deleteEmissionCategory(int id) {
        EmissionCategory existingCategory = emissionCategoryDAO.getEmissionCategoryById(id);
        if (existingCategory != null) {
            emissionCategoryDAO.remove(existingCategory);
        }
    }
}
