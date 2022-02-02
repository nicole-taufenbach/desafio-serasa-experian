package com.serasa.experian.rest.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.serasa.experian.rest.api.model.Pessoa;
import com.serasa.experian.rest.api.repository.PessoaRepository;

@RestController
public class PessoaController {
	
	@Autowired
	private PessoaRepository repository;
	
	@GetMapping(path = "/pessoa/{id}")
	public ResponseEntity<Pessoa> consultar(@PathVariable("id") Integer id) {
		return repository.findById(id)
				.map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.noContent().build());
	}
	
	@PostMapping(path = "/pessoa")
	public Pessoa salvar(@RequestBody Pessoa pessoa) {
		return repository.save(pessoa);
	}

}
