package dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import modelo.servidor;

public class servidordao {
	private DbConnection conn;

    public servidordao(DbConnection conn) {
        this.conn = conn;
    }
    public boolean insert(servidor srv) {
    	Date fecha=new Date();
    	SimpleDateFormat date= new SimpleDateFormat("yyyy-MM-dd");
    	try {
    		String sql="insert into servidor values(?,?,?,?,?)";
    		PreparedStatement pr=conn.getConnection().prepareStatement(sql);
    		pr.setInt(1,srv.getId());
    		pr.setString(2,date.format(fecha));
    		pr.setString(3,srv.getNombre());
    		pr.setString(4,srv.getDescripcion());
    		pr.setString(5,srv.getDetalle());
    		pr.executeUpdate();
    		return true;
    		
    	}catch(SQLException e){
    		System.out.println("Error en servidordao.insert: "+ e.getMessage());
    		return false;
    		
    	}
    	
    }
    public List<servidor> getUltimos(){
    	try {
    		String sql="select * from servidor order by idservidor desc limit 3";
    		PreparedStatement pr=conn.getConnection().prepareStatement(sql);
    		ResultSet rs =pr.executeQuery();
    		List<servidor> list= new LinkedList<>();
    		servidor srv;
    		while(rs.next()) {
    			srv=new servidor(rs.getInt("idservidor"));    			
    			srv.setFecha(rs.getDate("fechapublicacion"));
    			srv.setNombre(rs.getString("nombre"));
    			srv.setDescripcion(rs.getString("descripcion"));
    			srv.setDetalle(rs.getString("detalle"));
    			
    			list.add(srv);
    			System.out.println(list);
    		}
    		return list;
    	}catch(SQLException e) {
    		System.out.println("Error en servidordao.getUltimos: "+ e.getMessage());
    		return null;
    		
    	}
    	
    }
    public List<servidor> getAll(){
    	try {
    		String sql="select * from Servidor order by idservidor desc";
    		PreparedStatement pr=conn.getConnection().prepareStatement(sql);
    		ResultSet rs =pr.executeQuery();
    		List<servidor> list= new LinkedList<>();
    		servidor srv;
    		while(rs.next()) {
    			srv=new servidor(rs.getInt("idservidor"));    			
    			srv.setFecha(rs.getDate("fechapublicacion"));
    			srv.setNombre(rs.getString("nombre"));
    			srv.setDescripcion(rs.getString("descripcion"));
    			srv.setDetalle(rs.getString("detalle"));
    			
    			list.add(srv);
    			System.out.println(list);
    		}
    		return list;
    	}catch(SQLException e) {
    		System.out.println("Error en servidordao.getAll: "+ e.getMessage());
    		return null;
    		
    	}
    	
    }
    public servidor getById(int idServidor){

        try {
            String sql = "select * from servidor where idservidor=? limit 1";
            PreparedStatement preparedStatement = conn.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, idServidor); // Set idServidor
            ResultSet rs = preparedStatement.executeQuery();
            servidor srv = new servidor(0);
            while (rs.next()) {
                // Create an object for the movie
            	srv.setId(rs.getInt("idservidor"));
            	srv.setFecha(rs.getDate("fechaPublicacion"));
            	srv.setNombre(rs.getString("nombre"));
            	srv.setDescripcion(rs.getString("descripcion"));
            	srv.setDetalle(rs.getString("detalle"));
            }
            return srv;

        } catch (SQLException e) {            
            System.out.println("Error ServidorDao.getById: " + e.getMessage());
            return null;
        }
    }
    
    public List<servidor> getByQuery(String query){

        try {
            String sql = "select * from Servidor where (descripcion like ? or nombre like ?) order by idservidor desc";
            PreparedStatement preparedStatement = conn.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, "%" + query + "%");
            preparedStatement.setString(2, "%" + query + "%");
            ResultSet rs = preparedStatement.executeQuery();
            List<servidor> list = new LinkedList<>();
            servidor srv;
            while (rs.next()) {
            	srv = new servidor(rs.getInt("idservidor"));
            	srv.setFecha(rs.getDate("fechapublicacion"));
            	srv.setNombre(rs.getString("nombre"));
            	srv.setDescripcion(rs.getString("descripcion"));
            	srv.setDetalle(rs.getString("detalle"));                
                // Add servidor object to the list
                list.add(srv);
            }
            return list;

        } catch (SQLException e) {            
            System.out.println("Error ServidorDao.getByQuery: " + e.getMessage());
            return null;
        }
    }
    public int delete(int idServidor) {
        try {
            String sql = "delete from Servidor where idservidor=?";
            PreparedStatement preparedStatement = conn.getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, idServidor);
            int rows = preparedStatement.executeUpdate();
            return rows;

        } catch (SQLException e) {            
            System.out.println("Error ServidorDao.eliminar: " + e.getMessage());
            return 0;
        }
    }
}
