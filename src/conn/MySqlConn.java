package conn;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import conn.DatabaseConn;

public class MySqlConn implements DatabaseConn{
	private Connection conn = null;
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public Connection getConnection() {
		// TODO Auto-generated method stub
		if(conn==null){
			try {
				conn = DriverManager.getConnection("jdbc:mysql://localhost/studentinfo","root","1234");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return conn;
	}
}
