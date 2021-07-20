package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.Repository.ClienteRepository;
import com.example.demo.models.Cliente;

public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;
	
	/*
	public Cliente resgataClienteService(Integer matricula) {
		//Retorna o cliente
		Cliente cliente = clienteRepository.resgataClienteRepository(matricula);
		return cliente;
	}
	*/
}
