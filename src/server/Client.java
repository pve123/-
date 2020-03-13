package server;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import project1.Login;

public class Client {
	private String PORT;
	private Socket socket;

	private PrintWriter pw;
	private InputStreamReader isr;
	private InputStream is;
	private BufferedReader br;
	
	private Login login;
	
	public Client() {
		login = new Login();
	}
	public static void main(String[] args) {
		new Client();
	}

}
