package com.example.demo.controller.v1;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AgenciaDTO;
import com.example.demo.models.Agencia;
import com.example.demo.service.AgenciaService;

@RestController
@RequestMapping("/v1/agencias")
public class AgenciaControllerV1 {

	@Autowired
	AgenciaService agenciaService;
	
	//O requiredFalse indica que o campo não é obrigatório
	//O defaultValue estabelece um valor padrão quando o usuário não passa o parâmetro
	@GetMapping
	public ResponseEntity<Page<AgenciaDTO>> buscarTodos(
			@RequestParam(required = false, defaultValue = "0") Integer numeroPagina,
			@RequestParam(required = false, defaultValue = "2") Integer itensPorPagina,
			@RequestParam(required = false, defaultValue = "nome") String campoOrdenacao,
			@RequestParam(required = false, defaultValue = "ASC") String direcaoOrdenacao,
			@RequestParam(required = false) String nome,
			@RequestParam(required = false) Integer id){
		
		Page<AgenciaDTO> agencias =  agenciaService.buscarTodosComFiltros(numeroPagina,itensPorPagina, campoOrdenacao, direcaoOrdenacao,
				nome,id);
		
		return new ResponseEntity<Page<AgenciaDTO>>(agencias, HttpStatus.OK) ;
	}
	
	@GetMapping("/filtros")
	public ArrayList<Agencia> buscarPorNome(
			@RequestParam(required = false) String nome,
			@RequestParam(required = false) Integer id
			){
		
		return agenciaService.buscarPorFiltrosSpecification(nome, id);
	}
}
