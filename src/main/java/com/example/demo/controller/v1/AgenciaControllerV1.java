package com.example.demo.controller.v1;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Agencia;
import com.example.demo.service.AgenciaService;

@RestController
@RequestMapping("/v1/agencias")
public class AgenciaControllerV1 {

	@Autowired
	AgenciaService agenciaService;
	
	@GetMapping
	public ArrayList<Agencia> buscarTodos(){
		
		return agenciaService.buscarTodos();
	}
	
	@GetMapping("/filtros")
	public ArrayList<Agencia> buscarPorNome(
			@RequestParam String nome,
			@RequestParam Integer id
			){
		
		return agenciaService.buscarPorFiltros(nome, id);
	}
}
