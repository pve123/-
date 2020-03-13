//package project1;

//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
////아이디(중복확인),닉네임(중복확인),비밀번호,이메일,전화번호,문제(선택),답
//public class Signup extends JFrame implements ActionListener {
//	private JLabel id,pw,nick,mail,pnum,question,answer,title;
//	private JTextField idtxt,pwtxt,nicktxt,mailtxt,pnumtxt,questiontxt,answertxt;
//	private JButton idcheck,nickcheck,confirm,cancel;
//	
//	public Signup() {
//		//
//		id = new JLabel("ID :");pw = new JLabel("PW :");nick = new JLabel("닉네임 :");
//		mail = new JLabel("Mail :");question = new JLabel("질문 :"); answer = new JLabel("답 :");pnum = new JLabel("Phone :");
//		idtxt = new JTextField(); pwtxt = new JTextField();nicktxt= new JTextField();mailtxt= new JTextField();
//		pnumtxt= new JTextField();questiontxt= new JTextField();answertxt= new JTextField();
//		idcheck = new JButton("중복확인");nickcheck=new JButton("중복확인");confirm=new JButton("가입"); cancel = new JButton("취소");
//		title = new JLabel("회원가입!");
//		
//		idcheck.addActionListener(this);
//		nickcheck.addActionListener(this);
//		confirm.addActionListener(this);
//		cancel.addActionListener(this);
//		//위치잡기 ㅅㅂ
//		title.setBounds(160, 10, 60, 60);add(title);				
//		id.setBounds(55, 70, 60, 60);add(id);
//		idtxt.setBounds(120, 85, 150, 30);add(idtxt);
//		idcheck.setBounds(280, 85, 100, 30);add(idcheck);
//		nick.setBounds(55, 130, 60, 60);add(nick);
//		nicktxt.setBounds(120, 145, 150, 30);add(nicktxt);
//		nickcheck.setBounds(280, 145, 100, 30);add(nickcheck);
//		pw.setBounds(55, 190, 60, 60);add(pw);
//		pwtxt.setBounds(120, 205, 150, 30);add(pwtxt);
//		mail.setBounds(55, 250, 60, 60);add(mail);
//		mailtxt.setBounds(120, 265, 150, 30);add(mailtxt);
//		pnum.setBounds(55, 310, 60, 60);add(pnum);
//		pnumtxt.setBounds(120, 325, 150, 30);add(pnumtxt);
//		question.setBounds(55, 370, 60, 60);add(question);
//		questiontxt.setBounds(120, 385, 150, 30);add(questiontxt);
//		answer.setBounds(55, 430, 60, 60);add(answer);
//		answertxt.setBounds(120, 445, 150, 30);add(answertxt);
//		confirm.setBounds(115, 500, 60, 30);add(confirm);
//		cancel.setBounds(210, 500, 60, 30);add(cancel);
//
//		setLayout(null);
//		setSize(400,600);
//		setResizable(false);
//		setLocation(700,150);
//		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//		setVisible(true);
//	}
//	
//
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		if(e.getSource()==idcheck) {
//			if(idtxt.getText().equals("admin")) {
//				JOptionPane.showMessageDialog(null, "이미 가입되어있는 아이디 입니다.","아이디 중복",JOptionPane.ERROR_MESSAGE);
//			}
//			else if(idtxt.getText().isEmpty()) {
//				JOptionPane.showMessageDialog(null, "아이디를 입력해주세요","아이디 공백",JOptionPane.ERROR_MESSAGE);
//			}
//			else JOptionPane.showMessageDialog(null, "사용 가능한 아이디 입니다."," ",JOptionPane.INFORMATION_MESSAGE);
//		}
//		else if(e.getSource()==nickcheck) {
//			if(nicktxt.getText().equals("admin")) {
//				JOptionPane.showMessageDialog(null, "이미 가입되어있는 닉네임 입니다.","닉네임 중복",JOptionPane.ERROR_MESSAGE);
//			}
//			else if(nicktxt.getText().isEmpty()) {
//				JOptionPane.showMessageDialog(null, "닉네임을 입력해주세요","닉네임 공백",JOptionPane.ERROR_MESSAGE);
//			}
//			else JOptionPane.showMessageDialog(null, "사용 가능한 닉네임 입니다."," ",JOptionPane.INFORMATION_MESSAGE);
//		}
//		else if(e.getSource()==confirm) {
//			if(nicktxt.getText().isEmpty()) {
//				JOptionPane.showMessageDialog(null, "기입을 제대로 해주세요","공백",JOptionPane.ERROR_MESSAGE);
//			}
//		}
//		else {
//			dispose();
//		}
//		
//	}
//
//}
