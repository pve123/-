package UI;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Withdrawal extends JFrame implements ActionListener{
   private JButton yes,no;
   private JTextField idFld, pwFld, answerFld;
   private JLabel idlbl, pwlbl, answerlbl;
   
   public Withdrawal() {
      
      idlbl = new JLabel("id를 입력해주세요");
      idlbl.setBounds(100, 10, 180, 15);
      add(idlbl);
      idlbl.setFont(idlbl.getFont().deriveFont(13.0f));
      
      idFld = new JTextField();
      idFld.setBounds(100, 30, 300, 20);
      add(idFld);

      
      
      pwlbl = new JLabel("비밀번호를 입력해주세요");
      pwlbl.setBounds(100, 70, 180, 15);
      add(pwlbl);
      pwlbl.setFont(idlbl.getFont().deriveFont(13.0f));

      pwFld = new JTextField();
      pwFld.setBounds(100, 90, 300, 20);
      add(pwFld);
      
      
      answerlbl = new JLabel("문제의 정답을 맞춰라");
      answerlbl.setBounds(100, 130, 180, 15);
      add(answerlbl);
      answerlbl.setFont(idlbl.getFont().deriveFont(13.0f));

      answerFld = new JTextField();
      answerFld.setBounds(100, 150, 300, 20);
      add(answerFld);
      
      yes = new JButton("탈퇴");
      yes.setBounds(160, 200, 70, 30);
      add(yes);
      
      no = new JButton("취소");
      no.setBounds(250, 200, 70, 30);
      add(no);
      
      
      
      
      int x = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/8 - this.getWidth()/2;   // x좌표 가운데 설정
        int y = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/8 - this.getHeight()/2; //y좌표 가운데 설정
        setLocation(x,y); // 프레임창 위치
        Toolkit toolkit = getToolkit();
        Image image = toolkit.createImage("C:\\javawork\\Q_icon.png"); 
        setIconImage(image);
        setTitle(" Q_PLAY "); 
        getContentPane().setBackground(new Color(212,244,250));   //배경색    
        setLayout(null);
        setSize(500,300);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
   }

   public static void main(String[] args) {
      new Withdrawal();
   }
   @Override
   public void actionPerformed(ActionEvent e) {
      if(e.getSource() == yes) {
         
      }else if(e.getSource() == no) {
         
      }
   }
}