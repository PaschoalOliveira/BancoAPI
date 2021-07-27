package com.example.demo.controller.v2;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Cliente;
import com.example.demo.service.ClienteService;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

//Define um bean Controller
@RestController
@RequestMapping("/v2/clientes")
public class ClienteControllerV2 {
	
	@Autowired
	ClienteService clienteService;
	
	//Cria uma rota para consulta do FindALL do meu JPA
	@ApiResponses(value = {
			@ApiResponse(code=200,message="Retorna a lista de Clientes")
	})
	@GetMapping
	public ArrayList<Cliente> pesquisarTodos(){
		return clienteService.findAll();
	}
	
	//Cria uma nova rota para consulta do findById pelo JPA
	@GetMapping("{cpf}")
	public Cliente pesquisaPorId(@PathVariable Integer cpf) {
		
		return clienteService.findByID(cpf);
	}
	
	//Cria uma nova rota para consulta do cliente por sexo pelo JPA
	@GetMapping("/sexo/{sexo}")
	public ArrayList<Cliente> pesquisaPorSexo(@PathVariable Character sexo) {
		
		return clienteService.findBySexo(sexo);
	}
	
	//Cria uma nova rota para consulta do cliente por parte do telefone pelo JPA
	@GetMapping("/telefone/{numero}")
	public ArrayList<Cliente> pesquisaPorNumeroTelefone(@PathVariable String numero) {
		
		return clienteService.findByTelefone(numero);
	}
		
	//Método com o verbo POST que será responsável por salvar o cliente 
	//utilizando JPA
	@PostMapping
	public void inserirCliente(@RequestBody Cliente cliente) {
		
		clienteService.save(cliente);
	}

	//Método responsável por atualizar completamente meu objeto
	@PutMapping
	public void atualizarCliente(@RequestBody Cliente cliente) {
		
		clienteService.save(cliente);
	}
	
	//Deleta um Cliente a partir do cpf chamando o JPA
	@DeleteMapping("{cpf}")
	public void deletarCliente(@PathVariable Integer cpf) {
		
		clienteService.deleteById(cpf);
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
