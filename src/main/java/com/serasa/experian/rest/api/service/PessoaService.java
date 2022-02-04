package com.serasa.experian.rest.api.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.serasa.experian.rest.api.controller.PessoaController;
import com.serasa.experian.rest.api.model.Pessoa;
import com.serasa.experian.rest.api.model.ScoreDescricao;
import com.serasa.experian.rest.api.repository.PessoaRepository;

/**
 * Manipula a lógica da aplicação com os dados recebidos do {@link PessoaController}. 
 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
 */
@Service
public class PessoaService {
	
	@Autowired
	PessoaRepository repository;
	
	public Pessoa addPessoa(Pessoa pessoa) {
		setarScoreDescricao(pessoa);
		return repository.save(pessoa);
	}
	
	public Pessoa getPessoaById(Integer id) {
		Pessoa pessoa = null;
		Optional<Pessoa> optional = repository.findById(id);
		if (optional != null) {
			pessoa = optional.get();
		}
		return pessoa;
	}
	
	public List<Pessoa> getPessoas() {
		List<Pessoa> result = new ArrayList<Pessoa>();
		Iterable<Pessoa> pessoas = repository.findAll();
		pessoas.forEach(result::add);
		return result;
	}
	
	public void setarScoreDescricao(Pessoa pessoa) {
		if (pessoa != null) {
			int score = pessoa.getScore();
			ScoreDescricao scoreDescricao = null;
			
			if (score >= 0 && score <= 1000) {
				if (score > 700) {
					scoreDescricao = ScoreDescricao.RECOMENDAVEL;
				} else if (score > 500) {
					scoreDescricao = ScoreDescricao.ACEITAVEL;
				} else if (score > 200) {
					scoreDescricao = ScoreDescricao.INACEITAVEL;
				} else {
					scoreDescricao = ScoreDescricao.INSUFICIENTE;
				}
			} else throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O score inserido deve ser entre 0 e 1000.");
			
			pessoa.setScoreDescricao(scoreDescricao.toString());
		}
	}
	
}
