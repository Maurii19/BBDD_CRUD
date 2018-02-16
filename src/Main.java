import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.*;

public class Main {

	public static void main(String[] args) {
		
		final int CREAR = 1;
		final int LEER = 2;
		final int ACTUALIZAR = 3;
		final int ELIMINAR = 4;
		final int SALIR = 0;
		


	
		
		Scanner lector = new Scanner(System.in);
		
		int opcion;
		
		do{
			System.out.println(CREAR + "- Crear usuario a la base de datos biblioteca.");
			System.out.println(LEER + "- Listar usuarios que se encuentran en la base de datos biblioteca.");
			System.out.println(ACTUALIZAR + "- Actualizar usuarios de base datos biblioteca, mediante el ID.");
			System.out.println(ELIMINAR + "- Eliminar usuario de la base de datos biblioteca.");
			System.out.println(SALIR + "- Finalizar programa.");
			
			opcion = Integer.parseInt(lector.nextLine());
			
			switch(opcion){
			
			case CREAR:
				crearUsuario();
				break;
				
			case LEER:
				imprimirLista();
				break;
				
			case ACTUALIZAR:

			modificar();
				
				break;
			
			case ELIMINAR:
				eliminarUsuario();
				break;
				
			case SALIR:
				
				break;
				
			
			default:
				System.out.println("Opcion incorrecta, intentalo otra vez");
				break;
			
			}
			
		}while(SALIR != opcion);
			
	}
	
	
	
	//imprime los usuarios de la base de datos biblioteca
	public static void imprimirLista(){
		try {
			//se carga el driver
			Class.forName("com.mysql.jdbc.Driver");
			//crear la conexion con la BBDD biblioteca
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/biblioteca", "root", "");
			//statement para ejecutar querys
			Statement st = con.createStatement();
			//ejecutar query
			ResultSet rs = st.executeQuery("select * from usuarios");
			//imprimir en pantalla el resultado de la consulta
			System.out.println("Estos son los usuarios que se encuentran en la base de datos biblioteca:");
			while(rs.next()){
				
				System.out.println("ID: " + rs.getInt("id") + " "+ " Nombre: " + rs.getString("nombre") + " " + " Apellido: "+ rs.getString("apellido") + " "+" Edad: " + rs.getInt("edad"));
			}

			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
}
	
	//creamos usuario y lo agregamos a la base de datos
	public static void crearUsuario(){
		Connection con;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/biblioteca", "root", "");
			Scanner lector = new Scanner(System.in);
			System.out.println("Introduce un nombre");
			String nombre = lector.nextLine();
			System.out.println("Introduce un apellido");
			String apellido = lector.nextLine();
			System.out.println("Introduce una edad");
			int edad = Integer.parseInt(lector.nextLine());
			
		
			PreparedStatement pst = con.prepareStatement("INSERT INTO usuarios (id, nombre, apellido, edad) VALUES (NULL,?,?,?)");
			pst.setString(1, nombre);
			pst.setString(2,  apellido);
			pst.setInt(3, edad);
			
			pst.execute();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			

			
		}
	
	}
	public static void modificar(){// Metodo para modificar
		Connection con;
		Scanner lector = new Scanner(System.in);
		try
		{
		   con = DriverManager.getConnection("jdbc:mysql://localhost/biblioteca", "root", "");
		   Statement st = con.createStatement();
			System.out.println ("Introduce el id a modicar:");
			int id = lector.nextInt();
			System.out.println ("Introduce el nuevo nombre:");
			String nombre=lector.next();
			
			System.out.println ("Introduce el nuevo apellido:");
			String apellido=lector.next();
			
			System.out.println ("Introduce la nueva edad:");
			int edad = lector.nextInt();
			
			
		  st.executeUpdate("UPDATE usuarios SET nombre='"+nombre+"', apellido='"+apellido+"', edad='"+edad+"' WHERE id="+id);
		  System.out.println("Usuario actualizado");
		  st.close();
		}
		catch (Exception e)
		{
		   e.printStackTrace();
		}
	
	}
	
	public static void eliminarUsuario(){
		Connection  con;
		Scanner lector = new Scanner(System.in);
		try {
			  con = DriverManager.getConnection("jdbc:mysql://localhost/biblioteca", "root", "");
			  
			  Statement st = con.createStatement();

			  // borra un usuario en concreto
			 
			  System.out.println("Introduce el id del usuario a eliminar");
			  int id=lector.nextInt();
			  
			  st.execute("DELETE FROM usuarios WHERE id="+id);


			} catch (Throwable e)  {
			  System.out.println("Ha fallado el borrado de datos");
			  e.printStackTrace();
			}
	}
}






