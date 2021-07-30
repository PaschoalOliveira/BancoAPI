package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Empregado;

public interface EmpregadoRepository extends JpaRepository<Empregado, Integer> {

}
