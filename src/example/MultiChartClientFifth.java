package example;
import java.net.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//5��° ����� ä��Ŭ���̾�Ʈ
public class MultiChartClientFifth extends JFrame implements ActionListener
{
	Socket sock;
	static int port = 10001;
	static String ip = "127.0.0.1";
	ObjectInputStream in = null;
	ObjectOutputStream out = null;
	BufferedReader keyboard = null;
	PrintWriter pw = null;
	BufferedReader br = null;
	ClientInfo client = null;
	ArrayList list = null;

	private JTextArea main_jta = null;
	private JTextField msg_jtf = null;
	private JScrollPane jsp = null;
	private JTextField username_jtf = null;
	private JPanel jp = null;

	public MultiChartClientFifth()
	{
		System.out.println("Client����");
		createClientUI(this);
		
		msg_jtf.addActionListener(this);
		try
		{
			sock = new Socket(ip,port);
			String line = null;
			client = new ClientInfo();
			//keyboard = new BufferedReader(new InputStreamReader(System.in));			

//			line = msg_jtf.getText();
//			System.out.println(line);

			br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			pw = new PrintWriter(new OutputStreamWriter(sock.getOutputStream()));

			ClientThread thread = new ClientThread(br,list,line,main_jta);
			thread.start();

			/*
			in = new ObjectInputStream(sock.getInputStream());
			out = new ObjectOutputStream(sock.getOutputStream());
			System.out.println("�� �� : "+line);
			client.setMsg(line);
			out.writeObject(in);
			out.flush();
			*/
		
			
		}
		catch (Exception ex)
		{
			System.out.println(ex);
			
			try
			{				
				sock.close();
			}
			catch (Exception ex2)
			{
				
			}
				
		}
		
		
	}
	/**
	*ȭ�鱸��UI�Լ�
	*/
	public void createClientUI(JFrame jf)
	{
		jp = new JPanel(new FlowLayout());
		
		main_jta = new JTextArea();
		jsp = new JScrollPane(main_jta);
		username_jtf = new JTextField();
		username_jtf.setColumns(5);
		msg_jtf = new JTextField();
		msg_jtf.setColumns(18);
		
		jp.add(username_jtf);
		jp.add(msg_jtf);

		jf.setLayout(new BorderLayout());
		jf.add(jsp);
		jf.add(jp,BorderLayout.SOUTH);
		//jf.add(msg_jtf,BorderLayout.SOUTH);
		
		
		jf.setSize(300,300);
		jf.setVisible(true);	

	}
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==msg_jtf)
		{
			String username = username_jtf.getText();
			String msg = msg_jtf.getText();
			
			System.out.println(username+":"+msg);

			pw.println(username+":"+msg);
			pw.flush();
		}
	}
	public static void main(String[] args) 
	{
		new MultiChartClientFifth();
	}
}

class ClientThread extends Thread
{
	private ObjectInputStream in =null;
	private ObjectOutputStream out = null;
	private ArrayList list = null;
	private BufferedReader br = null;
	private PrintWriter pw = null;
	private String line =null;
	private Socket sock = null;
	private JTextArea jta = null;

	public ClientThread(BufferedReader br,ArrayList list,String line,JTextArea jta)
	{
		//this.sock = sock;
		this.list = list;
		this.line = line;
		this.br = br;
		this.jta = jta;

		try
		{
		

		}
		catch (Exception ex)
		{
			System.out.println(ex);
		}
		
	}

	public void run()
	{
		//System.out.println("111111111");
		try
		{
			String strLine = null;

			while((strLine=br.readLine())!= null)
			{
				System.out.println("�ٸ���� : "+strLine);
				jta.append(strLine + "\n");
			}
		}
		catch (Exception ex)
		{
			System.out.println(ex);
		}
		
	}
}