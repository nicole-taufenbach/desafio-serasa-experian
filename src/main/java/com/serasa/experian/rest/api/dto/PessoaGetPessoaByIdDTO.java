package com.serasa.experian.rest.api.dto;

import com.serasa.experian.rest.api.controller.PessoaController;
import com.serasa.experian.rest.api.model.Pessoa;

/**
 * Representa um DTO de {@link Pessoa}.
 * Contém apenas os atributos necessários para o retorno solicitado do 
 * <code>consultar(@PathVariable("id") int id)</code> no {@link PessoaController}
 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
 */
public class PessoaGetPessoaByIdDTO {

	private String nome;
    private String telefone;
    private int idade;
    private String scoreDescricao;
    
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public int getIdade() {
		return idade;
	}
	
	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getScoreDescricao() {
		return scoreDescricao;
	}

	public void setScoreDescricao(String scoreDescricao) {
		this.scoreDescricao = scoreDescricao;
	}
	
}
