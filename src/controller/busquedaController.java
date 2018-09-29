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
 * Servlet implementation class busquedacontroller
 */
@WebServlet("/busquedacontroller")
public class busquedaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public busquedaController() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String g=request.getParameter("query");
		List<servidor> lista=null;
		 DbConnection conn = new DbConnection();
	       servidordao servidorDao = new servidordao(conn);
	       lista=servidorDao.getByQuery(g);
	       conn.disconnect();
	       request.setAttribute("servidores", lista);
	        RequestDispatcher rd;

	        // Enviarmos respuesta a la vista detalle.jsp
	        rd = request.getRequestDispatcher("/servidores.jsp");
	        rd.forward(request, response);
	}

}
