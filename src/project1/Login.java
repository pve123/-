package project1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
public class Login extends JFrame implements ActionListener {
	private Signup sign;
	private LostId idlost;
	private LostPW pwlost;
	
	private JLabel lid, lpw;//id,pw
	private TextField insertid;//id�Է�â
	private JPasswordField insertpw;//pw�Է�â
	private JButton confirm,lostid,lostpw,signup;//Ȯ��,idã��,pw ã��,ȸ������ ��ư
	public Login() {
		lid = new JLabel("ID");
		lpw = new JLabel("PW");
		insertid = new TextField(20);
		insertpw = new JPasswordField(20);
		confirm = new JButton("Ȯ��");
		lostid = new JButton("ID ã��");
		lostpw = new JButton("pw ã��");
		signup =  new JButton("ȸ������");
		
		lostid.addActionListener(this);
		lostpw.addActionListener(this);
		signup.addActionListener(this);
		confirm.addActionListener(this);
		
		lid.setBounds(15, 20, 30, 30);add(lid);
		lpw.setBounds(15, 50, 30, 30);add(lpw);
		insertid.setBounds(50, 20, 150, 25);add(insertid);
		insertpw.setBounds(50, 50, 150, 25);add(insertpw);
		confirm.setBounds(200, 20, 65, 50);add(confirm);
		lostid.setBounds(5, 80, 80, 30);add(lostid);
		lostpw.setBounds(90, 80, 80, 30);add(lostpw);
		signup.setBounds(175, 80, 90, 30);add(signup);
		
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
			idlost = new LostId();//���̵� ã�� â ����
		}
		else if(e.getSource()==lostpw) {
			pwlost = new LostPW();//��й�ȣ ã�� â ����
		}
		else if(e.getSource()==signup) {
			sign = new Signup();//ȸ������ â ����
		}
		else {
			//�α��� ����--> ���� �̵�
			//		����--> �˸�â
			if(insertid.getText().equals("admin")) {
				JOptionPane.showMessageDialog(null, "�α��� �� �� �����ϴ�.", "�α��� ����", JOptionPane.ERROR_MESSAGE);
			}
			else if(insertid.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "���̵� �Է��ϼ���","�α��� ����",JOptionPane.ERROR_MESSAGE);
			}
			else System.out.println("�α���");
			}		
	}

}

class LostId extends JFrame implements ActionListener{
	private JLabel mail,pnum,title;
	private JTextField mailtxt,pnumtxt;
	private JButton find,cancel;
	
	public LostId() {
		title = new JLabel("���̵� ã��");
		mail = new JLabel("Mail :"); pnum = new JLabel("Phone :");
		mailtxt =  new JTextField(); pnumtxt = new JTextField();
		find = new JButton("ã��"); cancel = new JButton("���");
		
		find.addActionListener(this);
		cancel.addActionListener(this);
		
		title.setBounds(150, 30, 100, 30);add(title);
		mail.setBounds(50, 80, 60, 30);add(mail);
		mailtxt.setBounds(100, 80, 200, 30);add(mailtxt);
		pnum.setBounds(40, 140, 60, 30);add(pnum);
		pnumtxt.setBounds(100, 140, 200, 30);add(pnumtxt);
		find.setBounds(120, 200, 60, 40);add(find);
		cancel.setBounds(210, 200, 60, 40);add(cancel);
		
		setLayout(null);
		setSize(400,300);
		setResizable(false);
		setLocation(700,150);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==find) {
			if(mailtxt.getText().equals("hi")) {
				JOptionPane.showMessageDialog(null, "���̵�:  "+mailtxt.getText(),"���̵� ã��",JOptionPane.INFORMATION_MESSAGE);
			}
		}
		else {
			dispose();
		}
		
	}

}

//���̵�(�ߺ�Ȯ��),�г���(�ߺ�Ȯ��),��й�ȣ,�̸���,��ȭ��ȣ,����(����),��
class Signup extends JFrame implements ActionListener {
	private JLabel id,pw,nick,mail,pnum,question,answer,title;
	private JTextField idtxt,pwtxt,nicktxt,mailtxt,pnumtxt,questiontxt,answertxt;
	private JButton idcheck,nickcheck,confirm,cancel;
	
	public Signup() {
		//
		id = new JLabel("ID :");pw = new JLabel("PW :");nick = new JLabel("�г��� :");
		mail = new JLabel("Mail :");question = new JLabel("���� :"); answer = new JLabel("�� :");pnum = new JLabel("Phone :");
		idtxt = new JTextField(); pwtxt = new JTextField();nicktxt= new JTextField();mailtxt= new JTextField();
		pnumtxt= new JTextField();questiontxt= new JTextField();answertxt= new JTextField();
		idcheck = new JButton("�ߺ�Ȯ��");nickcheck=new JButton("�ߺ�Ȯ��");confirm=new JButton("����"); cancel = new JButton("���");
		title = new JLabel("ȸ������!");
		
		idcheck.addActionListener(this);
		nickcheck.addActionListener(this);
		confirm.addActionListener(this);
		cancel.addActionListener(this);
		//��ġ��� ����
		title.setBounds(160, 10, 60, 60);add(title);				
		id.setBounds(55, 70, 60, 60);add(id);
		idtxt.setBounds(120, 85, 150, 30);add(idtxt);
		idcheck.setBounds(280, 85, 100, 30);add(idcheck);
		nick.setBounds(55, 130, 60, 60);add(nick);
		nicktxt.setBounds(120, 145, 150, 30);add(nicktxt);
		nickcheck.setBounds(280, 145, 100, 30);add(nickcheck);
		pw.setBounds(55, 190, 60, 60);add(pw);
		pwtxt.setBounds(120, 205, 150, 30);add(pwtxt);
		mail.setBounds(55, 250, 60, 60);add(mail);
		mailtxt.setBounds(120, 265, 150, 30);add(mailtxt);
		pnum.setBounds(55, 310, 60, 60);add(pnum);
		pnumtxt.setBounds(120, 325, 150, 30);add(pnumtxt);
		question.setBounds(55, 370, 60, 60);add(question);
		questiontxt.setBounds(120, 385, 150, 30);add(questiontxt);
		answer.setBounds(55, 430, 60, 60);add(answer);
		answertxt.setBounds(120, 445, 150, 30);add(answertxt);
		confirm.setBounds(115, 500, 60, 30);add(confirm);
		cancel.setBounds(210, 500, 60, 30);add(cancel);

		setLayout(null);
		setSize(400,600);
		setResizable(false);
		setLocation(700,150);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==idcheck) {
			if(idtxt.getText().equals("admin")) {
				JOptionPane.showMessageDialog(null, "�̹� ���ԵǾ��ִ� ���̵� �Դϴ�.","���̵� �ߺ�",JOptionPane.ERROR_MESSAGE);
			}
			else if(idtxt.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "���̵� �Է����ּ���","���̵� ����",JOptionPane.ERROR_MESSAGE);
			}
			else JOptionPane.showMessageDialog(null, "��� ������ ���̵� �Դϴ�."," ",JOptionPane.INFORMATION_MESSAGE);
		}
		else if(e.getSource()==nickcheck) {
			if(nicktxt.getText().equals("admin")) {
				JOptionPane.showMessageDialog(null, "�̹� ���ԵǾ��ִ� �г��� �Դϴ�.","�г��� �ߺ�",JOptionPane.ERROR_MESSAGE);
			}
			else if(nicktxt.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "�г����� �Է����ּ���","�г��� ����",JOptionPane.ERROR_MESSAGE);
			}
			else JOptionPane.showMessageDialog(null, "��� ������ �г��� �Դϴ�."," ",JOptionPane.INFORMATION_MESSAGE);
		}
		else if(e.getSource()==confirm) {
			if(nicktxt.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "������ ����� ���ּ���","����",JOptionPane.ERROR_MESSAGE);
			}
		}
		else {
			dispose();
		}
		
	}

}


class LostPW extends JFrame implements ActionListener{
	private JLabel mail,pnum,title,id,question,answer;
	private JTextField mailtxt,pnumtxt,idtxt,questiontxt,answertxt;
	private JButton find,cancel;
	
	public LostPW() {
		title = new JLabel("��й�ȣ ã��");
		id = new JLabel("ID :");question = new JLabel("����:");answer = new JLabel("�� :");
		mail = new JLabel("Mail :"); pnum = new JLabel("Phone :");
		mailtxt =  new JTextField(); pnumtxt = new JTextField();
		idtxt = new JTextField();questiontxt = new JTextField();answertxt = new JTextField();
		find = new JButton("ã��"); cancel = new JButton("���");
		
		
		
		find.addActionListener(this);
		cancel.addActionListener(this);
		
		title.setBounds(150, 30, 100, 30);add(title);
		id.setBounds(60, 80, 60, 30);add(id);
		idtxt.setBounds(100, 80, 200, 30);add(idtxt);
		mail.setBounds(50, 140, 60, 30);add(mail);
		mailtxt.setBounds(100, 140, 200, 30);add(mailtxt);
		pnum.setBounds(35, 200, 60, 30);add(pnum);
		pnumtxt.setBounds(100, 200, 200, 30);add(pnumtxt);
		question.setBounds(45, 260, 60, 30);add(question);
		questiontxt.setBounds(100, 260, 200, 30);add(questiontxt);
		answer.setBounds(55, 320, 60, 30);add(answer);
		answertxt.setBounds(100, 320, 200, 30);add(answertxt);
		find.setBounds(120, 360, 60, 40);add(find);
		cancel.setBounds(210, 360, 60, 40);add(cancel);
		
		setLayout(null);
		setSize(400,450);
		setResizable(false);
		setLocation(700,150);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==find) {//ȸ�� ���o->���̵� �˸� , ��� x->�߸��Է� �˸�â
			if(idtxt.getText().equals("admin")) {
				JOptionPane.showInputDialog("���ο� ��й�ȣ �Է�");
			}
		}
		else {
			dispose();
		}
		
	}

}
