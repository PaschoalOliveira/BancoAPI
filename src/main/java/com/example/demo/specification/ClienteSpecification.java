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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteSpecification implements Specification<Cliente>{

	private Integer cpf;
	
	private String nome;
	
	private Character sexo;
	
	private String telefone;
	
	@Override
	public Predicate toPredicate(Root<Cliente> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		final Collection<Predicate> predicates = new ArrayList<>();
		
		if (cpf != null) {
			Predicate p = cb.equal(root.get(Cliente_.cpf),cpf);
			predicates.add(p);
		}
		if (nome != null) {
			Predicate p = cb.equal(root.get(Cliente_.nome),nome);
			predicates.add(p);
		}
		if (sexo != null) {
			Predicate p = cb.equal(root.get(Cliente_.sexo),sexo);
			predicates.add(p);
		}
		if (telefone != null) {
			Predicate p = cb.equal(root.get(Cliente_.telefone),telefone);
			predicates.add(p);
		}
		Predicate[] array = predicates.toArray(new Predicate[predicates.size()]);

		return cb.and(array);
	}
		 
}
