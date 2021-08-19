package com.example.demo.dto;

import java.io.Serializable;
import java.util.List;

import org.modelmapper.ModelMapper;

import com.example.demo.models.Agencia;
import com.example.demo.models.Empregado;
import com.example.demo.models.InstituicaoFinanceira;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AgenciaDTO implements Serializable{

	private static final long serialVersionUID = 2795404929733238241L;

	private Integer id;
	
	private String nome;
	
	private String nomeInstituicao;
	
	@JsonIgnore
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private InstituicaoFinanceira instituicao;
	
	private List<Empregado> empregados;
	
	public String getNomeInstituicao() {
		return instituicao.getName();
	}
	
	public AgenciaDTO(Integer id, String nome, InstituicaoFinanceira instituicao, List<Empregado> list) {
		
		this.id = id;
		this.nome = nome;
		this.instituicao = instituicao;
		this.empregados = list;
	}
	
	
	public AgenciaDTO create(Agencia agencia) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(agencia, AgenciaDTO.class);
	}

}
