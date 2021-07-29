package com.example.demo.repository;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.example.demo.models.Cliente;

//Cria um bean do tipo repositório
@Repository
//Classe responsável por consumir o banco
@Deprecated
public class ClienteRepository {

    // Método responsável por resgatar as informações do banco
	public String resgatarNome(Integer matricula) {
		// Criou um hashMap que irá simular o banco
		HashMap<Integer, String> hashClientes = new HashMap<Integer, String>();
		// Preenche o hashmap/banco
		hashClientes.put(1, "Duan");
		hashClientes.put(2, "Tayanne");
		hashClientes.put(3, "Henrique");
		// Resgata o hashMap a partir da matricula
		String nome = hashClientes.get(matricula);
		return nome;
	}

	// Método responsável por resgatar as informações do banco
	public Cliente resgatarClienteRepository(Integer cpf) {

		// Criou um hashMap que irá simular o banco
		HashMap<Integer, Cliente> hashClientes = new HashMap<Integer, Cliente>();

		// Preenche o hashmap/banco
		hashClientes.put(91872, new Cliente("Duan", 91872));
		hashClientes.put(91873, new Cliente("Tayanne", 91873));
		hashClientes.put(91874, new Cliente("Henrique", 91874));

		// Resgata o hashMap a partir da matricula
		Cliente cliente = hashClientes.get(cpf);
		return cliente;
	}
	
	
	// Método responsável por resgatar as informações do banco
	public ArrayList<Cliente> resgatarClientePorSexoRepository(Character sexo) {

		// Criou um array que irá simular o banco
		ArrayList<Cliente> arrayClientes = new ArrayList<Cliente>();

		// Preenche o array/banco
		arrayClientes.add(new Cliente("Luan", 91872, 'M'));
		arrayClientes.add(new Cliente("Fernanda", 91872, 'F'));
		arrayClientes.add(new Cliente("Willian", 91872, 'M'));
	
		// Resgata o hashMap a partir da matricula
		//Object[] retorno = 
		//		arrayClientes.stream().filter(p->p.getSexo().equals(sexo)).toArray();
		
		//Cria umsta de retorno com os clientes que possuem o sexo informado
		ArrayList<Cliente> listaRetorno = new ArrayList<Cliente>();
		for(Cliente cliente : arrayClientes) {
			if(cliente.getSexo().equals(sexo)) {
				listaRetorno.add(cliente);
			}
		}
		
		return listaRetorno;
	}
}
