package com.serasa.experian.rest.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.serasa.experian.rest.api.model.Pessoa;

/**
 * Interface responsável por abstrair a persistência de dados. 
 * @author Nicole Taufenbach <ntaufenbach@hotmail.com>
 */
@Repository
public interface PessoaRepository extends CrudRepository<Pessoa, Integer> {

}
