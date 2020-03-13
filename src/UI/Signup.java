package UI;

import javax.swing.*;

import dbTest.ClientDAO;
import dbTest.ClientVO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.regex.Pattern;

//아이디(중복확인),닉네임(중복확인),비밀번호,이메일,전화번호,문제(선택),답
public class Signup extends JFrame implements ActionListener {
   private JLabel id, pw, nick, mail, pnum, question, answer, title, sex, gol,ch_lbl;
   private JTextField idtxt, nicktxt, mailtxt, pnumtxt, questiontxt, answertxt;
   private JPasswordField pwtxt;
   private JButton idcheck, nickcheck, confirm, cancel;
   private JRadioButton sex_m, sex_f;
   private ClientVO cvo;
   private ClientDAO cdo;
   private JComboBox<String > cmbBox,cmbBox2 ;
   private ButtonGroup rdoGroup;
   
   public Signup() {
      //
      
      String [] question_a = {"당신의 사는 동네 이름은?", "초등학교의 이름은?", "가장 좋아하는 영화는?", "제일 존경하는 위인은?"};
      String [] email_kind = {"naver.com", "gmail.com", "daum.net", "nate.com"};
      cmbBox = new JComboBox<String>(question_a);
      cmbBox2 = new JComboBox<String>(email_kind);
      
      cvo = new ClientVO();
      cdo = new ClientDAO();
      id = new JLabel("ID :");
      pw = new JLabel("PW :");
      nick = new JLabel("닉네임 :");
      sex = new JLabel("성별 : ");
      mail = new JLabel("Mail :");
      question = new JLabel("질문 :");
      answer = new JLabel("답 :");
      pnum = new JLabel("Phone :");
      gol = new JLabel("@");
      idtxt = new JTextField();
      pwtxt = new JPasswordField();
      nicktxt = new JTextField();
      mailtxt = new JTextField();
      pnumtxt = new JTextField();
      questiontxt = new JTextField();
      answertxt = new JTextField();
      idcheck = new JButton("중복확인");
      nickcheck = new JButton("중복확인");
      confirm = new JButton("가입");
      cancel = new JButton("취소");
      title = new JLabel("회원가입!");
      sex_m = new JRadioButton("  남자",true);
      sex_f = new JRadioButton("  여자");
      rdoGroup = new ButtonGroup();
      rdoGroup.add(sex_m);
      rdoGroup.add(sex_f);
      
      idcheck.addActionListener(this);
      nickcheck.addActionListener(this);
      confirm.addActionListener(this);
      cancel.addActionListener(this);

      // 위치잡기 ㅅㅂ
      title.setBounds(160, 10, 60, 60);
      add(title);
      id.setBounds(55, 70, 60, 60);
      add(id);
      idtxt.setBounds(120, 85, 150, 30);
      add(idtxt);
      idcheck.setBounds(280, 85, 100, 30);
      add(idcheck);
      nick.setBounds(55, 130, 60, 60);
      add(nick);
      nicktxt.setBounds(120, 145, 150, 30);
      add(nicktxt);
      nickcheck.setBounds(280, 145, 100, 30);
      add(nickcheck);
      pw.setBounds(55, 190, 60, 60);
      add(pw);
      pwtxt.setBounds(120, 205, 150, 30);
      add(pwtxt);
      mail.setBounds(55, 250, 60, 60);
      add(mail);
      mailtxt.setBounds(120, 265, 100, 30);
      add(mailtxt); 
      gol.setBounds(230, 265, 30, 30);
      add(gol);
      cmbBox2.setBounds(260, 265, 120, 30);
      add(cmbBox2);
      pnum.setBounds(55, 310, 60, 60);
      add(pnum);
      pnumtxt.setBounds(120, 325, 150, 30);
      add(pnumtxt);
      sex.setBounds(55, 370, 60, 60);
      add(sex);
      sex_m.setBounds(120, 385, 60, 30);
      add(sex_m);
      sex_f.setBounds(220, 385, 60, 30);
      add(sex_f);
      question.setBounds(55, 430, 60, 60);
      add(question);
      cmbBox.setBounds(120, 445, 200, 30);
      add(cmbBox);
      answer.setBounds(55, 490, 60, 60);
      add(answer);
      answertxt.setBounds(120, 505, 150, 30);
      add(answertxt);

      confirm.setBounds(115, 600, 60, 30);
      add(confirm);
      cancel.setBounds(210, 600, 60, 30);
      add(cancel);
      
      
      setLayout(null);
      setSize(400, 700);
      setResizable(false);
      setLocation(700, 150);
      setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      setVisible(true);
      
    
      idtxt.addKeyListener(new KeyAdapter() {
		@Override
		public void keyReleased(KeyEvent e) {
		
			if (Pattern.matches(cvo.getId(), idtxt.getText())==false) {
				id.setForeground(Color.red);	
				confirm.setEnabled(false);
			}
			else {id.setForeground(Color.black);
			confirm.setEnabled(true);}
		}
    	  
    	  
	});
      
      nicktxt.addKeyListener(new KeyAdapter() {
  		@Override
  		public void keyReleased(KeyEvent e) {
  		
  			if (Pattern.matches(cvo.getNickname(), nicktxt.getText())==false) {
  				nick.setForeground(Color.red);
  				confirm.setEnabled(false);
  			}
  			else {nick.setForeground(Color.black);
  			confirm.setEnabled(true);}
  		}
      	  
      	  
  	});
  
      pwtxt.addKeyListener(new KeyAdapter() {
    		@Override
    		public void keyReleased(KeyEvent e) {
    		
    			if (Pattern.matches(cvo.getPw(), pwtxt.getText())==false) {
    				pw.setForeground(Color.red);
    				confirm.setEnabled(false);
    			}
    			else {pw.setForeground(Color.black);
    			 	confirm.setEnabled(true);}
    		}
        	  
        	  
    	});
  
      mailtxt.addKeyListener(new KeyAdapter() {
    		@Override
    		public void keyReleased(KeyEvent e) {
    		
    			if (Pattern.matches(cvo.getEmail(), mailtxt.getText())==false) {
    				mail.setForeground(Color.red);	
    				confirm.setEnabled(false);
    			}
    			else {mail.setForeground(Color.black);
    				confirm.setEnabled(true);}
    			
    		}
        	  
        	  
    	});

      pnumtxt.addKeyListener(new KeyAdapter() {
    		@Override
    		public void keyReleased(KeyEvent e) {
    		
    			if (Pattern.matches(cvo.getPhone(), pnumtxt.getText())==false) {
    				pnum.setForeground(Color.red);
    				confirm.setEnabled(false);
    			}
    			else    { 	
    				pnum.setForeground(Color.black);
    				confirm.setEnabled(true);
    			}	
    				
    			
    		}
        	  
        	  
    	});
   
      
   }//생성자
  
   @Override
   public void actionPerformed(ActionEvent e) {
	 
	   if (e.getSource() == idcheck) {		   	   
		  if (cdo.confirmId(idtxt.getText())==true) {
			JOptionPane.showMessageDialog(null, "이미 존재하는 아이디입니다.","아이디중복",JOptionPane.ERROR_MESSAGE);
		  }
		  else if(idtxt.getText().length()<1) {
			  JOptionPane.showMessageDialog(null, "공백 가입x"," ",JOptionPane.INFORMATION_MESSAGE);
		  }
		  else JOptionPane.showMessageDialog(null, "사용 가능한 아이디 입니다."," ",JOptionPane.INFORMATION_MESSAGE);		  
	   } //아이디중복확인
	   
	      
	   if (e.getSource() == nickcheck) {
		   if (cdo.confirmNickname(nicktxt.getText())==true) {
				JOptionPane.showMessageDialog(null, "이미 존재하는 닉네임입니다.","닉네임중복",JOptionPane.ERROR_MESSAGE);
			  }
		   else if(nicktxt.getText().length()<1) {
				  JOptionPane.showMessageDialog(null, "공백 가입x"," ",JOptionPane.INFORMATION_MESSAGE);
			  }
			  else JOptionPane.showMessageDialog(null, "사용 가능한 닉네임 입니다."," ",JOptionPane.INFORMATION_MESSAGE);
		
	} //닉네임중복확인
	   
	 if (e.getSource()==confirm) {
	
			cvo.setId(idtxt.getText());
			cvo.setNickname(nicktxt.getText());
			cvo.setPw(pwtxt.getText());
			cvo.setEmail(mailtxt.getText()+gol.getText()+cmbBox2.getSelectedItem().toString());
			cvo.setPhone(pnumtxt.getText());
			if (sex_m.isSelected()==true) 	cvo.setSex("M");			
			else							cvo.setSex("F");
			cvo.setQuestion(cmbBox.getSelectedItem().toString());;
			cvo.setQuestion_answer(answertxt.getText());			
			
			if (idtxt.getText().length()<1 ||  nicktxt.getText().length()<1 || pwtxt.getText().length() <1 || 
		 			mailtxt.getText().length()<1 || pnumtxt.getText().length()<1 || answertxt.getText().length()<1) {	 			
		 			JOptionPane.showMessageDialog(null, "빈칸을 채워주세요"," ",JOptionPane.ERROR_MESSAGE);
		 	}	  
			else if (cdo.join(cvo)==true) { 	
				JOptionPane.showMessageDialog(null, "회원가입 완료"," ",JOptionPane.INFORMATION_MESSAGE);
				idtxt.setText("");
				nicktxt.setText("");
				pwtxt.setText("");
				mailtxt.setText("");
				pnumtxt.setText("");
				answertxt.setText("");				
			}
			else			   
				 JOptionPane.showMessageDialog(null, "회원가입 실패"," ",JOptionPane.ERROR_MESSAGE);
				 
			} //회원가입
	 	  
	  if (e.getSource()==cancel) {
		dispose();
	  } //취소
	    
	} 

}




