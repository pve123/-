package UI;


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Timer.*;

public class InitialTeam extends JFrame implements ActionListener, MouseListener, MouseMotionListener, Runnable { // JFrame은
                                                                                    // 상속받고
                                                                                    // ActionListner는
                                                                                    // implements

   private JLabel nickLabel; // 채팅시 옆에 닉네임 나오게 하는 레이블
   private JButton btnExit, btnReport, ChkBtn, RedBtn, BlueBtn, readyBtn; // btnOX는 ox 버튼, btnExit는 나가기 버튼, btnReport는 신고 버튼
   private JTextArea roomInfo, quizArea, gameChatArea, clientCount, curCorrect; // roomInfo: 방제목 출력 quizArea: 퀴즈 문제 출력
                                                               // gameChatArea: 게임방 내 채팅창
                                                               // IniCountArea: 초성 맞춘 개수 출력
                                                               // clientCount: 접속자 수 curCorrect: 현재
                                                               // 맞춘 개수
   private JTextField gameChatFld, ChkFld; // gameChatFld: 게임 채팅 시 채팅을 입력받을 JTextField
   private JScrollPane curClient; // curClient : 접속자 명과 추후에 닉네임 옆에 강퇴버튼 생성

   public InitialTeam() {

      roomInfo = new JTextArea("방 제목이 들어간다!!");
      roomInfo.setBounds(70, 20, 590, 50); // setBounds(x, y, width, height)
      add(roomInfo); // 프레임에 roomInfo add

      quizArea = new JTextArea("DB에 저장한 문제 랜덤 출력");
      quizArea.setBounds(70, 90, 590, 530);
      add(quizArea);

      ChkFld = new JTextField();
      add(ChkFld);
      ChkFld.setBounds(70, 640, 500, 50);

      ChkBtn = new JButton("확인");
      add(ChkBtn);
      ChkBtn.setBounds(570, 640, 90, 50);

      clientCount = new JTextArea("접속자 수");
      clientCount.setBounds(700, 20, 160, 50);
      add(clientCount);

      btnExit = new JButton("나가기"); // 나가기 버튼 누르면 대기실로 이동
      btnExit.setBounds(890, 20, 160, 50);
      add(btnExit);

      curClient = new JScrollPane();
      curClient.setBounds(700, 90, 160, 100);
      add(curClient);

      RedBtn = new JButton("RED");
      add(RedBtn);
      RedBtn.setBounds(700, 200, 80, 100);
      BlueBtn = new JButton("BLUE");
      add(BlueBtn);
      BlueBtn.setBounds(780, 200, 80, 100);
      RedBtn.addMouseListener(this);
      RedBtn.addMouseMotionListener(this);
      BlueBtn.addMouseListener(this);
      BlueBtn.addMouseMotionListener(this);
      

      curCorrect = new JTextArea("현재 맞춘 개수");
      curCorrect.setBounds(890, 90, 160, 100);
      add(curCorrect);

      btnReport = new JButton("신고"); // 신고 버튼 누르면 신고 창 떠야함
      btnReport.setBounds(890, 200, 80, 100);
      add(btnReport);
      
      readyBtn = new JButton("READY"); // 레디
      readyBtn.setBounds(970, 200, 80, 100);
      add(readyBtn);
      

      gameChatArea = new JTextArea("채팅");
      gameChatArea.setBounds(700, 320, 350, 330);
      add(gameChatArea);
      gameChatFld = new JTextField();
      add(gameChatFld);
      gameChatFld.setBounds(760, 660, 290, 30);

      nickLabel = new JLabel("둔이둔이");
      add(nickLabel);
      nickLabel.setBounds(700, 660, 70, 30);

      ChkBtn.addActionListener(this);
      btnReport.addActionListener(this);
      btnExit.addActionListener(this);
      RedBtn.addActionListener(this);
      BlueBtn.addActionListener(this);
      readyBtn.addActionListener(this);
      
      roomInfo.setFont(roomInfo.getFont().deriveFont(30.0f));
      quizArea.setFont(quizArea.getFont().deriveFont(30.0f));
      clientCount.setFont(clientCount.getFont().deriveFont(15.0f));
      btnExit.setFont(btnExit.getFont().deriveFont(15.0f));
      curCorrect.setFont(curCorrect.getFont().deriveFont(20.0f));
      btnReport.setFont(btnReport.getFont().deriveFont(15.0f));

      int x = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 4 - this.getWidth() / 2; // x좌표 가운데 설정
      int y = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 8 - this.getHeight() / 2; // y좌표 가운데 설정
      Toolkit toolkit = getToolkit(); // Q 플레이 아이콘 설정하기 위한 객체 생성
      Image image = toolkit.createImage("C:\\javawork\\Q_icon.png"); // Q 플레이 아이콘 // 경로에 파일 이미지 없으면 자바컵으로 나옴
      setLayout(null);
      setIconImage(image); // QPLAY 아이콘 설정
      setTitle(" Q_PLAY "); // UI 제목 Q_PLAY 설정
      setLocation(x, y); // 실행시 위치 설정
      setSize(1100, 800); // SIZE 1500,850
      setVisible(true); // setVisible true로 지정하여 창 활성화
      setResizable(false); // 창 크기 조절 불가능
      setDefaultCloseOperation(DISPOSE_ON_CLOSE);
   }

   public static void main(String[] args) {
      new InitialTeam(); // waitRoom
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      if (e.getSource() == btnReport) {
         new ReportUI();
      }
      if (e.getSource() == btnExit) {
         int result = JOptionPane.showConfirmDialog(null, "정말 나갈꺼야?", "나가기", JOptionPane.YES_NO_OPTION);
         if (result == JOptionPane.CLOSED_OPTION) {
            dispose();
         } else {
            // 대기실 접속
         }
      }
      
      if (e.getSource() == readyBtn) {
			if (readyBtn.getText().equals("READY")) {
				readyBtn.setText("wait");
			} else
				readyBtn.setText("READY");
		}
	}

   @Override
   public void run() {

   }

   @Override
   public void mouseDragged(MouseEvent arg0) {
      // TODO Auto-generated method stub

   }

   @Override
   public void mouseMoved(MouseEvent arg0) {
      // TODO Auto-generated method stub

   }

   @Override
   public void mouseClicked(MouseEvent e) {
      // TODO Auto-generated method stub
      if (e.getSource() == RedBtn) {
         RedBtn.setBackground(Color.red);
         BlueBtn.setBackground(Color.LIGHT_GRAY);
      }
      if (e.getSource() == BlueBtn) {
         BlueBtn.setBackground(Color.blue);
         RedBtn.setBackground(Color.LIGHT_GRAY);
      }

   }

   @Override
   public void mouseEntered(MouseEvent arg0) {
      // TODO Auto-generated method stub

   }

   @Override
   public void mouseExited(MouseEvent arg0) {
      // TODO Auto-generated method stub

   }

   @Override
   public void mousePressed(MouseEvent arg0) {
      // TODO Auto-generated method stub

   }

   @Override
   public void mouseReleased(MouseEvent arg0) {
      // TODO Auto-generated method stub

   }

}