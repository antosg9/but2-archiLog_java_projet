package stockage;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LogDocument {

	private static LogDocument instance = new LogDocument(); //Singleton (constructeur privé)

	List<String> logs = Collections.synchronizedList(new ArrayList<String>()); //THREAD SAFE (se renseigner sur syncrhonisation explicite pour itération)

	private LogDocument(){
	}

	public static LogDocument getInstance()
	{
		return LogDocument.instance;
	}

	public synchronized void addLog(String string)
	{
		String event = this.getTime()+" > "+string;
		this.logs.add(event);
	}

	public void seeLog()
	{
		if(this.logs.size()==0)
		{
			System.out.println("Les logs sont encores vides");
			return;
		}
		
		for(int i=0; i<this.logs.size();i++)
			System.out.println(this.logs.get(i));
	}

	private String getTime()
	{
		LocalTime currentTime = LocalTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		return currentTime.format(formatter);
	}
}
