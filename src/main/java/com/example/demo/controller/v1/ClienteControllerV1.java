package com.example.demo.controller.v1;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Cliente;
import com.example.demo.service.ClienteService;

//Define um bean Controller
@RestController
@RequestMapping("/v1/clientes")
public class ClienteControllerV1 {
	
	@Autowired
	ClienteService clienteService;
	
	@GetMapping
	public ArrayList<Cliente> pesquisarTodos() {
		
		return clienteService.findAll();
	}
	
	@PostMapping
	public void inserirCliente(@RequestBody Cliente cliente) {
		//chama o meu service que vai inserir cliente
		System.out.println("Inserindo " + cliente.toString());
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
	
	//Criando nova rota para pesquisa de Clientes
	@GetMapping("/{sexo}/pesquisarClientePorSexo/{idade}")
	public ArrayList<Cliente> pesquisarClientePorSexo(@PathVariable("sexo") Character sexo,
													  @PathVariable("idade") String idade,
													  @RequestParam("cpf") String cpf) {
			
		//Realiza a chamada à camada service 
		ArrayList<Cliente> arrayCliente = clienteService.pesquisarClientePorSexo(sexo);
		return arrayCliente;
	}
	
	
}
