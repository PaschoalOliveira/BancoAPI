package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.Cliente;

//Interface que estende o JPARepository e por isso consegue prover 
// um conjunto de métodos como o pesquisarTodos e o achePorId
//É necessário informar o objeto e o TIPO DA CHAVE PRIMÁRIA
public interface ClienteRepository2 extends JpaRepository<Cliente, Integer>{

}
