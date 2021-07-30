package com.example.demo.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.demo.models.Agencia;
import com.example.demo.repository.AgenciaRepository;
import com.example.demo.specification.AgenciaSpecification;
import com.example.demo.specification.ClienteSpecification;

@Service
public class AgenciaService {

	@Autowired
	AgenciaRepository agenciaRepository;
	
	public Page<Agencia> buscarTodos(Integer numeroPagina, Integer itensPorPagina, String campoOrdenacao, String direcaoOrdenacao){
		
		//Crio um objeto PageREquest responsável por realizar a paginação e ordenação
		PageRequest pageRequest = PageRequest.of(numeroPagina, itensPorPagina, Direction.fromString(direcaoOrdenacao), campoOrdenacao);
		
		//O findAll com o parâmetro pageREquest retorna Page<Agencia>
		return agenciaRepository.findAll(pageRequest);
	}
	
	public Page<Agencia> buscarTodosComFiltros(Integer numeroPagina, Integer itensPorPagina, String campoOrdenacao, 
			String direcaoOrdenacao,String nome, Integer id){
		
		//Crio um objeto PageREquest responsável por realizar a paginação e ordenação
		PageRequest pageRequest = PageRequest.of(numeroPagina, itensPorPagina, Direction.fromString(direcaoOrdenacao), campoOrdenacao);
		
		AgenciaSpecification agenciaSpecification = new AgenciaSpecification(id,nome);
		
		//O findAll com o parâmetro pageREquest retorna Page<Agencia>
		return agenciaRepository.findAll(agenciaSpecification, pageRequest);
	}
	
	public ArrayList<Agencia> buscarPorFiltros(String nome, Integer id){
		
		ArrayList<Agencia> agencias = new ArrayList<Agencia>();
		if(id != null && nome != null) {
			agencias = (ArrayList<Agencia>) agenciaRepository.findByIdAndNome(id,nome);
		}else if(id == null) {
			agencias = (ArrayList<Agencia>) agenciaRepository.findByNome(nome);
		}else if(nome == null) {
			agencias.add(agenciaRepository.findById(id).get());
		}
		return agencias;
	}
	
	public ArrayList<Agencia> buscarPorFiltrosSpecification(String nome, Integer id){
		//Cria uma instância de specification definindo os campos que foram passados como parâmetro via GET do Controller
		AgenciaSpecification agenciaSpecification = new AgenciaSpecification(id,nome);
		
		return (ArrayList<Agencia>) agenciaRepository.findAll(agenciaSpecification);
	}
}
