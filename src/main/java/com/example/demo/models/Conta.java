package com.example.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
	
	@ManyToOne
	@JoinColumn(name="id_instituicao")
	@JsonIgnoreProperties("contas")
	private InstituicaoFinanceira instituicao;

	public Conta() {
		
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public InstituicaoFinanceira getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(InstituicaoFinanceira instituicao) {
		this.instituicao = instituicao;
	}
	

}
