package server;

import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;

import dbTest.RoomDAO;
import dbTest.RoomVO;

import java.io.*;

public class MainServer{
   private static final int PORT = 5000;
   private static final String DATE_TIME_PATTERN = " - yyyy.MM.dd";
   private static final String TIME_PATTERN = "[a hh:mm:ss]";
   private Socket socket;
   private Map<String, PrintWriter> clientMap;
//   private Map<int,a> room;
   private RoomVO rvo;
   private RoomDAO rdao;
   public MainServer() {
      
      try(ServerSocket serverSocket = new ServerSocket(PORT)){
         System.out.println("*** SERVER is running... ***");
         clientMap = Collections.synchronizedMap(new HashMap<String, PrintWriter>());
                     
         while(true) {
            System.out.println("> 클라이언트 접속 대기중...");
            socket = serverSocket.accept();
            //4.접속한 클라이언트 스레드로 처리 시작
            new ChatServerThread().start();
         }
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

   }//END MultiServer()
   
   class ChatServerThread extends Thread{
      private String nickname, msg;
      private PrintWriter pw;
      private BufferedReader br;
      
      public ChatServerThread() {
         try {
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String[] input = br.readLine().split("/");
            nickname = input[0];
            msg = input[1];

//            clientMap.put(nickname, pw);
            
            pw = new PrintWriter(socket.getOutputStream(), true);
            pw.println("*** 서버에 켜졌다  *** \n");
         } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }         
      }
//      map.put(2, "서울");

      @Override
      public void run() {
         clientMap.put(nickname, pw);
         broadCast(nickname + "님이 들어오셨습니다."); //최초 입장 시에는 환영 메세지 전달
         ArrayList <String> nick = new ArrayList<String>();
         nick.add(nickname);
         System.out.println(nickname);
         
         try {
            while(br!= null) {
               msg = br.readLine();
               
               if(msg.equals("-1")) {
                  broadCast(nickname + "님이 나갔습니다.");
                  clientMap.remove(nickname);
               }else {
                  broadCast(nickname + " > " + msg);
               }
               
            }
         } catch(SocketException e) {
            System.out.println(nickname + "퇴장");
            int a = nick.indexOf(nickname);
            System.out.println(nick.size());
            nick.remove(a);
            System.out.println(nick.size());
            System.out.println(a);
         }catch(IOException e) {
            e.printStackTrace();
         }
         
      }
      public void broadCast(String msg) {
         Iterator<String> iterator = clientMap.keySet().iterator();
         
         //clientMap에서  출력 스트림을 받아서
         while (iterator.hasNext()) {
            pw = (PrintWriter)clientMap.get(iterator.next());
            
            // 모두에게 메세지 전송
            pw.println(msg);
         }
      }

   }//END ChatServerThread
   public String timeStamp(String pattern) {
      SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
      return dateFormat.format(new Date());
   }   
   public static void main(String[] args) {
      new MainServer();

   }

}
