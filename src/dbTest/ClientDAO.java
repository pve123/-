package dbTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {

   private Statement stmt;
   private PreparedStatement pstmt;
   private ResultSet rs;
   private String query;
   private boolean result = true;
   private List<ClientVO> cvo;
   private ClientVO cvo1;
   private String id;
   private List<ClientVO> list;
   public boolean confirmId(String id) {

      try {
         query = "SELECT * FROM client_info WHERE id = ?";
         pstmt = DBconn.getConnection().prepareStatement(query);
         pstmt.setString(1, id);
         rs = pstmt.executeQuery();
         if (rs.next()) {
            result = true;
         } else
            result = false;
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return result;
   }

   // 아이디중복확인
   public boolean confirmNickname(String nickname) {

      try {
         query = "SELECT * FROM client_info WHERE nickname = ?";
         pstmt = DBconn.getConnection().prepareStatement(query);
         pstmt.setString(1, nickname);
         rs = pstmt.executeQuery();
         if (rs.next()) {
            result = true;
         } else
            result = false;
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return result;
   }

   // 닉네임중복확인
   public boolean join(ClientVO cvo) {

      query = "INSERT INTO client_info" + " VALUES(?,?,?,?,?,?,?,?,?)";

      try {
         pstmt = DBconn.getConnection().prepareStatement(query);
         pstmt.setString(1, cvo.getId());
         pstmt.setString(2, cvo.getNickname());
         pstmt.setString(3, cvo.getPw());
         pstmt.setString(4, cvo.getEmail());
         pstmt.setString(5, cvo.getPhone());
         pstmt.setString(6, cvo.getSex());
         pstmt.setString(7, cvo.getQuestion());
         pstmt.setString(8, cvo.getQuestion_answer());
         pstmt.setString(9, cvo.getOnlineCheck());

         if (pstmt.executeUpdate() == 1) {
            result = true; // 회원가입성공
         } else
            result = false; // 회원가입실패
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         DBconn.close(pstmt);
      }

      return result;

   }
   // 회원가입

   public boolean login(String id, String password) {

      try {
         query = "SELECT id,pw FROM client_info WHERE id=? AND pw=?";
         pstmt = DBconn.getConnection().prepareStatement(query);
         pstmt.setString(1, id);
         pstmt.setString(2, password);
         rs = pstmt.executeQuery();
         if (rs.next()) {
            result = true;
         } else
            result = false;
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return result;
   }
   // 로그인

   public String lostid(String email, String phone) {

      try {
         query = "SELECT id FROM client_info WHERE email=? AND phone=?";
         pstmt = DBconn.getConnection().prepareStatement(query);
         pstmt.setString(1, email);
         pstmt.setString(2, phone);
         rs = pstmt.executeQuery();
         if (rs.next()) {
            return (rs.getString("id"));
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return null;
   }
   // 아이디찾기

   public String lostpw(String id, String email, String phone, String qt, String answer) {

      try {
         query = "SELECT pw FROM client_info WHERE id=? AND email=? AND phone=? AND question=? AND question_answer=?";
         pstmt = DBconn.getConnection().prepareStatement(query);
         pstmt.setString(1, id);
         pstmt.setString(2, email);
         pstmt.setString(3, phone);
         pstmt.setString(4, qt);
         pstmt.setString(5, answer);
         rs = pstmt.executeQuery();
         if (rs.next()) {
            return (rs.getString("pw"));
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return null;

   }
   // 비밀번호찾기

   public boolean deleteUser(String id, String pw) {

      try {
         query = "DELETE FROM client_info WHERE id = ? AND pw = ?";
         pstmt = DBconn.getConnection().prepareStatement(query);
         pstmt.setString(1, id);
         pstmt.setString(2, pw);

         if (pstmt.executeUpdate() == 1) {
            result = true;
         } else {
            result = false;
         }

      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         DBconn.close(pstmt, rs);
      }
      return result;
   }
   // 회원탈퇴

   public List<ClientVO> showMyClient() {
      query = "SELECT * FROM CLIENT_INFO";
      list = new ArrayList<ClientVO>();
      try {
         stmt = DBconn.getConnection().createStatement();
         rs = stmt.executeQuery(query);
         while (rs.next()) {
            cvo1 = new ClientVO();
            cvo1.setNickname(rs.getString("nickname"));
            cvo1.setId(rs.getString("id"));
            list.add(cvo1);
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         DBconn.close(stmt, rs);
      }
      return list;
   }

   public List<ClientVO> showConnClient(String nickname) {
      query = "SELECT nickname FROM client_info WHERE online_check = 'O'";
      list = new ArrayList<ClientVO>();
      try {
         stmt = DBconn.getConnection().createStatement();
         rs = stmt.executeQuery(query);
         while (rs.next()) {
            ClientVO cvo = new ClientVO();
            cvo.setNickname(rs.getString("nickname"));
            
            list.add(cvo);
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         DBconn.close(stmt, rs);
      }
      return list;
   }

   public boolean updateConn(String id) { // 로그인 시 online_check = 'O'로 변경

         System.out.println("id : " + id);
         

         query = "UPDATE client_info SET online_check = 'O' WHERE id ='"+ id +"'";
      try {
         stmt = DBconn.getConnection().createStatement();
         rs = stmt.executeQuery(query);
         
         
         if(stmt.executeUpdate(query) == 1) {
            result = true;
         }else result = false;
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         DBconn.close(stmt, rs);
      }
      return result;
   }

   public boolean updateDisconn(String id) { // 로그아웃 시 online_check 'X로 변경
   
         System.out.println("id : " + id);
         
         
         query = "UPDATE client_info SET online_check = 'X' WHERE id ='"+ id +"'";
         
         System.out.println(query);
      try {
         stmt = DBconn.getConnection().createStatement();
         rs = stmt.executeQuery(query);
         
         if(stmt.executeUpdate(query) == 1) {
            result = true;
         }else result = false;
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         DBconn.close(stmt, rs);
      }
      return result;
   }

}