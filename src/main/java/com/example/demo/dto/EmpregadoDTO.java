package com.example.demo.dto;

import org.modelmapper.ModelMapper;

import com.example.demo.models.Agencia;
import com.example.demo.models.Empregado;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmpregadoDTO {

	private Integer cpf;
	
	private String nome;
	
	private String agenciaNome;
	
	private String nomeInstituicao;
	
	private Agencia agencia;
	
	public void createWithoutModelMapper(Empregado empr) {
		this.setCpf(empr.getCpf());
		this.setNome(empr.getNome());
		this.setAgenciaNome(empr.getAgencia() != null ? empr.getAgencia().getNome() : "");
		this.setNomeInstituicao(empr.getAgencia() != null && empr.getAgencia().getInstituicao() != null 
								? empr.getAgencia().getInstituicao().getName() : "");
	}
	
	public EmpregadoDTO create(Empregado empr) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(empr, EmpregadoDTO.class);
	}
}
