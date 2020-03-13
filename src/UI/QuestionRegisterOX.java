package UI;

import java.awt.*;
import javax.swing.*;

import dbTest.QuizDAO;
import dbTest.QuizVO;

import java.awt.event.*;
import java.util.Timer.*;

public class QuestionRegisterOX extends JFrame implements ActionListener, Runnable{   // JFrame은 상속받고 ActionListner는  implements
   
   private JLabel titleLabel, quizLabel, answerLabel;      
   private JButton btnO, btnX, btnRegister, btnCancel;      
   private QuizVO qvo;
   private QuizDAO qdao;
   private boolean results;
   private String ox;

   private JTextArea  quizArea;
   
   public QuestionRegisterOX(){
      qvo = new QuizVO();
      qdao = new QuizDAO();
      titleLabel = new JLabel("OX퀴즈 문제 등록");
      add(titleLabel);
      titleLabel.setBounds(70, 20, 350, 30);
      
      quizLabel = new JLabel("문제 ");
      add(quizLabel);
      quizLabel.setBounds(70, 70, 100, 50);
      
      quizArea = new JTextArea("등록 하고 싶은 문제");
      quizArea.setBounds(160, 75, 450, 40);
      add(quizArea);
      
      answerLabel = new JLabel("답 ");
      add(answerLabel);
      answerLabel.setBounds(70, 150, 100, 50);
      
      btnO = new JButton("O");               
      btnO.setBounds(160, 140, 200, 80);         
      add(btnO);
      btnX = new JButton("X");
      btnX.setBounds(370, 140, 200, 80);
      add(btnX);

      btnRegister = new JButton("등록");               
      btnRegister.setBounds(70, 240, 290, 80);         
      add(btnRegister);
      btnCancel = new JButton("취소");
      btnCancel.setBounds(370, 240, 290, 80);
      add(btnCancel);
   
      btnO.addActionListener(this);
      btnX.addActionListener(this);
      btnRegister.addActionListener(this);
      btnCancel.addActionListener(this);
      
      quizArea.setFont(quizArea.getFont().deriveFont(30.0f));
      titleLabel.setFont(titleLabel.getFont().deriveFont(30.0f));
      quizLabel.setFont(quizLabel.getFont().deriveFont(30.0f));
      answerLabel.setFont(answerLabel.getFont().deriveFont(30.0f));
      
      btnO.setFont(btnO.getFont().deriveFont(30.0f));
      btnX.setFont(btnX.getFont().deriveFont(30.0f));
      btnRegister.setFont(btnRegister.getFont().deriveFont(30.0f));
      btnCancel.setFont(btnCancel.getFont().deriveFont(30.0f));
      
      
      int x = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/4 - this.getWidth()/2;   // x좌표 가운데 설정
        int y = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/8 - this.getHeight()/2; //y좌표 가운데 설정
        Toolkit toolkit = getToolkit();   // Q 플레이 아이콘 설정하기 위한 객체 생성
        Image image = toolkit.createImage("C:\\javawork\\Q_icon.png"); // Q 플레이 아이콘      // 경로에 파일 이미지 없으면 자바컵으로 나옴
        setLayout(null);
        setIconImage(image);   // QPLAY 아이콘 설정
        setTitle(" Q_PLAY ");   // UI 제목 Q_PLAY 설정
        setLocation(x,y); // 실행시 위치 설정
      setSize(700,400);   // SIZE 1500,850
      setVisible(true);   // setVisible true로 지정하여 창 활성화
      setResizable(false);   // 창 크기 조절 불가능
      setDefaultCloseOperation(DISPOSE_ON_CLOSE);
   }
   
   
   public static void main(String[] args) {
      new QuestionRegisterOX();      //OX문제 등록
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      if(e.getSource() == btnO) {
         btnO.setBackground(Color.red);
         btnX.setBackground(new Color(238,238,238));
         ox="o";
      }if(e.getSource() == btnX){
         btnX.setBackground(Color.red);
         btnO.setBackground(new Color(238,238,238));
         ox="x";
      }
      
      
      if(e.getSource() == btnCancel) {
         int result = JOptionPane.showConfirmDialog(null, "문제등록 창으로 돌아가시겠습니까?", "제목",
                   JOptionPane.OK_CANCEL_OPTION);
   
           System.out.printf("%d\n", result);
           if (result == 0) { //OK=0 , Cancel=2 리턴
              dispose();
           }
      }if(e.getSource() == btnRegister) {
         int result = JOptionPane.showConfirmDialog(null, "해당문제를 등록하시겠습니까?", "제목",
                   JOptionPane.OK_CANCEL_OPTION);
   
           System.out.printf("%d\n", result);
           if (result == 0) { //OK=0 , Cancel=2 리턴
              quizOx();
              if(results) {
                 JOptionPane.showMessageDialog(null, "문제 등록 완료","문제등록",JOptionPane.INFORMATION_MESSAGE);
              }
           }
      }
   }
   
   public void quizOx() {
      qvo.setQuizQuestion(quizArea.getText());
      qvo.setAnswer(ox);
      results = qdao.quizOX(qvo);
   }


   @Override
   public void run() {
      
   }
}

