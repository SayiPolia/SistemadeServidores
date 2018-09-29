package modelo;

import java.sql.Date;

public class servidor {
	private int id ;
	private Date fechapublicacion;
	private String nombre;
	private String descripcion;
	private String detalle;
	
	
public servidor(int id) {
	this.id=id;
	
}

public int getId() {
	return id;
}
public Date getFecha() {
	return fechapublicacion;
}
public String getNombre() {
	return nombre;
}
public String getDescripcion() {
	return descripcion;
}
public String getDetalle() {
	return detalle;
}
public void setId(int aux) {
	id=aux;
}
public void setNombre(String aux) {
	nombre=aux;
}
public void setDetalle(String aux) {
	detalle=aux;
}
public void setDescripcion(String aux) {
	descripcion=aux;
}
public void setFecha(Date aux) {
	fechapublicacion=aux;
}
}
