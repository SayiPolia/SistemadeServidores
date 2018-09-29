package modelo;

public class usuario {
private int id;
private String nombre;
private String username;
private String email;
private String pass;
private String perfil;
private String estado="activo";

public usuario(int idA) {
	id=idA;
}
public int getId() {
	return id;
}
public String getNombre() {
	return nombre;
}
public String getUsername() {
	return username;
	
}
public String getEmail() {
	return email;
}
public String getPerfil() {
	return perfil;
}
public String getPass() {
	return pass;
}
public String getEstado() {
	return estado;
}
public void setId(int idaux) {
	id=idaux;
}
public void setNombre(String aux) {
	nombre=aux;
}
public void setEmail(String aux) {
	email=aux;
}
public void setUsername(String aux) {
	username=aux;
}
public void setEstado(String aux) {
	estado=aux;
}
public void setPass(String aux) {
	pass=aux;
}
public void setPerfil(String aux) {
	perfil=aux;
}
public String toString() {
	return("Usuario: ID "+id + " nombre: "+ nombre+"username: "+username+"email: "+email+"estado: "+estado );
}
}
