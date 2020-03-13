package UI;

import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;

import dbTest.QuizDAO;
import dbTest.QuizVO;

import java.io.*;

public class QuizServer {
   private static final int PORT = 7000;
   private static final String DATE_TIME_PATTERN = " - yyyy.MM.dd";
   private static final String TIME_PATTERN = "[a hh:mm:ss]";
   private Socket socket;
   public static Map<String, PrintWriter> clientMap;
   private int readycount;

   public QuizServer() {
      try (ServerSocket serverSocket = new ServerSocket(PORT)) {
         System.out.println("*** SERVER is running... ***");
         clientMap = Collections.synchronizedMap(new HashMap<String, PrintWriter>());

         while (true) {
            System.out.println("> 클라이언트 접속 대기중...");
            socket = serverSocket.accept();
            // 4.접속한 클라이언트 스레드로 처리 시작
            new ChatServerThread().start();
         }
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }// END MultiServer()

   class ChatServerThread extends Thread {
      private String nickname, msg;
      private PrintWriter pw;
      private BufferedReader br;
      private boolean quizset, check;

      private QuizDAO qdao;
      private List<QuizVO> list;
      
      private exm a;

      public ChatServerThread() {
         try {
            a= new exm();
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String[] input = br.readLine().split("/");
            nickname = input[0];
            msg = input[1];
            pw = new PrintWriter(socket.getOutputStream(), true);
            pw.println("*** 초성게임방에 접속되었습니다  *** \n");
         } catch (IOException e) {
            e.printStackTrace();
         }
      }

      @Override
      public void run() {
         clientMap.put(nickname, pw);
         broadCast(nickname + "님이 들어오셨습니다."); // 최초 입장 시에는 환영 메세지 전달
         try {
            while (br != null) {
               msg = br.readLine();
               System.out.println(msg);
               if (msg.equals("-1")) {
                  broadCast(nickname + "님이 나갔습니다.");
               }
               if (msg.equals("ready")) {
                  readycount++;
                  if (readycount == 2) {
                     quizset = true;
                     broadCast(sendquiz());
                     //
                     a.start();
                  }
               }
               
               if (msg.equals("wait")) {
                  readycount--;
               }
               if (msg.equals("correct")) {
                  a.interrupt();
               } else {
                  if (msg.equals("ready") || msg.equals("wait")) {
                  } else
                     broadCast(nickname + " > " + msg);
               }
            }
         } catch (SocketException e) {
            System.out.println(nickname + "퇴장");
            clientMap.remove(nickname);
            System.out.println(clientMap.size());
         } catch (IOException e) {
            e.printStackTrace();
         }
      }

      public void broadCast(String msg) {
         Iterator<String> iterator = clientMap.keySet().iterator();
         while (iterator.hasNext()) {
            pw = (PrintWriter) clientMap.get(iterator.next());
            pw.println(msg);
         }
      }

      public String sendquiz() {// 문제 보내기
         String str=null;
         if (quizset) {
            quizrecive();
            check=true;
            quizset = false;
         }
         
         if (list.isEmpty()) {
            gameEnd();
            str = "The End";
            return str;
         }
         if(check) {
         str = list.get(0).getQuizNum() + "&" + list.get(0).getQuizQuestion() + "&" + list.get(0).getQuizCho() + "&"
               + list.get(0).getAnswer();
         System.out.println(str);
         list.remove(0);
         }
         System.out.println(str);
         return str;
      }

      public boolean checkMessage() {
         return true;
      }

      public void quizrecive() {// 문제 받아서 저장 됭
         qdao = new QuizDAO();
         list = new ArrayList<QuizVO>();
         list = qdao.selectQuiz();
      }

      public void gameEnd() {// 게임이 끝남
         readycount = 0;
         quizset = true;
         check = false;
         
         
      }
      
      class exm extends Thread{
         @Override
         public void run() {
        	int k =0; 
            while(check && k<3) {
            try {
               while(true ) {
               sleep(3000);
               //퀴즈문제 출력
               
               interrupt();
               }
            } catch (InterruptedException e) {
               broadCast(sendquiz());
            }
            	k++;
            }
            gameEnd();
            
         }
      }

   }// END ChatServerThread
   


   public static void main(String[] args) {
      new QuizServer();

   }

}