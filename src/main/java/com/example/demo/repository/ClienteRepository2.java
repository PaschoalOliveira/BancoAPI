package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.models.Cliente;

//Interface que estende o JPARepository e por isso consegue prover 
// um conjunto de métodos como o pesquisarTodos e o achePorId
//É necessário informar o objeto e o TIPO DA CHAVE PRIMÁRIA
public interface ClienteRepository2 extends JpaRepository<Cliente, Integer>{

	 @Query("SELECT c FROM cliente c WHERE c.sexo = ?1")
	 List<Cliente> pesquisaPorSexo(Character sexo);
	 
	 @Query("SELECT c FROM cliente c WHERE c.telefone like '%' || ?1 || '%'")
	 List<Cliente> pesquisaPorTelefone(String numero);
}
