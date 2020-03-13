package UI;

import java.awt.*;
import javax.swing.*;

import dbTest.QuizDAO;
import dbTest.QuizVO;

import java.awt.event.*;
import java.util.Timer.*;

public class QuestionRegisterInitial extends JFrame implements ActionListener, Runnable{   // JFrame은 상속받고 ActionListner는  implements
   
   private JLabel titleLabel, quizLabel, choLabel, answerLabel;      
   private JButton btnO, btnX;   
   private JTextArea  quizArea, choArea,answerArea;
   private QuizVO qvo;
   private QuizDAO qdao;
   private boolean results;
   public QuestionRegisterInitial(){
      qvo = new QuizVO();
      qdao = new QuizDAO();
      titleLabel = new JLabel("초성퀴즈 문제 등록");
      getContentPane().add(titleLabel);
      titleLabel.setBounds(40, 32, 273, 30);
      
      quizLabel = new JLabel("문제 ");
      getContentPane().add(quizLabel);
      quizLabel.setBounds(40, 72, 100, 50);
      
      quizArea = new JTextArea("등록 하고 싶은 문제");
      quizArea.setBounds(132, 85, 478, 30);
      getContentPane().add(quizArea);
      
      choLabel  = new JLabel("초성");
      choLabel.setFont(new Font("굴림", Font.PLAIN, 25));
      choLabel.setBounds(40, 141, 54, 30);
      getContentPane().add(choLabel);
      
      choArea = new JTextArea("초성 적기");
      choArea.setFont(new Font("Monospaced", Font.PLAIN, 22));
      choArea.setBounds(132, 140, 478, 30);
      getContentPane().add(choArea);
      
      
      answerLabel = new JLabel("답 ");
      getContentPane().add(answerLabel);
      answerLabel.setBounds(40, 183, 100, 50);
      
      answerArea = new JTextArea("문제등록에 대한 답");
      answerArea.setBounds(132, 196, 478, 30);
      getContentPane().add(answerArea);
      
      btnO = new JButton("등록");               
      btnO.setBounds(40, 268, 290, 40);         
      getContentPane().add(btnO);
      btnX = new JButton("취소");
      btnX.setBounds(368, 268, 290, 40);
      getContentPane().add(btnX);
      
      btnX.addActionListener(this);
      btnO.addActionListener(this);
      
      quizArea.setFont(new Font("Monospaced", Font.PLAIN, 22));
      answerArea.setFont(new Font("Monospaced", Font.PLAIN, 22));
      titleLabel.setFont(new Font("굴림", Font.PLAIN, 25));
      quizLabel.setFont(new Font("굴림", Font.PLAIN, 25));
      answerLabel.setFont(new Font("굴림", Font.PLAIN, 25));
      
      btnO.setFont(new Font("굴림", Font.PLAIN, 25));
      btnX.setFont(new Font("굴림", Font.PLAIN, 25));
      
      
      int x = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/4 - this.getWidth()/2;   // x좌표 가운데 설정
        int y = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/8 - this.getHeight()/2; //y좌표 가운데 설정
        Toolkit toolkit = getToolkit();   // Q 플레이 아이콘 설정하기 위한 객체 생성
        Image image = toolkit.createImage("C:\\javawork\\Q_icon.png"); // Q 플레이 아이콘      // 경로에 파일 이미지 없으면 자바컵으로 나옴
        getContentPane().setLayout(null);
        setIconImage(image);   // QPLAY 아이콘 설정
        setTitle(" Q_PLAY ");   // UI 제목 Q_PLAY 설정
        setLocation(x,y); // 실행시 위치 설정
      setSize(700,368);   // SIZE 1500,850
      setVisible(true);   // setVisible true로 지정하여 창 활성화
      setResizable(false);   // 창 크기 조절 불가능
      setDefaultCloseOperation(DISPOSE_ON_CLOSE);
   }
   
   
   public static void main(String[] args) {
      new QuestionRegisterInitial();      //초성퀴즈 문제등록
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      if(e.getSource() == btnX) {
         int result = JOptionPane.showConfirmDialog(null, "문제등록 창으로 돌아가시겠습니까?", "제목",
                   JOptionPane.OK_CANCEL_OPTION);
   
           System.out.printf("%d\n", result);
           if (result == 0) { //OK=0 , Cancel=2 리턴
              dispose();
           }
      }if(e.getSource() == btnO) {
         int result = JOptionPane.showConfirmDialog(null, "해당문제를 등록하시겠습니까?", "제목",
                   JOptionPane.OK_CANCEL_OPTION);
   
           System.out.printf("%d\n", result);
           if (result == 0) { //OK=0 , Cancel=2 리턴
              quizIni();
              if(results) {
                 JOptionPane.showMessageDialog(null, "문제등록 완료","문제등록",JOptionPane.INFORMATION_MESSAGE);
              }
           }
      }
   }
   public void quizIni() {
      qvo.setQuizQuestion(quizArea.getText());
      qvo.setQuizCho(choArea.getText());
      qvo.setAnswer(answerArea.getText());
      results = qdao.quizIni(qvo);
   }

   @Override
   public void run() {
      
   }
}
