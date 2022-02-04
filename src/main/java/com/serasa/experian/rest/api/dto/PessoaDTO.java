package com.serasa.experian.rest.api.dto;

import com.serasa.experian.rest.api.model.Pessoa;

/**
 * Representa um DTO de {@link Pessoa} com todas as informações. 
 * Utilizado para retornar todas as informações ao fazer um POST.
 * Auxilia na prevenção de injeções de SQL.
 * @author Nicole Taufenbach
 */
public class PessoaDTO {

	private String nome;
	private String telefone;
	private int idade;
	private String cidade;
	private String estado;
	private int score;
	private String scoreDescricao;
	
	public String getNome() {
		return nome;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public int getIdade() {
		return idade;
	}
	
	public String getCidade() {
		return cidade;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public void setIdade(int idade) {
		this.idade = idade;
	}
	
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public void setScore(int score) {
		this.score = score;
	}

	public String getScoreDescricao() {
		return scoreDescricao;
	}

	public void setScoreDescricao(String scoreDescricao) {
		this.scoreDescricao = scoreDescricao;
	}
	
}
