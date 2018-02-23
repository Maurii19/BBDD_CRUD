package Comparators;
import java.util.Comparator;
import modelo.Libros;


public class IdComparator implements Comparator <Libros>{
	
	

	@Override
	public int compare(Libros id1, Libros id2) {
		
		return Integer.compare(id2.getId(), id1.getId());
	}
}


	