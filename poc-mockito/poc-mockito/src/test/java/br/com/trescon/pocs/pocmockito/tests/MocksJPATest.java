package br.com.trescon.pocs.pocmockito.tests;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.trescon.pocs.pocmockito.domains.Estado;
import br.com.trescon.pocs.pocmockito.repository.EstadoRepository;

// para fazer Mocks de EntityManager, é necessário anotar a classe como abaixo
@RunWith(MockitoJUnitRunner.class)
public class MocksJPATest {
	
	// Mocks de EntityManager. O nome do objeto pode ser qualquer um, claro
	@Mock
	EntityManager em;

	@Test
	public void testPopulacoesHMEstado() {
		Long eid = 1L;
		
		// Estado "fake", apenas para este teste
		Estado e = new Estado();
		e.setId(eid);
		e.setPopulacao(2000);
		
		// Mudando o comportamento do "find" do EntityManager para "Estado.class" e "eid" como parâmetros
		Mockito.when(em.find(Estado.class, eid)).thenReturn(e);
		
		EstadoRepository rep = new EstadoRepository(em);
		
		assertFalse(rep.atualizarPopulacaoHM(eid, 200, 200));
		assertTrue(rep.atualizarPopulacaoHM(eid, 200, 1800));

	}

}
