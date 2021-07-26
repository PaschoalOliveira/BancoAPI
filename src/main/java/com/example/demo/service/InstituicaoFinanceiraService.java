package com.example.demo.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.InstituicaoFinanceira;
import com.example.demo.repository.InstituicaoFinanceiraRepository;

@Service
public class InstituicaoFinanceiraService {

	@Autowired
	InstituicaoFinanceiraRepository instituicaoRepository;
	
	public ArrayList<InstituicaoFinanceira> findAll(){
		
		ArrayList<InstituicaoFinanceira> listaRetorno
									    = new ArrayList<InstituicaoFinanceira>();
											
		listaRetorno = (ArrayList<InstituicaoFinanceira>) instituicaoRepository.findAll();
		
		return listaRetorno;
	}
	
	public InstituicaoFinanceira findById(Integer codigo) {
		return instituicaoRepository.findById(codigo).get();
	}
	
	//Método que pesquisa por nome e faz o casting de List para ArrayList
	public ArrayList<InstituicaoFinanceira> findByName(String name) {
		
		return (ArrayList<InstituicaoFinanceira>) instituicaoRepository.findByName(name);
	}
	
	
	public void save(InstituicaoFinanceira instituicao) {
		instituicaoRepository.save(instituicao);
	}
	
	public void update(InstituicaoFinanceira instituicao) throws Exception {
		Optional<InstituicaoFinanceira> opInstituicaoBanco 
						= instituicaoRepository.findById(instituicao.getCodigo());
		if(opInstituicaoBanco.isPresent()) {
			instituicaoRepository.save(instituicao);
		}else {
			throw new Exception("Instituição não encontrada");
		}
	}
	
	public void delete(Integer codigo) {
		instituicaoRepository.deleteById(codigo);
	}
}
