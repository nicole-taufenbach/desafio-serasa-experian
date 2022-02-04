package com.serasa.experian.rest.api.dto;

import com.serasa.experian.rest.api.controller.PessoaController;
import com.serasa.experian.rest.api.model.Pessoa;

/**
 * Representa um DTO de {@link Pessoa}.
 * Contém apenas os atributos necessários para o retorno solicitado do 
 * <code>consultarTodos()</code> no {@link PessoaController}
 * @author Nicole Taufenbach
 */
public class PessoaGetPessoasDTO {

	private String nome;
	private String cidade;
	private String estado;
	private String scoreDescricao;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getScoreDescricao() {
		return scoreDescricao;
	}

	public void setScoreDescricao(String scoreDescricao) {
		this.scoreDescricao = scoreDescricao;
	}

}
