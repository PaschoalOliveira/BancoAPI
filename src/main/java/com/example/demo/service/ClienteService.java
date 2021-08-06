package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ClienteDTO;
import com.example.demo.models.Cliente;
import com.example.demo.models.Conta;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.ClienteRepository2;
import com.example.demo.repository.InstituicaoFinanceiraRepository;
import com.example.demo.specification.ClienteSpecification;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	ClienteRepository2 clienteRepository2;
	
	@Autowired
	CryptoService cryptoService;
	
	//Método que irá consultar o repository que estende o JPA
	public Page<Cliente> findAll(Integer pagina, Integer linhasPorPagina, String orderBy, String direction){
		
		PageRequest pageRequest = PageRequest.of(pagina, linhasPorPagina, Sort.Direction.valueOf(direction), orderBy);
		
		return clienteRepository2.findAll(pageRequest);
	}
	
	public ArrayList<Cliente> findAll(Integer cpf, String nome, Character sexo, String telefone){
		ArrayList<Cliente> arrayRetorno = new ArrayList<Cliente>();
		
		ClienteSpecification clienteSpecification = new ClienteSpecification(cpf, nome, sexo, telefone);
		
		arrayRetorno = (ArrayList<Cliente>) clienteRepository2
				.findAll(clienteSpecification);
		return arrayRetorno;
	}
	
	//Método que realiza a consulta por ID no JPARepository
	public ClienteDTO findByID(Integer cpf) {
		Optional<Cliente> optionalClienteRetorno;
		optionalClienteRetorno = clienteRepository2.findById(cpf);
		
		Cliente cliente = optionalClienteRetorno.get();

		Double dCotacao = cryptoService.getBitCoinPrice();
		List<Conta> contas = cliente.getContas();
		
		/*
		for(Conta conta : cliente.getContas()) {
			conta.setSaldoBitCoin(conta.getSaldoBitCoin() * dCotacao);
		}
		*/
		//Executa o código acima usando arrow function
		contas.forEach(conta -> conta.setSaldoBitCoin(conta.getSaldoBitCoin() * dCotacao));
		
		//contas.stream().filter(conta->conta.getInstituicao().getName().equals("Itau"));
		
		 //= new ClienteDTO();
		
		 ClienteDTO clienteDto = ClienteDTO.createClienteDTOWithModelMapper(cliente);
		
		return clienteDto;
	}
	
	public ArrayList<Cliente> findBySexo(Character sexo){
		ArrayList<Cliente> arrayRetorno = new ArrayList<Cliente>();
		//Faz o CAST de uma List para um ArrayList
		arrayRetorno = (ArrayList<Cliente>) clienteRepository2.pesquisaPorSexo(sexo);
		
		return arrayRetorno;
	}
	
	//Método no service responsável por pesquisar por parte do telefone
	//Chama o repository e realiza o casting
	public ArrayList<Cliente> findByTelefone(String numero){
		ArrayList<Cliente> arrayRetorno = new ArrayList<Cliente>();
		//Faz o CAST de uma List para um ArrayList
		arrayRetorno = (ArrayList<Cliente>) clienteRepository2.pesquisaPorTelefone(numero);
		return arrayRetorno;
	}
	
	public Cliente save(Cliente cliente) {
		return clienteRepository2.save(cliente);
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
