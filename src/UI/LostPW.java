package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import dbTest.ClientDAO;
import dbTest.ClientVO;

import java.awt.*;
//비밀번호 찾기
//입력값-> 아이디,질문(선택),답,이메일,전화번호
public class LostPW extends JFrame implements ActionListener{
	private JLabel mail,pnum,title,id,question,answer,gol;
	private JTextField mailtxt,pnumtxt,idtxt,questiontxt,answertxt;
	private JButton find,cancel;
	private JComboBox<String> cmbBox,cmbBox2;
	private ClientDAO cdo;
	private ClientVO cvo;

	public LostPW() {
		
		cvo = new ClientVO();
		cdo = new ClientDAO();
		
		title = new JLabel("비밀번호 찾기");
		id = new JLabel("ID :");
		question = new JLabel("질문:");
		answer = new JLabel("답 :");
		mail = new JLabel("Mail :"); 
		pnum = new JLabel("Phone :");
		gol = new JLabel("@");
		mailtxt =  new JTextField();
		pnumtxt = new JTextField();
		idtxt = new JTextField() ;
		answertxt = new JTextField();
		find = new JButton("찾기"); 
		cancel = new JButton("취소");
		String[] email_kind = { "naver.com", "gmail.com", "daum.net", "nate.com" };
		String[] question_a = { "당신의 사는 동네 이름은?", "초등학교의 이름은?", "가장 좋아하는 영화는?", "제일 존경하는 위인은?" };
		cmbBox2 = new JComboBox<String>(email_kind); //이메일 콤보박스
		cmbBox = new JComboBox<String>(question_a);//q&a 콤보박스

		find.addActionListener(this);
		cancel.addActionListener(this);
		title.setBounds(150, 30, 100, 30);add(title);
		id.setBounds(60, 80, 60, 30);add(id);
		idtxt.setBounds(100, 80, 200, 30);add(idtxt);
		mail.setBounds(50, 140, 60, 30);add(mail);
		mailtxt.setBounds(100, 140, 100, 30);add(mailtxt);
		gol.setBounds(210, 140, 30, 30);add(gol);
		cmbBox2.setBounds(240, 140, 100, 30);add(cmbBox2);
		pnum.setBounds(35, 200, 60, 30);add(pnum);
		pnumtxt.setBounds(100, 200, 200, 30);add(pnumtxt);
		question.setBounds(45, 260, 60, 30);add(question);
		cmbBox.setBounds(100, 260, 200, 30);add(cmbBox);
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
		if(e.getSource()==find) {//회원 등록o->아이디 알림 , 등록 x->잘못입력 알림창			
			String str=cdo.lostpw(idtxt.getText(), mailtxt.getText()+gol.getText()+cmbBox2.getSelectedItem().toString(), 
					pnumtxt.getText(), cmbBox.getSelectedItem().toString(), answertxt.getText());;
					String str1="";
			if (str!=null) {
				for (int i = 0; i < str.length()+1; i++) {
					if (i>3) 	str1+="*";				
				}
				JOptionPane.showMessageDialog(null,"비밀번호 : "+str.replace(str.substring(3), str1),"비밀번호",JOptionPane.INFORMATION_MESSAGE);
			}		
					
			else  
				JOptionPane.showMessageDialog(null, "해당 정보가 존재하지않습니다.","실패",JOptionPane.ERROR_MESSAGE);
		}
	
		if (e.getSource()==cancel) {
			dispose();
		} 	
}
}