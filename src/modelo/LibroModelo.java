package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class LibroModelo extends Conector {

	//nos devuelve todos los libros
	public ArrayList<Libros> selectAll(){

	ArrayList<Libros> libros = new ArrayList<Libros>();
	String sql  = "SELECT * FROM libros";
	try {
	Statement stt = super.conexion.createStatement();
	ResultSet rst = stt.executeQuery(sql);

	while(rst.next()){
		Libros libro = new Libros();
		libro.setId(rst.getInt("id"));
		libro.setTitulo(rst.getString("titulo"));
		libro.setAutor(rst.getString("autor"));

		libros.add(libro);
	}
	return libros;
	} catch (SQLException e) {
		e.printStackTrace();
	}
		return libros;
	}
	
	//Nos devuelve los registros por id del libro
	public Libros select(int id){	
		Libros libro = new Libros();
		try {
		    Statement st = super.conexion.prepareStatement("select * from libros");
			ResultSet rs = st.executeQuery("select * from libros WHERE id="+id);
		    libro.setId(rs.getInt("id"));
		    libro.setTitulo(rs.getString("titulo"));
		    libro.setAutor(rs.getString("autor"));


		} catch (SQLException e) {
			e.printStackTrace();
		}
		return libro;
	}
	
	//Nos devuelve los registros por titulo del libro
	public Libros selectPorTitulo(String titulo){
		PreparedStatement pst;
		Libros libro = new Libros();
		try {
			pst = super.conexion.prepareStatement("select * from libros where titulo=?");
			pst.setString(1, titulo);
			ResultSet rst = pst.executeQuery();
			if(rst.next()){
				
				libro.setId(rst.getInt("id"));
				libro.setTitulo(rst.getString("titulo"));
				libro.setAutor(rst.getString("autor"));
			
			return libro;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return libro;
	}
	
	//actualiza un registro de la BBDD LIBROS
	public void update (Libros libro){
		PreparedStatement pst;
		try {
			pst = super.conexion.prepareStatement("update libros set titulo=?, autor=?, where id=?");
			pst.setString(1, libro.getTitulo());
			pst.setString(2, libro.getAutor());
			pst.setInt(3, libro.getId());
			pst.executeUpdate();
			
		} catch (SQLException e) {	
			e.printStackTrace();
		}	
	}
	
	//elimina registro por id
	public void delete(int id){
		PreparedStatement pst;
		try {
			pst = super.conexion.prepareStatement("delete form libros where id=?");
			pst.setInt(1, id);
			pst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//insertamos un registro de un libro a la base de datos LIBROS
	public void insert(Libros libro){
		try {
			PreparedStatement pst = super.conexion.prepareStatement("insert into libros (titulo, autor) values(?,?)");
			pst.setString(1, libro.getTitulo());
			pst.setString(2, libro.getAutor());
			pst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
