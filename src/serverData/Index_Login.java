package serverData;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import DAO.Index_DAO;

/**
 * Servlet implementation class Index_Login
 */
//@WebServlet("/Index_Login")
public class Index_Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Index_Login() {
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
        PrintWriter out = response.getWriter();
        String id = request.getParameter("id");   
        String pw = request.getParameter("pw");   
        HttpSession session = request.getSession();
        
        JSONObject o = new JSONObject();
        
        Index_DAO dao = new Index_DAO();
        
        int number = dao.getLogin(id,pw);
        
        if(number == 0) {
        	o.put("tf", true);
        	
        }else {
        	session.setAttribute("info_code", number);
        	o.put("tf", false);
        }
        out.print(o); //response
	}

}
