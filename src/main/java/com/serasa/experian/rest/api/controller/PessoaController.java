package com.serasa.experian.rest.api.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.serasa.experian.rest.api.model.Pessoa;
import com.serasa.experian.rest.api.repository.PessoaRepository;
import com.serasa.experian.rest.api.service.PessoaService;

@RestController
@RequestMapping({"/pessoa"})
public class PessoaController {
	
	private PessoaRepository repository;
	private PessoaService service;
	
	PessoaController(PessoaRepository repository, PessoaService service) {
		this.repository = repository;
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<Pessoa> salvar(@RequestBody Pessoa pessoa) {
		int score = pessoa.getScore();
		
		String scoreDescricao = null;
		if (score <= 1000 && score >= 0) {
			return ResponseEntity.badRequest().body(null);
		} else if (score > 1000 || score < 701) {
			scoreDescricao = "Recomendável";
		} else if (score > 700 || score < 501) {
			scoreDescricao = "Aceitável";
		} else if (score > 500 || score < 201) {
			scoreDescricao = "Inaceitável";
		} else {
			scoreDescricao = "Insuficiente";
		}
		pessoa.setScoreDescricao(scoreDescricao);
		
		Pessoa save = repository.save(pessoa);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(save.getId())
                .toUri();
        return ResponseEntity.created(location).body(save);
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Pessoa> consultar(@PathVariable("id") Integer id) {
		return repository.findById(id)
				.map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.noContent().build());
	}
	
	@GetMapping
	public ResponseEntity<Iterable<Pessoa>> consultarTodos() {
		Iterable<Pessoa> findAll = repository.findAll();
		if (findAll.iterator().hasNext() == true) {
			return ResponseEntity.ok().body(findAll);
		}
		return ResponseEntity.noContent().build();
	}
}
