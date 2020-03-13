package project1;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import java.awt.event.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer.*;


public class Degisil extends JFrame implements Runnable,ActionListener{
 
   private JButton wd_btn,logout,room_btn;
   private JTextArea chat_txtarea,rank_txtarea,my_info,con_people,realtime;
   private JTextField chat_txtfield;
   private JScrollPane scroll_chat,scroll_con,scroll_game;
   private JLabel chat_label,con_num,gtype_lbl;
   private JComboBox<String> gtype_cb;
   private JList<Object> g_list;
   private List list;
   private Calendar cal;
   private JPanel panel;
   private CreateRoom cr;
   
   public Degisil(){        

      ArrayList<String> list = new ArrayList<>();      
       wd_btn= new JButton("탈퇴");        
         wd_btn.setBounds(1050, 570, 220, 100);
         add(wd_btn);  
         wd_btn.addActionListener(this);
   /////////////////////////////////////////////////////////////////////////////   탈퇴버ㅏ튼   
         chat_txtarea = new JTextArea();
         chat_txtarea.setBounds(60, 370, 750, 250);
        chat_txtarea.setEditable(false);      //글씨못치게함
       scroll_chat = new JScrollPane(chat_txtarea, 
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); //스크롤
       scroll_chat.setBounds(60,370,750,250);        
        add(scroll_chat);
            /////////////////////////////////////////////////////////////////////////////      채팅보여주는곳
        chat_txtfield = new JTextField();
        chat_txtfield.setBounds(60, 640, 750,30);
        add(chat_txtfield);
        chat_txtfield.addKeyListener(new KeyAdapter() {

         @Override
         public void keyReleased(KeyEvent e) {            
            if(e.getKeyCode() == 10) {   //엔터키가 눌린 경우            
                  chat_txtarea.append(chat_txtfield.getText() + "\n");               
               chat_txtfield.setText("");
               chat_txtfield.requestFocus();
            }
         }
      });
           /////////////////////////////////////////////////////////////////////////////      채팅치는곳
        chat_label = new JLabel("채   팅   창");
        chat_label.setFont(new Font("궁서", Font.BOLD, 30));      
        chat_label.setBounds(320,190,300,300);
        add(chat_label);
   /////////////////////////////////////////////////////////////////////////////  채팅창이름 라벨
        rank_txtarea = new JTextArea();
       rank_txtarea.setBounds(840, 270, 180, 400);
        rank_txtarea.setEditable(false);
        rank_txtarea.setText("전체랭킹중 상위 랭킹보여주는곳");
        rank_txtarea.setBorder(new MatteBorder(5,5,5,5,Color.black)); //테두리 두깨,색
        rank_txtarea.setBackground(new Color(234,234,234));//배경색
        add(rank_txtarea);
   /////////////////////////////////////////////////////////////////////////////    전체랭킹보여주는거
        my_info = new JTextArea();
        my_info.setBounds(1050, 160, 220, 400);
        my_info.setEditable(false);
        my_info.setText("내정보");
        my_info.setBorder(new MatteBorder(5,5,5,5,Color.orange)); //테두리 두깨,색
        my_info.setBackground(Color.pink);   //배경색
        add(my_info);
   ////////////////////////////////////////////////////////////////////////////     내정보확인
        logout = new JButton("로그아웃");
        logout.setBounds(1050, 50, 220, 100);
        add(logout);
        logout.addActionListener(this);
   ///////////////////////////////////////////////////////////////////////////     로그아웃버튼
        con_people = new JTextArea();
        con_people.setBounds(840,50,180,200);
        con_people.setEditable(false);
        scroll_con = new JScrollPane(con_people, 
            ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll_con.setBounds(840, 50, 180, 200);
        con_people.setBorder(new MatteBorder(5,5,5,5,Color.LIGHT_GRAY));
        add(scroll_con);
    ///////////////////////////////////////////////////////////////////////// 접속자 명단 
        realtime=new JTextArea();
      realtime.setEditable(false);
      realtime.setBounds(1090,15,220,30);
      add(realtime);
      realtime.setBackground(new Color(212,244,250));            
      realtime.setFont(new Font("궁서", Font.BOLD, 20));
    /////////////////////////////////////////////////////////////////////////   실시간 시간 area
        con_num = new JLabel("접속자");
        con_num.setBounds(900, 15, 220, 30);                 
        con_num.setFont(new Font("궁서", Font.BOLD, 20));
      add(con_num);
    /////////////////////////////////////////////////////////////////////////    접속자라벨   
      room_btn = new JButton("방 생성");
      room_btn.setBounds(600,20,220,80);
      add(room_btn);
      room_btn.addActionListener(this);
    /////////////////////////////////////////////////////////////////////////    방생성 버튼 
      gtype_lbl = new JLabel("게임 종류");
      gtype_lbl.setBounds(30,10,120,80);
      gtype_lbl.setFont(new Font("궁서", Font.BOLD, 20));
      add(gtype_lbl);
    /////////////////////////////////////////////////////////////////////////    게임종류 라벨 
      String[] g_type = {"모든게임","초성퀴즈게임","OX퀴즈게임"};
      gtype_cb = new JComboBox<String>(g_type);
      gtype_cb.setBounds(150,30,430,40);
      add(gtype_cb);
    /////////////////////////////////////////////////////////////////////////    게임종류 라벨 
      String[] room = {"1번방","2번방","3번방","1번방","2번방","3번방","2번방","3번방"};      
      g_list = new JList<Object>(room);
      g_list.setBounds(60,130,745,170);   
      scroll_game = new JScrollPane(g_list, 
            ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
      scroll_game.setBounds(60, 130, 745, 170);
      add(scroll_game);

        int x = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/8 - this.getWidth()/2;   // x좌표 가운데 설정
        int y = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/8 - this.getHeight()/2; //y좌표 가운데 설정
        setLocation(x,y); // 프레임창 위치
        Toolkit toolkit = getToolkit();
        Image image = toolkit.createImage("C:\\javawork\\Q_icon.png"); 
        setIconImage(image);
        setTitle(" Q_PLAY "); 
        getContentPane().setBackground(new Color(212,244,250));   //배경색    
        setLayout(null);
        setSize(1300,750);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
   }

   public static void main(String[] args) {     
      Thread t =new Thread(new Degisil());
      t.start();
      
   }

@Override
public void run() {

   while(true){

   SimpleDateFormat simple = new SimpleDateFormat("a hh:mm:ss");
   cal=Calendar.getInstance();      
   realtime.setText(simple.format(new Date()));
   try{
      Thread.sleep(10);
   }catch(Exception e){
   }
   }
} //현재 시간이랑 날짜를 출력해주는 쓰레드 

@Override
public void actionPerformed(ActionEvent e) {

   if (e.getSource() == wd_btn) {
      JOptionPane.showConfirmDialog(null, "정말 탈퇴하시겠습니까 ?", "탈퇴", 
      JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE);
   }
   if (e.getSource() == logout) {
      JOptionPane.showConfirmDialog(null, "정말 로그아웃 하시겠습니까 ?", "로그아웃", 
      JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE);
   }
   if (e.getSource() ==room_btn) {
      cr = new CreateRoom();
   }
}
}

class CreateRoom extends JFrame implements ActionListener{
   
   private JTextField roomNameSet;      // 방 제목을 입력하는 roomNameSet
   private JButton cancel, create;      // 방 생성 취소 cancel, 방 생성 버튼 create
   private JCheckBox choGame, oxGame, pGame, tGame;   // 초성게임 : choGame  ox게임: oxGame  개인전: pGame  팀전: tGame
   
   public CreateRoom(){
      
      // setBounds(x,y,width,height)
      
      roomNameSet = new JTextField("방 제목 입력");      
      roomNameSet.setBounds(30, 20, 420, 40);
      add(roomNameSet);
      
      create = new JButton("생성");
      create.setBounds(370, 80, 80, 40);
      add(create);
      
      cancel = new JButton("취소");
      cancel.setBounds(370, 130, 80, 40);
      add(cancel);
      
      choGame = new JCheckBox("  초성 게임");
      choGame.setBounds(40, 80, 100, 50);
      add(choGame);
      
      oxGame = new JCheckBox("  OX 게임");
      oxGame.setBounds(200, 80, 100, 50);
      add(oxGame);
      
      pGame = new JCheckBox("  개인전");
      pGame.setBounds(40, 130, 100, 50);
      add(pGame);
      
      tGame = new JCheckBox("  팀전");
      tGame.setBounds(200, 130, 100, 50);
      add(tGame);
      
      
      roomNameSet.setFont(roomNameSet.getFont().deriveFont(25.0f));
      create.setFont(create.getFont().deriveFont(20.0f));
      cancel.setFont(cancel.getFont().deriveFont(20.0f));
      choGame.setFont(choGame.getFont().deriveFont(15.0f));
      oxGame.setFont(oxGame.getFont().deriveFont(15.0f));
      pGame.setFont(pGame.getFont().deriveFont(15.0f));
      tGame.setFont(tGame.getFont().deriveFont(15.0f));
      
      
      int x = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/3 - this.getWidth()/2;      // x좌표 가운데 설정
        int y = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/3 - this.getHeight()/2;    //y좌표 가운데 설정
        Toolkit toolkit = getToolkit();      // Q 플레이 아이콘 설정하기 위한 객체 생성
        Image image = toolkit.createImage("C:\\javawork\\Q_icon.png"); // Q 플레이 아이콘      // 경로에 파일 이미지 없으면 자바컵으로 나옴
        setLayout(null);
        setIconImage(image);   // QPLAY 아이콘 설정
        setTitle(" Q_PLAY ");   // UI 제목 Q_PLAY 설정
        setLocation(x,y);       // 실행시 위치 설정
      setSize(500,250);      // SIZE 500,250
      setVisible(true);      // setVisible true로 지정하여 창 활성화
      setResizable(false);   // 창 크기 조절 불가능
      setDefaultCloseOperation(EXIT_ON_CLOSE);
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      
      
   }
}

