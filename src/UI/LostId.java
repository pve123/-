package UI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;

import dbTest.ClientDAO;
import dbTest.ClientVO;

import java.awt.*;
//아이디 찾기
//입력값->이메일,핸드폰 번호
public class LostId extends JFrame implements ActionListener{
	private JLabel mail,pnum,title,gol;
	private JTextField mailtxt,pnumtxt;
	private JButton find,cancel;
	private JComboBox<String> cmbBox2;
	private ClientDAO cdo;
	private ClientVO cvo;
	
	public LostId() {
		
		cdo = new ClientDAO();
		cvo = new ClientVO();
		title = new JLabel("아이디 찾기");
		mail = new JLabel("Mail :"); 
		pnum = new JLabel("Phone :");
		mailtxt =  new JTextField();
		pnumtxt = new JTextField();
		find = new JButton("찾기"); 
		cancel = new JButton("취소");
		gol = new JLabel("@");
		String[] email_kind = { "naver.com", "gmail.com", "daum.net", "nate.com"};
		cmbBox2 = new JComboBox<String>(email_kind); //이메일 콤보박스

		find.addActionListener(this);
		cancel.addActionListener(this);
		title.setBounds(150, 30, 100, 30);add(title);
		mail.setBounds(50, 80, 60, 30);add(mail);
		mailtxt.setBounds(100, 80, 100, 30);add(mailtxt);
		gol.setBounds(210, 80, 30, 30);add(gol);
		cmbBox2.setBounds(240, 80, 100, 30);add(cmbBox2);
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

	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == find) {
			String str;
			str =cdo.lostid(mailtxt.getText()+gol.getText()+cmbBox2.getSelectedItem().toString(),pnumtxt.getText());
			
	
			if (str!=null)				
				JOptionPane.showMessageDialog(null,"아이디 : "+ str," ",JOptionPane.INFORMATION_MESSAGE);				
			
			else 
				JOptionPane.showMessageDialog(null, "아이디 존재하지 않습니다.","실패",JOptionPane.ERROR_MESSAGE);
		}
		
		
		if (e.getSource()==cancel) {
			dispose();
		}	
			
		
	}
}

 