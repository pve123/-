package UI;

import java.awt.*;
import javax.swing.*;
import dbTest.QuizDAO;
import dbTest.QuizVO;
import dbTest.ReportCDAO;
import dbTest.ReportCVO;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer.*;

public class AdminWindow extends JFrame implements ActionListener, Runnable{   // JFrame은 상속받고 ActionListner는  implements
   
   private JButton btnQuestion, btnNowConnect, btnReport, btnLogOut
               ,btnModify, btnDelete;      
   private JTextArea    questionList;
   private String[] game = {"--전체--","초성퀴즈", "OX퀴즈"};
   private JList<String> quizlist;
   private QuizVO qvo;
   private QuizDAO qdao;
   
   private int index;
   
   
   public AdminWindow(){
      qdao = new QuizDAO();
      qvo = new QuizVO();
      JComboBox cmbBox = new JComboBox(game);
      getContentPane().add(new JLabel("cmbBox "));
      getContentPane().add(cmbBox);
      cmbBox.setBounds(70, 10, 100, 30);
      
      questionList = new JTextArea(); //setBounds(x, y, width, height)
      questionList.setBounds(70, 60, 1000, 482);
      getContentPane().add(questionList);
      questionList.setEditable(false);
      
      btnQuestion = new JButton("문제등록");               
      btnQuestion.setBounds(70, 646, 590, 49);         
      getContentPane().add(btnQuestion);
      
      btnModify = new JButton("문제수정");               
      btnModify.setBounds(70, 573, 284, 49);         
      getContentPane().add(btnModify);
      
      btnDelete = new JButton("문제삭제");               
      btnDelete.setBounds(376, 573, 284, 49);         
      getContentPane().add(btnDelete);
      
      btnNowConnect = new JButton("현재 접속자 조회");            
      btnNowConnect.setBounds(700,573,370,49);
      getContentPane().add(btnNowConnect);
      
      btnReport = new JButton("신고현황");            
      btnReport.setBounds(700,646,370,49);
      getContentPane().add(btnReport);
      
      btnLogOut = new JButton("로그아웃");         
      btnLogOut.setBounds(700,719,370,49);
      getContentPane().add(btnLogOut);
      
   
      //폰트크기 지정
      questionList.setFont(questionList.getFont().deriveFont(15.0f));
      btnNowConnect.setFont(btnNowConnect.getFont().deriveFont(15.0f));
      btnReport.setFont(btnReport.getFont().deriveFont(15.0f));
      btnLogOut.setFont(btnLogOut.getFont().deriveFont(15.0f));
      btnQuestion.setFont(btnQuestion.getFont().deriveFont(15.0f));

      cmbBox.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            JComboBox<String> cb =(JComboBox<String>) e.getSource();
             index = cb.getSelectedIndex();
             questionList.setText(null);
             showQuiz();
             
         }
      });
      
      btnQuestion.addActionListener(this);
      btnLogOut.addActionListener(this);
      btnModify.addActionListener(this);
      btnDelete.addActionListener(this);
      btnReport.addActionListener(this);
      btnNowConnect.addActionListener(this);
      
      showQuiz();
      
      int x = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/4 - this.getWidth()/2;   // x좌표 가운데 설정
        int y = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/8 - this.getHeight()/2; //y좌표 가운데 설정
        Toolkit toolkit = getToolkit();   // Q 플레이 아이콘 설정하기 위한 객체 생성
        Image image = toolkit.createImage("C:\\javawork\\Q_icon.png"); // Q 플레이 아이콘      // 경로에 파일 이미지 없으면 자바컵으로 나옴
        getContentPane().setLayout(null);
        
        JButton createRoom = new JButton("\uBC29\uC0DD\uC131");
        createRoom.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent arg0) {
              new CreateRoomUi();
           }
        });
        
        createRoom.setFont(createRoom.getFont().deriveFont(15f));
        createRoom.setBounds(70, 719, 590, 49);
        getContentPane().add(createRoom);
        setIconImage(image);   // QPLAY 아이콘 설정
        setTitle(" Q_PLAY ");   // UI 제목 Q_PLAY 설정
        setLocation(x,y); // 실행시 위치 설정
      setSize(1149,849);   // SIZE 1500,850
      setVisible(true);   // setVisible true로 지정하여 창 활성화
      setResizable(false);   // 창 크기 조절 불가능
      setDefaultCloseOperation(DISPOSE_ON_CLOSE);
   }
   
   
   public static void main(String[] args) {
      new AdminWindow();      // 관리자창
      
   }

   @Override
   public void actionPerformed(ActionEvent e) {

      if(e.getSource() == btnQuestion) {
         new QuestionRegisterSelect();
         
         
       }if(e.getSource() == btnLogOut) {
         int result = JOptionPane.showConfirmDialog(null, "프로그램을 종료하시 것습니까?", "제목",
                   JOptionPane.OK_CANCEL_OPTION);
   
           System.out.printf("%d\n", result);
           if (result == 0) { //OK=0 , Cancel=2 리턴
               System.exit(0);
           }
       }if(e.getSource() == btnModify) {
         new QuestionModifyInitial();
         
         
       }if(e.getSource() == btnDelete) {
         new QuestionDelete();
         
         
       }if(e.getSource() == btnNowConnect) {
         new NowConnectUser();
         
         
       }if(e.getSource() == btnReport) {
          new ReportSelect();
       } 

      
      
   }
   public void showQuiz() {
      if(index==0) {
      List<QuizVO> list = qdao.quizlist();
      for (QuizVO quizVO : list) {
         String a ="유형 :"+quizVO.getQuizSep()+"\t번호 :"+quizVO.getQuizNum()+
               "\t\t문제 :"+quizVO.getQuizQuestion() + "\t\t초성: " + quizVO.getQuizCho() + "\t\t답 : "+quizVO.getAnswer()+"\n";
         questionList.append(a);
         
      }
      }
      else {
         if(index==1) {
         List<QuizVO> list = qdao.quizlist("초성");
         for (QuizVO quizVO : list) {
            String a ="유형 :"+quizVO.getQuizSep()+"\t번호 :"+quizVO.getQuizNum()+
                  "\t\t문제 :"+quizVO.getQuizQuestion()+"\t\t답 : "+quizVO.getAnswer()+"\n";
            questionList.append(a);
            
         }
         }
         else {
            List<QuizVO> list = qdao.quizlist("OX");
            for (QuizVO quizVO : list) {
               String a ="유형 :"+quizVO.getQuizSep()+"\t번호 :"+quizVO.getQuizNum()+
                     "\t\t문제 :"+quizVO.getQuizQuestion()+"\t\t답 : "+quizVO.getAnswer()+"\n";
               questionList.append(a);
               
            }
         }
         
      }
   }

   @Override
   public void run() {
      
   }
}

