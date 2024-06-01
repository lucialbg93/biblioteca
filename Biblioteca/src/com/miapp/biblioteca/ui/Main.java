package com.miapp.biblioteca.ui;

import java.util.ArrayList;
import java.util.Scanner;

import com.miapp.biblioteca.Libro;
import com.miapp.biblioteca.Usuario;
import com.miapp.biblioteca.service.LibroServicio;
import com.miapp.biblioteca.service.UsuarioServicio;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		ArrayList<Libro> biblioteca = new ArrayList<Libro>();
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		LibroServicio libroServicio = new LibroServicio(biblioteca);
		UsuarioServicio usuarioServicio = new UsuarioServicio(usuarios);
		Scanner scanner = new Scanner(System.in);
		
		//CREACION DE USUARIOS DE FORMA MANUAL
		Usuario usuario1 = new Usuario();
		usuario1.setNombre("Ana Perez");
		usuario1.setId("2521");
		
		Usuario usuario2 = new Usuario();
		usuario2.setNombre("Juan Romero");
		usuario2.setId("2522");
		
		Usuario usuario3 = new Usuario();
		usuario3.setNombre("Maria Lopez");
		usuario1.setId("2523");
		
		usuarioServicio.crearUsuarios("Ana Perez", "2521");
		usuarioServicio.crearUsuarios("Juan Romero", "2522");
		usuarioServicio.crearUsuarios("Maria Lopez", "2523");
		
		//CREACION DE LIBROS
		libroServicio.crearLibro("Mi Planta de Naranja Lima", "Jose Mauro de Vanconcelos", "9789500210362", "Novela");
		libroServicio.crearLibro("Ojos Del Perro Siberiano", "Antonio Santa Ana", "9789875453289", "Ficcion");
		libroServicio.crearLibro("Analisis Financiero", "Guillermo Sierra", "9788436816761", "Finanzas");
		
		int item;
		do {
			System.out.println("--- Biblioteca en JAVA ---");
			System.out.println("(1) Crear libro");
			System.out.println("(2) Actualizar libro");
			System.out.println("(3) Buscar libro por ISBN");
			System.out.println("(4) Buscar libros por Titulo");
			System.out.println("(5) Listar libros");
			System.out.println("(6) Eliminar libro");
			System.out.println("(7) Prestar libro");
			System.out.println("(8) Devolver Libro");
			System.out.println("(9) Crear Usuario");
			System.out.println("(0) Salir");
			System.out.println("Seleccione una opcion ");
			item = scanner.nextInt();
			scanner.nextLine();
			switch (item) {
			case 1:
				//crear libro
				System.out.println("Ingrese titulo: ");
				String titulo = scanner.nextLine();
				System.out.println("Ingrese autor: ");
				String autor = scanner.nextLine();
				System.out.println("Ingrese ISBN: ");
				String isbn = scanner.nextLine();
				System.out.println("Ingrese genero: ");;
				String genero = scanner.nextLine();
				libroServicio.crearLibro(titulo, autor, isbn, genero);
				break;
			case 2: //actualizar libro
				System.out.println("Ingrese ISBN: ");
				String isbnParaActualizar = scanner.nextLine();
				System.out.println("Ingrese nuevo titulo: ");
				String tituloNuevo = scanner.nextLine();
				System.out.println("Ingrese nuevo autor: ");
				String autorNuevo = scanner.nextLine();
				System.out.println("Ingrese nuevo genero: ");
				String generoNuevo = scanner.nextLine();
				libroServicio.actualizarLibro(isbnParaActualizar, tituloNuevo, autorNuevo, generoNuevo);
				break;
			case 3: //buscar libro por isbn
				System.out.println("Ingrese ISBN del libro que desea buscar: ");
				String isbnBuscar = scanner.nextLine();
				Libro libroISBN = libroServicio.buscarLibroPorISBN(isbnBuscar);
				if (libroISBN != null) {
					System.out.println("El libro encontrado es: " + libroISBN.getTitulo());
				} else {
					System.out.println("No se encontro libro con ISBN " + isbnBuscar);
				}
				break;
			case 4: //Buscar libros por Titulo
				System.out.println("Ingrese titulo: ");
				String tituloBuscar = scanner.nextLine();
				ArrayList<Libro> librosBuscados = libroServicio.buscarLibroPorTitulo(tituloBuscar);
				if (!librosBuscados.isEmpty()) {
					System.out.println("Resultado de la busqueda: ");
					for (Libro libro : librosBuscados) {
						System.out.println(libro.getTitulo());
					}
				} else {
					System.out.println("No se encontro libro");
				}
				break;
			case 5: //Listar libros
				ArrayList<Libro> listaLibros = libroServicio.obtenerLibros();
				for (Libro libro : listaLibros) {
					System.out.println(libro.getTitulo());
				}
				break;
			case 6: //eliminar libro
				System.out.println("Ingrese ISBN del libro que desea eliminar: ");
				String isbnEliminado = scanner.nextLine();
				libroServicio.eliminarLibro(isbnEliminado);
				break;
			case 7: //Prestar libro
				System.out.println("Ingrese id de usuario: ");
				String idUsuario = scanner.nextLine();
				Usuario usu = usuarioServicio.buscarUsuarioPorId(idUsuario);
				if (usu != null) {
					System.out.println("Ingrese ISBN del libro solicitado: ");
					String isbnPrestar = scanner.nextLine();
					Libro libroPrestar = libroServicio.buscarLibroPorISBN(isbnPrestar);
					if (libroPrestar != null) {
						if (libroServicio.verificarDisponibilidad(libroPrestar)) {
							usuarioServicio.prestarlibro(usu, libroPrestar);
							System.out.println("Prestamo confirmado a " + usu.getNombre());
						} else {
							System.out.println("Libro no disponible.");
						}
					} else {
						System.out.println("Libro no encontrado");
					}
				} else {
					System.out.println("Usuario no existe");
				}
				break;
			case 8: //devoluciones
				
				System.out.print("Ingrese el id del usuario: ");

				String idUsuario2 = scanner.nextLine();

				Usuario usuarioDevolucion = usuarioServicio.buscarUsuarioPorId(idUsuario2);

				if (usuarioDevolucion != null) { 
					System.out.print("Ingrese el ISBN del libro a devolver: ");
					String isbnDevolucion = scanner.nextLine();
					Libro libroDevolucion = libroServicio.buscarLibroPorISBN(isbnDevolucion);
					if (libroDevolucion != null) {
						usuarioServicio.devolverLibro(usuarioDevolucion, libroDevolucion);
						System.out.println("Devolución exitosa por  " + usuarioDevolucion.getNombre());

					} else {
						System.out.println("Libro no encontrado.");
					}
				} else {
					System.out.println("Usuario no encontrado.");
				}
				break;
			case 9: //crear usuario
				System.out.println("Ingrese nombre de usuario: ");
				String nombreUsuarioNuevo = scanner.nextLine();
				System.out.println("Ingrese id: ");
				String idUsuarioNuevo = scanner.nextLine();
				usuarioServicio.crearUsuarios(nombreUsuarioNuevo, idUsuarioNuevo);
			}
		
	} while (item != 0);

}
}	