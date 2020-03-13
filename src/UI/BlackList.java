package UI;

import java.awt.*;
import javax.swing.*;

import dbTest.BlacklistDAO;
import dbTest.BlacklistVO;
import dbTest.ReportQDAO;
import dbTest.ReportQVO;

import java.awt.event.*;
import java.util.List;

public class BlackList extends JFrame implements ActionListener{
	private JLabel titleLabel;		
	private JButton btnRegister, btnX;		
	private JTextArea  blackList; 
	private BlacklistVO bvo;
	private BlacklistDAO bdao;
	
	public BlackList(){
		
		titleLabel = new JLabel("블랙 리스트 현황");
		add(titleLabel);
		titleLabel.setBounds(70, 20, 350, 30);
		
		blackList = new JTextArea();
		blackList.setBounds(70, 90, 590, 390);
		add(blackList);
		
		showBlacklist();

		btnX = new JButton("뒤로가기");
		btnX.setBounds(370, 490, 290, 80);
		add(btnX);
		
		btnX.addActionListener(this);
		
		
		//폰트크기
		titleLabel.setFont(titleLabel.getFont().deriveFont(30.0f));
		blackList.setFont(blackList.getFont().deriveFont(30.0f));

		btnX.setFont(btnX.getFont().deriveFont(30.0f));
		
		
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

	private void showBlacklist() {
		
		bdao = new BlacklistDAO();
		bvo = new BlacklistVO();
		
		bdao.showBlacklist();
		
		List<BlacklistVO>list = bdao.showBlacklist();
		for (BlacklistVO bvo : list) {
			blackList.append(bvo.getReportNo() + "\t" + bvo.getReportId() + "\n");
		}
		
	}

	public static void main(String[] args) {
		new BlackList();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnX) {
			dispose();
		}
	}
}

