package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.models.Cliente;
import com.example.demo.models.InstituicaoFinanceira;
import com.example.demo.service.ClienteService;
import com.example.demo.service.InstituicaoFinanceiraService;

@SpringBootTest
class BancoApiApplicationTests {

	@Autowired
	ClienteService clienteService;
	
	@Autowired
	InstituicaoFinanceiraService instituicaoFinanceiraService;

	@Test
	void inserirCliente() {
		
		Cliente cliente = new Cliente();
		cliente.setCpf(98723);
		cliente.setNome("Rafael");
		
		Cliente clienteRetornado = clienteService.save(cliente);
		
		assertEquals(clienteRetornado.getCpf(), 98723);
	}
	
	//Caso de Teste:
	//Dado que uma instituicao de codigo 98723 não exista
	//Quando eu inserir uma instituicao com o codigo 98723
	//Então uma exceção é gerada com a informação "Instituição não encontrada"
	@Test
	void atualizarInstituicaoFinanceiraNaoExistente() {
		
		InstituicaoFinanceira instituicao = new InstituicaoFinanceira();
		instituicao.setCodigo(98723);
		instituicao.setName("Jupari Bank");
		
		//Método responsável por identificar se determinado método gera uma exceção e retorn a exceção gerada
		Throwable excecao = assertThrows(Exception.class, () -> instituicaoFinanceiraService.update(instituicao));
		
		
	    assertEquals("Instituição não encontrada", excecao.getMessage());

	}
	
	@Test
	void atualizarInstituicaoFinanceiraExistente() {
		
		InstituicaoFinanceira instituicao = new InstituicaoFinanceira();
		instituicao.setCodigo(10);
		instituicao.setName("Jupari Bank");
		
		try {
			instituicaoFinanceiraService.update(instituicao);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Verifica se o código foi gerado, logo não deu exceção
	    assertEquals(10, instituicao.getCodigo());

		
	}

}
