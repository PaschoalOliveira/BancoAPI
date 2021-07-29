package com.example.demo.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Agencia;
import com.example.demo.repository.AgenciaRepository;

@Service
public class AgenciaService {

	@Autowired
	AgenciaRepository agenciaRepository;
	
	public ArrayList<Agencia> buscarTodos(){
		
		return (ArrayList<Agencia>) agenciaRepository.findAll();
	}
	
	public ArrayList<Agencia> buscarPorFiltros(String nome, Integer id){
		
		return (ArrayList<Agencia>) agenciaRepository.findByIdGreaterThan(id);
	}
}
