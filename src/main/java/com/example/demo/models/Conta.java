package com.example.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.example.demo.utils.UtilsUrl;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="conta")
public class Conta {

	@Id
	@Column(name="id_conta")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private Double saldo;
	
	//(fetch = FetchType.LAZY)
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="cpf")
	@JsonIgnoreProperties("contas")
	private Cliente cliente;
	
	@Transient
	private String uriCliente;
	
	public String getUriCliente() {
		return new UtilsUrl()
				.getUri("/v2/clientes", getCliente().getCpf()).toString();
	}
	
	@ManyToOne
	@JoinColumn(name="id_instituicao")
	@JsonIgnoreProperties("contas")
	private InstituicaoFinanceira instituicao;
	

}
