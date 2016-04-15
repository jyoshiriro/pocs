package br.com.trescon.pocs.restassured.tests;

import static org.junit.Assert.*;

import org.hibernate.jpa.HibernatePersistenceProvider;

import static com.jayway.restassured.RestAssured.*;
import static com.jayway.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.jayway.restassured.RestAssured;

public class EstadosTest {
	
	// valor de "uf" usado em vários testes
	String uf = "PA";
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
//		se omitida essa linha, o contexto e qualquer parte "repetida" terá que estar em todos os testes
		RestAssured.basePath = "/piloto1/rest/estados";
		
// 		não precisa dessa linha se o host for localhost e a porta for 8080
//		RestAssured.baseURI = "http://localhost:8080";
	}

	@Test
	public void testPesquisaUmaUFQuantidade() {
		// teste de uma requisição GET
		// esperando que exista apenas um atributo de nome "uf" no corpo da resposta 
		get("/uf/"+uf).then().body("uf.size()", equalTo(1));
	}
	
	@Test
	public void testPesquisaUmaUFValor() {
		// teste de uma requisição GET
		// esperando que o valor do primeiro atributo de nome "uf" seja o valor de uf 
		get("/uf/"+uf).then().body("uf[0]", equalTo(uf));
	}
	
	@Test
	public void testPesquisaUmaUFValorNaoExiste() {
		// teste de uma requisição GET
		// esperando que o valor do primeiro atributo de nome "uf" seja vazio 
		uf += "x"; 
		get("/uf/"+uf).then().body("uf[0]", equalTo(null));
	}
	
	@Test
	public void testPesquisaSemUF() {
		// teste de uma requisição GET
		// esperando que a resposta consista numa do tipo "método não permitido" 
		get("/uf/").then().statusCode(405);
	}
	
	@Test
	public void test404() {
		// teste de uma requisição GET
		// esperando que a resposta consista numa do tipo "não encontrado" 
		get("x").then().statusCode(404);
	}

}
