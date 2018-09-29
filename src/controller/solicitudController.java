package controller;

import java.io.IOException;
import java.io.File;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.servlet.http.Part;

import util.Utility;
import javax.servlet.http.Part;

import dao.DbConnection;
import dao.servidordao;
import dao.solicituddao;
import modelo.servidor;
import modelo.solicitud;

/**
 * Servlet implementation class solicitudController

@WebServlet(name = "solicitudcontroller", urlPatterns = { "/solicitudcontroller" })
 */
@MultipartConfig
public class solicitudController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String UPLOAD_DIR = "uploads";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public solicitudController() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        //Recibimos el parametro action, el cual servira para saber que accion GET se ejecutara
        String action = request.getParameter("action");
        // Recuperamos la session activa que viene junto con el request
        HttpSession session = request.getSession();
        RequestDispatcher rd;
        switch (action) {
            case "solicitudes":
                if (session.getAttribute("usuario") == null) {
                    rd = request.getRequestDispatcher("/login.jsp");
                    rd.forward(request, response);
                } else {
                    this.verTodas(request, response);
                }
                break;
        }
    	
    	
    	
    }
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		solicitud sol=new solicitud(0);
		String nombre=request.getParameter("nombre");
		sol.setNombre(nombre);		
		String email=request.getParameter("email");
		sol.setEmail(email);		
		String telef=request.getParameter("telefono");
		sol.setTelefono(telef);		
		String direccion=request.getParameter("direccion");
		sol.setDireccion(direccion);		
	
		int idServidorParam = Integer.parseInt(request.getParameter("idservidor"));
		
		DbConnection conn=new DbConnection();
		servidordao sdao=new servidordao(conn);
		servidor srv=sdao.getById(idServidorParam);
		sol.setServidor(srv);
		
            solicituddao soldao=new solicituddao(conn);
            
            String  msg;
            if(soldao.insert(sol)) { 
            	msg = "<b>" + sol.getNombre() + "</b> hemos recibido tus datos."
                        + "<br> Revisaremos tu solicitud y nos pondremos en contacto contigo.<br><br>Gracias.";
            }else {
    			msg="Ocurrio un error.El servidor no fue guardado en la BD.";
    		}
            conn.disconnect();            
            request.setAttribute("message", msg);
            RequestDispatcher rd = request.getRequestDispatcher("/mensaje_guest.jsp");
            rd.forward(request, response);
        
	}
	private void verTodas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String msg = null; 
		DbConnection conn = new DbConnection();
	       solicituddao soldao = new solicituddao(conn);
	       List<solicitud> sol = soldao.getAll();
	       System.out.println("t"+sol.size());
	        conn.disconnect();
	        request.setAttribute("message", msg);
	        // Compartimos la variable srv para acceder desde la vista con Expression Language
	        request.setAttribute("solicitudes", sol);
	        RequestDispatcher rd;

	        // Enviarmos respuesta a la vista detalle.jsp
	        rd = request.getRequestDispatcher("/solicitudes.jsp");
	        rd.forward(request, response);
	    }	

}
