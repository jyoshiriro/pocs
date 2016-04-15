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
import com.jayway.restassured.http.ContentType;

public class CidadesTest {
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
//		se omitida essa linha, o contexto e qualquer parte "repetida" terá que estar em todos os testes
		RestAssured.basePath = "/piloto1/rest/cidades";
		
// 		não precisa dessa linha se o host for localhost e a porta for 8080
//		RestAssured.baseURI = "http://localhost:8080";
	}

	@Test
	public void testListagem() {
		// teste de uma requisição GET
		// esperando que exista apenas um atributo de nome "id" no corpo da resposta
		get("/").then().body("id.size()", greaterThan(0));
	}
	
	@Test
	public void testParametroRequisicao() {
		// teste de uma requisição GET, passando 2 parâmetros de requisição
		// esperando que exista apenas um atributo de nome "id" no corpo da resposta
		// esperando que existam 2 atributos de nome "id" no corpo da resposta
		given()
		.parameter("uf", "PA")
		.parameter("palavras", "turismo")
		.get("/pesquisar").then().body("id.size()", equalTo(2));
	}
	
	@Test
	public void testPostCorpo() {
		// teste de uma requisição POST, passando um corpo de requisição no formato JSON
		// esperando que o primeiro atributo de nome "uf", dentro de um "estado" tenha o valor "PA"
		given().contentType(ContentType.JSON).body("{\"palavras\":\"turismo\", \"uf\":\"PA\"}")
		.post("/").then().body("estado.uf[0]", equalTo("PA"));
	}
	
	

}
