package com.serasa.experian.rest.api.controller;

import java.net.URI;

import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.serasa.experian.rest.api.model.Pessoa;
import com.serasa.experian.rest.api.service.PessoaService;

@RestController
@RequestMapping({"/pessoa"})
public class PessoaController {
	
	@Autowired
	private PessoaService service;

	@PostMapping
	public ResponseEntity<Pessoa> salvar(@RequestBody Pessoa pessoa) {
		Pessoa save = service.addPessoa(pessoa);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(save.getId())
                .toUri();
        return ResponseEntity.created(location).body(save);
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Pessoa> consultar(@PathVariable("id") Integer id) {
		return service.getPessoaById(id)
				.map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.noContent().build());
	}
	
	@GetMapping
	public ResponseEntity<Iterable<Pessoa>> consultarTodos() {
		Iterable<Pessoa> pessoas = service.getPessoas();
		if (!IteratorUtils.isEmpty(pessoas.iterator())) {
			return ResponseEntity.ok().body(pessoas);
		}
		return ResponseEntity.noContent().build();
	}
}
