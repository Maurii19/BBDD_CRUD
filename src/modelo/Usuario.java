package modelo;

import java.util.ArrayList;
import java.util.Date;

public class Usuario {

	private int id;
	private String nombre;
	private String apellido;
	private int edad;
	private String dni;
	private Date fecha_nacimiento;
	private ArrayList<Prestamo> prestamos;
	
	
	
	public Usuario(){
		
	}
	
	public Usuario(int id, String nombre, String apellido, int edad, String dni, Date fecha_nacimiento) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad=edad;
		this.dni=dni;
		this.fecha_nacimiento = fecha_nacimiento;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public Date getFecha_nacimiento() {
		return fecha_nacimiento;
	}
	public void setFecha_nacimiento(Date fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	

}
