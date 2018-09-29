package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DbConnection;
import dao.servidordao;
import modelo.servidor;

/**
 * Servlet implementation class ServidorController

@WebServlet("/servidorcontroller") */
public class ServidorController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServidorController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		if("ver".equals(action)) {
			this.verDetalle(request, response);
        } else if ("lista".equals(action)) {
            this.verTodas(request, response);
        } else if ("enviar".equals(action)) {
            this.mostrarFormulario(request, response);
        }
    
	}

	private void verTodas(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 DbConnection conn = new DbConnection();
	       servidordao servidorDao = new servidordao(conn);
	       List<servidor> srv = servidorDao.getAll();
	        conn.disconnect();

	        // Compartimos la variable srv para acceder desde la vista con Expression Language
	        request.setAttribute("servidores", srv);
	        RequestDispatcher rd;

	        // Enviarmos respuesta a la vista detalle.jsp
	        rd = request.getRequestDispatcher("/servidores.jsp");
	        rd.forward(request, response);
	    }	

		
	

	private void mostrarFormulario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 // Recibimos id del servidor a consultar
        int idServidor = Integer.parseInt(request.getParameter("id"));
        DbConnection conn = new DbConnection(); 
       // LCDLL no funciona
       servidordao servidorDao = new servidordao(conn);
       servidor srv = servidorDao.getById(idServidor);
        conn.disconnect();
        
       
        // Compartimos la variable srv para acceder desde la vista con Expression Language
        request.setAttribute("servidor",srv );
        
        RequestDispatcher rd;

        // Enviarmos respuesta a la vista detalle.jsp
        rd = request.getRequestDispatcher("/frm.jsp");
        rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		servidor srv=new servidor(0);
		String nombreparam=request.getParameter("nombre");
		srv.setNombre(nombreparam);
		String detalleparam=request.getParameter("detalle");
		srv.setDetalle(detalleparam);
		String descparam=request.getParameter("descripcion");
		srv.setDescripcion(descparam);
		System.out.println(srv);
		DbConnection conn=new DbConnection();
		servidordao sdao=new servidordao(conn);
		boolean status=sdao.insert(srv);
		
		//mensaje para el usuario
		String msg="";
		if(status) {
			msg="El servidor fue guardado correctamente.";
		}else {
			msg="Ocurrio un error.El servidor no fue guardado en la BD.";
		}
		conn.disconnect();
		RequestDispatcher rd;
		
		//se comparte msg para que pueda ser vista con EL
		request.setAttribute("message",msg); //<-- message esta en mensaje_admin.jsp
		rd=request.getRequestDispatcher("/mensaje_admin.jsp");
		rd.forward(request,response);
		
	}
	
	 protected void verDetalle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	        // Recibimos id del servidor a consultar
	        int idServidor = Integer.parseInt(request.getParameter("id"));
	        DbConnection conn = new DbConnection();
	       servidordao servidorDao = new servidordao(conn);
	       servidor srv = servidorDao.getById(idServidor);
	        conn.disconnect();

	        // Compartimos la variable srv para acceder desde la vista con Expression Language
	        request.setAttribute("servidor", srv);
	        RequestDispatcher rd;

	        // Enviarmos respuesta a la vista detalle.jsp
	        rd = request.getRequestDispatcher("/detalle.jsp");
	        rd.forward(request, response);
	    }	

}
