package com.example.demo.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="dependente")
public class Dependente {

	@Id
	@Column(name="id_dependente")
	private Integer id;
	
	@Column
	private String nome;
	
	@ManyToMany(mappedBy = "dependentes")
	@JsonIgnore
	private List<Empregado> empregados;
	
}
