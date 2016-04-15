package br.com.trescon.pocs.restassured.controllers;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import br.com.trescon.pocs.restassured.domains.Cidade;

@Path("/cidades")
public class CidadeController extends AbstractController {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Cidade> listar() {
		return getEm().createQuery("from Cidade").getResultList();
	}
	
	@GET
	@Path("/pesquisar")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Cidade> pesquisar(@QueryParam("uf") String uf, @QueryParam("palavras") String palavras) {
		return getEm().createQuery("from Cidade where palavrasChave like ? and estado.uf=?")
				.setParameter(1, "%"+palavras+"%")
				.setParameter(2, uf.toUpperCase())
				.getResultList();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Cidade> pesquisar(PalavrasEstado palavrasEstado) {
		System.out.println(palavrasEstado);
		return getEm().createQuery("from Cidade where palavrasChave like ? and estado.uf=?")
				.setParameter(1, "%"+palavrasEstado.getPalavras()+"%")
				.setParameter(2, palavrasEstado.getUf())
				.getResultList();
	}
}

class PalavrasEstado {
	String palavras;
	String uf;
	public String getPalavras() {
		return palavras;
	}
	public void setPalavras(String palavras) {
		this.palavras = palavras;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	@Override
	public String toString() {
		return "PalavrasEstado [palavras=" + palavras + ", uf=" + uf + "]";
	}
}
