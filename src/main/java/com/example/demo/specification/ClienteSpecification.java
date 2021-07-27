package com.example.demo.specification;

import org.springframework.data.jpa.domain.Specification;

import com.example.demo.models.Cliente;

public class ClienteSpecification {

	public static Specification<Cliente> pesquisaPorNome(String nome){
		return ((root, criteriaQuery, criteriaBuilder) ->
		{
			return criteriaBuilder.equal(root.get("name"),nome);
		});
	}
}
