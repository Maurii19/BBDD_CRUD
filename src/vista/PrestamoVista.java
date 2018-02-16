package vista;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import modelo.LibroModelo;
import modelo.Libros;
import modelo.Prestamo;
import modelo.PrestamoModelo;
import modelo.Usuario;
import modelo.UsuarioModelo;

public class PrestamoVista {
	
	static final int TOMAR_PRESTADO = 1;
	static final int ENTREGAR = 2;
	static final int SALIR = 0;
	

	
	
	public void menuPrestamo(){
		int opcion;
		Scanner lector = new Scanner(System.in);
		
		do{
			
			System.out.println("---Prestamos---");
			System.out.println(TOMAR_PRESTADO + "- Tomar prestado un libro.");
			System.out.println(ENTREGAR + "- Entregar un libro.");
			System.out.println(SALIR + "- Finalizar el programa.");
			opcion = Integer.parseInt(lector.nextLine());
			
			switch(opcion){
			
			case TOMAR_PRESTADO:
				realizarPrestamo(lector);
				break;
				
			case ENTREGAR:
				entregarLibro(lector);
				break;
				
			case SALIR:
				
				break;
				
			default:
				System.out.println("Opcion mal.");
			
			}
		}while(opcion != SALIR);
			
		
	}
	private void realizarPrestamo(Scanner lector){
		System.out.println("Introduce el titulo");
		String titulo = lector.nextLine();
		LibroModelo libroModelo = new LibroModelo();
		Libros libro = libroModelo.selectPorTitulo(titulo);
		
		if(libro != null){ //libro existe
			System.out.println("Introduce el DNI");
			String dni = lector.nextLine();
			
			UsuarioModelo usuarioModelo = new UsuarioModelo();
			
			Usuario usuario = usuarioModelo.selectPorDni(dni);
			
			//CREAR OBJETO PRESTAMO
			Prestamo prestamo = new Prestamo();
			prestamo.setIdLibro(libro.getId());
			prestamo.setIdUsuario(usuario.getId());
			
			Date fechaPrestamo = new Date();
			Date fechaLimite = new Date();
			//Date fechaLimite = new Date(fechaPrestamo.getTime()+(21*24*60*60*10000));
			//prestamo.setFechaPrestamo(fechaPrestamo);
			//prestamo.setFechaLimite(fechaLimite);
			
			Calendar calendario = Calendar.getInstance();
			calendario.setTime(fechaPrestamo);
			calendario.add(Calendar.DATE, 21);
			fechaLimite = calendario.getTime();
			
			prestamo.setFechaPrestamo(fechaPrestamo);
			prestamo.setFechaLimite(fechaLimite);
			prestamo.setEntregado(false);
			
			//CREAR EL OBJETO MODELO PRESTAMO E INSERTAR EL OBJETO PRESTAMO EN EL MODELO PRESTAMO
			PrestamoModelo pModelo = new PrestamoModelo();
			pModelo.insertar(prestamo);
			System.out.println("Prestamo realizado");
			
		}else{//libro no existe
			System.out.println("Prestamo no realizado, el libro no existe");
			realizarPrestamo(lector);
		}
		
		
		
	}
	private void entregarLibro(Scanner lector){
		//pedir dni
		System.out.println("Introduce el DNI del usuario");
		String dni = lector.nextLine();
		//conseguir el usuario
		UsuarioModelo usuarioModelo = new UsuarioModelo();
		Usuario usuario = usuarioModelo.selectPorDni(dni);
		//pedir el titulo
		System.out.println("Introduce el titulo del libro");
		String tituloLibro = lector.nextLine();
		LibroModelo libroModelo = new LibroModelo();
		Libros libro = libroModelo.selectPorTitulo(tituloLibro);
		//conseguir el prestamo de la BBDD
		PrestamoModelo prestamoModelo = new PrestamoModelo();
		Prestamo prestamo = prestamoModelo.prestamoNoFinalizado(libro, usuario);
		//cambiar el objeto prestamo a entregado
		prestamo.setEntregado(true);
		//update de BBDD
		prestamoModelo.update(prestamo);
		
		System.out.println("El libro " + libro.getTitulo() + " ha sido entregado.");
	}
}
