package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import entities.EmissionCategory;

import java.util.List;

public class EmissionCategoryDAO {

    protected static EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("GHGEmission");

    public EmissionCategoryDAO() {
    }

    public void persist(EmissionCategory emissionCategory) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(emissionCategory);
        em.getTransaction().commit();
        em.close();
    }

    public void remove(EmissionCategory emissionCategory) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.merge(emissionCategory));
        em.getTransaction().commit();
        em.close();
    }

    public EmissionCategory merge(EmissionCategory emissionCategory) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        EmissionCategory updatedEmissionCategory = em.merge(emissionCategory);
        em.getTransaction().commit();
        em.close();
        return updatedEmissionCategory;
    }

    public List<EmissionCategory> getAllEmissionCategories() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<EmissionCategory> categoriesFromDB =
                em.createQuery("SELECT ec FROM EmissionCategory ec", EmissionCategory.class).getResultList();
        em.getTransaction().commit();
        em.close();
        return categoriesFromDB;
    }

    public EmissionCategory getEmissionCategoryById(int id) {
        EntityManager em = emf.createEntityManager();
        EmissionCategory emissionCategory = em.find(EmissionCategory.class, id);
        em.close();
        return emissionCategory;
    }

    public EmissionCategory getEmissionCategoryByDescription(String categoryDescription) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<EmissionCategory> query = em.createQuery(
                "SELECT ec FROM EmissionCategory ec WHERE ec.categoryDescription = :categoryDescription",
                EmissionCategory.class
        );
        query.setParameter("categoryDescription", categoryDescription);
        List<EmissionCategory> result = query.getResultList();
        em.close();

        return result.isEmpty() ? null : result.get(0);
    }
}
