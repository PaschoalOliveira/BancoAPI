package com.example.demo.specification;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

import com.example.demo.models.Agencia;

public class AgenciaSpecification implements Specification<Agencia> {

	ArrayList<Agencia> agencias = new ArrayList<Agencia>();
	
	private Integer id;
	
	private String nome;
		
	@Override
	public Predicate toPredicate(Root<Agencia> rootAgencia, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		
		ArrayList<Predicate> predicates = new ArrayList<Predicate>();
		
		if(id !=null) {
			Predicate p = criteriaBuilder.equal(rootAgencia.get("id"), id);
			predicates.add(p);
		}
		if(nome !=null) {
			Predicate p = criteriaBuilder.like(rootAgencia.get("nome"), nome);
			predicates.add(p);
		}

		Predicate [] arrayPredicates = predicates.toArray(new Predicate[predicates.size()]);
		
		return criteriaBuilder.and(arrayPredicates);
	}
	
	public AgenciaSpecification() {}

	public AgenciaSpecification(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
