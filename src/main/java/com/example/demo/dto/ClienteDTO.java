package com.example.demo.dto;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.models.Cliente;
import com.example.demo.models.Conta;
import com.example.demo.models.InstituicaoFinanceira;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {

	private Integer cpf;
	
	private String nome;
	
	private Character sexo;
	
	private String telefone;
	
	private Double saldo;
	
	private Double saldoBitcoin;
	
	private List<String> nomeInstituicoes = new ArrayList<String>();
	
	//Método responsável por criar um DTO a partir de um Cliente
	public void createClienteDTO(Cliente cliente) {
		this.setCpf(cliente.getCpf());
		this.setNome(cliente.getNome());
		this.setSexo(cliente.getSexo());
		this.setTelefone(cliente.getTelefone());
		
		Double saldo = 0.0;
		Double saldoBitCoin = 0.0;
		for(Conta conta : cliente.getContas()) {
			saldo = saldo + conta.getSaldo();
			saldoBitCoin = saldoBitCoin + conta.getSaldoBitCoin();
			nomeInstituicoes.add(conta.getInstituicao().getName());
		}
		this.setSaldo(saldo);
		this.setSaldoBitcoin(saldoBitCoin);
	}
}
