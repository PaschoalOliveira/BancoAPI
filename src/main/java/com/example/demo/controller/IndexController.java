package com.example.demo.controller;

import java.util.ArrayList;

import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//Define um bean Controller
@RestController
@RequestMapping("/")
public class IndexController {

	@GetMapping
	public String bemVindo() {
		return "Bem vindo !";
	}
	
	@GetMapping("/nome")
	public String bemVindoNome(@Nullable @RequestParam("name") String nome,
			@Nullable @RequestParam("cidade") String cidade,
			@Nullable @RequestParam("estado") String estado) {
		
		return "Bem Vindo " + nome + ". Como está o tempo aí em " + cidade + estado + "?";
	}
	@GetMapping("/bemVindoGestor")
	public ArrayList<String> bemVindoGestor() {
		ArrayList<String> gestores = new ArrayList<String>();
		gestores.add("FErnando");
		gestores.add("Aguiar");
		gestores.add("Juliano");
		return gestores;
	}
}
