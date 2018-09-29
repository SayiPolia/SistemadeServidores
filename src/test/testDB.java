package test;

import com.sun.jndi.ldap.Connection;

import dao.DbConnection;
import dao.usuariodao;
import modelo.usuario;

public class testDB {
	public static void main(String[] args) {
		DbConnection conn=new DbConnection();
		usuariodao usu=new usuariodao(conn);
		usuario user=usu.login("admin","admin");
		System.out.println(user);
	}

}
