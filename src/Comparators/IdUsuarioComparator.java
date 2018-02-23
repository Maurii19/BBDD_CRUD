package Comparators;
import modelo.Usuario;
import java.util.Comparator;

public class IdUsuarioComparator implements Comparator<Usuario> {
	
	@Override
	public int compare(Usuario id1, Usuario id2){
		
		return Integer.compare(id1.getId(), id2.getId());
	}
	

	
}
