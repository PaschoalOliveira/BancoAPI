package com.example.demo;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import com.example.demo.dto.ClienteDTO;
import com.example.demo.models.Cliente;
import com.example.demo.models.InstituicaoFinanceira;
import com.example.demo.service.ClienteService;
import com.example.demo.service.CryptoService;
import com.example.demo.service.InstituicaoFinanceiraService;

@SpringBootTest
class BancoApiApplicationTests {

	@Autowired
	ClienteService clienteService;
	
	@Autowired
	InstituicaoFinanceiraService instituicaoFinanceiraService;
	
	
	@MockBean
	CryptoService cryptoService;

	@BeforeEach
	public void onSetUp() throws Exception {
	    // Inicializa os mocks
	    MockitoAnnotations.initMocks(this);
	    // Modificação do comportamento mockando o método
	    when(cryptoService.getBitCoinPrice()).thenReturn(Double.valueOf(3.15));   
	}
	
	
	
	@Test
	//DADO QUE eu tenha um cpf existente no banco
	//QUANDO eu pesquisar um cliente
	//ENTÃO deve me retornar um cliente com a informação de nome preenchida
	void verificaResgateCliente() {
		ClienteDTO clienteDto = clienteService.findByID(123457);
		assertNotNull(clienteDto.getNome());
	}
	
	@Test
	//DADO que eu tenho um cliente
	//QUANDO inserir este cliente
	//ENTÃO não deve gerar exceção
	void inserirCliente() {
		
		Cliente cliente = new Cliente();
		cliente.setCpf(98723);
		cliente.setNome("Rafael");
		
		assertDoesNotThrow(() -> clienteService.save(cliente));
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
	//DADO QUE eu tenho uma instituicao com o código que não existe no banco
	//QUANDO eu tentar atualizar esta instituicao
	//ENTÃO deve ser gerado um erro
	void atualizarInstituicaoFinanceiraExistente() {
		
		InstituicaoFinanceira instituicao = new InstituicaoFinanceira();
		instituicao.setCodigo(70);
		instituicao.setName("Leeman Brothers Bank");
		
		assertThrows(Exception.class, () -> instituicaoFinanceiraService.update(instituicao));
	}

}
