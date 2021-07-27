package com.example.demo.controller.v1;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.InstituicaoFinanceira;
import com.example.demo.service.InstituicaoFinanceiraService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/v1/instituicoes")
public class InstituicaoFinanceiraControllerV1 {

	@Autowired
	InstituicaoFinanceiraService instituicaoService;
	
	@ApiOperation(value = "Retorna uma lista de instituições")
	@GetMapping
	public ArrayList<InstituicaoFinanceira> buscarTodasAsInstituicoes() {
		return instituicaoService.findAll();
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<InstituicaoFinanceira> buscarPorId(@PathVariable Integer codigo) {
		
		InstituicaoFinanceira instituicaoRetorno = instituicaoService.findById(codigo);
		
		return new ResponseEntity(instituicaoRetorno,HttpStatus.OK);
	}
	
	//rota que pesquisa por anme e retorna um responseEntity OK com a lista de pesquisa
	@GetMapping("/name")
	public ResponseEntity<ArrayList<InstituicaoFinanceira>> buscarPorNome(@RequestParam String nome) {
		
		ArrayList<InstituicaoFinanceira> listaInstituicaoRetorno = instituicaoService.findByName(nome);
		
		return new ResponseEntity(listaInstituicaoRetorno,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity inserir(@RequestBody InstituicaoFinanceira instituicao) {
		instituicaoService.save(instituicao);
		return new ResponseEntity(HttpStatus.CREATED);
	}
	
	@DeleteMapping("{codigo}")
	public ResponseEntity deletar(@PathVariable Integer codigo) {
		return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).body("URI modificada para http://localhost:8090/delete/{codigo}");
		
		//return new ResponseEntity(HttpStatus.MOVED_PERMANENTLY);
	}
	
	@DeleteMapping("/delete/{codigo}")
	public void deletarNovo(@PathVariable Integer codigo) {
		instituicaoService.delete(codigo);
	}
	
	//Anotações para indicar as respostas do meu endpoint
	@ApiResponses(value = {
			@ApiResponse(code=200,message="Instituição atualizada com sucesso"),
			@ApiResponse(code=422,message="Não há entidade com o código especificado")
	})
	@PutMapping
	public ResponseEntity atualizar(@RequestBody InstituicaoFinanceira instituicao) {
		try {
			instituicaoService.update(instituicao);			
			return new ResponseEntity(HttpStatus.OK);
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Não há Instituição para o código informado");
		}
		
	}
}
