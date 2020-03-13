package example;
import java.util.*;
import java.io.*;
import java.net.*;

//5��° ����� �ڹ�ä��â
public class MultiChartServerFifth
{
	//Ŭ���̾�Ʈ���� ������ input�� ������ ������ ���� �ʿ�
	ServerSocket server= null;
	Socket sock = null;
	static int port = 10001;
	ArrayList list = null;
	HashMap map = null;

	public MultiChartServerFifth()
	{
		System.out.println("Server����");
		try
		{
			server = new ServerSocket(port);
			//list = new ArrayList();
			map = new HashMap();
			while(true)
			{
				sock = server.accept();
				InputThread inth = new InputThread(sock,map);
				inth.start();
			}
		
				
		}
		catch (Exception ex)
		{
			
			System.out.println(ex);
		}
		finally
		{
			try
			{
				if(sock!=null)
					sock.close();
			}
			catch (Exception ex)
			{
			}
		}
		
	}
	public static void main(String[] args) 
	{
		new MultiChartServerFifth();
	}
}

class InputThread extends Thread
{
	ObjectInputStream in = null;
	ObjectOutputStream out = null;
	BufferedReader br = null;
	PrintWriter pw = null;
	ArrayList list = null;
	Socket sock = null;
	ClientInfo client = null;
	HashMap map =null;

	public InputThread(Socket sock,HashMap map)
	{
		this.sock=sock;
		this.map = map;

		try
		{
			
			System.out.println(sock.getInetAddress()+"���� �����߽��ϴ�");
			br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			System.out.println("br");
			pw = new PrintWriter(new OutputStreamWriter(sock.getOutputStream()));
			
			System.out.println("pw");
			
			//in = new ObjectInputStream(sock.getInputStream());				
			//out = new ObjectOutputStream(sock.getOutputStream());
			
			
			//System.out.println(br.readLine());
			/*
			synchronized(list)
			{
				list.add(out);
			}
			
			
			String msg = (String)in.readObject();
			System.out.println(msg);
			*/
		}
		catch (Exception ex)
		{
			System.out.println("Ŭ���̾�Ʈ��������");
			System.out.println(ex);
		}
		/*
		finally
		{
			try
			{
				if(br!=null)
					br.close();
				if(pw!=null)
					pw.close();
				if(sock!=null)
					sock.close();
			}
			catch (Exception ex)
			{
			}
		}
		*/
	}
	public void run()
	{
		try
		{

			String line = null;
			while( (line=br.readLine()) !=null)
			{			
				String[] userInfo = line.split(":");
				
				synchronized(map)
				{
					//System.out.println("list");
					
					map.put(userInfo[0],pw);
				}
				System.out.println("line : "+line);
				broadcast(line);
			}
			
			/*
			String line = null;
			line = br.readLine(); 
			System.out.println(line);
			
			while( (line=br.readLine()) !=null)
			{
				System.out.println(br.readLine());	
			}
			*/
		}
		catch (SocketException sex)
		{
			System.out.println("client ������");
		}
		catch (Exception ex)
		{
			
			System.out.println(ex);
		}
		finally
		{
			try
			{
				if(br!=null)
					br.close();
				if(pw!=null)
					pw.close();
				if(sock!=null)
					sock.close();
			}
			catch (Exception ex)
			{
			}
		}
		
	}//run

	public void broadcast(String line)
	{
//		System.out.println("broadcast");
		try
		{
			synchronized(map)
			{
				System.out.println("111111111");
				Collection col = map.values();
				Iterator iter =col.iterator();
				System.out.println("2222222");
				while(iter.hasNext())
				{
					PrintWriter tempPw = (PrintWriter)iter.next();
					System.out.println("broadcast_line : "+line);
					tempPw.println(line);
					tempPw.flush();
				}//while
			}
			/*
			for(PrintWriter tempPw : (PrintWriter)list)
			{
				tempPw.println(line);
				tempPw.flush();

			}
			*/
		}
		catch (Exception ex)
		{
			System.out.println(ex);
		}
	}
} 
