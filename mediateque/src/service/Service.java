package service;

import java.net.Socket;

public interface Service {
	
	public void setSocket(Socket socket);
	
	public void launchService();
}