package UI;

import java.awt.*;
import javax.swing.*;

import dbTest.ClientDAO;
import dbTest.ClientVO;

import java.awt.event.*;
import java.util.List;
import java.util.Timer.*;

public class NowConnectUser extends JFrame implements ActionListener, Runnable{   // JFrame은 상속받고 ActionListner는  implements
   
   private JLabel titleLabel;      
   private JButton btnX;   
   private JTextArea  conUserList; 
   private ClientDAO cdao1;
   private ClientVO cvo1;
   private String nickname, id;
   
   
   public NowConnectUser(){
      
      titleLabel = new JLabel("현재 접속자 조회");
      add(titleLabel);
      titleLabel.setBounds(70, 20, 350, 30);
      
      conUserList = new JTextArea();
      conUserList.setBounds(70, 90, 590, 390);
      add(conUserList);
      
      showConnClientAdmin(nickname);
      
      btnX = new JButton("뒤로가기");
      btnX.setBounds(370, 490, 290, 80);
      add(btnX);
      
      btnX.addActionListener(this);
      
      //폰트 크기지정
      titleLabel.setFont(titleLabel.getFont().deriveFont(30.0f));
      conUserList.setFont(conUserList.getFont().deriveFont(20.0f));
      btnX.setFont(btnX.getFont().deriveFont(30.0f));
      
      
      int x = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/4 - this.getWidth()/2;   // x좌표 가운데 설정
        int y = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/8 - this.getHeight()/2; //y좌표 가운데 설정
        Toolkit toolkit = getToolkit();   // Q 플레이 아이콘 설정하기 위한 객체 생성
        Image image = toolkit.createImage("C:\\javawork\\Q_icon.png"); // Q 플레이 아이콘      // 경로에 파일 이미지 없으면 자바컵으로 나옴
        setLayout(null);
        setIconImage(image);   // QPLAY 아이콘 설정
        setTitle(" Q_PLAY ");   // UI 제목 Q_PLAY 설정
        setLocation(x,y); // 실행시 위치 설정
      setSize(700,650);   // SIZE 1500,850
      setVisible(true);   // setVisible true로 지정하여 창 활성화
      setResizable(false);   // 창 크기 조절 불가능
      setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      
      
      
      
      
   }
   
   public List<ClientVO> showConnClientAdmin(String nickname) {
      cdao1 = new ClientDAO();
      cvo1 = new ClientVO();
      
      List<ClientVO> list = cdao1.showMyClient();
      
      for (ClientVO cvo1 : list) {
         conUserList.append("닉네임 : " + cvo1.getNickname() + "\t" + 
               "id :  " + cvo1.getId() + "\n"
               );
      }
      return list;
   }
   
   
   public static void main(String[] args) {
      new NowConnectUser();      // 현재접속한 회원목록
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      if(e.getSource() == btnX) {
         int result = JOptionPane.showConfirmDialog(null, "관리자 창으로 돌아가시겠습니까?", "제목",
                   JOptionPane.OK_CANCEL_OPTION);
   
           System.out.printf("%d\n", result);
           if (result == 0) { //OK=0 , Cancel=2 리턴
              dispose();
           }
      }
   }


   @Override
   public void run() {
      
   }
}

