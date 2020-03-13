package UI;

import java.awt.*;
import javax.swing.*;
import dbTest.QuizDAO;
import dbTest.QuizVO;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.channels.GatheringByteChannel;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Timer.*;

public class InitialP extends JFrame implements ActionListener, Runnable { // JFrame은 상속받고 ActionListner는 implements

   private JLabel nickLabel; // 채팅시 옆에 닉네임 나오게 하는 레이블
   private JButton btnExit, btnReport, ChkBtn, readyBtn; // btnOX는 ox 버튼, btnExit는 나가기 버튼, btnReport는 신고 버튼
   private JTextArea quizArea, gameChatArea, clientCount, curCorrect; // roomInfo: 방제목 출력 quizArea: 퀴즈 문제 출력
   private JLabel roomInfo, quiz;
   private JScrollPane scroll_chat;
   // gameChatArea: 게임방 내 채팅창
   // IniCountArea: 초성 맞춘 개수 출력
   // clientCount: 접속자 수 curCorrect: 현재
   // 맞춘 개수
   private JTextField gameChatFld, ChkFld; // gameChatFld: 게임 채팅 시 채팅을 입력받을 JTextField
   private JScrollPane curClient; // curClient : 접속자 명과 추후에 닉네임 옆에 강퇴버튼 생성

   private JFileChooser jfc;
   private File file;
   private BufferedReader br;
   private PrintWriter pw;
   private Socket socket;
   private String id;
   private int score,count;
   private String qquiz, aanswer, cho, num, example;
   private boolean recheck, gamestart;
   private QuizVO qvo;
   private QuizDAO qdo;
   
   
   

   // 서버에서 문제 받아

   public synchronized boolean isGamestart() {
      return gamestart;
   }

   public synchronized void setGamestart(boolean gamestart) {
      this.gamestart = gamestart;
   }

   public InitialP(String id) {

      Timer m_timer = new Timer();//고쳐
      qvo = new QuizVO();
      qdo = new QuizDAO();

      this.id = id;

      roomInfo = new JLabel(" 초성퀴즈 개인전에 오신걸 환영합니다.  ");
      roomInfo.setBounds(70, 20, 590, 50); // setBounds(x, y, width, height)
      roomInfo.setFont(new Font("궁서", Font.ITALIC, 20));
      add(roomInfo); // 프레임에 roomInfo add

      quizArea = new JTextArea("    대기중");
      quizArea.setBounds(130, 230, 480, 200);
      quizArea.setBackground(new Color(178, 205, 205));
      quizArea.setEditable(false);
      quizArea.setFont(new Font("궁서", Font.BOLD, 200));
      
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
      curClient.setBounds(700, 90, 160, 210);
      add(curClient);

      curCorrect = new JTextArea();
      curCorrect.setFont(new Font("견명조", Font.BOLD, 10));
      curCorrect.setBounds(890, 90, 160, 100);
      curCorrect.setBackground(new Color(250, 236, 197));
      curCorrect.setEditable(false);

      btnReport = new JButton("신고"); // 신고 버튼 누르면 신고 창 떠야함
      btnReport.setBounds(890, 200, 80, 100);
      add(btnReport);

      readyBtn = new JButton("READY"); // 레디
      readyBtn.setBounds(970, 200, 80, 100);
      add(readyBtn);

      gameChatArea = new JTextArea();
    //gameChatArea.setBounds(700, 320, 350, 330);
      gameChatArea.setFont(new Font("견명조", Font.HANGING_BASELINE, 18));
      scroll_chat = new JScrollPane(gameChatArea, 
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); //스크롤
	 scroll_chat.setBounds(700,320,350,330);	 
      getContentPane().add(scroll_chat);
      
      gameChatFld = new JTextField();
      add(gameChatFld);
      gameChatFld.setBounds(760, 660, 290, 30);

      nickLabel = new JLabel(this.id);
      add(nickLabel);
      nickLabel.setBounds(700, 660, 70, 30);

      ChkBtn.addActionListener(this);
      btnReport.addActionListener(this);
      btnExit.addActionListener(this);
      readyBtn.addActionListener(this);
      ChkFld.addKeyListener(new KeyAdapter() {
         @Override
         public void keyReleased(KeyEvent e) {
            if (e.getKeyChar() == 10) {
               if (!ChkFld.getText().equals("")) {
                  ChkBtn.doClick();
               }
            }
         }
      });
      gameChatFld.addKeyListener(new KeyAdapter() {
         @Override
         public void keyReleased(KeyEvent e) {
            if (e.getKeyChar() == 10) {
               if (!gameChatFld.getText().equals("")) {
                  pw.println(gameChatFld.getText());
                  gameChatFld.setText("");
               }
            }

         }
      });

      roomInfo.setFont(roomInfo.getFont().deriveFont(30.0f));
      quizArea.setFont(quizArea.getFont().deriveFont(30.0f));
      clientCount.setFont(clientCount.getFont().deriveFont(15.0f));
      btnExit.setFont(btnExit.getFont().deriveFont(15.0f));
      curCorrect.setFont(curCorrect.getFont().deriveFont(20.0f));
      btnReport.setFont(btnReport.getFont().deriveFont(15.0f));

      addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent e) {
            pw.println("-1");
            dispose();
            System.exit(0);
            try {
               if (br != null)
                  br.close();
               if (pw != null)
                  pw.close();
               if (socket != null)
                  socket.close();
            } catch (IOException e1) {
               // TODO Auto-generated catch block
               e1.printStackTrace();
            }
         }
         @Override
         public void windowOpened(WindowEvent e) {

            try {
               socket = new Socket("localHost", 7000);
               pw = new PrintWriter(socket.getOutputStream(), true);
               pw.println(id + "/" + "님이접속");

               br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
               gameChatArea.append(br.readLine() +"\n");
               new Thread(() -> {
                  try {
                     while (br != null) {
                        if ((example = br.readLine()).contains("&")) {
//                        setGamestart(true);
                           readyBtn.setEnabled(false);
                           new games().start();
                           num = example.split("&")[0];
                           qquiz = example.split("&")[1];
                           cho = example.split("&")[2];
                           aanswer = example.split("&")[3];

                        }
                        else if (example.equals("The End")) {
                           readyBtn.setEnabled(true);
                           readyBtn.setText("READY");
                       

							pw.println("***** "+score+"개 맞추셨습니다. ***** ");		
                        } else
                           gameChatArea.append(example + "\n");
                     }
                  } catch (IOException e1) {
                     e1.printStackTrace();
                  }
               }).start();
               
            add(quizArea);
               
            new Thread(() ->{
                  try {
                     while(true) {
                        curCorrect.setText("      정답 개수 \n\n            "+score+"개");
                     }
                  } catch   (Exception e2) {
                     e2.printStackTrace();
                  }
               }).start();
               add(curCorrect);
            } catch (UnknownHostException e1) {
               System.err.println("> 서버 연결 오류 : 지정된 서버(" + e1.getMessage() + ")가 존재하지 않습니다.");
            } catch (ConnectException e1) {
               System.err.println("> 서버 연결 실패 : 서버 연결 상태를 확인 해 주세요.");
            } catch (IOException e1) {
               e1.printStackTrace();
            }
         }
      });
      int x = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 4 - this.getWidth() / 2; // x좌표 가운데 설정
      int y = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 8 - this.getHeight() / 2; // y좌표 가운데 설정
      Toolkit toolkit = getToolkit(); // Q 플레이 아이콘 설정하기 위한 객체 생성
      getContentPane().setBackground(new Color(178, 205, 205));
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
            pw.println("ready");
         } else {
            readyBtn.setText("READY");
            pw.println("wait");
         }
      }
      if (e.getSource() == ChkBtn) {
//         checkAnswer();//
         if (aanswer.equals(ChkFld.getText())) {
            if (recheck) {
               JOptionPane.showMessageDialog(null, "정답", " ", JOptionPane.INFORMATION_MESSAGE);
               pw.println("correct");
               ChkFld.setText("");
               ChkFld.requestFocus();
               score += 1;
               count +=1;
               recheck = false;
            } else {
               JOptionPane.showMessageDialog(null, "이미 맞춘 문제입니다.", " ", JOptionPane.ERROR_MESSAGE);
            }
         } else {
            JOptionPane.showMessageDialog(null, "틀림", " ", JOptionPane.INFORMATION_MESSAGE);
         }
      } //
   }

   @Override
   public void run() {
      
   }
   
   public void quizs() {
      quizArea.setText(null);
      recheck = true;
        quizArea.append("  "+num+".  "+qquiz+"\n\n\n\n"+"         초성 :    "+cho);
   }

   class games extends Thread {
      @Override
      public void run() {
        int k =0;
            try {
            while (true && k<3) {
            	k++;
               quizs();
               sleep(3000);
            }
           
		    pw.println("***** "+score+"개 맞추셨습니다. ***** ");	
            readyBtn.setEnabled(true);
            readyBtn.setText("READY");
            quizArea.setText(null);
            score=0;
            
            } catch (InterruptedException e) {
               e.printStackTrace();
            }
         
      }
   }

   public static void main(String[] args) {
      new InitialP("aa");
   }
}