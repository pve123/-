//package project1;
//
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import javax.swing.*;
//import java.awt.*;
////���̵� ã��
////�Է°�->�̸���,�ڵ��� ��ȣ
//public class LostId extends JFrame implements ActionListener{
//	private JLabel mail,pnum,title;
//	private JTextField mailtxt,pnumtxt;
//	private JButton find,cancel;
//	
//	public LostId() {
//		title = new JLabel("���̵� ã��");
//		mail = new JLabel("Mail :"); pnum = new JLabel("Phone :");
//		mailtxt =  new JTextField(); pnumtxt = new JTextField();
//		find = new JButton("ã��"); cancel = new JButton("���");
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
//				JOptionPane.showMessageDialog(null, "���̵�:  "+mailtxt.getText(),"���̵� ã��",JOptionPane.INFORMATION_MESSAGE);
//			}
//		}
//		else {
//			dispose();
//		}
//		
//	}
//
//}
