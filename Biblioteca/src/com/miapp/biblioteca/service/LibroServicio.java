package com.miapp.biblioteca.service;

import java.util.ArrayList;
import java.util.Iterator;

import com.miapp.biblioteca.Libro;

public class LibroServicio {
	
	private ArrayList<Libro> biblioteca;
	
	public LibroServicio(ArrayList<Libro> biblioteca) {
		this.biblioteca = biblioteca;
	}
	
	//crea nuevo libro
	public void crearLibro(String titulo, String autor, String ISBN, String genero){
		Libro nuevoLibro = new Libro(titulo, autor, ISBN, genero);
		biblioteca.add(nuevoLibro);
	}
	
	//leer libros
	public ArrayList<Libro> obtenerLibros(){
		return biblioteca;
	}
	
	//actualizar libros
	public void actualizarLibro(String ISBN, String tituloNuevo, String autorNuevo, String nuevoGenero){
		for (Libro libro : biblioteca){
			if (libro.getISBN().equals(ISBN)){
				libro.setTitulo(tituloNuevo);
				libro.setAutor(autorNuevo);
				libro.setGenero(nuevoGenero);
			}
		}
	}
	
	public void eliminarLibro(String ISBN){
		
		Iterator<Libro> it = biblioteca.iterator();
		
		while (it.hasNext()){
			if (it.next().getISBN().equals(ISBN)){
				it.remove();
			}
		}
	}
	//Metodo buscar libro por ISBN
	public Libro buscarLibroPorISBN(String ISBN){
		for (Libro libro : biblioteca) {
			if (libro.getISBN().equals(ISBN)){
				return libro;
			}
		}
		return null;
	}
	
	//buscar libro por titulo
	public ArrayList<Libro> buscarLibroPorTitulo(String titulo){
		ArrayList<Libro> librosEncontrados = new ArrayList<Libro>();
		for (Libro libro : biblioteca) {
			if (libro.getTitulo().equalsIgnoreCase(titulo)) {
				librosEncontrados.add(libro);
			}
		}
		return librosEncontrados;
	}
	
	public boolean verificarDisponibilidad(Libro libro) {
		return libro.isDisponible();
	}

}
