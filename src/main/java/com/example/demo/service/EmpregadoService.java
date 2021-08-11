package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.demo.dto.EmpregadoDTO;
import com.example.demo.models.Empregado;
import com.example.demo.repository.EmpregadoRepository;
import com.example.demo.specification.EmpregadoSpecification;

@Service
public class EmpregadoService {

	@Autowired
	EmpregadoRepository empregadoRepository;
	
	public Page<EmpregadoDTO> findAll(Integer cpf, String nome, String nomeAgencia, 
			Integer qtdItens, Integer numeroPagina, String direcaoOrdenacao, String campoOrdenacao){
	
		EmpregadoSpecification specification = new EmpregadoSpecification(cpf,nome,nomeAgencia);
		
		PageRequest pageRequest = PageRequest.of(numeroPagina, qtdItens, Direction.valueOf(direcaoOrdenacao), campoOrdenacao);
		
		Page<Empregado> pageEmpregados = empregadoRepository.findAll(specification, pageRequest);
		
		//Código de criação de lista sem lambda
		/* 
		List<EmpregadoDTO> listEmpregadoDto1 = new ArrayList<EmpregadoDTO>();
		for(Empregado empregado : pageEmpregados) {
			EmpregadoDTO emprDto = EmpregadoDTO.createWithoutModelMapper(empregado);
			listEmpregadoDto1.add(emprDto);
		}
		*/
	
		//Código de criação de lista com lambda
		List<EmpregadoDTO> listEmpregadoDto = pageEmpregados.stream()
				.map(empregado -> EmpregadoDTO.createWithoutModelMapper(empregado))
				.collect(Collectors.toList());
        
		
		int totalElements = (int) pageEmpregados.getTotalElements();
        Page<EmpregadoDTO> pageEmprDto = new PageImpl<EmpregadoDTO>(listEmpregadoDto, pageRequest, totalElements);
		
		return pageEmprDto;
        
		/*
		ArrayList<EmpregadoDTO> empregadosDTO = new ArrayList<EmpregadoDTO>();
		for(Empregado empr: empregados) {
			EmpregadoDTO emprDTo = new EmpregadoDTO();
			emprDTo.createWithoutModelMapper(empr);
			empregadosDTO.add(emprDTo);
		}
		return empregadosDTO;
		*/
	}
}
