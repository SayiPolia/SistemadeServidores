package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DbConnection;
import dao.servidordao;
import dao.usuariodao;
import modelo.usuario;

/**
 * Servlet implementation class adminController
 */
@WebServlet(name = "admincontroller", urlPatterns = { "/admincontroller" })
public class adminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public adminController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		HttpSession session=request.getSession();
		RequestDispatcher rd;
		String msg="";
		switch(action) {
		case "login":
		if(session.getAttribute("usuario")==null) {//cuando no existe sesion de usuario
			request.setAttribute("message",msg);
			rd=request.getRequestDispatcher("/login.jsp");//lo manda a iniciar sesion
			rd.forward(request,response);
		}else {//ya esta logeado pero lo mandamos a index de administracion
			rd=request.getRequestDispatcher("/admin.jsp");
			rd.forward(request,response);
			
		}
		break;
		case "crear":
			if(session.getAttribute("usuario")==null) {
				msg="Acceso Denegado";
				request.setAttribute("message",msg);
				rd=request.getRequestDispatcher("/login.jsp");
				rd.forward(request,response);
			}else {
				rd=request.getRequestDispatcher("/frmservidor.jsp");
				rd.forward(request,response);
				
			}
			break;
		case "eliminar":
			if(session.getAttribute("usuario")==null) {
				msg="Acceso Denegado";
				request.setAttribute("message",msg);
				rd=request.getRequestDispatcher("/login.jsp");
				rd.forward(request,response);
			} else {
                this.eliminarServidor(request, response);
            }
            break;
        /* 
        Cuando es logout (GET), cerramos la session. Aqui se puede comprobar en el administrador
        de aplicaciones de tomcat, como la session es destruida.
        */
        case "logout":
            session.invalidate();
            response.sendRedirect(request.getContextPath() + "/homepage");
            break;
    }
		
	}
	 private void eliminarServidor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        // Recibimos el id del servidor que vamos a eliminar
	        int idServidorParam = Integer.parseInt(request.getParameter("idServidor"));
	        DbConnection conn = new DbConnection();
	        servidordao servidorDao = new servidordao(conn);
	        int respuesta = servidorDao.delete(idServidorParam);
	        String msg = "";
	        if (respuesta == 1) { // Fue afectado un registro, esto significa que si se borro
	            msg = "El servidor fue eliminado correctamente.";
	        } else {
	            msg = "Ocurrio un error. El servidor no fue eliminado.";
	        }
	        conn.disconnect();
	        request.setAttribute("message", msg);
	        RequestDispatcher rd;
	        rd = request.getRequestDispatcher("/mensaje_admin.jsp");
	        rd.forward(request, response);
	    }

	 protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        // Recibimos parametros del formulario de login
	        String userParam = request.getParameter("user");
	        String passParam = request.getParameter("pass");
	        String msg = "";
	        // Recuperamos una instancia del objeto HttpSession
	        HttpSession session = request.getSession();

	        DbConnection conn = new DbConnection();
	        usuariodao usuarioDao = new usuariodao(conn);
	        // Verificamos en la BD, si es un usuario correcto.
	        usuario usuario = usuarioDao.login(userParam, passParam);
	        conn.disconnect();
	        if(usuario!=null) {
	        	System.out.println("existe"+ usuario.getId() + usuario.getNombre());
	        }

	        RequestDispatcher rd;
	        if (usuario.getId() > 0) {//cuando es 0 el usuario no existe
	            // Creamos una variable de session, con el registro de usuario (Bean)
	            // Verificar en el administrador de aplicaciones de tomcat.
	            session.setAttribute("usuario", usuario);//se comparte para todos los jsp el usuario
	            rd = request.getRequestDispatcher("/admin.jsp");
	            rd.forward(request, response);

	        } else {
	            msg = "Usuario y/o password incorrectos";
	            request.setAttribute("message", msg);
	            rd = request.getRequestDispatcher("/login.jsp");
	            rd.forward(request, response);
	        }
	    }
}
