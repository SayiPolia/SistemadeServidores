package modelo;

import java.sql.Date;

public class solicitud {
	private int id;
	private Date fecha;
	private String nombre;
	private String email;
	private String telefono;
	private String direccion;
	private String archivo;
	private servidor servidor;
	
public solicitud(int auxid) {
	id=auxid;
}
public int getId() {
	return id;
}
public String getNombre() {
	return nombre;
}

public String getEmail() {
	return email;
}
public String getTel() {
	return telefono;
}
public String getDireccion() {
	return direccion;
}
public Date getFecha() {
	return fecha;
}
public servidor getServidor() {
	return servidor;
}
public void setNombre(String aux) {
	nombre=aux;
}
public void setEmail(String aux) {
	email=aux;
}
public void setTelefono(String aux) {
	telefono=aux;
}
public void setDireccion(String aux) {
	direccion=aux;
}
public void setArchivo(String aux) {
	archivo=aux;
}
public void setFecha(Date aux) {
	fecha=aux;
}

public void setServidor(servidor aux) {
	servidor=aux;
}
public String toString() {
	return("Solicitud: ID "+id + " nombre: "+ nombre+"direccion: "+direccion+"email: "+email+"archivo: "+archivo+"servidor: "+servidor );
}

}
