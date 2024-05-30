package stockage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import document.AbstractDocument;
import document.Dvd;

public class ListeDocument {

	private static ListeDocument instance = new ListeDocument(); //Singleton (constructeur privé)

	List<AbstractDocument> liste = Collections.synchronizedList(new ArrayList<AbstractDocument>()); //THREAD SAFE (se renseigner sur syncrhonisation explicite pour itération)

	private ListeDocument(){
		this.initialiaze();
	}

	public static ListeDocument getInstance()
	{
		return ListeDocument.instance;
	}

	private void initialiaze()
	{
		ImportSql importSql = ImportSql.getInstance();

		try {
			ResultSet resultSet = importSql.selectData("document");

			while(resultSet.next())	
			{
				int numDoc = resultSet.getInt(1);
				String titre = resultSet.getString(2);
				String typeDoc = resultSet.getString(3);
				int numAbo = resultSet.getInt(4);
				String stateBase = resultSet.getString(5);
				boolean adulte = resultSet.getBoolean(6);

				this.liste.add(new Dvd(numDoc, titre, typeDoc, numAbo, stateBase, adulte));	
			}
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public AbstractDocument findDoc(int numDoc) throws Exception
	{
		for(int i =0;i<this.liste.size();i++)
			if(liste.get(i).numero()==numDoc)
				return this.liste.get(i);

		throw new Exception("Élément introuvable");
	}
}
