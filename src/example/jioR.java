package example;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class jioR extends JFrame implements Runnable,ActionListener{
	   private JButton oxBtn, cancel;
	   private String port;
	   private Socket socket;
	   private Map<String, PrintWriter> clientMap;
	   boolean bc ;
	public jioR() {
		setResizable(false);
	      
	      oxBtn = new JButton("실행");
	      cancel = new JButton("취소");
	      cancel.setEnabled(false);
//	      initBtn.setBounds(249, 40, 148, 117);
	      	add(oxBtn);
	      	add(cancel);

	        oxBtn.addActionListener(this);
	        cancel.addActionListener(this);
	        
	      setTitle("서버");
	      setLayout(new FlowLayout());
	      setSize(300,150);
	      setLocation(700,150);
	      setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	      setVisible(true);
	}

	public static void main(String[] args) {
		new Thread(new jioR()).start();
	}

	@Override
	public void run() {
		while(true) {
			pp();
		}
	}
	private void pp() {
		if(bc==true) System.out.println("hi");
		System.out.println("no");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==oxBtn) {
			cancel.setEnabled(true);
			port = JOptionPane.showInputDialog(port);
	        if(port==null) {
	     	   JOptionPane.showMessageDialog(null, "취소","취소 ",JOptionPane.ERROR_MESSAGE);
	     	   }
	        else if(port.isEmpty()) {
	     	   JOptionPane.showMessageDialog(null, "빈칸입니다","방 생성 실패",JOptionPane.ERROR_MESSAGE);
	     	   }
	        else {
	        	bc=true;
	        }
		}
		if(e.getSource()==cancel) {
			bc=false;
			
		}
		
	}

}
