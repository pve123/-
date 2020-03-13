package UI;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Timer.*;

public class QuestionRegisterSelect extends JFrame implements ActionListener, Runnable{   // JFrame은 상속받고 ActionListner는  implements
   
   private JLabel titleLabel;      
   private JButton btnCheck, btnCancel;   
   private JRadioButton chkBoxInitial, chkBoxOX;

   public QuestionRegisterSelect(){
      
      titleLabel = new JLabel("문제 등록");
      add(titleLabel);
      titleLabel.setBounds(70, 20, 350, 30);
      
      chkBoxInitial = new JRadioButton(" 초성게임", true);
      chkBoxInitial.setBounds(70, 100, 250, 30);
      
      chkBoxOX = new JRadioButton(" OX게임");
      chkBoxOX.setBounds(400, 100, 250, 30);
      
      ButtonGroup rdogroup = new ButtonGroup();      //그룹으로 묶으면 그룹중 하나선택만 가능
      rdogroup.add(chkBoxInitial);
      rdogroup.add(chkBoxOX);
      
      add(chkBoxInitial);
      add(chkBoxOX);

      btnCheck = new JButton("확인");               
      btnCheck.setBounds(70, 180, 290, 80);         
      add(btnCheck);
      btnCancel = new JButton("취소");
      btnCancel.setBounds(370, 180, 290, 80);
      add(btnCancel);

      
      
      btnCancel.addActionListener(this);
      btnCheck.addActionListener(this);
      
      
      //폰트 크기
      titleLabel.setFont(titleLabel.getFont().deriveFont(30.0f));
      chkBoxInitial.setFont(chkBoxInitial.getFont().deriveFont(30.0f));
      chkBoxOX.setFont(chkBoxOX.getFont().deriveFont(30.0f));
      btnCheck.setFont(btnCheck.getFont().deriveFont(30.0f));
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
      new QuestionRegisterSelect();      //문제등록시 초성, OX선택
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      if(e.getSource() == btnCancel) {
         int result = JOptionPane.showConfirmDialog(null, "관리자 창으로 돌아가시겠습니까?", "제목",
                   JOptionPane.OK_CANCEL_OPTION);
   
           System.out.printf("%d\n", result);
           if (result == 0) { //OK=0 , Cancel=2 리턴
              dispose();
           }
      }if(e.getSource() == btnCheck) {
         if(chkBoxInitial.isSelected() == true) {
            int result = JOptionPane.showConfirmDialog(null, "초성게임 문제 등록 하시겠습니까?", "제목",
                      JOptionPane.OK_CANCEL_OPTION);
      
              System.out.printf("%d\n", result);
              if (result == 0) { //OK=0 , Cancel=2 리턴
                 new QuestionRegisterInitial();
              }
         }else if(chkBoxOX.isSelected() == true) {
            int result = JOptionPane.showConfirmDialog(null, "OX게임 문제 등록 하시겠습니까?", "제목",
                      JOptionPane.OK_CANCEL_OPTION);
      
              System.out.printf("%d\n", result);
              if (result == 0) { //OK=0 , Cancel=2 리턴
                 new QuestionRegisterOX();
              }
         }
      
      }
   }


   @Override
   public void run() {
      
   }
}

