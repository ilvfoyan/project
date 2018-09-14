package cn.java.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*import org.junit.Test;

import com.mysql.jdbc.Driver;*/

public class JDBC {
	
	private static String driver ;
	private static String url ;
	private static String user ;
	private static String password ;
	private JDBC() {}
	
	static { 
	   try {
		  driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
		  url="jdbc:sqlserver://localhost:1433;DatabaseName=mysql";
		  user="sa";
		  password="Yyt307*";
		  Class.forName(driver);
		  System.out.println("Successfully connect to the SQLServer.");
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}
	
	public static void free(ResultSet rs, Statement st,Connection conn) {
		// TODO Auto-generated method stub
		try { if (rs != null) rs.close();
		} catch (SQLException e) {e.printStackTrace();
		} finally {
			try { if (st != null) st.close();
			} catch (SQLException e) {e.printStackTrace();
			} finally {
				  if (conn != null)
				    try { conn.close();
				    } catch (SQLException e) {e.printStackTrace();
				   }
		          }
		     }
	}
}
