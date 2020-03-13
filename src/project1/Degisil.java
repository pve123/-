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
       wd_btn= new JButton("Ż��");        
         wd_btn.setBounds(1050, 570, 220, 100);
         add(wd_btn);  
         wd_btn.addActionListener(this);
   /////////////////////////////////////////////////////////////////////////////   Ż�����ư   
         chat_txtarea = new JTextArea();
         chat_txtarea.setBounds(60, 370, 750, 250);
        chat_txtarea.setEditable(false);      //�۾���ġ����
       scroll_chat = new JScrollPane(chat_txtarea, 
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); //��ũ��
       scroll_chat.setBounds(60,370,750,250);        
        add(scroll_chat);
            /////////////////////////////////////////////////////////////////////////////      ä�ú����ִ°�
        chat_txtfield = new JTextField();
        chat_txtfield.setBounds(60, 640, 750,30);
        add(chat_txtfield);
        chat_txtfield.addKeyListener(new KeyAdapter() {

         @Override
         public void keyReleased(KeyEvent e) {            
            if(e.getKeyCode() == 10) {   //����Ű�� ���� ���            
                  chat_txtarea.append(chat_txtfield.getText() + "\n");               
               chat_txtfield.setText("");
               chat_txtfield.requestFocus();
            }
         }
      });
           /////////////////////////////////////////////////////////////////////////////      ä��ġ�°�
        chat_label = new JLabel("ä   ��   â");
        chat_label.setFont(new Font("�ü�", Font.BOLD, 30));      
        chat_label.setBounds(320,190,300,300);
        add(chat_label);
   /////////////////////////////////////////////////////////////////////////////  ä��â�̸� ��
        rank_txtarea = new JTextArea();
       rank_txtarea.setBounds(840, 270, 180, 400);
        rank_txtarea.setEditable(false);
        rank_txtarea.setText("��ü��ŷ�� ���� ��ŷ�����ִ°�");
        rank_txtarea.setBorder(new MatteBorder(5,5,5,5,Color.black)); //�׵θ� �α�,��
        rank_txtarea.setBackground(new Color(234,234,234));//����
        add(rank_txtarea);
   /////////////////////////////////////////////////////////////////////////////    ��ü��ŷ�����ִ°�
        my_info = new JTextArea();
        my_info.setBounds(1050, 160, 220, 400);
        my_info.setEditable(false);
        my_info.setText("������");
        my_info.setBorder(new MatteBorder(5,5,5,5,Color.orange)); //�׵θ� �α�,��
        my_info.setBackground(Color.pink);   //����
        add(my_info);
   ////////////////////////////////////////////////////////////////////////////     ������Ȯ��
        logout = new JButton("�α׾ƿ�");
        logout.setBounds(1050, 50, 220, 100);
        add(logout);
        logout.addActionListener(this);
   ///////////////////////////////////////////////////////////////////////////     �α׾ƿ���ư
        con_people = new JTextArea();
        con_people.setBounds(840,50,180,200);
        con_people.setEditable(false);
        scroll_con = new JScrollPane(con_people, 
            ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll_con.setBounds(840, 50, 180, 200);
        con_people.setBorder(new MatteBorder(5,5,5,5,Color.LIGHT_GRAY));
        add(scroll_con);
    ///////////////////////////////////////////////////////////////////////// ������ ��� 
        realtime=new JTextArea();
      realtime.setEditable(false);
      realtime.setBounds(1090,15,220,30);
      add(realtime);
      realtime.setBackground(new Color(212,244,250));            
      realtime.setFont(new Font("�ü�", Font.BOLD, 20));
    /////////////////////////////////////////////////////////////////////////   �ǽð� �ð� area
        con_num = new JLabel("������");
        con_num.setBounds(900, 15, 220, 30);                 
        con_num.setFont(new Font("�ü�", Font.BOLD, 20));
      add(con_num);
    /////////////////////////////////////////////////////////////////////////    �����ڶ�   
      room_btn = new JButton("�� ����");
      room_btn.setBounds(600,20,220,80);
      add(room_btn);
      room_btn.addActionListener(this);
    /////////////////////////////////////////////////////////////////////////    ����� ��ư 
      gtype_lbl = new JLabel("���� ����");
      gtype_lbl.setBounds(30,10,120,80);
      gtype_lbl.setFont(new Font("�ü�", Font.BOLD, 20));
      add(gtype_lbl);
    /////////////////////////////////////////////////////////////////////////    �������� �� 
      String[] g_type = {"������","�ʼ��������","OX�������"};
      gtype_cb = new JComboBox<String>(g_type);
      gtype_cb.setBounds(150,30,430,40);
      add(gtype_cb);
    /////////////////////////////////////////////////////////////////////////    �������� �� 
      String[] room = {"1����","2����","3����","1����","2����","3����","2����","3����"};      
      g_list = new JList<Object>(room);
      g_list.setBounds(60,130,745,170);   
      scroll_game = new JScrollPane(g_list, 
            ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
      scroll_game.setBounds(60, 130, 745, 170);
      add(scroll_game);

        int x = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/8 - this.getWidth()/2;   // x��ǥ ��� ����
        int y = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/8 - this.getHeight()/2; //y��ǥ ��� ����
        setLocation(x,y); // ������â ��ġ
        Toolkit toolkit = getToolkit();
        Image image = toolkit.createImage("C:\\javawork\\Q_icon.png"); 
        setIconImage(image);
        setTitle(" Q_PLAY "); 
        getContentPane().setBackground(new Color(212,244,250));   //����    
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
} //���� �ð��̶� ��¥�� ������ִ� ������ 

@Override
public void actionPerformed(ActionEvent e) {

   if (e.getSource() == wd_btn) {
      JOptionPane.showConfirmDialog(null, "���� Ż���Ͻðڽ��ϱ� ?", "Ż��", 
      JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE);
   }
   if (e.getSource() == logout) {
      JOptionPane.showConfirmDialog(null, "���� �α׾ƿ� �Ͻðڽ��ϱ� ?", "�α׾ƿ�", 
      JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE);
   }
   if (e.getSource() ==room_btn) {
      cr = new CreateRoom();
   }
}
}

class CreateRoom extends JFrame implements ActionListener{
   
   private JTextField roomNameSet;      // �� ������ �Է��ϴ� roomNameSet
   private JButton cancel, create;      // �� ���� ��� cancel, �� ���� ��ư create
   private JCheckBox choGame, oxGame, pGame, tGame;   // �ʼ����� : choGame  ox����: oxGame  ������: pGame  ����: tGame
   
   public CreateRoom(){
      
      // setBounds(x,y,width,height)
      
      roomNameSet = new JTextField("�� ���� �Է�");      
      roomNameSet.setBounds(30, 20, 420, 40);
      add(roomNameSet);
      
      create = new JButton("����");
      create.setBounds(370, 80, 80, 40);
      add(create);
      
      cancel = new JButton("���");
      cancel.setBounds(370, 130, 80, 40);
      add(cancel);
      
      choGame = new JCheckBox("  �ʼ� ����");
      choGame.setBounds(40, 80, 100, 50);
      add(choGame);
      
      oxGame = new JCheckBox("  OX ����");
      oxGame.setBounds(200, 80, 100, 50);
      add(oxGame);
      
      pGame = new JCheckBox("  ������");
      pGame.setBounds(40, 130, 100, 50);
      add(pGame);
      
      tGame = new JCheckBox("  ����");
      tGame.setBounds(200, 130, 100, 50);
      add(tGame);
      
      
      roomNameSet.setFont(roomNameSet.getFont().deriveFont(25.0f));
      create.setFont(create.getFont().deriveFont(20.0f));
      cancel.setFont(cancel.getFont().deriveFont(20.0f));
      choGame.setFont(choGame.getFont().deriveFont(15.0f));
      oxGame.setFont(oxGame.getFont().deriveFont(15.0f));
      pGame.setFont(pGame.getFont().deriveFont(15.0f));
      tGame.setFont(tGame.getFont().deriveFont(15.0f));
      
      
      int x = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/3 - this.getWidth()/2;      // x��ǥ ��� ����
        int y = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/3 - this.getHeight()/2;    //y��ǥ ��� ����
        Toolkit toolkit = getToolkit();      // Q �÷��� ������ �����ϱ� ���� ��ü ����
        Image image = toolkit.createImage("C:\\javawork\\Q_icon.png"); // Q �÷��� ������      // ��ο� ���� �̹��� ������ �ڹ������� ����
        setLayout(null);
        setIconImage(image);   // QPLAY ������ ����
        setTitle(" Q_PLAY ");   // UI ���� Q_PLAY ����
        setLocation(x,y);       // ����� ��ġ ����
      setSize(500,250);      // SIZE 500,250
      setVisible(true);      // setVisible true�� �����Ͽ� â Ȱ��ȭ
      setResizable(false);   // â ũ�� ���� �Ұ���
      setDefaultCloseOperation(EXIT_ON_CLOSE);
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      
      
   }
}

