package UI;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import dbTest.CheckDAO;
import dbTest.CheckVO;
import dbTest.ClientDAO;
import dbTest.ClientVO;
import dbTest.ReportCDAO;
import dbTest.ReportCVO;

import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Timer.*;


public class Degisil extends JFrame implements Runnable,ActionListener,KeyListener{
 
   private JButton wd_btn,logout,btnInip,btnIniT,btnOX,help_btn;
   private JTextArea chat_txtarea,rank_txtarea,my_info,con_people,realtime;
   private JTextField chat_txtfield;
   private JScrollPane scroll_chat,scroll_con,scroll_game;
   private JLabel chat_label,con_num,gtype_lbl;
   private JComboBox<String> gtype_cb;
   private JList<Object> g_list;
   private List list;
   private Calendar cal;
   private JPanel panel;
   
   private CheckDAO cdao1;
   private CheckVO cvo1;
   
	private JFileChooser jfc;
	private File file;
	private BufferedReader br;
	private PrintWriter pw;
	private Socket socket;
	private String id;
	private String nickname;
	
	private ClientVO cvo;
	private ClientDAO cdo;
	
	private OxGameUI ogui;
	   private InitialP inip;
	   private InitialTeam init;

	
   public Degisil(ClientVO cvo){ 
	   this.cvo = cvo;
	   cdo = new ClientDAO();

	   id = cvo.getId();
	   System.out.println("대기실 id : " + id);

      ArrayList<String> list = new ArrayList<>();      
       wd_btn= new JButton("탈퇴");        
         wd_btn.setBounds(1050, 570, 220, 100);
         add(wd_btn);  
         wd_btn.addActionListener(this);
   /////////////////////////////////////////////////////////////////////////////   탈퇴버ㅏ튼   
         chat_txtarea = new JTextArea();
         chat_txtarea.setBounds(60, 370, 750, 250);
        chat_txtarea.setEditable(false);      //글씨못치게함
       scroll_chat = new JScrollPane(chat_txtarea, 
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); //스크롤
       scroll_chat.setBounds(60,370,750,250);        
        add(scroll_chat);
            /////////////////////////////////////////////////////////////////////////////      채팅보여주는곳
        chat_txtfield = new JTextField();
        chat_txtfield.setBounds(60, 640, 750,30);
        add(chat_txtfield);
        chat_txtfield.addKeyListener(this);

//        chat_txtfield.addKeyListener(new KeyAdapter() {
//
//	         @Override
//	         public void keyReleased(KeyEvent e) {            
//	            if(e.getKeyCode() == 10) {   //엔터키가 눌린 경우            
//	                  chat_txtarea.append("["+id+"] "+ chat_txtfield.getText() + "\n");               
//	               chat_txtfield.setText("");
//	               chat_txtfield.requestFocus();
//	            }
//	         }	
//		});
        
        addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				cdo.updateDisconn(id);
				pw.println("-1");
				
				
				dispose();
				
				try {
					if(br!= null)br.close();
					if(pw!= null)pw.close();
					if(socket!= null)socket.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
			@Override
			public void windowOpened(WindowEvent e) {
				
				
				try {
				//소켓 및 스트림들 생성
				//닉네임과 초기메시지 (Hello Server을 서버로 전송
				//프레임이 표시되면 - 서버에서 수신한 환영메시지 및 시간 표시
					socket = new Socket("localHost", 5000);
//					socket = new Socket(address, port, localAddr, localPort);
					pw = new PrintWriter(socket.getOutputStream(), true);
					pw.println(id + "/" + "님이접속");

					br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					
					chat_txtarea.append(br.readLine());
					
					
					new Thread(() ->{
						try {
							while(br != null) {
								chat_txtarea.append( br.readLine() + "\n");
								
							}
						} catch	(IOException e1) {
							e1.printStackTrace();
						}
					
					}).start();
					
					new Thread(() ->{
						while(true) {
							  SimpleDateFormat simple = new SimpleDateFormat("a hh:mm:ss");
							   cal=Calendar.getInstance();      
							   realtime.setText(simple.format(new Date()));							
						}
					
					}).start();
					
					
					   

//					System.out.println("먹히는부분");					
				} catch (UnknownHostException e1) {
					System.err.println("> 서버 연결 오류 : 지정된 서버(" + e1.getMessage()+ ")가 존재하지 않습니다.");
				}	 catch (ConnectException e1) {
					// TODO Auto-generated catch block
					System.err.println("> 서버 연결 실패 : 서버 연결 상태를 확인 해 주세요.");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
		});

        
        
        help_btn = new JButton("도움말");
        help_btn.addActionListener(this);
        help_btn.setBounds(1170, 50, 100, 100);
        add(help_btn);

   
           /////////////////////////////////////////////////////////////////////////////      채팅치는곳
        chat_label = new JLabel("채   팅   창");
        chat_label.setFont(new Font("궁서", Font.BOLD, 30));      
        chat_label.setBounds(320,190,300,300);
        add(chat_label);
   /////////////////////////////////////////////////////////////////////////////  채팅창이름 라벨
        rank_txtarea = new JTextArea();
       rank_txtarea.setBounds(840, 270, 180, 400);
        rank_txtarea.setEditable(false);

        rank_txtarea.setBorder(new MatteBorder(5,5,5,5,Color.black)); //테두리 두깨,색
        rank_txtarea.setBackground(new Color(234,234,234));//배경색
        add(rank_txtarea);
        RankAll();
   /////////////////////////////////////////////////////////////////////////////    전체랭킹보여주는거
        my_info = new JTextArea();
        my_info.setBounds(1050, 160, 220, 400);
        my_info.setEditable(false);
        my_info.setText("내정보");
        my_info.setBorder(new MatteBorder(5,5,5,5,Color.orange)); //테두리 두깨,색
        my_info.setBackground(Color.pink);   //배경색
        add(my_info);
        myAll();

   ////////////////////////////////////////////////////////////////////////////     내정보확인
        logout = new JButton("로그아웃");
        logout.setBounds(1050, 50, 100, 100);
        add(logout);
        logout.addActionListener(this);
   ///////////////////////////////////////////////////////////////////////////     로그아웃버튼
        con_people = new JTextArea();
        

        
        con_people.setBounds(840,50,180,200);
        con_people.setEditable(false);
        scroll_con = new JScrollPane(con_people, 
            ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll_con.setBounds(840, 50, 180, 200);
        con_people.setBorder(new MatteBorder(5,5,5,5,Color.LIGHT_GRAY));
        add(scroll_con);
        
        showConnClient(nickname);
    ///////////////////////////////////////////////////////////////////////// 접속자 명단 
        realtime=new JTextArea();
      realtime.setEditable(false);
      realtime.setBounds(1090,15,220,30);
      add(realtime);
      realtime.setBackground(new Color(212,244,250));            
      realtime.setFont(new Font("궁서", Font.BOLD, 20));
    /////////////////////////////////////////////////////////////////////////   실시간 시간 area
        con_num = new JLabel("접속자");
        con_num.setBounds(900, 15, 220, 30);                 
        con_num.setFont(new Font("궁서", Font.BOLD, 20));
      add(con_num);
      
      
    /////////////////////////////////////////////////////////////////////////    접속자라벨   
//      room_btn = new JButton("방 생성");
//      room_btn.setBounds(600,20,220,80);
//      add(room_btn);
//      room_btn.addActionListener(this);
    /////////////////////////////////////////////////////////////////////////    방생성 버튼 
      gtype_lbl = new JLabel("게임 종류");
      gtype_lbl.setBounds(30,10,120,80);
      gtype_lbl.setFont(new Font("궁서", Font.BOLD, 20));
      add(gtype_lbl);
    /////////////////////////////////////////////////////////////////////////    게임종류 라벨 
      String[] g_type = {"모든게임","초성퀴즈게임","OX퀴즈게임"};
      gtype_cb = new JComboBox<String>(g_type);
      gtype_cb.setBounds(150,30,430,40);
      add(gtype_cb);
    /////////////////////////////////////////////////////////////////////////    게임종류 라벨 
//      String[] room = {"1번방","2번방","3번방","1번방","2번방","3번방","2번방","3번방"};      
//      g_list = new JList<Object>(room);
//      g_list.setBounds(60,130,745,170);   
//      scroll_game = new JScrollPane(g_list, 
//            ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
//            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
//      scroll_game.setBounds(60, 130, 745, 170);
//      add(scroll_game);
      
//      Iterator<String> it = ChatServer.clientMap.keySet().iterator();
//      while(it.hasNext()) {
//      	String keys = it.next();
//      	con_people.append(keys);
//      }
      
      // 개인전 초성퀴즈,팀전 초성퀴즈, ox게임 방(버튼 만들기)
      btnInip = new JButton("초성게임 개인전");
      btnIniT = new JButton("초성게임 팀전");
      btnOX = new JButton("OX게임 ");
      btnInip.setBounds(90, 100, 200, 200);
      add(btnInip);
      btnIniT.setBounds(320, 100, 200, 200);
      add(btnIniT);
      btnOX.setBounds(550, 100, 200, 200);
      add(btnOX);
      
      btnInip.addActionListener(this);
      btnIniT.addActionListener(this);
      btnOX.addActionListener(this);

        int x = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/8 - this.getWidth()/2;   // x좌표 가운데 설정
        int y = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/8 - this.getHeight()/2; //y좌표 가운데 설정
        setLocation(x,y); // 프레임창 위치
        Toolkit toolkit = getToolkit();
        Image image = toolkit.createImage("C:\\javawork\\Q_icon.png"); 
        setIconImage(image);
        setTitle(" Q_PLAY "); 
        getContentPane().setBackground(new Color(212,244,250));   //배경색    
        setLayout(null);
        setSize(1300,750);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
   }
   ///ㅅㅄㅄㅂ
//   public class threadtest extends Thread {
//		public void run() {
//		   while(true){
//		
//		   SimpleDateFormat simple = new SimpleDateFormat("a hh:mm:ss");
//		   cal=Calendar.getInstance();      
//		   realtime.setText(simple.format(new Date()));
//			   try{
//			      Thread.sleep(10);
//			      }catch(Exception e){
//			   }
//		   }
//		}
//	   
//   }

//   public static void main(String[] args) {     
//      Thread t =new Thread(new Degisil());
//      t.start();
//   }
   
   public void myAll() {
		
		cdao1 = new CheckDAO();
		cvo1 = new CheckVO();
		
//		cdao1.myAll();
		System.out.println("myAll : " + cvo.getId());
		
		List<CheckVO> list = cdao1.myAll(cvo);
		
		for (CheckVO cvo1 : list) {
			my_info.append("닉네임 : " + cvo1.getNickname() + " \n" + 
					"초성퀴즈 판수(개인전) " + cvo1.getPansuInip() + "판 \n" + 
					"초성퀴즈 승(개인전) " + cvo1.getPansuWinp() + "승 \n" +
					"초성퀴즈 패(개인전) " + cvo1.getPansuLosep() + "패 \n" +
					"초성퀴즈 점수(개인전) " + cvo1.getJumsuP() + "점 \n" +
					"초성퀴즈 승률(개인전) " + ((double)cvo1.getPansuWinp() / (double)cvo1.getPansuInip()) * 100 + "% \n\n" +
					
					"초성퀴즈 판수(팀전) " + cvo1.getPansuInit() + "판 \n" + 
					"초성퀴즈 승(팀전) " + cvo1.getPansuWint() + "승 \n" + 
					"초성퀴즈 패(팀전) " + cvo1.getPansuLoset() + "패 \n" + 
					"초성퀴즈 점수(팀전) " + cvo1.getJumsuT() + "점 \n" +
					"초성퀴즈 승률(팀전) " + ((double)cvo1.getPansuWint() / (double)cvo1.getPansuInit()) * 100 + "% \n\n" +
					
					"OX 퀴즈 판수 " + cvo1.getPansuOx() + "판 \n" + 
					"OX퀴즈 승 " + cvo1.getPansuOxw() + "승 \n"  +
					"OX퀴즈 패 " + cvo1.getPansuOxl() + "패 \n" + 
					"OX퀴즈 점수 " + cvo1.getJumsuOx() + "점 \n" +
					"OX퀴즈 승률 " + ((double)cvo1.getPansuOxw() / (double)cvo1.getPansuOx()) * 100 + "% \n"
					
					);
			
		}
	}
   
   private void RankAll() {
		
		cdao1 = new CheckDAO();
		cvo1 = new CheckVO();
		
		cdao1.RankAll();
		
		List<CheckVO> list = cdao1.RankAll();
		int i =1;
		for (CheckVO cvo1 : list) {
			
			rank_txtarea.append(i++ + "등 닉네임 : " + cvo1.getNickname() + " \n" + 
					"초성퀴즈 점수(개인전) " + cvo1.getJumsuP() + "점 \n"

					
					);
			
		}
	}
   
   private void showConnClient(String nickname) {
	      cdo = new ClientDAO();
	      cvo = new ClientVO();
	      
	      cdo.showConnClient(nickname);
	      
	      List<ClientVO> list = cdo.showConnClient(nickname);
	      
	      for(ClientVO cvo : list) {
	         con_people.append(cvo.getNickname() + "\n");;
	      }
	   }
   
@Override
	public void run() {
	
	   while(true){
	
	   SimpleDateFormat simple = new SimpleDateFormat("a hh:mm:ss");
	   cal=Calendar.getInstance();      
	   realtime.setText(simple.format(new Date()));
		   try{
		      Thread.sleep(10);
		      }catch(Exception e){
		   }
	   }
	} //현재 시간이랑 날짜를 출력해주는 쓰레드 

@Override
	public void actionPerformed(ActionEvent e) {
	
	 
//	   if (e.getSource() ==room_btn) {
//		  dispose();
//	      new CreateRoomUI();
//	   }
	   
	   if (e.getSource() == wd_btn) {
			  
		      int rs=JOptionPane.showConfirmDialog(null, "정말 탈퇴하시겠습니까 ?", "탈퇴", 
		      JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE);
		      
		      if (rs==JOptionPane.YES_OPTION) {
		    	 
				String id_c = JOptionPane.showInputDialog("본인의 아이디를 입력해주세요");	
				String pw_c = JOptionPane.showInputDialog("본인의 비밀번호를 입력해주세요");
				if (cdo.deleteUser(id_c,pw_c)==true) {
					JOptionPane.showMessageDialog(null, "탈퇴완료","탈퇴성공",JOptionPane.INFORMATION_MESSAGE);
					System.exit(0);
				}
				if ((cdo.deleteUser(id_c,pw_c)==false)) {
					JOptionPane.showMessageDialog(null, "탈퇴실패 아이디랑 비밀번호 다시확인해주세요","탈퇴실패",JOptionPane.ERROR_MESSAGE);			
				}
				if (id_c ==null || pw_c ==null) {
					System.out.println("널");
				}

		      }  
		   }//회원탈퇴
		   
		   
		   if (e.getSource() == logout) {
		      int rs = JOptionPane.showConfirmDialog(null, "정말 로그아웃 하시겠습니까 ?", "로그아웃", 
		      JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE);  
		      if (rs==JOptionPane.YES_OPTION) {
				dispose();
				new Login();
			}
		   }
		   if(e.getSource()==btnInip) {
	            //??-- 방에 들어간 인원수 카운트
			   //서버끊기
			   
			   dispose();
	            inip = new InitialP(id);
	            
			  // inip = new InitialP();
	         }
	         if(e.getSource()==btnIniT) {
	            //??
	            init = new InitialTeam();
	         }
	         if(e.getSource()==btnOX) {
	            //??
	            ogui = new OxGameUI();
	         }

	         if (e.getSource() ==help_btn) {
	            JOptionPane.showConfirmDialog(null, "개인전 초성퀴즈 -  \n팀전 초성퀴즈 - \nOX퀴즈 - ", "도움말", 
	                     JOptionPane.CLOSED_OPTION,JOptionPane.PLAIN_MESSAGE);
	            }//도움말
	}


@Override
public void keyTyped(KeyEvent e) { }
	
	

@Override
public void keyPressed(KeyEvent e) {
	
}
@Override
public void keyReleased(KeyEvent e) {
	if(e.getKeyChar() == 10) {
		if(!chat_txtfield.getText().equals("")) {
		//닉네임과 대화내용을 서버로 전송하고
		//서버에서 수신한 메시지와 시간 표시 후 초기화 및 포커스 맞추기
			pw.println(chat_txtfield.getText());
			chat_txtfield.setText("");
		}
	}	
}
}






