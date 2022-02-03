package com.serasa.experian.rest.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.collections4.IterableUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.serasa.experian.rest.api.model.Pessoa;
import com.serasa.experian.rest.api.repository.PessoaRepository;
import com.serasa.experian.rest.api.service.PessoaService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PessoaServiceTest {

    @Autowired
    private PessoaService service;
	
	@MockBean
    private PessoaRepository repository;
    
    @Test
    public void addPessoaTest() {
    	Pessoa pessoa = new Pessoa("Fulano de Tal", "99 99999-9999", 11, "São Paulo", "SP", 900);
    	Mockito.when(repository.save(pessoa)).thenReturn(pessoa);
    	assertEquals(pessoa, service.addPessoa(pessoa));
    }
	
    @Test
    public void getPessoasTest() {
       Mockito.when(repository.findAll()).thenReturn(Stream
    		   .of(new Pessoa("Fulano de Tal", "99 99999-9999", 11, "São Paulo", "SP", 900),
    			   new Pessoa("Ciclano de Tal", "99 99999-9999", 12, "Blumenau", "SC", 600),
	    		   new Pessoa("Beltrano de Tal", "99 99999-9999", 13, "São Carlos", "SP", 300),
	    		   new Pessoa("Anoful de Tal", "99 99999-9999", 14, "Brasília", "DF", 100))
    		   .collect(Collectors.toList()));
       assertEquals(4, IterableUtils.size(service.getPessoas()));
    }
    
    @Test
    public void getPessoaByIdTest() {
    	Integer id = 1;
    	Pessoa pessoa = new Pessoa("Fulano de Tal", "99 99999-9999", 11, "São Paulo", "SP", 900);
		Mockito.when(repository.findById(id)).thenReturn(Optional.of(pessoa));
		assertEquals(Optional.of(pessoa), service.getPessoaById(id));
    }

    @Test
    public void setarScoreDescricaoTest() {
    	Pessoa pessoaMock1 = Mockito.mock(Pessoa.class);
    	pessoaMock1.setScore(200);
    	service.setarScoreDescricao(pessoaMock1);
    	
    	Pessoa pessoa1 = new Pessoa();
    	pessoa1.setScore(200);
    	service.setarScoreDescricao(pessoa1);
    	
    	Pessoa pessoaMock2 = Mockito.mock(Pessoa.class);
    	pessoaMock2.setScore(500);
    	service.setarScoreDescricao(pessoaMock2);
    	
    	Pessoa pessoaMock3 = Mockito.mock(Pessoa.class);
    	pessoaMock3.setScore(900);
    	service.setarScoreDescricao(pessoaMock3);
    	
    	Pessoa pessoaMock4 = Mockito.mock(Pessoa.class);
    	pessoaMock4.setScore(1000);
    	service.setarScoreDescricao(pessoaMock4);
    	
    	Mockito.when(pessoaMock1.getScoreDescricao()).thenReturn("Insuficiente");
    	Mockito.when(pessoaMock2.getScoreDescricao()).thenReturn("Inaceitável");
    	Mockito.when(pessoaMock3.getScoreDescricao()).thenReturn("Aceitável");
    	Mockito.when(pessoaMock4.getScoreDescricao()).thenReturn("Recomendável");
    	
		assertEquals(pessoaMock1.getScoreDescricao(), pessoa1.getScoreDescricao());
		assertEquals(pessoaMock2.getScoreDescricao(), "Inaceitável");
		assertEquals(pessoaMock3.getScoreDescricao(), "Aceitável");
		assertEquals(pessoaMock4.getScoreDescricao(), "Recomendável");
    }
    
}
