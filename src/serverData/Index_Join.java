package serverData;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import DAO.Index_DAO;

/**
 * Servlet implementation class Index_Join
 */
//@WebServlet("/Index_Join")
public class Index_Join extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Index_Join() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/x-json; charset=UTF-8");
        String id = request.getParameter("id");   
        String pw = request.getParameter("pw");  
        String name = request.getParameter("name");  
        String email = request.getParameter("email");  
        
        PrintWriter out = response.getWriter();
        
        JSONObject o = new JSONObject();
        
        Index_DAO dao = new Index_DAO();
        
        o.put("tf", dao.addJoin(id, pw, name, email));
        
        out.print(o); //response
	}

}
