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
import VO.Info_VO;

/**
 * Servlet implementation class Info_Values
 */
//@WebServlet("/Info_Values")
public class Info_Values extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Info_Values() {
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
        HttpSession session = request.getSession();
        Object info_code = null;
        try {
            info_code = session.getAttribute("info_code");
		} catch (Exception e) {
			// TODO: handle exception
			info_code = null;
			e.printStackTrace();
		}
        JSONObject o = new JSONObject();
        
        Index_DAO dao = new Index_DAO();
        Info_VO vo = dao.getInfo((Integer)info_code);
        
        o.put("name", vo.getName());
        o.put("id", vo.getId());
        o.put("email", vo.getEmail());
        
        out.print(o); //response
	}

}
