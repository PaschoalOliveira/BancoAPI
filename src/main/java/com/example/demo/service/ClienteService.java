package com.example.demo.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Cliente;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.ClienteRepository2;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	ClienteRepository2 clienteRepository2;
	
	//Método que irá consultar o repository que estende o JPA
	public ArrayList<Cliente> findAll(){
		ArrayList<Cliente> arrayRetorno = new ArrayList<Cliente>();
		//Faz o CAST de uma List para um ArrayList
		arrayRetorno = (ArrayList<Cliente>) clienteRepository2.findAll();
		return arrayRetorno;
	}
	
	//Método que realiza a consulta por ID no JPARepository
	public Cliente findByID(Integer cpf) {
		Optional<Cliente> optionalClienteRetorno;
		optionalClienteRetorno = clienteRepository2.findById(cpf);
		
		return optionalClienteRetorno.get();
	}
	
	public ArrayList<Cliente> findBySexo(Character sexo){
		ArrayList<Cliente> arrayRetorno = new ArrayList<Cliente>();
		//Faz o CAST de uma List para um ArrayList
		arrayRetorno = (ArrayList<Cliente>) clienteRepository2.findBySexo(sexo);
		return arrayRetorno;
	}
	
	public void save(Cliente cliente) {
		clienteRepository2.save(cliente);
	}
	
	//Método responsável por chamar o delete do JPA
	public void deleteById(Integer cpf) {
		clienteRepository2.deleteById(cpf);
	}
	
	
	//método responsável pelas regras de negócio realitavas a operação
	//e por se conectar com a camada REpository
	public String pesquisarNomeService(Integer matricula) {
		
		String nome = clienteRepository.resgatarNome(matricula);
		return nome;
	}
	
	//Responsavel por executar alguma operação sobre clientes
	//e resgatar os dados no Repository
	public Cliente pesquisarClienteService(Integer cpf) {
		
		Cliente cliente = clienteRepository.resgatarClienteRepository(cpf);
		
		return cliente;
	}
	
	//Responsavel por executar alguma operação sobre clientes
		//e resgatar os dados no Repository
	public ArrayList<Cliente> pesquisarClientePorSexo(Character sexo) {
		ArrayList<Cliente> arrayClientes = clienteRepository.resgatarClientePorSexoRepository(sexo);
		return arrayClientes;
	}
	
}
