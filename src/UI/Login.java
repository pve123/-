package UI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;

import dbTest.ClientDAO;
import dbTest.ClientVO;

public class Login extends JFrame implements ActionListener {
   private Signup sign;
   private LostId idlost;
   private LostPW pwlost;
   private JLabel lid, lpw;//id,pw
   private TextField insertid;//id입력창
   private JPasswordField insertpw;//pw입력창
   private JButton confirm,lostid,lostpw,signup;//확인,id찾기,pw 찾기,회원가입 버튼
   private ClientVO cvo;
   private ClientDAO cdo;
//   private boolean result;
   
   public Login() {
      
      cvo = new ClientVO();
      cdo = new ClientDAO();
      
      lid = new JLabel("ID");
      lpw = new JLabel("PW");
      insertid = new TextField(20);
      insertpw = new JPasswordField(20);
      confirm = new JButton("확인");
      lostid = new JButton("ID 찾기");
      lostpw = new JButton("pw 찾기");
      signup =  new JButton("회원가입");
     
      lostid.addActionListener(this);
      lostpw.addActionListener(this);
      signup.addActionListener(this);
      getRootPane().setDefaultButton(confirm);
      confirm.addActionListener(this);

      confirm.addKeyListener(new KeyAdapter() {

  		
  		@Override
  		public void keyPressed(KeyEvent e) {
  			if (e.getKeyCode()==KeyEvent.VK_ENTER) {
  				
  				confirm.requestFocus();
  				confirm.doClick();
  			}
  			
  		}
      	  
      	 
  	});
      
      lid.setBounds(15, 20, 30, 30);add(lid);
      lpw.setBounds(15, 50, 30, 30);add(lpw);
      insertid.setBounds(50, 20, 150, 25);add(insertid);
      insertpw.setBounds(50, 50, 150, 25);add(insertpw);
      confirm.setBounds(200, 20, 65, 50);add(confirm);
      lostid.setBounds(5, 80, 80, 30);add(lostid);
      lostpw.setBounds(90, 80, 80, 30);add(lostpw);
      signup.setBounds(175, 80, 90, 30);add(signup);
      
      setTitle("로그인");
      setLayout(null);
      setSize(280,150);
      setResizable(false);
      setLocation(800,450);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setVisible(true);
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      if (e.getSource()==lostid) {
         idlost = new LostId();//아이디 찾기 창 실행
      }
      else if(e.getSource()==lostpw) {
         pwlost = new LostPW();//비밀번호 찾기 창 실행
      }
      else if(e.getSource()==signup) {
         sign = new Signup();//회원가입 창 실행
      }
      else {
         if (e.getSource()==confirm) {
            if (cdo.login(insertid.getText(), insertpw.getText()) == true) {
               JOptionPane.showMessageDialog(null, "로그인 성공"," ",JOptionPane.INFORMATION_MESSAGE);
               dispose();
               cvo = new ClientVO();
               cvo.setId(insertid.getText());
               
               if(insertid.getText().equals("admin")) {
                  new AdminWindow();
               }else {
                  cdo.updateConn(cvo.getId());
                  new Degisil(cvo);
               }

            }
            else {
               JOptionPane.showMessageDialog(null, "로그인 실패","아이디중복",JOptionPane.ERROR_MESSAGE);   
               insertid.setText("");
               insertpw.setText("");
            }
      
   }
         }
   }
      
   public static void main(String[] args) {
      new Login();
   }
}