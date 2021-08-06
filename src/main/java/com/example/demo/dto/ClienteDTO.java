package com.example.demo.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.DoubleStream;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import com.example.demo.models.Cliente;
import com.example.demo.models.Conta;

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
	
	public static ClienteDTO createClienteDTOWithModelMapper(Cliente cliente) {
		ModelMapper modelMapper = new ModelMapper();
		
		Converter<Cliente, ClienteDTO> converter = new AbstractConverter<Cliente, ClienteDTO>() {
		    @Override
		    protected ClienteDTO convert(Cliente source) {
		        ClienteDTO destination = new ClienteDTO();

		        //Usa a estrutura lambda para resgatar o somatório e Saldos nas contas
		        Double somatorioSaldo = 0.0;
		        for(Conta conta : source.getContas()) {
		        	somatorioSaldo = somatorioSaldo + conta.getSaldo();
		        }
		        //Retorna todos os saldos da conta em uma estrutura de stream(imaginem esse stream como um array de Double)
		        DoubleStream stream = source.getContas().stream().mapToDouble(c ->c.getSaldo());
		        //Faço o sum de todos os saldos contidos no stream
		        somatorioSaldo = stream.sum();
		        Double somatorioSaldoBitcoin = source.getContas().stream().mapToDouble(c ->c.getSaldoBitCoin()).sum();

		        destination.setSaldo(somatorioSaldo);
	            destination.setSaldoBitcoin(somatorioSaldoBitcoin);

		        return destination;
		    }
		};
		
		TypeMap<Cliente, ClienteDTO> typeMap = modelMapper.createTypeMap(Cliente.class, ClienteDTO.class);
				
		typeMap.setPostConverter(converter);
		
		modelMapper.typeMap(null, null);
		return modelMapper.map(cliente, ClienteDTO.class);
	}
}
