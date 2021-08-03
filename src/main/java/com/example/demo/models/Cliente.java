package com.example.demo.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name="cliente")
public class Cliente {

	@Id
	private Integer cpf;
	
	@Column(name="nome")
	private String nome;
	
	@Column
	private Character sexo;
	
	@Column
	private String telefone;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy="cliente")
	private List<Conta> contas;

	public Cliente() {
		
	}
	
	//Construtor que define nome e cpf
	public Cliente(String nome, Integer cpf) {
		this.setNome(nome);
		this.setCpf(cpf);
	}
	
	public Cliente(String nome, Integer cpf, Character sexo) {
		this.setNome(nome);
		this.setCpf(cpf);
		this.setSexo(sexo);
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getCpf() {
		return cpf;
	}
	public void setCpf(Integer cpf) {
		this.cpf = cpf;
	}

	public Character getSexo() {
		return sexo;
	}

	public void setSexo(Character sexo) {
		this.sexo = sexo;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	
	public List<Conta> getContas() {
		return contas;
	}

	public void setContas(ArrayList<Conta> contas) {
		this.contas = contas;
	}
	
}
