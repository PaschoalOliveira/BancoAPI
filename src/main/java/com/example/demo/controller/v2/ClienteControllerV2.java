package com.example.demo.controller.v2;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ClienteDTO;
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
	public Page<Cliente> pesquisarTodos(
		@RequestParam(required = false, defaultValue = "0") Integer pagina, 
		@RequestParam(required = false , defaultValue = "5") Integer linhas, 
	    @RequestParam(required = false, defaultValue = "cpf") String ordenandoPor, 
		@RequestParam(required = false, defaultValue = "ASC") String direcaoOrderBy
		){
		return clienteService.findAll(pagina, linhas, ordenandoPor, direcaoOrderBy);
	}
	
	@GetMapping
	@RequestMapping("/filtros")
	public ArrayList<Cliente> pesquisarTodos(
			@Nullable @RequestParam Integer cpf,
			@Nullable @RequestParam String nome,
			@Nullable @RequestParam Character sexo,
			@Nullable @RequestParam String telefone){
		
		return clienteService.findAll(cpf, nome, sexo, telefone);
	}
	
	//Cria uma nova rota para consulta do findById pelo JPA
	@GetMapping("/{cpf}")
	public ResponseEntity<ClienteDTO> pesquisaPorId(@PathVariable Integer cpf) {
		
		return new ResponseEntity<ClienteDTO>(clienteService.findByID(cpf), HttpStatus.OK);
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
		
	//M??todo com o verbo POST que ser?? respons??vel por salvar o cliente 
	//utilizando JPA
	@PostMapping
	public void inserirCliente(@RequestBody Cliente cliente) {
		
		clienteService.save(cliente);
	}

	//M??todo respons??vel por atualizar completamente meu objeto
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
		
		//Realiza a chamada ?? camada service 
		String nome = clienteService.pesquisarNomeService(matricula);
		return nome;
	}
	
	//Criando nova rota para pesquisa de Clientes
	@GetMapping("/pesquisarCliente")
	public Cliente pesquisarCliente(@RequestParam("cpf") Integer cpf) {
			
		//Realiza a chamada ?? camada service 
		Cliente cliente = clienteService.pesquisarClienteService(cpf);
		return cliente;
	}
	
	//Criando nova rota para pesquisa de Clientes
	@GetMapping("/{sexo}/pesquisarClientePorSexo/{idade}")
	public ArrayList<Cliente> pesquisarClientePorSexo(@PathVariable("sexo") Character sexo,
													  @PathVariable("idade") String idade,
													  @RequestParam("cpf") String cpf) {
			
		//Realiza a chamada ?? camada service 
		ArrayList<Cliente> arrayCliente = clienteService.pesquisarClientePorSexo(sexo);
		return arrayCliente;
	}
	
	
}
