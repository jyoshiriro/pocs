package br.com.trescon.pocs.restassured.controllers;

import java.util.List;

import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.trescon.pocs.restassured.domains.Estado;

@Path("/estados")
public class EstadoController extends AbstractController {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Estado> listar() {
		return getEm().createQuery("from Estado").getResultList();
	}
	
	@GET
	@Path("/uf/{uf}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Estado> findByUf(@PathParam("uf") String uf) {
		return getEm().createQuery("from Estado where uf=?").setParameter(1, uf.toUpperCase()).getResultList();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String novo(Estado estado) {
		EntityManager em = getEm();
		em.persist(estado);
		commit(em);
		return "ok";
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void atualizar(Estado estado) {
		EntityManager em = getEm();
		em.merge(estado);
		commit(em);
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void excluir(@PathParam("id") Long id) {
		EntityManager em = getEm();
		em.createQuery("delete from Estado where id=?").setParameter(1, id).executeUpdate();
		commit(em);
	}
}
