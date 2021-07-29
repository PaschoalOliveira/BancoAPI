package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.models.Conta;

public interface ContaRepository extends JpaRepository<Conta, Integer>{

	List<Conta> findBySaldoGreaterThan(Double saldo);
	
	@Query(value="SELECT * FROM cliente WHERE saldo = :saldo", nativeQuery=true)
	List<Conta> findBySaldoMaior(Double saldo);
}
