package com.example.demo.dto;

import java.io.Serializable;

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
public class EmpregadoDTO implements Serializable{

	private static final long serialVersionUID = 5211838357113483763L;

	private Integer cpf;
	
	private String nome;
	
	private String agenciaNome;
	
	private String nomeInstituicao;
	
	private Double salario;
	
	private Agencia agencia;
	
	public static EmpregadoDTO createWithoutModelMapper(Empregado empr) {
		EmpregadoDTO emprDto = new EmpregadoDTO();
		emprDto.setCpf(empr.getCpf());
		emprDto.setNome(empr.getNome());
		emprDto.setAgenciaNome(empr.getAgencia() != null ? empr.getAgencia().getNome() : "");
		emprDto.setNomeInstituicao(empr.getAgencia() != null && empr.getAgencia().getInstituicao() != null 
								? empr.getAgencia().getInstituicao().getName() : "");
		
		emprDto.setSalario(empr.getSalario());
		return emprDto;
	}
	
	public EmpregadoDTO create(Empregado empr) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(empr, EmpregadoDTO.class);
	}
}
