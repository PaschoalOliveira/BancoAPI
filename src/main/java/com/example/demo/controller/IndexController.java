package com.example.demo.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Cliente;
import com.example.demo.service.CalculadoraService;
import com.example.demo.service2.ClienteService;

//Define um bean Controller
@RestController
@RequestMapping("/")
public class IndexController {
	
	@Autowired
	ClienteService clienteService;
	
	@GetMapping
	public String bemVindo() {
		return "Bem vindo !";
	}

	@GetMapping("/nome")
	public String bemVindoNome(@Nullable @RequestParam("name") String nome,
			@Nullable @RequestParam("cidade") String cidade,
			@Nullable @RequestParam("estado") String estado) {
		
		return "Bem Vindo " + nome + ". Como está o tempo aí em " + cidade + estado + "?";
	}
	
	//Criando nova rota
	@GetMapping("/pesquisarNome")
	public String pesquisarNome(@RequestParam("mat") Integer matricula) {
		
		//Realiza a chamada à camada service 
		String nome = clienteService.pesquisarNomeService(matricula);
		return nome;
	}
	
	//Criando nova rota para pesquisa de Clientes
	@GetMapping("/pesquisarCliente")
	public Cliente pesquisarCliente(@RequestParam("cpf") Integer cpf) {
			
		//Realiza a chamada à camada service 
		Cliente cliente = clienteService.pesquisarClienteService(cpf);
		return cliente;
	}
	
	/*
	@GetMapping("/pesquisaNome")
	public Cliente resgataNome(@RequestParam("matricula") Integer matricula) {
		
		//Cliente cliente = clienteService.resgataClienteService(matricula);
		//return cliente;
	}
	*/
	
	/*
	@GetMapping("/pesquisaTodos")
	public ArrayList<Cliente> resgataTodos(){
		//Lista de Clientes
		ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();

		//Preenche a lista
		listaClientes.add(new Cliente("Paschoal","1"));
		listaClientes.add(new Cliente("Moises","2"));
		listaClientes.add(new Cliente("Hamilton","3"));
		//Retorna a lista completa
		return listaClientes;
	}
	*/
	
	@GetMapping("/bemVindoGestor")
	public ArrayList<String> bemVindoGestor() {
		ArrayList<String> gestores = new ArrayList<String>();
		gestores.add("FErnando");
		gestores.add("Aguiar");
		gestores.add("Juliano");
		return gestores;
	}
	
	@PostMapping("/calcularMedia")
	public String calculaMedia(@RequestParam("num1")Integer num1, 
			@RequestParam("num2")Integer num2) {
		
		this.bemVindoGestor();
		CalculadoraService calculadoraService = new CalculadoraService();
		Integer soma = calculadoraService.somar(num1, num2);
		return calculadoraService.dividir(soma,2).toString();
	}
	
	
}
