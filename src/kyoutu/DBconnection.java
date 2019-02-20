package kyoutu;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBconnection {
	public static Connection getConnection() throws SQLException{
		Connection con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:XE",
					"yamasita",
					"shinemoon");

		return con;
	}

	public static void close(Connection con,  PreparedStatement ps  ,ResultSet rs) throws SQLException{
			if(null != rs){	rs.close();}

			if(null != ps){	ps.close();}

			if(null != con){con.close();}
	}
}
