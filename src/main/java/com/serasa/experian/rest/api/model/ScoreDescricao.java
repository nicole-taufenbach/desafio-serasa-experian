package com.serasa.experian.rest.api.model;

public enum ScoreDescricao {

	INSUFICIENTE("Insuficiente"),
	INACEITAVEL("Inaceitável"),
	ACEITAVEL("Aceitável"),
	RECOMENDAVEL("Recomendável");
	
	private String scoreDescricao;
	
	private ScoreDescricao(String scoreDescricao) {
		this.scoreDescricao = scoreDescricao;
	}
	
	@Override
	public String toString() {
		return scoreDescricao;
	}
	
}
