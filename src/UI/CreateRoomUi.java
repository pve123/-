package UI;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreateRoomUi extends JFrame{
   
   private JButton oxBtn, inipBtn, initBtn;
   
   public CreateRoomUi() {
      setResizable(false);
      
      oxBtn = new JButton();
      oxBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent arg0) {
            
         }
      });
      oxBtn.setText("ox\uAC8C\uC784");
      oxBtn.setBounds(59, 37, 142, 123);
      getContentPane().add(oxBtn);
      
      inipBtn = new JButton();
      inipBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent arg0) {
            
         }
      });
      inipBtn.setText("\uCD08\uC131 \uAC8C\uC784 \uD300\uC804");
      inipBtn.setBounds(442, 37, 148, 123);
      
      getContentPane().add(inipBtn);
      initBtn = new JButton();
      initBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent arg0) {
         
         }
      });
      initBtn.setText("\uCD08\uC131 \uAC8C\uC784 \uAC1C\uC778\uC804");
      initBtn.setBounds(249, 40, 148, 117);
      getContentPane().add(initBtn);
      
      
      
      getContentPane().setLayout(null);
      setSize(650,237);
      setLocation(700,150);
      setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      setVisible(true);
   }
   
   public static void main(String[] args) {
      new CreateRoomUi();
   }
}