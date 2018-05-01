import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BotToken {
	
	static String tokenUrl = null;
	PreparedStatement statement = null;
	ResultSet rs = null;

	public Connection connection(String dbFileName) throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		String url = "jdbc:sqlite:database/idDB.db";
		Connection conn = DriverManager.getConnection(url);
		statement = conn.prepareStatement("select * from Code where ID=GRUPPE;");
		rs = statement.executeQuery();
		
		tokenUrl = rs.getString("String");
		
		return conn;
	}
	
	public static final String discordToken = tokenUrl;
}
