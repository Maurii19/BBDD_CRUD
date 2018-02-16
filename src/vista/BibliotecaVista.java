package vista;

import java.util.Scanner;

public class BibliotecaVista {

	static final int GESTIONAR_USUARIOS = 1;
	static final int GESTIONAR_LIBROS = 2;
	static final int GESTIONAR_PRESTAMOS = 3;
	static final int SALIR = 0;
	
	
	public void menuBiblioteca(){
		
		LibroVista libroVista = new LibroVista();
		UsuarioVista usuarioVista = new UsuarioVista();
		PrestamoVista prestamoVista = new PrestamoVista();
		
		Scanner lector = new Scanner(System.in);
		int opcion;
		
		do{
			System.out.println(GESTIONAR_USUARIOS + "- Gestionar usuarios de la BBDD");
			System.out.println(GESTIONAR_LIBROS + "- Gestionar libros de la BBDD");
			System.out.println(GESTIONAR_PRESTAMOS + "- Gestionar prestamos de la BBDD");
			System.out.println(SALIR + "- Finalizar el programa");
			
			opcion = Integer.parseInt(lector.nextLine());
			
			switch(opcion){
			
			case GESTIONAR_USUARIOS:
				usuarioVista.menuDeUsuario();
				break;
				
			case GESTIONAR_LIBROS:
				libroVista.menuLibro();
				break;
				
			case GESTIONAR_PRESTAMOS:
				prestamoVista.menuPrestamo();
				break;
				
			case SALIR:
				System.out.println("Finalizando programa...");
				break;
				
			default:
				System.out.println("Opcion mal");
				break;
			}
			
		}while(opcion != SALIR);
		
	}
}
