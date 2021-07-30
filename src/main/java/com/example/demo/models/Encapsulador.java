package com.example.demo.models;

import java.util.ArrayList;

public class Encapsulador<Y> {

	private ArrayList<Y> listaTipos;
	
	public void add(Y tipo) {
		System.out.println(tipo);
	}

	public ArrayList<Y> getListaTipos() {
		return listaTipos;
	}

	public void setListaTipos(ArrayList<Y> listaTipos) {
		this.listaTipos = listaTipos;
	}
}
