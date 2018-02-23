package Comparators;
import java.util.Comparator;
import modelo.Usuario;


public class NombreComparator implements Comparator<Usuario> {


		
		@Override
		public int compare(Usuario nombre1, Usuario nombre2) {

			return nombre1.getNombre().compareToIgnoreCase(nombre2.getNombre());
			
		}

	}

