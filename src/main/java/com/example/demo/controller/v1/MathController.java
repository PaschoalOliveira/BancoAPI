package com.example.demo.controller.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.CalculadoraService;

@RestController
@RequestMapping("/math")
public class MathController {

	@GetMapping("/calcularMedia")
	public String calculaMedia(@RequestParam("num1")Integer num1, 
			@RequestParam("num2")Integer num2) {
		CalculadoraService calculadoraService = new CalculadoraService();
		Integer soma = calculadoraService.somar(num1, num2);
		return calculadoraService.dividir(soma,2).toString();
	}
}
