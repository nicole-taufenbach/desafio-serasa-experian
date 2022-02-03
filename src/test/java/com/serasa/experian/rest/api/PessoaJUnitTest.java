package com.serasa.experian.rest.api;

import java.util.Optional;

import org.apache.commons.collections4.IterableUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.serasa.experian.rest.api.model.Pessoa;
import com.serasa.experian.rest.api.service.PessoaService;

@SpringBootTest
class PessoaJUnitTest {

	@Autowired
	private PessoaService service;
	
	@Order(1)
	@Rollback(value = false)
	@Test
	void salvarTest() {
		Pessoa pessoa = new Pessoa("Fulano de Tal", "99 99999-9999", 99, "Cidade de Fulano", "XX", 1000);
		service.addPessoa(pessoa);
		Assertions.assertThat(pessoa.getId()).isGreaterThan(0);
	}
	
	@Test
	void consultarTest() {
		Optional<Pessoa> findById = service.getPessoaById(1);
		Assertions.assertThat(findById.isPresent());
	}
	
	@Test
	void consultarTodosTest() {
        Iterable<Pessoa> pessoas = service.getPessoas();
        Assertions.assertThat(IterableUtils.size(pessoas)).isGreaterThan(0);
	}

}
