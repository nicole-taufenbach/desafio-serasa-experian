package com.serasa.experian.rest.service;

import java.util.Optional;

import org.apache.commons.collections4.IterableUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.serasa.experian.rest.api.model.Pessoa;
import com.serasa.experian.rest.api.repository.PessoaRepository;

@SpringBootTest
class PessoaTest {

	@Autowired
	private PessoaRepository repository;
	
	@Test
	@Order(1)
	@Rollback(value = false)
	void salvarTest() {
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("Fulano de Tal");
		pessoa.setTelefone("99 99999-9999");
		pessoa.setIdade(99);
		pessoa.setCidade("Cidade de Fulano");
		pessoa.setEstado("XX");
		pessoa.setScore(1000);
		
		repository.save(pessoa);
		
		Assertions.assertThat(pessoa.getId()).isGreaterThan(0);
	}
	
	@Test
	void consultarTest() {
		Optional<Pessoa> findById = repository.findById(1);
		Assertions.assertThat(findById.isPresent());
	}
	
	@Test 
	void consultarTodosTest() {
        Iterable<Pessoa> pessoas = repository.findAll();
        Assertions.assertThat(IterableUtils.size(pessoas)).isGreaterThan(0);
	}

}
