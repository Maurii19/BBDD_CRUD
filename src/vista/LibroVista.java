package vista;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;
import modelo.Libros;
import modelo.LibroModelo;
import modelo.Usuario;


public class LibroVista extends Libros {

	final int LISTAR = 1;
	final int INSERTAR = 2;
	final int SALIR = 0;
	
	LibroModelo libroModelo = new LibroModelo();
	
	public void menuLibro(){
		Scanner lector = new Scanner(System.in);
		int opcion;
		
		do{
			System.out.println("----MENU----");
			System.out.println(LISTAR + "- Listar libros que estan en la BBDD.");
			System.out.println(INSERTAR + "- Insertar libro a la BBDD.");
			System.out.println(SALIR + "- Finalizar el programa.");
			
			opcion = Integer.parseInt(lector.nextLine());
			
			switch(opcion){
			
			case LISTAR:
				ArrayList<Libros> libros = libroModelo.selectAll();
				this.mostarLibros(libros);
				break;
			
			case INSERTAR:
				libroModelo.insert(this.insertarLibro());
				break;
				
			case SALIR:
				System.out.println("Programa finalizado.");
				break;
				
			default:
				System.out.println("Error, introduce una opcion correcta");
			
			
			
			}
		}while(opcion != SALIR);
		
		
	}
	
	public Libros insertarLibro(){
		
		Libros libro = new Libros();
		Scanner lector = new Scanner(System.in);
		System.out.println("Introduce el titulo del libro");
		String titulo = lector.nextLine();
		libro.setTitulo(titulo);;
		System.out.println("Introduce el autor del libro");
		String autor = lector.nextLine();
		libro.setAutor(autor);

		
		return libro;
		
	}
	public void mostarLibros(ArrayList<Libros> libros) {

		Iterator<Libros> i = libros.iterator();
		while(i.hasNext()){
			Libros libro = i.next();
			mostrarLibro(libro);
		}
	}
	public void mostrarLibro(Libros libro) {
		System.out.println("ID: " + libro.getId()+ " Titulo del libro : " + libro.getTitulo()  + " Autor del libro: " + libro.getAutor());
		
	}
}
