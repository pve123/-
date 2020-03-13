//package project1;
//
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import javax.swing.*;
//import java.awt.*;
////비밀번호 찾기
////입력값-> 아이디,질문(선택),답,이메일,전화번호
//public class LostPW extends JFrame implements ActionListener{
//	private JLabel mail,pnum,title,id,question,answer;
//	private JTextField mailtxt,pnumtxt,idtxt,questiontxt,answertxt;
//	private JButton find,cancel;
//	
//	public LostPW() {
//		title = new JLabel("비밀번호 찾기");
//		id = new JLabel("ID :");question = new JLabel("질문:");answer = new JLabel("답 :");
//		mail = new JLabel("Mail :"); pnum = new JLabel("Phone :");
//		mailtxt =  new JTextField(); pnumtxt = new JTextField();
//		idtxt = new JTextField();questiontxt = new JTextField();answertxt = new JTextField();
//		find = new JButton("찾기"); cancel = new JButton("취소");
//		
//		
//		
//		find.addActionListener(this);
//		cancel.addActionListener(this);
//		
//		title.setBounds(150, 30, 100, 30);add(title);
//		id.setBounds(60, 80, 60, 30);add(id);
//		idtxt.setBounds(100, 80, 200, 30);add(idtxt);
//		mail.setBounds(50, 140, 60, 30);add(mail);
//		mailtxt.setBounds(100, 140, 200, 30);add(mailtxt);
//		pnum.setBounds(35, 200, 60, 30);add(pnum);
//		pnumtxt.setBounds(100, 200, 200, 30);add(pnumtxt);
//		question.setBounds(45, 260, 60, 30);add(question);
//		questiontxt.setBounds(100, 260, 200, 30);add(questiontxt);
//		answer.setBounds(55, 320, 60, 30);add(answer);
//		answertxt.setBounds(100, 320, 200, 30);add(answertxt);
//		find.setBounds(120, 360, 60, 40);add(find);
//		cancel.setBounds(210, 360, 60, 40);add(cancel);
//		
//		setLayout(null);
//		setSize(400,450);
//		setResizable(false);
//		setLocation(700,150);
//		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//		setVisible(true);
//	}
//
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		if(e.getSource()==find) {//회원 등록o->아이디 알림 , 등록 x->잘못입력 알림창
//			if(idtxt.getText().equals("admin")) {
//				JOptionPane.showInputDialog("새로운 비밀번호 입력");
//			}
//		}
//		else {
//			dispose();
//		}
//		
//	}
//
//}
