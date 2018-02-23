package vista;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import Comparators.IdComparator;
import Comparators.IdUsuarioComparator;
import Comparators.NombreComparator;

import java.util.Date;

import modelo.Usuario;
import modelo.UsuarioModelo;
import modelo.Conector;
import modelo.Config;

public class UsuarioVista {

	static final int INSERTAR = 1;
	//static final int ELIMINAR = 2;
	static final int LISTAR = 2;
	static final int SALIR = 0;
	
	public void menuDeUsuario(){
		
		Scanner lector = new Scanner(System.in);
		UsuarioModelo usuarioModelo = new UsuarioModelo();
		int opcion;
		
		do{
			System.out.println("----MENU----");
			System.out.println(INSERTAR + "- Insertar usuario a la BBDD USUARIOS");
			//System.out.println(ELIMINAR + "- Eliminar usuario de la BBDD USUARIOS");
			System.out.println(LISTAR + "- Listar usuarios de la BBDD USUARIOS");
			System.out.println(SALIR + "- Finalizar el programa");
			
			opcion = Integer.parseInt(lector.nextLine());
			
			switch(opcion){
			
			case INSERTAR:
				usuarioModelo.insert(this.insertarUsuario());
				break;

			case LISTAR:
				ArrayList<Usuario> usuarios = usuarioModelo.selectAll();
				NombreComparator d = new NombreComparator();
				usuarios.sort(d);
				IdUsuarioComparator id = new IdUsuarioComparator();
				usuarios.sort(id);
				this.mostrarUsuarios(usuarios);
				break;
				
			case SALIR:
				System.out.println("Finalizando programa...");
				break;
				
			default:
				System.out.println("Opcion incorrecta");
				break;
			
			
		}
		}while(opcion != SALIR);
			
		
		
		
		
		
	}

	public Usuario insertarUsuario() {
			Usuario usuario = new Usuario();
			Scanner lector = new Scanner(System.in);
			System.out.println("Introduce un nombre");
			String nombre = lector.nextLine();
			usuario.setNombre(nombre);
			System.out.println("Introduce un apellido");
			String apellido = lector.nextLine();
			usuario.setApellido(apellido);
			System.out.println("Introduce una edad");
			int edad = Integer.parseInt(lector.nextLine());
			usuario.setEdad(edad);
			System.out.println("Introduce el DNI");
			String dni = lector.nextLine();
			usuario.setDni(dni);
		Date fechaNacimiento;
		try{
			System.out.println("Introduce la fecha de nacimiento del usuario em fomrato yyyy-mm-dd");
			String fecha = lector.nextLine();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
			fechaNacimiento =  sdf.parse(fecha);
			usuario.setFecha_nacimiento(fechaNacimiento);
		}catch(ParseException e){
			System.err.println("Introduce una fecha correcta");
		}
			
			
			

		return usuario;
	}

	public void mostrarUsuarios(ArrayList<Usuario> usuarios) {

		Iterator<Usuario> i = usuarios.iterator();
		while(i.hasNext()){
			Usuario usuario = i.next();
			mostrarUsuario(usuario);
		}
	}
	public void mostrarUsuario(Usuario usuario) {
		java.util.Date fecha_nacimiento = usuario.getFecha_nacimiento();
		String fecha="";
		if(fecha_nacimiento == null){
			fecha = "fecha vacia";
		}else{
			fecha = new SimpleDateFormat("dd/MM/yyyy").format(usuario.getFecha_nacimiento());
		}
		System.out.println("ID: " + usuario.getId()+ " Nombre y Apellido: " + usuario.getNombre() + " " + usuario.getApellido() + " DNI: " + usuario.getDni() +  " Edad: "+ usuario.getEdad() + " Fecha Nacimiento: " + fecha);
		
	}
}

