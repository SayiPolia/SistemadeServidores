package dao;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Statement;

import modelo.servidor;
import modelo.solicitud;

/**
 * Servlet implementation class solicituddao
 */
@WebServlet("/solicituddao")
public class solicituddao extends HttpServlet {
	private DbConnection conn;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public solicituddao(DbConnection con) {
    	 this.conn = con;
        // TODO Auto-generated constructor stub
    }
    public boolean insert(solicitud sol) {
    	try {
    		Date fecha=new Date();
        	SimpleDateFormat date= new SimpleDateFormat("yyyy-MM-dd");
    		String sql="insert into solicitud (id,fecha,nombre,email,telefono,direccion,idservidor) values(?,?,?,?,?,?,?)";
    		 PreparedStatement pr = conn.getConnection().prepareStatement(sql);
    		pr.setInt(1,sol.getId());
    		pr.setString(2,date.format(fecha));
    		pr.setString(3,sol.getNombre());
    		pr.setString(4,sol.getEmail());
    		pr.setString(5,sol.getTel());
    		pr.setString(6,sol.getDireccion());
    		pr.setInt(7,sol.getServidor().getId());
    		pr.executeUpdate();
    	            
              return true;
    		
    	}catch(SQLException e){
    		System.out.println("Error en solicituddao.insert: "+ e.getMessage());
    		return false;
    		
    	}
    }
    public List<solicitud> getAll(){
    	try {
    		String sql="select * from solicitud order by id desc";
    		PreparedStatement pr=conn.getConnection().prepareStatement(sql);
    		ResultSet rs =pr.executeQuery();
    		List<solicitud> list= new LinkedList<>();
    		servidordao sdao=new servidordao(conn);
    		solicitud sol;
    		while(rs.next()) {
    			sol=new solicitud(rs.getInt("id"));    			
    			sol.setFecha(rs.getDate("fecha"));
    			sol.setNombre(rs.getString("nombre"));
    			sol.setEmail(rs.getString("email"));
    			sol.setTelefono(rs.getString("telefono"));
    			sol.setDireccion((rs.getString("direccion")));
    			servidor srv=sdao.getById(rs.getInt("idservidor"));
    			sol.setServidor(srv);
    					
    			list.add(sol);
    			 
    			System.out.println(list);
    		}
    		return list;
    	}catch(SQLException e) {
    		System.out.println("Error en solicitud.getAll: "+ e.getMessage());
    		return null;
    		
    	}
    	
    }
}
