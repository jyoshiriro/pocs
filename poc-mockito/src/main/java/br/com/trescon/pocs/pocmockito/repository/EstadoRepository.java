package br.com.trescon.pocs.pocmockito.repository;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.trescon.pocs.pocmockito.domains.Estado;

public class EstadoRepository {
	
	private EntityManager em;

	@Inject
	public EstadoRepository(EntityManager em) {
		this.em = em;
	}
	
	public boolean atualizarPopulacaoHM(Long id, int populacaoHomens, int populacaoMulheres) {
		Estado estado = em.find(Estado.class, id);
		if (populacaoHomens + populacaoMulheres != estado.getPopulacao()) {
			return false;
		}
		estado.setPopulacaoHomens(populacaoHomens);
		estado.setPopulacaoMulheres(populacaoMulheres);
		return true;
	}

}
