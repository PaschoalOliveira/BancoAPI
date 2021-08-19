package com.example.demo.controller.v1;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.EmpregadoDTO;
import com.example.demo.models.Empregado;
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
	
	@GetMapping("/{cpf}")
	public ResponseEntity<Empregado> buscarEmpregado(
			@PathVariable Integer cpf){
		
		return new ResponseEntity<Empregado>(empregadoService.findById(cpf),HttpStatus.OK);
	}
	
	@GetMapping("/buscarSalarios/{salario}")
	public ResponseEntity<List<EmpregadoDTO>> buscarSalariosMaiores(
			@PathVariable Double salario){
		
		return new ResponseEntity<List<EmpregadoDTO>>(empregadoService.buscarSalariosMaiores(salario),HttpStatus.OK);
	}
	
	@PostMapping
	public void saveEmpregado(
			@RequestParam("cpf") Integer cpf,
			@RequestParam("nome") String nome,
			@RequestParam("foto") MultipartFile foto) {
		
		empregadoService.salvarEmpregado(cpf, nome, foto);
	}
	
}
