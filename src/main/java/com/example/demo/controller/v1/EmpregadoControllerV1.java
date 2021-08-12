package com.example.demo.controller.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.EmpregadoDTO;
import com.example.demo.service.EmpregadoService;

@RestController
@RequestMapping("/v1/empregados")
@CrossOrigin
public class EmpregadoControllerV1 {

	@Autowired
	EmpregadoService empregadoService;
	
	@GetMapping
	public ResponseEntity<Page<EmpregadoDTO>> buscarTodos(
			@RequestParam(required = false) Integer cpf,
			@RequestParam(required = false) String nome,
			@RequestParam(required = false) String nomeAgencia,
			@RequestParam(required = false, defaultValue = "5") Integer qtdItens,
			@RequestParam(required = false, defaultValue = "0") Integer numeroPagina,
			@RequestParam(required = false, defaultValue = "ASC") String direcaoOrdenacao,
			@RequestParam(required = false, defaultValue = "nome") String campoOrdenacao){
		
		return new ResponseEntity<Page<EmpregadoDTO>>(empregadoService.findAll(cpf, nome, nomeAgencia,
				qtdItens,numeroPagina,direcaoOrdenacao,campoOrdenacao),HttpStatus.OK);
	}
}
