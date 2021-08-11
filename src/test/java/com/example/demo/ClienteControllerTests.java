package com.example.demo;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.example.demo.dto.ClienteDTO;

//@SpringBootTest(classes=BancoApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ClienteControllerTests {

	@Autowired
	protected TestRestTemplate rest;
	
	public ResponseEntity<ClienteDTO> getObjectClienteDTO(String url) {
		return rest.exchange(url, 
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<ClienteDTO>() {
				});
	}
	
	//DADO que eu tenho um  cliente com o id 98723
	//QUANDO buscar por esse id
	//ENTÃO deve me retornar um cliente com o nome não nulo
	//@Test
	public void testaRetornoCliente() {
		ClienteDTO clienteDTO =  this.getObjectClienteDTO("/v2/clientes/98723").getBody();
		assertNotNull(clienteDTO.getNome());
	}
}
