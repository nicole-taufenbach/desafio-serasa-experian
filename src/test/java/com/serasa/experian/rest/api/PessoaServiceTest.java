package com.serasa.experian.rest.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.collections4.IterableUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.serasa.experian.rest.api.model.Pessoa;
import com.serasa.experian.rest.api.model.ScoreDescricao;
import com.serasa.experian.rest.api.repository.PessoaRepository;
import com.serasa.experian.rest.api.service.PessoaService;

@SpringBootTest
public class PessoaServiceTest {
	
	private static int ID = 1;

    @Autowired
    private PessoaService service;
	
	@MockBean
    private PessoaRepository repository;
    
	private Pessoa createPessoa() {
		Pessoa pessoa = new Pessoa("Fulano de Tal", "99 99999-9999", 11, "São Paulo", "SP", 900);
		return pessoa;
	}
	
    @Test
    public void addPessoaTest() {
    	Pessoa pessoa = createPessoa();
    	Mockito.when(repository.save(pessoa)).thenReturn(pessoa);
    	assertEquals(pessoa, service.addPessoa(pessoa));
    }

    @Test
    public void addPessoaNullTest() {
    	Pessoa pessoa = null;
    	Mockito.when(repository.save(pessoa)).thenReturn(pessoa);
    	assertEquals(pessoa, service.addPessoa(pessoa));
    }
	
    @Test
    public void getPessoasTest() {
       Mockito.when(repository.findAll()).thenReturn(Stream
    		   .of(new Pessoa("Fulano de Tal", "99 99999-9999", 11, "São Paulo", "SP", 900),
    			   new Pessoa("Ciclano de Tal", "99 99999-9999", 12, "Blumenau", "SC", 600),
	    		   new Pessoa("Beltrano de Tal", "99 99999-9999", 13, "São Carlos", "SP", 300))
    		   .collect(Collectors.toList()));
       assertEquals(3, IterableUtils.size(service.getPessoas()));
    }
    
    
    @Test
    public void getPessoaByIdTest() {
    	Pessoa pessoa = createPessoa();
		Mockito.when(repository.findById(ID)).thenReturn(Optional.of(pessoa));
		assertEquals(pessoa, service.getPessoaById(ID));
    }
    
    @Test
    public void setarScoreDescricaoTest() {
    	Pessoa pessoaMock = Mockito.mock(Pessoa.class);
    	Pessoa pessoa = new Pessoa();

    	pessoa.setScore(200);
    	service.setarScoreDescricao(pessoa);
    	Mockito.when(pessoaMock.getScoreDescricao()).thenReturn(ScoreDescricao.INSUFICIENTE.toString());
    	assertEquals(pessoaMock.getScoreDescricao(), pessoa.getScoreDescricao());
    	
    	pessoa.setScore(500);
    	service.setarScoreDescricao(pessoa);
    	Mockito.when(pessoaMock.getScoreDescricao()).thenReturn(ScoreDescricao.INACEITAVEL.toString());
    	assertEquals(pessoaMock.getScoreDescricao(), pessoa.getScoreDescricao());
    	
    	pessoa.setScore(700);
    	service.setarScoreDescricao(pessoa);
    	Mockito.when(pessoaMock.getScoreDescricao()).thenReturn(ScoreDescricao.ACEITAVEL.toString());
    	assertEquals(pessoaMock.getScoreDescricao(), pessoa.getScoreDescricao());
    	
    	pessoa.setScore(900);
    	service.setarScoreDescricao(pessoa);
    	Mockito.when(pessoaMock.getScoreDescricao()).thenReturn(ScoreDescricao.RECOMENDAVEL.toString());
		assertEquals(pessoaMock.getScoreDescricao(), pessoa.getScoreDescricao());
    }
    
    @Test
    public void setarScoreDescricaoExceptionTest() {
    	Pessoa pessoa = new Pessoa();
    	ResponseStatusException thrown = new ResponseStatusException(HttpStatus.BAD_REQUEST);
    	
    	pessoa.setScore(-100);
    	thrown = Assertions.assertThrows(ResponseStatusException.class, () -> {
    		service.setarScoreDescricao(pessoa);
    	});
    	Assertions.assertEquals(HttpStatus.BAD_REQUEST, thrown.getStatus());
    	
    	pessoa.setScore(1000);
    	thrown = Assertions.assertThrows(ResponseStatusException.class, () -> {
    		service.setarScoreDescricao(pessoa);
    	});
    	Assertions.assertEquals(HttpStatus.BAD_REQUEST, thrown.getStatus());
    }
    
}
