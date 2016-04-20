package br.com.trescon.pocs.pocmockito.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="estado")
public class Estado {

	@Id
	@GeneratedValue
	private Long id;
	
	private String nome;
	
	private String uf;
	
	private Integer populacao;
	
	@Column(name="populacao_homens")
	private Integer populacaoHomens;
	
	@Column(name="populacao_mulheres")
	private Integer populacaoMulheres;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public Integer getPopulacao() {
		return populacao;
	}

	public void setPopulacao(Integer populacao) {
		this.populacao = populacao;
	}

	public Integer getPopulacaoHomens() {
		return populacaoHomens;
	}

	public void setPopulacaoHomens(Integer populacaoHomens) {
		this.populacaoHomens = populacaoHomens;
	}

	public Integer getPopulacaoMulheres() {
		return populacaoMulheres;
	}

	public void setPopulacaoMulheres(Integer populacaoMulheres) {
		this.populacaoMulheres = populacaoMulheres;
	}

}
