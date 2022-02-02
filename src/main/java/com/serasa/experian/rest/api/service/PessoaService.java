package com.serasa.experian.rest.api.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.serasa.experian.rest.api.model.Pessoa;
import com.serasa.experian.rest.api.repository.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	PessoaRepository repository;
	
	public Pessoa addPessoa(Pessoa pessoa) {
		setarScoreDescricao(pessoa);
		return repository.save(pessoa);
	}
	
	public Iterable<Pessoa> getPessoas() {
		Iterable<Pessoa> pessoas = repository.findAll();
		return pessoas;
	}
	
	public Optional<Pessoa> getPessoaById(Integer id) {
		return repository.findById(id);
	}
	
	public void setarScoreDescricao(Pessoa pessoa) {
		int score = pessoa.getScore();
		String scoreDescricao = null;
		
		if (score <= 1000 && score >= 0) {
			if (score <= 1000 && score >= 701) {
				scoreDescricao = "Recomendável";
			} else if (score <= 700 && score >= 501) {
				scoreDescricao = "Aceitável";
			} else if (score <= 500 && score >= 201) {
				scoreDescricao = "Inaceitável";
			} else {
				scoreDescricao = "Insuficiente";
			}
		} else throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		
		pessoa.setScoreDescricao(scoreDescricao);
	}
}
