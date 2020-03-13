package UI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BugReportUI extends JFrame implements ActionListener{
	private JTextField gameBugFld;// quizNo: 퀴즈의 번호를 입력받음, quizContent: 퀴즈의 이상한 점 입력받기
	private JButton btnSubmit, btnCancel;
	
	public BugReportUI() {		// 게임 버그 신고 받는 UI 메서드	
		
		gameBugFld = new JTextField("문제가 이상한 점을 신고해주세요!");
		gameBugFld.setBounds(20, 15, 345, 400);
		add(gameBugFld);
		
		btnSubmit = new JButton("제출");
		btnSubmit.setBounds(110, 420, 60, 30);
		add(btnSubmit);
		
		btnCancel = new JButton("취소");
		btnCancel.setBounds(210, 420, 60, 30);
		add(btnCancel);
		
		
		btnSubmit.addActionListener(this);
		btnCancel.addActionListener(this);
		
		
		int x = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/3 - this.getWidth()/2;  	 // x좌표 가운데 설정
        int y = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/4 - this.getHeight()/2; 	//y좌표 가운데 설정
        Toolkit toolkit = getToolkit();		// Q 플레이 아이콘 설정하기 위한 객체 생성
        Image image = toolkit.createImage("C:\\javawork\\Q_icon.png"); // Q 플레이 아이콘		// 경로에 파일 이미지 없으면 자바컵으로 나옴
        setLayout(null);
        setIconImage(image);	// QPLAY 아이콘 설정
        setTitle(" Q_PLAY ");	// UI 제목 Q_PLAY 설정
        setLocation(x,y); 		// 실행시 위치 설정
		setSize(400,510);		// SIZE 500,250
		setVisible(true);		// setVisible true로 지정하여 창 활성화
		setResizable(false);	// 창 크기 조절 불가능
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnSubmit) {
			JOptionPane.showMessageDialog(null, "성공적으로 제출되었습니다", "Message", JOptionPane.INFORMATION_MESSAGE);
			dispose();
		}
		if(e.getSource() == btnCancel) {
			dispose();
		}
		
	}
}
