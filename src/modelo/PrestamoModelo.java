package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PrestamoModelo extends Conector {

	LibroModelo lm = new LibroModelo();
	UsuarioModelo um = new UsuarioModelo();
	
	public void insertar (Prestamo prestamo){
		try {
			PreparedStatement pst = super.conexion.prepareStatement("insert into prestamos (id_libro, id_usuario, fecha_prestamo, fecha_limite, entregado) values(?,?,?,?,?)");
			pst.setInt(1, prestamo.getLibro().getId());
			pst.setInt(2, prestamo.getUsuario().getId());
			pst.setDate(3, new java.sql.Date(prestamo.getFechaPrestamo().getTime()));
			pst.setDate(4, new java.sql.Date(prestamo.getFechaLimite().getTime()));
			pst.setBoolean(5, prestamo.isEntregado());
			pst.execute();
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	public ArrayList<Prestamo> selectAll(){
		Statement st;
		ArrayList<Prestamo> prestamos = new ArrayList<Prestamo>();
		try {
		Prestamo prestamo = new Prestamo();
		st = super.conexion.createStatement();
		ResultSet rst = st.executeQuery("select * from prestamos");
		while(rst.next()){
			prestamo = new Prestamo();
			prestamo.setId(rst.getInt("id"));
			prestamo.setLibro(lm.select(rst.getInt("id_libro")));
			prestamo.setUsuario(um.select(rst.getInt("id_usuario")));
			prestamo.setFechaPrestamo(rst.getDate("fecha_prestamo"));
			prestamo.setFechaLimite(rst.getDate("fecha_limite"));
			prestamo.setEntregado(rst.getBoolean("entregado"));
			
			prestamos.add(prestamo);
		}
			
			return prestamos;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return prestamos;
		
	}
	public Prestamo prestamoNoFinalizado(Libros libro, Usuario usuario) {
		PreparedStatement pst;
		try{
			pst = super.conexion.prepareStatement("select * from prestamos where id_libro=? and id_usuario=? and entregado=?");
			pst.setInt(1,  libro.getId());
			pst.setInt(2, usuario.getId());
			pst.setBoolean(3,  false);
			ResultSet rst = pst.executeQuery();
			
			if(rst.next()){
				Prestamo prestamo = new Prestamo();
				prestamo.setId(rst.getInt("id"));
				prestamo.setUsuario(um.select(rst.getInt("id_usuario")));
				prestamo.setLibro(lm.select(rst.getInt("id_libro")));
				prestamo.setFechaPrestamo(rst.getDate("fecha_prestamo"));
				prestamo.setFechaLimite(rst.getDate("fecha_limite"));
				prestamo.setEntregado(rst.getBoolean("entregado"));
				
				return prestamo;
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
	public void update(Prestamo prestamo) {
		PreparedStatement pst;
		try{
			pst = super.conexion.prepareStatement("update prestamos set id_libro=?, id_usuario=?, fecha_prestamo=?, fecha_limite=?, entregado=? where id=?");
			pst.setInt(1, prestamo.getLibro().getId());
			pst.setInt(2, prestamo.getUsuario().getId());
			pst.setDate(3, new java.sql.Date(prestamo.getFechaPrestamo().getTime()));
			pst.setDate(4, new java.sql.Date(prestamo.getFechaLimite().getTime()));
			pst.setBoolean(5, prestamo.isEntregado());
			pst.setInt(6, prestamo.getId());
			
			pst.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
		
	}
	
	
}
