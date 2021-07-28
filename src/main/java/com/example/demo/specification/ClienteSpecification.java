package com.example.demo.specification;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.example.demo.models.Cliente;
import com.example.demo.models.Cliente_;

public class ClienteSpecification implements Specification<Cliente>{

	public ClienteSpecification() {
		
	}
	public static Specification<Cliente> pesquisaPorNome(String nome){
		return ((root, criteriaQuery, criteriaBuilder) ->
		{
			return criteriaBuilder.equal(root.get(Cliente_.nome),nome);
		});
	}
	
	public static Specification<Cliente> pesquisaPorSexo(Character sexo){
		return ((root, criteriaQuery, criteriaBuilder) ->
		{
			return criteriaBuilder.equal(root.get(Cliente_.sexo),sexo);
		});
	}

	@Override
	public Predicate toPredicate(Root<Cliente> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		final Collection<Predicate> predicates = new ArrayList<>();
		
		/*
		if (sistema != null) {
			Predicate p = cb.equal(root.get(TransacaoPagamento_.SISTEMA), this.sistema);
			predicates.add(p);
		}
		*/
		return null; 
	}
		 
}
