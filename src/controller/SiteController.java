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
 * Servlet implementation class SiteControler

@WebServlet("/homepage")*/
public class SiteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd;
		DbConnection conn= new DbConnection();
		servidordao srv=new servidordao(conn);
		List<servidor> lista=srv.getUltimos();
		conn.disconnect();
		request.setAttribute("ultimos",lista);
		rd=request.getRequestDispatcher("/index.jsp");
		rd.forward(request, response);
		
		
	}

	

}
