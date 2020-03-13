package example;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;

public class pratice extends JFrame implements ActionListener{
	int index=60;
	ArrayList<JButton> cr;
	JButton btn1;
	
	public pratice() {
		btn1 = new JButton("adsad");
		btn1.setBounds(100, 100, 200, 20);
		add(btn1);
		btn1.addActionListener(this);
        setLayout(null);
        setSize(1300,750);
        setVisible(true);
//        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void createRoom() {
		JButton btn = new JButton("op");
		cr = new ArrayList<JButton>();
		cr.add(btn);
		for (JButton jButton : cr) {
			add(jButton);
			jButton.setBounds(10+index, 50+index, 60, 60);
			index+=10;

		}
	}

	public static void main(String[] args) {
		new pratice();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btn1) {
			createRoom();
		}
	}

}
