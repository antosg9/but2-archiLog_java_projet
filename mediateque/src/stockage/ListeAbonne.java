package stockage;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import abonne.Abonne;

public class ListeAbonne{
	private static ListeAbonne instance = new ListeAbonne(); //Singleton

	private List<Abonne> liste = Collections.synchronizedList(new ArrayList<Abonne>()); //THREAD SAFE (se renseigner sur syncrhonisation explicite pour itération)

	private ListeAbonne(){
		this.initialiaze();
	}

	public static ListeAbonne getInstance()
	{
		return ListeAbonne.instance;
	}

	private synchronized void initialiaze() //Risque de concurrence ?
	{
		ImportSql importSql = ImportSql.getInstance();

		try {
			ResultSet resultSet = importSql.selectData("abonne");

			while(resultSet.next())	
			{
				int numAbo = resultSet.getInt(1);
				String nom = resultSet.getString(2);
				Date dateNaiss = resultSet.getDate(3);

				this.liste.add(new Abonne(numAbo, nom, dateNaiss));
			}
			resultSet.close();
		} catch (Exception e) {e.printStackTrace();}
	}

	public Abonne findAbo(int numAbo) throws Exception
	{
		for(int i =0;i<this.liste.size();i++)
			if(liste.get(i).getNumAbo()==numAbo)
				return this.liste.get(i);

		throw new Exception("Fixe ça");
	}
	
	public void updateData() //Pour update les données
	{
		String[][] dataToUpdate = new String[this.liste.size()][3];
		for(int i=0; i<this.liste.size();i++)
		{
			try {
				dataToUpdate[i]=this.findAbo(i+1).getTable();
			} catch (Exception e) {e.printStackTrace();}
		}
		
		try {
			ImportSql.getInstance().updateData("abonne", dataToUpdate);
		} catch (SQLException e) {e.printStackTrace();}
	}
	
}
