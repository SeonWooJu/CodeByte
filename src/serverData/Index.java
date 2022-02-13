package serverData;
 
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import DAO.Index_DAO;
import VO.Banner_VO;
import VO.Top_3_VO;
import VO.Update_List_VO;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;
/**
 * Servlet implementation class Serv
 */
//@WebServlet("/Index")
public class Index extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Index() {
        super();
        // TODO Auto-generated constructor stub
    }
 
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
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
            ArrayList<Update_List_VO> list = dao.getUpdate_List();
            
            String object = "{"
            		+ "\"updatelist\":[";
            
            int count = 0;
            
            for (Update_List_VO vo : list) {
    			object += "{\"text\":\""+vo.getText()+"\","
    					+ "\"date\":\""+vo.getDate()+"\"}";
    			
    			count++;
    			if(count <= 10) {
    				object += ",";
    			}
    		}
            object += "],"
            		+ "\"top3\":[";
            
            ArrayList<Top_3_VO> top = dao.getTop_3();
            
            count = 0;
            int number = 0;
            
            for (Top_3_VO vo : top) {
            	number += vo.getPoint();
			}
            
            for (Top_3_VO vo : top) {
            	object += "{\"name\":\""+vo.getName()+"\","
    					+ "\"point\":\""+vo.getPoint()+"\","
    					+ "\"margin\":\"" + (double) vo.getPoint() / (double) number * 100.0 + "\"}";
            	
            	count++;
    			if(count <= 2) {
    				object += ",";
    			}
			}
            
            object += "],";
            
            Banner_VO vo = dao.getBanner();
            
            object += "\"Basic\":"+vo.getBasic_Question()+","
            		+ "\"Custom\":"+vo.getCustom_Question()+","
            		+ "\"User\":"+vo.getUser_Count()+"}";
            
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
