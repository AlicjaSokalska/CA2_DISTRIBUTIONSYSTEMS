package Parser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import dao.EmissionCategoryDAO;
import entities.EmissionCategory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ReadCategory {

    public static void main(String[] args) {
        Document doc;
        try {
            doc = Jsoup.connect("https://www.ipcc-nggip.iges.or.jp/EFDB/find_ef.php").get();

            // Get all "<script>" tags
            Elements allScripts = doc.getElementsByTag("script");

            // Go through the collection of tags
            for (Element script : allScripts) {
                // Extract the content of the script tag
                String scriptContent = script.html();

                // Check if the script content contains the specific string you are looking for
                if (scriptContent.contains("ipccTree = new dTree('ipccTree')")) {
                    // Extract the content between the parentheses
                    int startIndex = scriptContent.indexOf('(') + 1;
                    int endIndex = scriptContent.lastIndexOf(')');
                    String contentInsideParentheses = scriptContent.substring(startIndex, endIndex);

                    // Split the content into lines based on commas
                    String[] lines = contentInsideParentheses.split(",");

                    // Create a list to store EmissionCategory instances
                    List<EmissionCategory> emissionCategories = new ArrayList<>();

                    // Iterate through lines and populate the list
                    for (int i = 0; i < lines.length; i += 2) {
                        // Remove single quotes and trim whitespaces
                        String categoryName = lines[i].replaceAll("'", "").trim();
                        String categoryDescription = lines[i + 1].replaceAll("'", "").trim();

                        // Create EmissionCategory instance
                        EmissionCategory emissionCategory = new EmissionCategory();
                        emissionCategory.setCategoryName(categoryName);
                        emissionCategory.setCategoryDescription(categoryDescription);

                        // Add to the list
                        emissionCategories.add(emissionCategory);
                    }

                    // Now, you can persist the list to the database using your DAO
                    EmissionCategoryDAO emissionCategoryDAO = new EmissionCategoryDAO();
                    for (EmissionCategory emissionCategory : emissionCategories) {
                        emissionCategoryDAO.persist(emissionCategory);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

