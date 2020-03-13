package dbTest;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JList;

public class QuizDAO {
   private Statement stmt;
   private PreparedStatement pstmt;
   private String query;
   private ResultSet rs;
   private QuizVO qvo;
   private boolean result;
   private int i,total;
   
   public QuizVO quizModify(int num) {
      query = "select * from quiz where quiz_num ='"+num+"'";
      qvo = new QuizVO();
      try {
         stmt = DBconn.getConnection().createStatement();
         rs = stmt.executeQuery(query);
         if(rs.next()) {
            qvo.setQuizQuestion(rs.getString("quiz_question"));
            qvo.setAnswer(rs.getString("answer"));
            
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }finally {
         DBconn.close(stmt,rs);
      }
      return qvo;
   }
   public boolean quizDelete(int num) {
      query = "delete from quiz where quiz_num ='"+num+"'";
      try {
         stmt = DBconn.getConnection().createStatement();
         if(stmt.executeUpdate(query)==1) {
            result = true;
         }
         else result = false;
      } catch (SQLException e) {
         e.printStackTrace();
      }finally {
         DBconn.close(stmt);
      }
      return result;
   }
   public boolean quizUpdate(QuizVO qvo) {
      query = "update quiz set quiz_question=?,answer=? where quiz_num=?";
      try {
         pstmt = DBconn.getConnection().prepareStatement(query);
         pstmt.setString(1, qvo.getQuizQuestion());
         pstmt.setString(2, qvo.getAnswer());
         pstmt.setInt(3, qvo.getQuizNum());
         if(pstmt.executeUpdate()==1) {
            result = true;
         }
         else result = false;
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return result;
   }
   public boolean quizOX(QuizVO qvo) {
      query = "INSERT INTO quiz VALUES('1',12,?,?)";
      try {
         pstmt = DBconn.getConnection().prepareStatement(query);
         pstmt.setString(1, qvo.getQuizQuestion());
         pstmt.setString(2, qvo.getAnswer());
         if(pstmt.executeUpdate()==1) {
            result = true;
         }
         else result = false;
      } catch (SQLException e) {
         e.printStackTrace();
      }finally {
         DBconn.close(pstmt);
      }
      return result;
   }
   public boolean quizIni(QuizVO qvo) {
      System.out.println(qvo.getQuizQuestion());
      System.out.println(qvo.getAnswer());
      query = "INSERT INTO quiz VALUES('0',13,?,?)";
      try {
         pstmt = DBconn.getConnection().prepareStatement(query);
         pstmt.setString(1, qvo.getQuizQuestion());
         pstmt.setString(2, qvo.getAnswer());
         if(pstmt.executeUpdate()==1) {
            result = true;
         }
         else result = false;
      } catch (SQLException e) {
         e.printStackTrace();
      }finally {
         DBconn.close(pstmt);
      }
      return result;
   }
   public List<QuizVO> quizlist(){
      query = "SELECT * FROM QUIZ order by quiz_num ASC";
      List<QuizVO> list = new ArrayList<QuizVO>();
      try {
         stmt = DBconn.getConnection().createStatement();
         rs = stmt.executeQuery(query);
         while(rs.next()) {
            qvo = new QuizVO();
            qvo.setAnswer(rs.getString("answer"));
            qvo.setQuizNum(rs.getInt("quiz_num"));
            qvo.setQuizQuestion(rs.getString("quiz_question"));
            qvo.setQuizSep(rs.getString("quiz_sep"));
            list.add(qvo);
         }
         
      } catch (SQLException e) {
         e.printStackTrace();
      }finally {
         DBconn.close(stmt,rs);
      }
      return list;
      
   }
   public List<QuizVO> quizlist(String quiz){
      query = "SELECT * FROM QUIZ WHERE quiz_sep='"+quiz+"'order by quiz_num";
      List<QuizVO> list = new ArrayList<QuizVO>();
      try {
         stmt = DBconn.getConnection().createStatement();
         rs = stmt.executeQuery(query);
         while(rs.next()) {
            qvo = new QuizVO();
            qvo.setAnswer(rs.getString("answer"));
            qvo.setQuizNum(rs.getInt("quiz_num"));
            qvo.setQuizQuestion(rs.getString("quiz_question"));
            qvo.setQuizSep(rs.getString("quiz_sep"));
            list.add(qvo);
         }
         
      } catch (SQLException e) {
         e.printStackTrace();
      }finally {
         DBconn.close(stmt,rs);
      }
      return list;
      
   }

   public List<QuizVO> quizgame(String name,int num){
         query = "SELECT * FROM QUIZ WHERE quiz_sep='"+name+"'"+" AND quiz_num="+num;
         List<QuizVO> list = new ArrayList<QuizVO>();
         try {
            stmt = DBconn.getConnection().createStatement();
            rs = stmt.executeQuery(query);
            while(rs.next()) {
               qvo = new QuizVO();               
               qvo.setQuizNum(rs.getInt("quiz_num"));
               qvo.setQuizQuestion(rs.getString("quiz_question"));
               qvo.setQuizCho(rs.getString("quiz_cho"));
               list.add(qvo);
            }
            
         } catch (SQLException e) {
            e.printStackTrace();
         }finally {
            DBconn.close(stmt,rs);
         }
         return list;
         
      }
   
   public QuizVO quizanswer(int num){
       query = "SELECT answer FROM QUIZ WHERE  quiz_num="+num;
       try {
          stmt = DBconn.getConnection().createStatement();
          rs = stmt.executeQuery(query);
          while(rs.next()) {
             qvo = new QuizVO();               
             qvo.setAnswer(rs.getString("answer"));
             System.out.println(qvo.getAnswer());
          }
          
       } catch (SQLException e) {
          e.printStackTrace();
       }finally {
          DBconn.close(stmt,rs);
       }
       
       return qvo;
    }
   //<<문제 번호, 초성, 답 저장 >>리스트에 저장하는 메서드
   public List<QuizVO> selectQuiz(){
      query = "select * from (select * from quiz order by dbms_random.random) where rownum<11";//10문제 랜덤뽑기
      List<QuizVO> quiz = new ArrayList<QuizVO>();
      try {
      stmt = DBconn.getConnection().createStatement();
      rs = stmt.executeQuery(query);
      while(rs.next()) {
         qvo = new QuizVO();
         qvo.setQuizNum(rs.getInt("QUIZ_NUM"));
         qvo.setQuizQuestion(rs.getString("QUIZ_QUESTION"));
         qvo.setQuizCho(rs.getString("QUIZ_CHO"));
         qvo.setAnswer(rs.getString("answer"));
         quiz.add(qvo);
      }
   } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
   }
      return quiz;
   }
   
}