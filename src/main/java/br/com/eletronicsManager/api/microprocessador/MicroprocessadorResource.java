package br.com.eletronicsManager.api.microprocessador;

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


@Path("microprocessadores")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Stateless
public class MicroprocessadorResource {

    @PersistenceContext(unitName = "EletronicsmanagerPU")
    private EntityManager entityManager;
    
    public MicroprocessadorResource() {}

    @GET
    public List<Microprocessador> getMicroprocessadores(@QueryParam("codFabricante") String codFabricante) {
        if(codFabricante != null) {
           return entityManager
                   .createQuery("SELECT a FROM Microprocessador a WHERE LOWER(a.codFabricante) LIKE :codFabricante", Microprocessador.class) // JPQL
                   .setParameter("codFabricante", "%" + codFabricante.toLowerCase() + "%")
                   .getResultList();
        }
        return entityManager
                .createQuery("SELECT a FROM Microprocessador a", Microprocessador.class) // JPQL
                .getResultList();
    }

    @GET
    @Path("{id}")
    public Response getMicroprocessador(@PathParam("id") int id) {
        Microprocessador microprocessador = findMicroprocessador(id);        
        if (microprocessador == null) {
            return MicroprocessadorNotFoundResponse();
        }
        return Response.ok(microprocessador).build();
    }

    @POST
    public Response addMicroprocessador(Microprocessador microprocessador) {
        entityManager.persist(microprocessador);
        return Response.status(Status.CREATED).entity(microprocessador).build();
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") int id) {
        Microprocessador microprocessador = findMicroprocessador(id);
        if(microprocessador == null) {
            return MicroprocessadorNotFoundResponse();
        } 
        entityManager.remove(microprocessador);
        return Response.noContent().build();
    }
    
    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") int id, Microprocessador microprocessadorAtualizado) {
        Microprocessador microprocessador = findMicroprocessador(id);
        if(microprocessador == null) {
            return MicroprocessadorNotFoundResponse();
        }
        microprocessadorAtualizado.setId(id);
        entityManager.merge(microprocessadorAtualizado);
        return Response.ok(microprocessador).build();
    }
    
    public Microprocessador findMicroprocessador(int id) {
        return entityManager.find(Microprocessador.class, id);
    }
    
    public Response MicroprocessadorNotFoundResponse() {      
        return Response
                    .status(Status.NOT_FOUND)
                    .entity("Microprocessador não encontrado para exclusão")
                    .build();
    }
}
