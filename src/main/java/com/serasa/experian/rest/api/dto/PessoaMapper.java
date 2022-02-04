package com.serasa.experian.rest.api.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.serasa.experian.rest.api.model.Pessoa;

@Component
public class PessoaMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	public Pessoa pessoaDTOtoPessoa(PessoaDTO pessoaDTO) {
		return modelMapper.map(pessoaDTO, Pessoa.class);
	}

	public PessoaDTO pessoaToPessoaDTO(Pessoa pessoa) {
		return modelMapper.map(pessoa, PessoaDTO.class);
	}
	
	public PessoaGetPessoaByIdDTO pessoaToPessoaGetPessoaByIdDTO(Pessoa pessoa) {
		return modelMapper.map(pessoa, PessoaGetPessoaByIdDTO.class);
	}
	
	public PessoaGetPessoasDTO pessoaToGetPessoasDTO(Pessoa pessoa) {
		return modelMapper.map(pessoa, PessoaGetPessoasDTO.class);
	}
	
    public List<PessoaGetPessoasDTO> pessoasToPessoaGetPessoasDTO(List<Pessoa> pessoas) {
    	return pessoas.stream()
	    			  .map(this::pessoaToGetPessoasDTO)
	    			  .collect(Collectors.toList());
    }

}
