//package project1;

//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
////���̵�(�ߺ�Ȯ��),�г���(�ߺ�Ȯ��),��й�ȣ,�̸���,��ȭ��ȣ,����(����),��
//public class Signup extends JFrame implements ActionListener {
//	private JLabel id,pw,nick,mail,pnum,question,answer,title;
//	private JTextField idtxt,pwtxt,nicktxt,mailtxt,pnumtxt,questiontxt,answertxt;
//	private JButton idcheck,nickcheck,confirm,cancel;
//	
//	public Signup() {
//		//
//		id = new JLabel("ID :");pw = new JLabel("PW :");nick = new JLabel("�г��� :");
//		mail = new JLabel("Mail :");question = new JLabel("���� :"); answer = new JLabel("�� :");pnum = new JLabel("Phone :");
//		idtxt = new JTextField(); pwtxt = new JTextField();nicktxt= new JTextField();mailtxt= new JTextField();
//		pnumtxt= new JTextField();questiontxt= new JTextField();answertxt= new JTextField();
//		idcheck = new JButton("�ߺ�Ȯ��");nickcheck=new JButton("�ߺ�Ȯ��");confirm=new JButton("����"); cancel = new JButton("���");
//		title = new JLabel("ȸ������!");
//		
//		idcheck.addActionListener(this);
//		nickcheck.addActionListener(this);
//		confirm.addActionListener(this);
//		cancel.addActionListener(this);
//		//��ġ��� ����
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
//				JOptionPane.showMessageDialog(null, "�̹� ���ԵǾ��ִ� ���̵� �Դϴ�.","���̵� �ߺ�",JOptionPane.ERROR_MESSAGE);
//			}
//			else if(idtxt.getText().isEmpty()) {
//				JOptionPane.showMessageDialog(null, "���̵� �Է����ּ���","���̵� ����",JOptionPane.ERROR_MESSAGE);
//			}
//			else JOptionPane.showMessageDialog(null, "��� ������ ���̵� �Դϴ�."," ",JOptionPane.INFORMATION_MESSAGE);
//		}
//		else if(e.getSource()==nickcheck) {
//			if(nicktxt.getText().equals("admin")) {
//				JOptionPane.showMessageDialog(null, "�̹� ���ԵǾ��ִ� �г��� �Դϴ�.","�г��� �ߺ�",JOptionPane.ERROR_MESSAGE);
//			}
//			else if(nicktxt.getText().isEmpty()) {
//				JOptionPane.showMessageDialog(null, "�г����� �Է����ּ���","�г��� ����",JOptionPane.ERROR_MESSAGE);
//			}
//			else JOptionPane.showMessageDialog(null, "��� ������ �г��� �Դϴ�."," ",JOptionPane.INFORMATION_MESSAGE);
//		}
//		else if(e.getSource()==confirm) {
//			if(nicktxt.getText().isEmpty()) {
//				JOptionPane.showMessageDialog(null, "������ ����� ���ּ���","����",JOptionPane.ERROR_MESSAGE);
//			}
//		}
//		else {
//			dispose();
//		}
//		
//	}
//
//}
