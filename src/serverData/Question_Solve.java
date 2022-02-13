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
import VO.Question_Solve_VO;

/**
 * Servlet implementation class Question_Solve
 */
//@WebServlet("/Question_Solve")
public class Question_Solve extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Question_Solve() {
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
        int number = Integer.parseInt(request.getParameter("number"));
        
        JSONObject o = new JSONObject();
        
        Index_DAO dao = new Index_DAO();
        
        Question_Solve_VO vo = dao.getSolve(number);
        String string = "public void test(String string) {\n"
        		+ "\n"
        		+ "}";
        String integer = "public void test(int integer) {\n"
		        + "\n"
				+ "}";
        
        o.put("title", vo.getTitle());
        o.put("text", vo.getText());
        o.put("in", vo.getIn());
        o.put("out", vo.getOut());
        if (vo.getKind() == 0) {
        	o.put("kind", integer);
        } 
        else if (vo.getKind() == 1) {
        	o.put("kind", string);
        }
        System.out.println();
        out.print(o); //response
	}

}
