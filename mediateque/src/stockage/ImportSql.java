package stockage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ImportSql {

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

	public void updateData(String table, String dataToUpdate[][]) throws SQLException
	{
		PreparedStatement updateAll;
		ResultSet resultSet = this.selectData(table);
		ResultSetMetaData rsmd = resultSet.getMetaData();
		int nbCol = rsmd.getColumnCount();
		String[] columnsNames = new String[nbCol];

		for (int i=0;i<nbCol;i++) //Affichage méta-données
		{
			columnsNames[i]=rsmd.getColumnName(i+1); //La méthodes getColumnName commence à 1
		}

		int y = 0;

		while(resultSet.next())
		{
			String request="UPDATE "+table+" SET ";
			for(int i=1; i<nbCol;i++)
			{
				request +=columnsNames[i]+" = ";
				
				if(dataToUpdate[y][i].equalsIgnoreCase("null"))
					request+=dataToUpdate[y][i];
				else
					request+="\'"+dataToUpdate[y][i]+"\'";
				
				if(i!=(nbCol-1))
					request+=", ";
			}
			request +=" WHERE "+ columnsNames[0]+" = "+dataToUpdate[y][0]+";"; 
			++y;
			updateAll = this.connection.prepareStatement(request);
			updateAll.executeUpdate();
		}
	}

	public void closeData()
	{
		try {
			this.connection.close();
			this.selectAll.close();
		} catch (SQLException e) {e.printStackTrace();}
	}	
}