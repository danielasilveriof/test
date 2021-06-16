package br.com.eletronicsManager.api.projeto;

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


@Path("projetos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Stateless
public class ProjetoResource {

    @PersistenceContext(unitName = "EletronicsmanagerPU")
    private EntityManager entityManager;
    
    public ProjetoResource() {}

    @GET
    public List<Projeto> getProjetos(@QueryParam("nome") String nome) {
        if(nome != null) {
           return entityManager
                   .createQuery("SELECT a FROM Projeto a WHERE LOWER(a.nome) LIKE :nome", Projeto.class) // JPQL
                   .setParameter("nome", "%" + nome.toLowerCase() + "%")
                   .getResultList();
        }
        return entityManager
                .createQuery("SELECT a FROM Projeto a", Projeto.class) // JPQL
                .getResultList();
    }

    @GET
    @Path("{id}")
    public Response getProjeto(@PathParam("id") int id) {
        Projeto projeto = findProjeto(id);        
        if (projeto == null) {
            return projetoNotFoundResponse();
        }
        return Response.ok(projeto).build();
    }

    @POST
    public Response addProjeto(Projeto projeto) {
        entityManager.persist(projeto);
        return Response.status(Status.CREATED).entity(projeto).build();
    }

    @DELETE
    @Path("{id}")
    public Response remove(@PathParam("id") int id) {
        Projeto projeto = findProjeto(id);
        if(projeto == null) {
            return projetoNotFoundResponse();
        } 
        entityManager.remove(projeto);
        return Response.noContent().build();
    }
    
    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") int id, Projeto projetoAtualizado) {
        Projeto projeto = findProjeto(id);
        if(projeto == null) {
            return projetoNotFoundResponse();
        }
        projetoAtualizado.setId(id);
        entityManager.merge(projetoAtualizado);
        return Response.ok(projeto).build();
    }
    
    public Projeto findProjeto(int id) {
        return entityManager.find(Projeto.class, id);
    }
    
    public Response projetoNotFoundResponse() {      
        return Response
                    .status(Status.NOT_FOUND)
                    .entity("Projeto não encontrado para exclusão")
                    .build();
    }
}

