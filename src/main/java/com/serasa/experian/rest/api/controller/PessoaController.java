package com.serasa.experian.rest.api.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.serasa.experian.rest.api.dto.PessoaDTO;
import com.serasa.experian.rest.api.dto.PessoaGetPessoaByIdDTO;
import com.serasa.experian.rest.api.dto.PessoaGetPessoasDTO;
import com.serasa.experian.rest.api.dto.PessoaMapper;
import com.serasa.experian.rest.api.model.Pessoa;
import com.serasa.experian.rest.api.service.PessoaService;

/**
 * Controller responsável por cuidar das requisições da API.
 * Executa chamadas ao serviço para acessar a lógica da aplicação.
 * @author Nicole Taufenbach
 */
@RestController
@RequestMapping({"/pessoa"})
public class PessoaController {
	
	@Autowired
	private PessoaService service;
	
	@Autowired
	private PessoaMapper mapper;

	@PostMapping
	public ResponseEntity<PessoaDTO> salvar(@RequestBody Pessoa pessoa) {
		Pessoa save = service.addPessoa(pessoa);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(save.getId())
                .toUri();
        return ResponseEntity.created(location).body(mapper.pessoaToPessoaDTO(pessoa));
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<PessoaGetPessoaByIdDTO> consultar(@PathVariable("id") int id) {
		Pessoa pessoa = service.getPessoaById(id);
		if (pessoa != null) {
			return ResponseEntity.ok().body(mapper.pessoaToPessoaGetPessoaByIdDTO(pessoa));
		}
		return ResponseEntity.noContent().build();	
	}
	
	@GetMapping
	public ResponseEntity<List<PessoaGetPessoasDTO>> consultarTodos() {
		List<Pessoa> pessoas = service.getPessoas();
		if (!pessoas.isEmpty()) {
			return ResponseEntity.ok().body(mapper.pessoasToPessoaGetPessoasDTO(pessoas));
		}
		return ResponseEntity.noContent().build();
	}
}
