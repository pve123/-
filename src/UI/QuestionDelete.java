package UI;

import java.awt.*;
import javax.swing.*;

import dbTest.QuizDAO;
import dbTest.QuizVO;

import java.awt.event.*;
import java.util.Timer.*;

public class QuestionDelete extends JFrame implements ActionListener, Runnable{   // JFrame은 상속받고 ActionListner는  implements
   private QuizDAO qdao;
   private QuizVO qvo;
   private JLabel titleLabel, numLabel;      
   private JButton btnO, btnX, btnNumCheck;      
   private boolean resultset;
   private JTextArea  quizNumArea, quizArea, AnswerArea;
   
   public QuestionDelete(){
      qdao = new QuizDAO();
      qvo = new QuizVO();
      titleLabel = new JLabel("문제삭제");
      add(titleLabel);
      titleLabel.setBounds(70, 20, 350, 30);
      
      numLabel = new JLabel("문제 번호 ");
      add(numLabel);
      numLabel.setBounds(70, 75, 180, 50);
      
      quizNumArea = new JTextArea("문제 번호 입력");
      quizNumArea.setBounds(250, 80, 200, 40);
      add(quizNumArea);
      
      btnNumCheck = new JButton("확인");               
      btnNumCheck.setBounds(560, 80, 100, 40);         
      add(btnNumCheck);
      
      quizArea = new JTextArea("문제번호에 해당되는 문제 출력");
      quizArea.setBounds(70, 140, 590, 40);
      add(quizArea);
      quizArea.setEditable(false);
      
      AnswerArea = new JTextArea("문제번호에 해당되는 답안 출력");
      AnswerArea.setBounds(70, 190, 590, 40);
      add(AnswerArea);
      AnswerArea.setEditable(false);
      
      btnO = new JButton("삭제");               
      btnO.setBounds(70, 250, 290, 80);         
      add(btnO);
      btnX = new JButton("취소");
      btnX.setBounds(370, 250, 290, 80);
      add(btnX);
      
      quizNumArea.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
            // TODO Auto-generated method stub
            quizNumArea.setText(null);
         }
      });
      
      btnX.addActionListener(this);
      btnNumCheck.addActionListener(this);
      btnO.addActionListener(this);


      quizNumArea.setFont(quizNumArea.getFont().deriveFont(30.0f));
      quizArea.setFont(quizArea.getFont().deriveFont(30.0f));
      AnswerArea.setFont(AnswerArea.getFont().deriveFont(30.0f));
      
      titleLabel.setFont(titleLabel.getFont().deriveFont(30.0f));
      numLabel.setFont(numLabel.getFont().deriveFont(30.0f));
      
      btnNumCheck.setFont(btnNumCheck.getFont().deriveFont(30.0f));
      btnO.setFont(btnO.getFont().deriveFont(30.0f));
      btnX.setFont(btnX.getFont().deriveFont(30.0f));
      
      
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
      new QuestionDelete();      // 문제삭제
      
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
      }if(e.getSource() == btnNumCheck) {
         int result = JOptionPane.showConfirmDialog(null, "해당문제를 검색하시겠습니까?", "제목",
                   JOptionPane.OK_CANCEL_OPTION);
   
           System.out.printf("%d\n", result);
           if (result == 0) { //OK=0 , Cancel=2 리턴
              getOneQuiz();
           }
      }if(e.getSource() == btnO) {
         int result = JOptionPane.showConfirmDialog(null, "해당문제를 삭제하시겠습니까?", "제목",
                   JOptionPane.OK_CANCEL_OPTION);
   
           System.out.printf("%d\n", result);
           if (result == 0) { //OK=0 , Cancel=2 리턴
              quizDelete();
              if(resultset) {
                 JOptionPane.showMessageDialog(null, "해당 문제를 삭제 하였습니다.","문제삭제",JOptionPane.INFORMATION_MESSAGE);
                 quizArea.setText(null);
                 AnswerArea.setText(null);
              }
              else JOptionPane.showMessageDialog(null, "문제 삭제 실패","실패",JOptionPane.ERROR_MESSAGE);
           }
      }
   }
   public void getOneQuiz() {//문제 번호를 입력 받고 문제와 답을 각각의area에 출력
      qvo = qdao.quizModify(Integer.parseInt(quizNumArea.getText()));
      quizArea.setText(qvo.getQuizQuestion());
      AnswerArea.setText(qvo.getAnswer());
   }
   public void quizDelete() {
      resultset = qdao.quizDelete(Integer.parseInt(quizNumArea.getText()));
   }


   @Override
   public void run() {
      
   }
}

