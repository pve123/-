package UI;


import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;


public class ChatClient extends JFrame implements ActionListener, KeyListener {
	private static final long serialVersionUID = 1;
	private static final String DATE_TIME_PATTERN = " yyyy년 MM월 dd일 [a hh:mm:ss] ";
	private static final String TIME_PATTERN = " [a hh:mm:ss] ";
	
	private JPanel panel;
	private JButton btnClear,btnSave,oneone;
	private JTextArea chatArea;
	private JTextField txtFld;
	private JLabel label1,label2;
	private String id;
	private JScrollPane scrollPane;
	private Date now;
	private SimpleDateFormat dateFormat;
	private JFileChooser jfc;
	private File file;
	private BufferedReader br;
	private PrintWriter pw;
	private Socket socket;
	
	public ChatClient(){
	
		id = JOptionPane.showInputDialog("대화명을 입력해 주세요.");	
		if(id==null) {
			return;
		}if(id.equals("")) {
			id = "이름 없음";
		}
		
		label1 = new JLabel("Java CHFIT v.1");
		label1.setFont(new Font("Arial Black", Font.BOLD, 15));
		label1.setPreferredSize(new Dimension(350, 40));
		
		btnClear = new JButton("지우기");
		btnSave = new JButton("파일저장");
		oneone = new JButton("1:1 대화");
		label2 = new JLabel(id);
		txtFld = new JTextField(10);
		chatArea = new JTextArea(20, 30);
		chatArea.setLineWrap(true);
		chatArea.setWrapStyleWord(true);
		scrollPane = new JScrollPane(chatArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		btnClear.addActionListener(this);
		btnSave.addActionListener(this);
		oneone.addActionListener(this);
		
		chatArea.setEditable(false);
		txtFld.addKeyListener(this);
		panel = new JPanel();
//		panel.add(chatArea);
		add(scrollPane,"Center");
		panel.add(label2,BorderLayout.SOUTH);
		panel.add(txtFld,BorderLayout.SOUTH);
		panel.add(btnClear,BorderLayout.SOUTH);
		panel.add(btnSave,BorderLayout.SOUTH);
		panel.add(oneone,BorderLayout.SOUTH);
		add(label1,"North");
		
		add(panel,BorderLayout.SOUTH);
		
		


		//프레임 관련 설정
		setTitle("ExerciseSwing");
//		setLayout(new FlowLayout());
		setSize(450,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(600,300);
		setVisible(true);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				pw.println("-1");
				dispose();
				System.exit(0);
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
				txtFld.requestFocus();
				
				try {
				//소켓 및 스트림들 생성
				//닉네임과 초기메시지 (Hello Server을 서버로 전송
				//프레임이 표시되면 - 서버에서 수신한 환영메시지 및 시간 표시
					socket = new Socket("localHost", 5000);
//					socket = new Socket(address, port, localAddr, localPort);
					pw = new PrintWriter(socket.getOutputStream(), true);
					pw.println(id + "/" + "님이접속");
					br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					chatArea.append(br.readLine());
					
					
					new Thread(() ->{
						try {
							while(br != null) {
								chatArea.append( br.readLine() + "\n");
								
							}
					
						} catch	(IOException e1) {
							e1.printStackTrace();
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

	}



	public static void main(String[] args) {
		new ChatClient();
		
	}
	public String timeStamp(String pattern) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		return dateFormat.format(new Date());
	}
	@Override
	public void keyTyped(KeyEvent e) { }
		
		
	
	@Override
	public void keyPressed(KeyEvent e) {
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyChar() == 10) {
			if(!txtFld.getText().equals("")) {
			//닉네임과 대화내용을 서버로 전송하고
			//서버에서 수신한 메시지와 시간 표시 후 초기화 및 포커스 맞추기
				pw.println(txtFld.getText());
				txtFld.setText("");
			}
		}
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();
		if(btn == btnClear) {
			chatArea.setText("\t  " + id + "님 어서오세요\n\t" + timeStamp(DATE_TIME_PATTERN)+ "\n");
			txtFld.requestFocus();
		}
		if(btn == oneone) {
			String nick = JOptionPane.showInputDialog("1:1 대화를 원하시는 상대의 닉네임을 입력 해 주세요");
			pw.println(nick);
		}
		if(btn == btnSave) {
			jfc = new JFileChooser();
			int returnVal = jfc.showOpenDialog(null);
	        if(returnVal == 0) {
	            file = jfc.getSelectedFile();
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                bw.write(chatArea.getText());
                bw.flush();
                bw.close();
                 
            }catch(Exception e1) {e1.printStackTrace();}
             
            
//           JFileChooser fc = new JFileChooser();
//            fc.setCurrentDirectory(new File("c:\\javawork\\")); 초기 지정 디렉토리
//            int returnVal = fc.showSaveDialog(this);
//            if(returnVal == JFileChooser.APPOROVE_OPTION){
//            	File file = fc.getSelectedFile();
//            	try(FileWriter fw = new FileWriter(file)){
//           		fw.write(chatArea.getText());
//            	}catch (IOException e1) {
//            		e1.printStackTrace();
//	        	}
//	        }
         
	        }

		}
		
	}



//	@Override
//	public void run() {
//		try {
//			chatArea.append(timeStamp(TIME_PATTERN)+ br.readLine() + timeStamp(DATE_TIME_PATTERN));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}

}
