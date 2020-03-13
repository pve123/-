package UI;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class OxGameUI extends JFrame implements ActionListener, Runnable{
// JFrame은 상속받고 ActionListner는  implements
	
	private JLabel nickLabel;		// 채팅시 옆에 닉네임 나오게 하는 레이블
	private JButton btnO, btnX, btnExit, btnReport;		// btnOX는 ox 버튼, btnExit는 나가기 버튼, btnReport는 신고 버튼
	private JTextArea 	roomInfo, quizArea, gameChatArea,oCountArea, xCountArea, clientCount, 
						curCorrect; // roomInfo: 방제목 출력  quizArea: 퀴즈 문제 출력   gameChatArea: 게임방 내 채팅창   oxCountArea: ox 맞춘 개수 출력
									// clientCount: 접속자 수   curCorrect: 현재 맞춘 개수
	private JTextField gameChatFld;	// gameChatFld: 게임 채팅 시 채팅을 입력받을 JTextField
	private JScrollPane curClient;	// curClient : 접속자 명과 추후에 닉네임 옆에 강퇴버튼 생성
	
	private JButton btn1, btn2, btn3, btn4, btn5, btn6;
	
	
	public OxGameUI(){
		
		roomInfo = new JTextArea("방 제목이 들어간다!!");	
		roomInfo.setBounds(70, 20, 590, 50);		// setBounds(x, y, width, height)
		add(roomInfo);								// 프레임에 roomInfo  add
		
		
		quizArea = new JTextArea("DB에 저장한 문제 랜덤 출력");
		quizArea.setBounds(70, 90, 590, 90);
		add(quizArea);
		
		btnO = new JButton("O");					
		btnO.setBounds(70, 190, 290, 380);			
		add(btnO);
		btnX = new JButton("X");
		btnX.setBounds(370, 190, 290, 380);
		add(btnX);
		
		oCountArea = new JTextArea("현재 O를 누른 클라이언트 수");
		oCountArea.setBounds(70, 590, 290, 100);
		add(oCountArea);
		xCountArea = new JTextArea("현재 X를 누른 클라이언트 수");
		xCountArea.setBounds(370, 590, 290, 100);
		add(xCountArea);
		
		clientCount = new JTextArea("접속자 수");
		clientCount.setBounds(700, 20, 160, 50);
		add(clientCount);
		
		btnExit = new JButton("나가기");				// 나가기 버튼 누르면 대기실로 이동
		btnExit.setBounds(890,20,160,50);
		add(btnExit);
		
		curClient = new JScrollPane();
		curClient.setBounds(700, 90, 160, 210);
		add(curClient);
		
		
		
		
		
		curCorrect = new JTextArea("현재 맞춘 개수");
		curCorrect.setBounds(890, 90, 160, 100);
		add(curCorrect);
		
		btnReport = new JButton("신고");				// 신고 버튼 누르면 신고 창 떠야함
		btnReport.setBounds(890, 200, 160, 100);
		add(btnReport);
		
		
		gameChatArea = new JTextArea("채팅");
		gameChatArea.setBounds(700, 320, 350, 330);
		add(gameChatArea);
		gameChatFld = new JTextField();
		add(gameChatFld);
		gameChatFld.setBounds(760, 660, 290, 30);
		
		nickLabel = new JLabel("둔이둔이");
		add(nickLabel);
		nickLabel.setBounds(700, 660, 70, 30);
		
		
		btnO.addActionListener(this);
		btnX.addActionListener(this);
		btnReport.addActionListener(this);
		btnExit.addActionListener(this);
		
		
		
		roomInfo.setFont(roomInfo.getFont().deriveFont(30.0f));
		quizArea.setFont(quizArea.getFont().deriveFont(30.0f));
		clientCount.setFont(clientCount.getFont().deriveFont(15.0f));
		btnExit.setFont(btnExit.getFont().deriveFont(15.0f));
		curCorrect.setFont(curCorrect.getFont().deriveFont(20.0f));
		btnReport.setFont(btnReport.getFont().deriveFont(15.0f));
		oCountArea.setFont(oCountArea.getFont().deriveFont(15.0f));
		xCountArea.setFont(xCountArea.getFont().deriveFont(15.0f));
		btnO.setFont(btnO.getFont().deriveFont(100.0f));	// OX 버튼의 텍스트 크기 설정
		btnX.setFont(btnX.getFont().deriveFont(100.0f));
		
		
		
		int x = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/5 - this.getWidth()/2;   // x좌표 가운데 설정
        int y = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/8 - this.getHeight()/2; //y좌표 가운데 설정
        Toolkit toolkit = getToolkit();	// Q 플레이 아이콘 설정하기 위한 객체 생성
        Image image = toolkit.createImage("C:\\javawork\\Q_icon.png"); // Q 플레이 아이콘		// 경로에 파일 이미지 없으면 자바컵으로 나옴
        setLayout(null);
        setIconImage(image);	// QPLAY 아이콘 설정
        setTitle(" Q_PLAY ");	// UI 제목 Q_PLAY 설정
        setLocation(x,y); // 실행시 위치 설정
		setSize(1100,800);	// SIZE 1500,850
		setVisible(true);	// setVisible true로 지정하여 창 활성화
		setResizable(false);	// 창 크기 조절 불가능
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnReport) {
			new ReportUI();
		}
		if(e.getSource() == btnExit) {
			int result = JOptionPane.showConfirmDialog(null,"정말 나가시겠습니까?","confirm",JOptionPane.YES_NO_OPTION);
			if(result == JOptionPane.CANCEL_OPTION) {
				// joptionPane 창 꺼지고 아무 처리 하지 않음
			}else if(result == JOptionPane.YES_OPTION) {
				dispose();	
			}
			// 대기실 다시 접속
		}
	}


	public static void main(String[] args) {
		new OxGameUI();
	}
	@Override
	public void run() {
		
	}
}
