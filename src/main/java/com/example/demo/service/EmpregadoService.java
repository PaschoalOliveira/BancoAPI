package com.example.demo.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.EmpregadoDTO;
import com.example.demo.models.Empregado;
import com.example.demo.repository.EmpregadoRepository;

@Service
public class EmpregadoService {

	@Autowired
	EmpregadoRepository empregadoRepository;
	
	public ArrayList<EmpregadoDTO> findAll(){
		
		ArrayList<Empregado> empregados = (ArrayList<Empregado>) empregadoRepository.findAll();
		ArrayList<EmpregadoDTO> empregadosDTO = new ArrayList<EmpregadoDTO>();
		for(Empregado empr: empregados) {
			EmpregadoDTO emprDTo = new EmpregadoDTO();
			emprDTo.createWithoutModelMapper(empr);
			empregadosDTO.add(emprDTo);
		}
		return empregadosDTO;
	}
}
