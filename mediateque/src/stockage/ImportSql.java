package stockage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class ImportSql {

	private static ImportSql importSql = new ImportSql(); //Singleton

	private String url = "jdbc:mysql://localhost:3306/media";
	private String user = "root";

	private Connection connection;
	private PreparedStatement selectAll;

	private ImportSql()
	{
		this.initialize();
	}

	public static ImportSql getInstance()
	{
		return ImportSql.importSql;
	}

	private void initialize()
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.connection = DriverManager.getConnection(url, user,"");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public ResultSet selectData(String table) throws SQLException
	{
		String selectRequest = "SELECT * FROM "+table;
		selectAll = this.connection.prepareStatement(selectRequest);
		return selectAll.executeQuery();
	}

	public void closeData()
	{
		try {
			this.connection.close();
			this.selectAll.close();
		} catch (SQLException e) {e.printStackTrace();}
	}	
}