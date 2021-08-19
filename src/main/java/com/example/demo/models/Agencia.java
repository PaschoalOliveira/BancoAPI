package com.example.demo.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="agencia")
public class Agencia implements Serializable{

	private static final long serialVersionUID = 3299265296110493550L;

	@Id
	@Column(name="id_agencia")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String nome;
	
	@ManyToOne
	@JoinColumn(name = "id_instituicao")
	@JsonIgnoreProperties("agencias")
	private InstituicaoFinanceira instituicao;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="agencia")
	private List<Empregado> empregados;
	
}
