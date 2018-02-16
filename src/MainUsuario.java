import java.util.Scanner;

import modelo.Usuario;
import modelo.UsuarioModelo;
import vista.UsuarioVista;


public class MainUsuario extends UsuarioVista {

	public static void main(String[] args) {
		Scanner lector = new Scanner(System.in);
		
		UsuarioVista usuarioVista = new UsuarioVista();
		
		usuarioVista.menuDeUsuario();
		
		
	}

}
