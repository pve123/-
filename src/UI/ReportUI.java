package UI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ReportUI extends JFrame implements ActionListener {
	
	private JButton iniReport, bugReport, btnCancel;
	
	public ReportUI(){
		iniReport = new JButton("게임 문제 오류 신고");
		iniReport.setBounds(60, 35, 150, 70);
		add(iniReport);
		bugReport = new JButton("게임 버그 신고");
		bugReport.setBounds(280, 35, 150, 70);
		add(bugReport);
		btnCancel = new JButton("취소");
		btnCancel.setBounds(215, 120, 60, 35);
		add(btnCancel);
		
		iniReport.addActionListener(this);
		bugReport.addActionListener(this);
		btnCancel.addActionListener(this);
		
		
		int x = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/3 - this.getWidth()/2;  	 // x좌표 가운데 설정
        int y = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/3 - this.getHeight()/2; 	//y좌표 가운데 설정
        Toolkit toolkit = getToolkit();		// Q 플레이 아이콘 설정하기 위한 객체 생성
        Image image = toolkit.createImage("C:\\javawork\\Q_icon.png"); // Q 플레이 아이콘		// 경로에 파일 이미지 없으면 자바컵으로 나옴
        setLayout(null);
        setIconImage(image);	// QPLAY 아이콘 설정
        setTitle(" Q_PLAY ");	// UI 제목 Q_PLAY 설정
        setLocation(x,y); 		// 실행시 위치 설정
		setSize(500,200);		// SIZE 500,250
		setVisible(true);		// setVisible true로 지정하여 창 활성화
		setResizable(false);	// 창 크기 조절 불가능
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new ReportUI();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == iniReport) {
			dispose();
			new GameReportUI();
		}
		if(e.getSource() == bugReport) {
			dispose();
			new BugReportUI();
		}
		if(e.getSource() == btnCancel) {
			dispose();
		}
		
	}
}
