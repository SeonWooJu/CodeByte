package serverData;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import DAO.Index_DAO;
import VO.Question_VO;
import VO.Rank_List_VO;

/**
 * Servlet implementation class Question
 */
//@WebServlet("/Custom_Question")
public class Custom_Question extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Custom_Question() {
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
		try {
        	response.setContentType("application/x-json; charset=UTF-8");
            PrintWriter out = response.getWriter();
            
            Index_DAO dao = new Index_DAO();
            ArrayList<Question_VO> list = dao.getCustom_Question();
            
            String object = "{"
            		+ "\"question\":[";
            
            int count = 0;
            
            for (Question_VO vo : list) {
    			object += "{\"number\":\""+vo.getNumber()+"\","
    					+ "\"title\":\""+vo.getTitle()+"\","
    					+ "\"cnumber\":\""+vo.getCnumber()+"\","
    					+ "\"point\":\""+vo.getPoint()+"\"}";
    			
    			count++;
    			if(count <= list.size()) {
    				object += ",";
    			}
    		}
            object += "]}";
            
            JSONObject obj = null;
        	JSONParser jsonParse = new JSONParser();
        	obj =  (JSONObject)jsonParse.parse(object);
            out.print(obj); //response
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
