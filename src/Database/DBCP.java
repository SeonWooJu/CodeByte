package Database;

import java.sql.Connection;

import javax.naming.Context;
import javax.sql.DataSource;


public class DBCP {
	public static Connection getConn() {
		Connection conn = null;
	    Context initial = null;
	    DataSource ds = null;

	    try {
	        initial =  new javax.naming.InitialContext();
	        ds = (javax.sql.DataSource) initial.lookup("java:comp/env/jdbc/UserDatabase");
	        conn = ds.getConnection();
	        conn.setAutoCommit(false);
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        if (initial != null)
	        try {
	            initial.close();
	            initial = null;
	        } catch (Exception ee) {
	            ee.printStackTrace();
	        }
	    }

	    return conn;
	}
}
