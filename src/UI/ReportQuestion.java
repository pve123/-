package UI;
import java.awt.*;
import javax.swing.*;

import dbTest.ReportQDAO;
import dbTest.ReportQVO;

import java.awt.event.*;
import java.sql.ResultSet;
import java.util.List;
import java.util.Timer.*;

public class ReportQuestion extends JFrame implements ActionListener, Runnable{	// JFrame은 상속받고 ActionListner는  implements
	
	private ReportQDAO rdao;
	private ReportQVO rvo;
	private JLabel titleLabel;		
	private JButton btnRegister, btnX;		
	private JList<String>  blackList; 
	private JTextArea blackList2;
	
	public ReportQuestion(){
		
		
		
		titleLabel = new JLabel("문제 신고 현황");
		add(titleLabel);
		titleLabel.setBounds(70, 20, 350, 30);
		
//		blackList = new JList<String>();
//		
//		
//		blackList.setBounds(70, 90, 590, 390);
//		
//		add(blackList);
		
		blackList2 = new JTextArea();
		add(blackList2);
		blackList2.setBounds(70, 90, 590, 390);
		reportAll();
		blackList2.setEditable(false);
		

		btnRegister = new JButton("문제 수정");					
		btnRegister.setBounds(70, 490, 290, 80);			
		add(btnRegister);
		
		btnX = new JButton("뒤로가기");
		btnX.setBounds(370, 490, 290, 80);
		add(btnX);
		
		btnX.addActionListener(this);
		btnRegister.addActionListener(this);
		
		//폰트크기
		titleLabel.setFont(titleLabel.getFont().deriveFont(30.0f));
		blackList2.setFont(blackList2.getFont().deriveFont(30.0f));
		btnRegister.setFont(btnRegister.getFont().deriveFont(30.0f));
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
	
	
	private void reportAll() {
		
		rdao = new ReportQDAO();
		rvo = new ReportQVO();
		
		
		
		rdao.reportAll();
		
		List<ReportQVO>list = rdao.reportAll();
		for (ReportQVO rvo : list) {
			blackList2.append(rvo.getQuizNum() +"\t"+ rvo.getContent()+"\n");
		}
		
	}


	public static void main(String[] args) {
		new ReportQuestion();		//신고리스트 목록
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
		}if(e.getSource() == btnRegister) {
			new QuestionModifyInitial();
	
		}
	}


	@Override
	public void run() {
		
	}
}