package main;

import dao.EmissionDAO;
import dao.PredictionDAO;
import dao.UserDAO;
import entities.Emission;
import entities.Prediction;
import entities.User;
import service.EmissionService;
import service.PredictionService;
import service.UserService;
public class Test {

    public static void main(String[] args) {
        // Test User
        testUser();

        // Test Prediction
        testPrediction();

        // Test Emission
        testEmission();
    }

    private static void testUser() {
        User user = new User(0,"tom", "password123");

        // Create UserDAO instance
        UserDAO userDAO = new UserDAO();

        // Persist User
        userDAO.persist(user);

        // Retrieve User by email
        User retrievedUser = userDAO.getUserByUsername("john@example.com");
        if (retrievedUser != null) {
            System.out.println("Retrieved User: " + retrievedUser);
        } else {
            System.out.println("User not found");
        }
    }

    private static void testPrediction() {
        Prediction prediction = new Prediction(0,"CategoryA", 100.0, 2023, "MCF", "WEM");

        // Create PredictionDAO instance
        PredictionDAO predictionDAO = new PredictionDAO();

        // Persist Prediction
        predictionDAO.persist(prediction);

        // Retrieve Prediction by category
        Prediction retrievedPrediction = predictionDAO.getPredictionsByCategory("CategoryA");
        if (retrievedPrediction != null) {
            System.out.println("Retrieved Prediction: " + retrievedPrediction);
        } else {
            System.out.println("Prediction not found");
        }
    }

    private static void testEmission() {
        Emission emission = new Emission(0,"CategoryB", 50.0, "CO2");

        // Create EmissionDAO instance
        EmissionDAO emissionDAO = new EmissionDAO();

        // Persist Emission
        emissionDAO.persist(emission);

        // Retrieve Emission by category
        Emission retrievedEmission = emissionDAO.getEmissionsByCategory("CategoryB");
        if (retrievedEmission != null) {
            System.out.println("Retrieved Emission: " + retrievedEmission);
        } else {
            System.out.println("Emission not found");
        }
    }
}
