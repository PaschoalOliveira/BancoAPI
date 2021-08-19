package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.demo.dto.AgenciaDTO;
import com.example.demo.models.Agencia;
import com.example.demo.repository.AgenciaRepository;
import com.example.demo.specification.AgenciaSpecification;

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
	@Cacheable(value="agencias")
	public Page<AgenciaDTO> buscarTodosComFiltros(Integer numeroPagina, Integer itensPorPagina, String campoOrdenacao, 
			String direcaoOrdenacao,String nome, Integer id){
		
		//Crio um objeto PageREquest responsável por realizar a paginação e ordenação
		PageRequest pageRequest = PageRequest.of(numeroPagina, itensPorPagina, Direction.fromString(direcaoOrdenacao), campoOrdenacao);
		
		AgenciaSpecification agenciaSpecification = new AgenciaSpecification(id,nome);
		
		//O findAll com o parâmetro pageREquest retorna Page<Agencia>
		//Recebo o Page<Agencia vindo da base repository com os dados especificados
		Page<Agencia> pageAgencia = agenciaRepository.findAll(agenciaSpecification, pageRequest);
		
		//Transformo o Page<Agencia> e Um List<Agencia>
		List<Agencia> agencias = pageAgencia.toList();
		
		ArrayList<AgenciaDTO> agenciasDTO = new ArrayList<AgenciaDTO>();
		
		//Percorre a lista de AgenciaDTO, preenche as informações do DTO e adiciona em uma lista que será retornada
		for(Agencia agencia : agencias) {
			AgenciaDTO agenciaDto = new AgenciaDTO(agencia.getId(), agencia.getNome(),agencia.getInstituicao(), agencia.getEmpregados());
			agenciasDTO.add(agenciaDto);
		}
		
		final int start = (int)pageRequest.getOffset();
		final int end = Math.min((start + pageRequest.getPageSize()), agenciasDTO.size());
		
		Page<AgenciaDTO> pageAgenciaDTO = new PageImpl<>(agenciasDTO.subList(start, end), pageRequest, agenciasDTO.size());
		
		return pageAgenciaDTO;
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
