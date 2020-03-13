package UI;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Timer.*;


public class resultgame extends JFrame{
	
	private JTextArea area;
	
	
	public resultgame(String score){
		
				Timer m_timer = new Timer();
				
				area = new JTextArea(30,30);
				area.setBounds(70, 70, 500, 500);
				add(area);
				
				area.setText(score);
				int x = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/4 - getWidth()/2;   // x좌표 가운데 설정
		        int y = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/8 - getHeight()/2; //y좌표 가운데 설정
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
				
				
				m_timer.schedule(new TimerTask() {
					
					@Override
					public void run() {
						dispose();
						m_timer.cancel();
					}
				}, 3000);
				
	}
	

}
