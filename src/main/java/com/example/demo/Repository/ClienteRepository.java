package com.example.demo.Repository;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.example.demo.models.Cliente;

//Cria um bean do tipo repositório
@Repository
//Classe responsável por consumir o banco
public class ClienteRepository {

	// Método responsável por resgatar as informações do banco
	public String resgatarNome(Integer matricula) {
		// Criou um hashMap que irá simular o banco
		HashMap<Integer, String> hashClientes = new HashMap<Integer, String>();
		// Preenche o hashmap/banco
		hashClientes.put(1, "Duan");
		hashClientes.put(2, "Tayanne");
		hashClientes.put(3, "Henrique");
		// REsgata o hashMap a partir da matricula
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
}
