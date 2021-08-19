package com.example.demo.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="empregado")
public class Empregado implements Serializable{


	private static final long serialVersionUID = -1900845071854485601L;

	@Id
	private Integer cpf;
	
	@Column
	private String nome;
	
	@Column
	private Double salario;
	
	@Column
	@Lob
	private Byte[] foto;
	
	@ManyToOne
	@JoinColumn(name="id_agencia")
	@JsonIgnoreProperties("empregados")
	private Agencia agencia;
	
	@ManyToMany
	@JoinTable(
			name="empregado_dependente",
			joinColumns=@JoinColumn(name = "cpf_empregado"),
			inverseJoinColumns = @JoinColumn(name = "id_dependente"))
	private List<Dependente> dependentes;
}
