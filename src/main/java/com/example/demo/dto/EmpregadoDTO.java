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
	
	public static EmpregadoDTO createWithoutModelMapper(Empregado empr) {
		EmpregadoDTO emprDto = new EmpregadoDTO();
		emprDto.setCpf(empr.getCpf());
		emprDto.setNome(empr.getNome());
		emprDto.setAgenciaNome(empr.getAgencia() != null ? empr.getAgencia().getNome() : "");
		emprDto.setNomeInstituicao(empr.getAgencia() != null && empr.getAgencia().getInstituicao() != null 
								? empr.getAgencia().getInstituicao().getName() : "");
		
		return emprDto;
	}
	
	public EmpregadoDTO create(Empregado empr) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(empr, EmpregadoDTO.class);
	}
}
