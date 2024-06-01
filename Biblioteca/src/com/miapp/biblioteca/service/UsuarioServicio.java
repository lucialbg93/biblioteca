package com.miapp.biblioteca.service;

import java.util.ArrayList;
import java.util.Iterator;

import com.miapp.biblioteca.Libro;
import com.miapp.biblioteca.Usuario;

public class UsuarioServicio {
	
	private ArrayList<Usuario> usuarios;
	
	public UsuarioServicio(ArrayList<Usuario> usuarios){
		this.usuarios = usuarios;
	}
	
	//creacion de usuarios
	public void crearUsuarios(String nombre, String id){
		Usuario nuevoUsuario = new Usuario(nombre, id);
		usuarios.add(nuevoUsuario);
	}
	
	//Leer usuarios
	public ArrayList<Usuario> obtenerUsuarios(){
		return usuarios;
	}
	
	//actualizar usuario
	public void actualizarUsuario(String id, String nuevoNombre){
		for (Usuario usuario : usuarios){
			if (usuario.getId().equals(id)) {
				usuario.setNombre(nuevoNombre);
				return;
			}
		}
	}
	
	//eliminar usuario
	public void eliminarUsuario(String id){
		Iterator<Usuario> it = usuarios.iterator();
		
		while (it.hasNext()){
			if (it.next().getId().equals(id)){
				it.remove();
			}
		}
	}
	//PRESTAMOS
	public void prestarlibro(Usuario usuario, Libro libro) {
		if (libro.isDisponible()) {
			usuario.getLibrosPrestados().add(libro);
			libro.setDisponible(false);
		} else {
			System.out.println("Lo sentimos, El libro no esta disponible");
		}
	}
	
	public Usuario buscarUsuarioPorId(String id) {
		for (Usuario usuario : usuarios) {
			if (usuario.getId().equals(id)){
				return usuario;
			}
		}
		return null;
	}
	
	//DEVOLUCIONES
	public void devolverLibro(Usuario usuario, Libro libro) {
		if (usuario.getLibrosPrestados().contains(libro)) {
			usuario.getLibrosPrestados().remove(libro);
			libro.setDisponible(true);
		} else {
			System.out.println("Libro no encontrado");
		}
	}
	
	//obtener libros prestados
	public ArrayList<Libro> obtenerLibrosPrestados(Usuario usuario) {
		return usuario.getLibrosPrestados();
	}

}
