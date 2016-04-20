package br.com.trescon.pocs.pocmockito.tests;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;

public class MocksComListTest {

	@Test
	public void testVericarMetodoChamado1Vez() {

		// criação do mock
		List listaMock = Mockito.mock(List.class);

		listaMock.add("vai!");
		listaMock.clear();

		// verificando se o "add" com "vai!" e "clear" já foram invocados no
		// mock 1 (uma) vez
		Mockito.verify(listaMock).add("vai!");
		Mockito.verify(listaMock).clear();

	}

	@Test
	public void testStubMetodo() {

		// criação do mock
		List listaMock = Mockito.mock(List.class);

		// criando stubs...
		
		// get() com qualquer indice retornará "qualquer um"
		Mockito.when(listaMock.get(Mockito.anyInt())).thenReturn("qualquer um");
		
		// get(0) retornará "primeiro"
		Mockito.when(listaMock.get(0)).thenReturn("primeiro");
		
		// get(1) retornará lançará uma IndexOutOfBoundsException
		Mockito.when(listaMock.get(1)).thenThrow(new IndexOutOfBoundsException());
		
		List listaTeste = Arrays.asList("primeiro");
		
		assertTrue(listaMock.get(0).equals(listaTeste.get(0)));
		assertTrue(listaMock.get(16).equals("qualquer um"));
		
		try {
			listaMock.get(1);
			assertTrue(false);			
		} catch (IndexOutOfBoundsException e) {
			assertTrue(true);			
		}
	}

}
