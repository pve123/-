package UI;

import java.awt.*;
import javax.swing.*;

import dbTest.CheckDAO;
import dbTest.CheckVO;

import java.awt.event.*;
import java.util.List;
import java.util.Timer.*;

public class AdminUserCheck extends JFrame implements ActionListener, Runnable{	// JFrame은 상속받고 ActionListner는  implements
	
	private JLabel titleLabel, selectLabel;		
	private JButton btnX, btnSelect;		
	private JTextArea  userList, userSelect; 
	

	
	public AdminUserCheck(){
		
		titleLabel = new JLabel("전체 회원목록 조회");
		add(titleLabel);
		titleLabel.setBounds(220, 20, 350, 30);
		
		userList = new JTextArea("회원 목록 조회");
		userList.setBounds(70, 90, 590, 390);
		add(userList);
		
		btnX = new JButton("뒤로가기");
		btnX.setBounds(370, 490, 290, 80);
		add(btnX);
		
		//폰트크기 지정
		titleLabel.setFont(titleLabel.getFont().deriveFont(30.0f));
		userList.setFont(userList.getFont().deriveFont(30.0f));
		btnX.setFont(btnX.getFont().deriveFont(30.0f));
		
		
		btnX.addActionListener(this);
		
		int x = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/4 - this.getWidth()/2;   // x좌표 가운데 설정
        int y = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/8 - this.getHeight()/2; //y좌표 가운데 설정
        Toolkit toolkit = getToolkit();	// Q 플레이 아이콘 설정하기 위한 객체 생성
        Image image = toolkit.createImage("C:\\javawork\\Q_icon.png"); // Q 플레이 아이콘		// 경로에 파일 이미지 없으면 자바컵으로 나옴
        setLayout(null);
        setIconImage(image);	// QPLAY 아이콘 설정
        setTitle(" Q_PLAY ");	// UI 제목 Q_PLAY 설정
        setLocation(x,y); // 실행시 위치 설정
		setSize(700,650);	// SIZE 1500,850
		setVisible(true);	// setVisible true로 지정하여 창 활성화
		setResizable(false);	// 창 크기 조절 불가능
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	
	
	public static void main(String[] args) {
		new AdminUserCheck();		// 회원목록보기
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnX) {
			int result = JOptionPane.showConfirmDialog(null, "관리자 창으로 돌아가시겠습니까?", "제목",
	                JOptionPane.OK_CANCEL_OPTION);
	
	        System.out.printf("%d\n", result);
	        if (result == 0) { //OK=0 , Cancel=2 리턴
	        	dispose();
	        }
		}
	}


	@Override
	public void run() {
		
	}
}


