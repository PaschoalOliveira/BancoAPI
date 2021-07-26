package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.models.InstituicaoFinanceira;

public interface InstituicaoFinanceiraRepository 
							extends JpaRepository<InstituicaoFinanceira, Integer>{

	//Consulta que utiliza o hibernate query language para buscar instiuicoes pelo nome
	@Query("SELECT inst FROM instituicao_financeira inst WHERE inst.name = ?1")
	public List<InstituicaoFinanceira> findByName(String name);
}
