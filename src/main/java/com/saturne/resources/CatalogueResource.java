package com.saturne.resources;

import com.saturne.entities.Catalogue;
import com.saturne.services.CatalogueService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/api/catalogue")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.MULTIPART_FORM_DATA)
public class CatalogueResource {

  @Inject
  private CatalogueService serviceCatalogue;

  @GET
  @Path("/all")
  public Response getAllCatalogues() {
    List<Catalogue> catalogues = serviceCatalogue.findAllCatalogues();
    return Response.ok(catalogues).build();
  }

  @GET
  @Path("/find/{id}")
  public Response getCatalogueById(@PathParam("id") int id) {
    Catalogue catalogue = serviceCatalogue.findCatalogueById(id);
    return Response.ok(catalogue).build();
  }

  @POST
  @Path("/add")
  @Consumes(MediaType.MULTIPART_FORM_DATA)
  public Response addCatalogue(
    @FormParam("titre") String titre,
    @FormParam("auteur") String auteur,
    @FormParam("dateCreation") String dateCreation
  ) {
    Catalogue newCatalogue = serviceCatalogue.addCatalogue(new Catalogue(titre, auteur, dateCreation));
    return Response.ok(newCatalogue).build();
  }
}
