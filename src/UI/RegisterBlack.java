package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dbTest.BlacklistDAO;
import dbTest.BlacklistVO;

public class RegisterBlack extends JFrame implements ActionListener{
	
	private JLabel pnum,title;
	private JTextField pnumtxt;
	private JButton register,cancel;
	private BlacklistVO bvo;
	private BlacklistDAO bdo;
	
	public RegisterBlack() {
		
		title = new JLabel("블랙 리스트 등록");
		pnum = new JLabel("id 입력");

		pnumtxt = new JTextField();
		
		register = new JButton("등록"); 
		cancel = new JButton("취소");
		
		register.addActionListener(this);
		cancel.addActionListener(this);
		
		title.setBounds(150, 30, 100, 30);add(title);

		pnum.setBounds(40, 80, 60, 30);add(pnum);
		pnumtxt.setBounds(100, 80, 200, 30);add(pnumtxt);
		register.setBounds(120, 120, 60, 40);add(register);
		cancel.setBounds(210, 120, 60, 40);add(cancel);
		
		setLayout(null);
		setSize(400,200);
		setResizable(false);
		setLocation(700,150);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		new RegisterBlack();
	}
	
	private void blacklist() {
		bvo = new BlacklistVO();
		bdo = new BlacklistDAO();
		bvo.setReportId(pnumtxt.getText());
		
		boolean result = bdo.getOne(bvo.getReportId());
		
		if(result) {
			
			result = bdo.blacklist(bvo);
			if(result) {
				System.out.println("\t입력 성공");
				
			}
			else JOptionPane.showMessageDialog(null, "블랙리스트에 이미 있는 아이디 입니다.");
		}
		else JOptionPane.showMessageDialog(null, "등록되어있지 않는 아이디 ㅆ비ㅏㅓㄹ");
		
	}
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == register) {
			dispose();
			blacklist();
			new BlackList();
		}
		if(e.getSource() == cancel) {
			int result = JOptionPane.showConfirmDialog(null, "관리자 창으로 돌아가시겠습니까?", "제목",
	                JOptionPane.OK_CANCEL_OPTION);
	
	        System.out.printf("%d\n", result);
	        if (result == 0) { //OK=0 , Cancel=2 리턴
	        	dispose();
	        }
		}
		
	}
}

	
