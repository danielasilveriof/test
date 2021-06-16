package br.com.eletronicsManager.api.fabricante;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import static javax.ws.rs.core.Response.Status;


@Path("fabricantes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Stateless
public class FabricanteResource {

    @PersistenceContext(unitName = "EletronicsmanagerPU")
    private EntityManager entityManager;
    
    public FabricanteResource() {}

    @GET
    public List<Fabricante> getFabricantes(@QueryParam("nome") String nome) {
        if(nome != null) {
           return entityManager
                   .createQuery("SELECT a FROM Fabricante a WHERE LOWER(a.nome) LIKE :nome", Fabricante.class) // JPQL
                   .setParameter("nome", "%" + nome.toLowerCase() + "%")
                   .getResultList();
        }
        return entityManager
                .createQuery("SELECT a FROM Fabricante a", Fabricante.class) // JPQL
                .getResultList();
    }

    @GET
    @Path("{id}")
    public Response getFabricante(@PathParam("id") int id) {
        Fabricante fabricante = findFabricante(id);        
        if (fabricante == null) {
            return fabricanteNotFoundResponse();
        }
        return Response.ok(fabricante).build();
    }

    @POST
    public Response addFabricante(Fabricante fabricante) {
        entityManager.persist(fabricante);
        return Response.status(Status.CREATED).entity(fabricante).build();
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") int id) {
        Fabricante fabricante = findFabricante(id);
        if(fabricante == null) {
            return fabricanteNotFoundResponse();
        } 
        entityManager.remove(fabricante);
        return Response.noContent().build();
    }
    
    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") int id, Fabricante fabricanteAtualizado) {
        Fabricante fabricante = findFabricante(id);
        if(fabricante == null) {
            return fabricanteNotFoundResponse();
        }
        fabricanteAtualizado.setId(id);
        entityManager.merge(fabricanteAtualizado);
        return Response.ok(fabricante).build();
    }
    
    public Fabricante findFabricante(int id) {
        return entityManager.find(Fabricante.class, id);
    }
    
    public Response fabricanteNotFoundResponse() {      
        return Response
                    .status(Status.NOT_FOUND)
                    .entity("Fabricante não encontrado para exclusão")
                    .build();
    }
}
