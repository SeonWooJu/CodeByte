package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Database.DBCP;
import VO.Banner_VO;
import VO.Info_VO;
import VO.Question_Log_VO;
import VO.Question_Solve_VO;
import VO.Question_VO;
import VO.Rank_List_VO;
import VO.Top_3_VO;
import VO.Update_List_VO;
import VO.compiler_VO;
import compiler.java;

public class Index_DAO {
	Connection conn = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	String sql = "";

	public ArrayList<Update_List_VO> getUpdate_List() {

		ArrayList<Update_List_VO> updatelist = null;

		try {
			conn = DBCP.getConn();

			sql = "SELECT * FROM UPDATE_LIST ORDER BY TO_DATE(U_L_DATE) DESC";

			stmt = conn.prepareStatement(sql);

			rs = stmt.executeQuery();

			updatelist = new ArrayList<Update_List_VO>();

			int count = 0;

			while (rs.next()) {
				if (count == 11) {
					break;
				}

				Update_List_VO vo = new Update_List_VO();

				vo.setText(rs.getString(2));
				vo.setDate(rs.getString(1));

				updatelist.add(vo);

				count++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			if (stmt != null)
				try {
					stmt.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
		}

		return updatelist;
	}

	public ArrayList<Top_3_VO> getTop_3() {

		ArrayList<Top_3_VO> top_3 = null;

		try {
			conn = DBCP.getConn();

			sql = "SELECT U_NAME, U_POINT FROM USER_INFO ORDER BY U_POINT DESC";

			stmt = conn.prepareStatement(sql);

			rs = stmt.executeQuery();

			top_3 = new ArrayList<Top_3_VO>();

			int count = 0;

			while (rs.next()) {
				if (count == 3) {
					break;
				}

				Top_3_VO vo = new Top_3_VO();

				vo.setName(rs.getString("U_NAME"));
				vo.setPoint(rs.getInt("U_POINT"));

				top_3.add(vo);

				count++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			if (stmt != null)
				try {
					stmt.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
		}

		return top_3;
	}

	public Banner_VO getBanner() {
		Banner_VO vo = null;

		try {
			conn = DBCP.getConn();

			sql = "SELECT NVL(COUNT(Q_TYPE),0) Basic_Question FROM QUESTION WHERE Q_TYPE = 0";

			stmt = conn.prepareStatement(sql);

			rs = stmt.executeQuery();

			vo = new Banner_VO();

			if (rs.next()) {
				vo.setBasic_Question(rs.getInt("Basic_Question"));
			}

			sql = "SELECT NVL(COUNT(Q_TYPE),0) Custom_Question FROM QUESTION WHERE Q_TYPE = 1";

			stmt = conn.prepareStatement(sql);

			rs = stmt.executeQuery();

			vo = new Banner_VO();

			if (rs.next()) {
				vo.setCustom_Question(rs.getInt("Custom_Question"));
			}

			sql = "SELECT NVL(COUNT(USER_CODE),0) User_Count FROM USER_INFO";

			stmt = conn.prepareStatement(sql);

			rs = stmt.executeQuery();

			vo = new Banner_VO();

			if (rs.next()) {
				vo.setUser_Count(rs.getInt("user_Count"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			if (stmt != null)
				try {
					stmt.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
		}

		return vo;
	}

	public int getId_Ck(String id) {
		int number = 0;
		try {
			conn = DBCP.getConn();

			sql = "SELECT U_ID FROM USER_INFO WHERE U_ID = ?";

			stmt = conn.prepareStatement(sql);

			stmt.setString(1, id);

			rs = stmt.executeQuery();

			if (rs.next()) {
				number++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			if (stmt != null)
				try {
					stmt.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
		}
		return number;
	}

	public boolean addJoin(String id, String pw, String name, String email) {
		boolean tf = true;
		try {
			conn = DBCP.getConn();

			sql = "INSERT INTO USER_INFO " + "VALUES((SELECT NVL(MAX(USER_CODE),1000)+1 FROM USER_INFO),?,?,?,?,0,0)";
			// 유저번호,이름,이메일,아이디,비밀번호,접근,포인트

			stmt = conn.prepareStatement(sql);

			stmt.setString(1, name);
			stmt.setString(2, email);
			stmt.setString(3, id);
			stmt.setString(4, pw);

			int cunt = stmt.executeUpdate();

			if (cunt == 1) {
				tf = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			tf = true;
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			if (stmt != null)
				try {
					stmt.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}

		}
		return tf;
	}

	public int getLogin(String id, String pw) {
		int number = 0;
		try {
			conn = DBCP.getConn();

			sql = "SELECT USER_CODE FROM USER_INFO WHERE U_ID = ? AND U_PW = ?";

			stmt = conn.prepareStatement(sql);

			stmt.setString(1, id);
			stmt.setString(2, pw);

			rs = stmt.executeQuery();

			if (rs.next()) {
				number = rs.getInt("USER_CODE");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			if (stmt != null)
				try {
					stmt.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}

		}
		return number;
	}

	public Info_VO getInfo(int info_code) {
		Info_VO vo = null;

		try {
			conn = DBCP.getConn();

			sql = "SELECT U_NAME, U_ID, U_EMAIL FROM USER_INFO WHERE USER_CODE = ?";

			stmt = conn.prepareStatement(sql);

			stmt.setInt(1, info_code);

			rs = stmt.executeQuery();

			vo = new Info_VO();

			if (rs.next()) {
				vo.setName(rs.getString("U_NAME"));
				vo.setId(rs.getString("U_ID"));
				vo.setEmail(rs.getString("U_EMAIL"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			if (stmt != null)
				try {
					stmt.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
		}

		return vo;
	}

	public ArrayList<Rank_List_VO> getRank_List() {

		ArrayList<Rank_List_VO> ranklist = null;

		try {
			conn = DBCP.getConn();

			sql = "SELECT U_NAME, U_POINT FROM USER_INFO ORDER BY U_POINT DESC";

			stmt = conn.prepareStatement(sql);

			rs = stmt.executeQuery();

			ranklist = new ArrayList<Rank_List_VO>();

			int count = 1;

			while (rs.next()) {
				Rank_List_VO vo = new Rank_List_VO();

				vo.setRank(count);
				vo.setName(rs.getString("U_NAME"));
				vo.setPoint(rs.getInt("U_POINT"));

				ranklist.add(vo);

				count++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			if (stmt != null)
				try {
					stmt.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
		}

		return ranklist;
	}

	public ArrayList<Question_Log_VO> getQuestion_Log() {

		ArrayList<Question_Log_VO> loglist = null;

		try {
			conn = DBCP.getConn();

			sql = "SELECT Q_NUMBER, Q_L_TIME, Q_L_DATE, ANSWER_YN  FROM QUESTION_LOG ORDER BY Q_L_DATE DESC";

			stmt = conn.prepareStatement(sql);

			rs = stmt.executeQuery();

			loglist = new ArrayList<Question_Log_VO>();

			int count = 1;

			while (rs.next()) {
				Question_Log_VO vo = new Question_Log_VO();

				vo.setNumber(rs.getInt("Q_NUMBER"));
				vo.setTime(rs.getString("Q_L_TIME"));
				vo.setDate(rs.getString("Q_L_DATE"));
				if (rs.getInt("ANSWER_YN") == 0) {
					vo.setTy("오답");
				} else {
					vo.setTy("정답");
				}

				loglist.add(vo);

				count++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			if (stmt != null)
				try {
					stmt.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
		}

		return loglist;
	}

	public ArrayList<Question_VO> getQuestion() {

		ArrayList<Question_VO> questionlist = null;

		try {
			conn = DBCP.getConn();

			sql = "SELECT Q_TITLE, Q_NUMBER, Q_POINT FROM QUESTION WHERE Q_TYPE = 0 ORDER BY Q_NUMBER ASC";

			stmt = conn.prepareStatement(sql);

			rs = stmt.executeQuery();

			questionlist = new ArrayList<Question_VO>();

			int count = 1;

			while (rs.next()) {
				Question_VO vo = new Question_VO();

				vo.setNumber(count);
				vo.setTitle(rs.getString("Q_TITLE"));
				vo.setCnumber(rs.getInt("Q_NUMBER"));
				vo.setPoint(rs.getInt("Q_POINT"));

				questionlist.add(vo);

				count++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			if (stmt != null)
				try {
					stmt.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
		}

		return questionlist;
	}

	public Question_Solve_VO getSolve(int number) {
		Question_Solve_VO vo = null;

		try {
			conn = DBCP.getConn();

			sql = "SELECT q.Q_TITLE, q.Q_TEXT, qp.Q_IN_VALUE_1, qp.Q_OUT_VALUE_1, qp.Q_KIND "
					+ "FROM QUESTION q, QUESTION_PARA qp " + "WHERE q.Q_NUMBER = qp.Q_NUMBER AND q.Q_NUMBER = ?";

			stmt = conn.prepareStatement(sql);

			stmt.setInt(1, number);

			rs = stmt.executeQuery();

			vo = new Question_Solve_VO();

			if (rs.next()) {
				vo.setTitle(rs.getString("Q_TITLE"));
				vo.setText(rs.getString("Q_TEXT"));
				vo.setIn(rs.getString("Q_IN_VALUE_1"));
				vo.setOut(rs.getString("Q_OUT_VALUE_1"));
				vo.setKind(rs.getInt("Q_KIND"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			if (stmt != null)
				try {
					stmt.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
		}

		return vo;
	}

	public String getCompiler(int number, String code, int info_code) {
		String ty = "";
		try {
			conn = DBCP.getConn();

			sql = "SELECT Q_IN_VALUE_1, Q_IN_VALUE_2, Q_OUT_VALUE_1, Q_OUT_VALUE_2, Q_KIND " + "FROM QUESTION_PARA "
					+ "WHERE Q_NUMBER = ?";

			stmt = conn.prepareStatement(sql);

			stmt.setInt(1, number);

			rs = stmt.executeQuery();

			String in_1 = "", in_2 = "", out_1 = "", out_2 = "";
			int kind = 0;

			if (rs.next()) {
				in_1 = rs.getString("Q_IN_VALUE_1");
				in_2 = rs.getString("Q_IN_VALUE_2");
				out_1 = rs.getString("Q_OUT_VALUE_1");
				out_2 = rs.getString("Q_OUT_VALUE_2");
				kind = rs.getInt("Q_KIND");
			}
			
			java com = new java();
			compiler_VO vo_1 = null;
			compiler_VO vo_2 = null;
			
			int yn = 0;
			
			if (kind == 0) {
				int i_in = Integer.parseInt(in_1);
				vo_1 = com.integer(code, i_in);
				i_in = Integer.parseInt(in_2);
				vo_2 = com.integer(code, i_in);
				
				if (Integer.parseInt(vo_1.getChnew()) == Integer.parseInt(out_1) && Integer.parseInt(vo_2.getChnew()) == Integer.parseInt(out_2)) {
					ty="정답입니다.";
					yn = 1;
				}else {
					ty="오답입니다.";
					yn = 0;
				}
			} else if (kind == 1) {
				vo_1 = com.string(code, in_1);
				vo_2 = com.string(code, in_2);
				if (vo_1.getChnew().equals(out_1) && vo_2.getChnew().equals(out_2)) {
					ty="정답입니다.";
					yn = 1;
				}else {
					ty="오답입니다.";
					yn = 0;
				}
			}
			
			
			
			sql = "SELECT Q_NUMBER " + 
					"FROM QUESTION_LOG " + 
					"WHERE USER_CODE = ? AND Q_NUMBER = ? AND ANSWER_YN = 1";

			stmt = conn.prepareStatement(sql);

			stmt.setInt(1, info_code);
			stmt.setInt(2, number);

			rs = stmt.executeQuery();
			
			int cc = 0;
			
			if(rs.next()) {
				cc++;
			}
			if(cc==0) {
				if(yn==1) {
					sql = "SELECT Q_POINT " + "FROM QUESTION "
							+ "WHERE Q_NUMBER = ?";
		
					stmt = conn.prepareStatement(sql);
		
					stmt.setInt(1, number);
		
					rs = stmt.executeQuery();
					
					int point = 0;
					
					if(rs.next()) {
						point = rs.getInt("Q_POINT");
					}
					
					sql = "SELECT U_POINT FROM USER_INFO "
							+ "WHERE USER_CODE = ?";
		
					stmt = conn.prepareStatement(sql);
		
					stmt.setInt(1, info_code);
		
					rs = stmt.executeQuery();
					
					int pointall = 0;
					if(rs.next()) {
						pointall = rs.getInt("U_POINT");
					}
					
					sql = "UPDATE USER_INFO SET U_POINT = ? "
							+ "WHERE USER_CODE = ?";
		
					stmt = conn.prepareStatement(sql);
		
					stmt.setInt(1, pointall+point);
					stmt.setInt(2, info_code);
		
					stmt.executeUpdate();
				}
			}
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			Date time = new Date();
			sql = "INSERT INTO QUESTION_LOG VALUES(?,?,?,?,?,?)";

			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, dateFormat.format(time));
			stmt.setInt(2, number);
			stmt.setInt(3, info_code);
			stmt.setString(4, vo_1.getMs());
			stmt.setString(5, code);
			stmt.setInt(6, yn);

			stmt.executeUpdate();
			
				
		} catch (Exception e) {
			e.printStackTrace();
			ty="오답입니다.";
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			if (stmt != null)
				try {
					stmt.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
		}
		return ty;
	}

	public String getCode_Log(String code_number) {
		String code = "";
		try {
			conn = DBCP.getConn();

			sql = "SELECT CODE "
					+ "FROM QUESTION_LOG " + "WHERE Q_L_DATE = ?";

			stmt = conn.prepareStatement(sql);

			stmt.setString(1, code_number);

			rs = stmt.executeQuery();

			if (rs.next()) {
				code = rs.getString("CODE");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			if (stmt != null)
				try {
					stmt.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
		}

		return code;
	}

	public ArrayList<Question_VO> getCustom_Question() {

		ArrayList<Question_VO> customlist = null;

		try {
			conn = DBCP.getConn();

			sql = "SELECT Q_TITLE, Q_NUMBER, Q_POINT FROM QUESTION WHERE Q_TYPE = 1 ORDER BY Q_NUMBER ASC";

			stmt = conn.prepareStatement(sql);

			rs = stmt.executeQuery();

			customlist = new ArrayList<Question_VO>();

			int count = 1;

			while (rs.next()) {
				Question_VO vo = new Question_VO();

				vo.setNumber(count);
				vo.setTitle(rs.getString("Q_TITLE"));
				vo.setCnumber(rs.getInt("Q_NUMBER"));
				vo.setPoint(rs.getInt("Q_POINT"));

				customlist.add(vo);

				count++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			if (stmt != null)
				try {
					stmt.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
		}

		return customlist;
	}

	public boolean addQuestion(int info_code, String title, String text, int kind, String in_1, String out_1, String in_2, String out_2) {
		boolean ty = true;
		try {
			conn = DBCP.getConn();
			conn.setAutoCommit(false);

			sql = "SELECT NVL(MAX(Q_NUMBER),1000)+1 MAX_NUMBER FROM QUESTION";
			// 유저번호,이름,이메일,아이디,비밀번호,접근,포인트

			stmt = conn.prepareStatement(sql);

			rs = stmt.executeQuery();
			
			int max_number = 0;
			
			if (rs.next()) {
				max_number = rs.getInt("MAX_NUMBER");
			}
			
			sql = "INSERT INTO QUESTION VALUES(?,?,5,?,?,1,1)";

			stmt = conn.prepareStatement(sql);

			stmt.setInt(1, max_number);
			stmt.setString(2, title);
			stmt.setString(3, text);
			stmt.setInt(4, info_code);

			int cunt = stmt.executeUpdate();
			int num = 0;
			
			if (cunt == 1) {
				num++;
			}
			
			sql = "INSERT INTO QUESTION_PARA VALUES(?,?,?,?,?,?)";

			stmt = conn.prepareStatement(sql);

			stmt.setInt(1, max_number);
			stmt.setString(2, in_1);
			stmt.setString(3, out_1);
			stmt.setString(4, in_2);
			stmt.setString(5, out_2);
			stmt.setInt(6, kind);

			cunt = stmt.executeUpdate();
			if (cunt == 1) {
				num++;
			}
			
			if (num == 2) {
				conn.commit();
				ty = false;
			} else {
				conn.rollback();
				ty = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			ty = true;
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			if (stmt != null)
				try {
					stmt.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}

		}
		return ty;
	}

	public int getPoint(int info_code) {
		int point = 0;
		try {
			conn = DBCP.getConn();

			sql = "SELECT U_POINT FROM USER_INFO WHERE USER_CODE = ?";

			stmt = conn.prepareStatement(sql);

			stmt.setInt(1, info_code);

			rs = stmt.executeQuery();

			if (rs.next()) {
				point = rs.getInt("U_POINT");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			if (stmt != null)
				try {
					stmt.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
		}
		return point;
	}

}
