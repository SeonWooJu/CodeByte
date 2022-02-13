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
 * Servlet implementation class Add_Question
 */
//@WebServlet("/Add_Question")
public class Add_Question extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Add_Question() {
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
		String title = request.getParameter("title");   
        String text = request.getParameter("text");  
        int kind = Integer.parseInt(request.getParameter("kind"));  
        String in_1 = request.getParameter("in_1");  
        String out_1 = request.getParameter("out_1");  
        String in_2 = request.getParameter("in_2");  
        String out_2 = request.getParameter("out_2");  
        
        PrintWriter out = response.getWriter();
        
        HttpSession session = request.getSession();
        
        JSONObject o = new JSONObject();
        
        Index_DAO dao = new Index_DAO();
        
		int info_code = 0;
        
        try {
        	Object object = session.getAttribute("info_code");
        	info_code = Integer.parseInt(String.valueOf(object));
		} catch (Exception e) {
			// TODO: handle exception
			info_code = 0;
			e.printStackTrace();
		}
        
        o.put("ty", dao.addQuestion(info_code, title, text, kind, in_1, out_1, in_2, out_2));
        
        out.print(o); //response
	}

}
