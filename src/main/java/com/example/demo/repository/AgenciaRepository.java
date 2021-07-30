package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.models.Agencia;
import com.example.demo.models.Cliente;

public interface AgenciaRepository extends JpaRepository<Agencia, Integer> , JpaSpecificationExecutor<Agencia>{

	/*
	//Query Nativa
	@Query(value="SELECT * FROM agencia WHERE agencia.id_agencia > :id", nativeQuery = true)
	//Hibernate Query Language
	@Query("SELECT ag FROM agencia WHERE agencia.id > :id")
	*/
	//Método Jpa Data
	public List<Agencia> findByIdGreaterThan(Integer id);
	
	public List<Agencia> findByIdAndNome(Integer id, String nome);
	
	public List<Agencia> findByNome(String nome);
}
