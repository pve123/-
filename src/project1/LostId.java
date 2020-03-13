//package project1;
//
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import javax.swing.*;
//import java.awt.*;
////아이디 찾기
////입력값->이메일,핸드폰 번호
//public class LostId extends JFrame implements ActionListener{
//	private JLabel mail,pnum,title;
//	private JTextField mailtxt,pnumtxt;
//	private JButton find,cancel;
//	
//	public LostId() {
//		title = new JLabel("아이디 찾기");
//		mail = new JLabel("Mail :"); pnum = new JLabel("Phone :");
//		mailtxt =  new JTextField(); pnumtxt = new JTextField();
//		find = new JButton("찾기"); cancel = new JButton("취소");
//		
//		find.addActionListener(this);
//		cancel.addActionListener(this);
//		
//		title.setBounds(150, 30, 100, 30);add(title);
//		mail.setBounds(50, 80, 60, 30);add(mail);
//		mailtxt.setBounds(100, 80, 200, 30);add(mailtxt);
//		pnum.setBounds(40, 140, 60, 30);add(pnum);
//		pnumtxt.setBounds(100, 140, 200, 30);add(pnumtxt);
//		find.setBounds(120, 200, 60, 40);add(find);
//		cancel.setBounds(210, 200, 60, 40);add(cancel);
//		
//		setLayout(null);
//		setSize(400,300);
//		setResizable(false);
//		setLocation(700,150);
//		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//		setVisible(true);
//	}
//
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		if(e.getSource()==find) {
//			if(mailtxt.getText().equals("hi")) {
//				JOptionPane.showMessageDialog(null, "아이디:  "+mailtxt.getText(),"아이디 찾기",JOptionPane.INFORMATION_MESSAGE);
//			}
//		}
//		else {
//			dispose();
//		}
//		
//	}
//
//}
