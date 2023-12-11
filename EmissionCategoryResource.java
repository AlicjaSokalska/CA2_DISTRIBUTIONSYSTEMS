package resource;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import entities.EmissionCategory;
import service.EmissionCategoryService;

import java.util.List;

@Path("/emissioncategories")
public class EmissionCategoryResource {

    private final EmissionCategoryService emissionCategoryService;

    public EmissionCategoryResource() {
        this.emissionCategoryService = new EmissionCategoryService();
    }
//getall
    @GET
    @Path("/emissioncategories")
    @Produces("application/json")
    public List<EmissionCategory> getAllEmissionCategories() {
        return emissionCategoryService.getAllEmissionCategories();
    }

    @GET
    @Path("/emissioncategory/{id}")
    @Produces("application/json")
    public EmissionCategory getEmissionCategory(@PathParam("id") int id) {
        return emissionCategoryService.getEmissionCategoryById(id);
    }

    @POST
    @Path("/emissioncategory")
    @Consumes("application/json")
    public void createEmissionCategory(EmissionCategory emissionCategory) {
        emissionCategoryService.createEmissionCategory(emissionCategory);
    }

    @PUT
    @Path("/emissioncategory/{id}")
    @Consumes("application/json")
    public void updateEmissionCategory(@PathParam("id") int id, EmissionCategory updatedEmissionCategory) {
        emissionCategoryService.updateEmissionCategory(id, updatedEmissionCategory);
    }

  //delet
    @DELETE
    @Path("/emissioncategory/{id}")
    public void deleteEmissionCategory(@PathParam("id") int id) {
        emissionCategoryService.deleteEmissionCategory(id);
    }
}
