package dbTest;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CheckDAO {
   
   private Statement stmt;
   private PreparedStatement pstmt;
   private ResultSet rs;
   private String query;
   private boolean result;
   
   private ClientVO cvo;
   private ClientDAO cdo;
   private String nickname;
   private String id;
   
   public List<CheckVO> myAll(ClientVO cvo){
	      
	      id = cvo.getId();
	      System.out.println("id : " + id);
	      query = "SELECT ch.* FROM check_info ch, client_info cl WHERE ch.nickname = cl.nickname AND cl.id = '" + id + "'";
	      
	      List<CheckVO> list = new ArrayList<CheckVO>();
	      try {
	         stmt = DBconn.getConnection().createStatement();
	         rs = stmt.executeQuery(query);
	         while(rs.next()) {
	            CheckVO cvo1 = new CheckVO();
	            cvo1.setNickname(rs.getString("nickname"));
	            cvo1.setPansuInip(rs.getInt("pansu_inip"));
	            cvo1.setPansuInit(rs.getInt("pansu_init"));
	            cvo1.setPansuOx(rs.getInt("pansu_ox"));
	            cvo1.setPansuWinp(rs.getInt("pansu_winp"));
	            cvo1.setPansuWint(rs.getInt("pansu_wint"));
	            cvo1.setPansuOxw(rs.getInt("pansu_oxw"));
	            cvo1.setPansuLosep(rs.getInt("pansu_losep"));
	            cvo1.setPansuLoset(rs.getInt("pansu_loset"));
	            cvo1.setPansuOxl(rs.getInt("pansu_oxl"));
	            cvo1.setJumsuP(rs.getInt("jumsu_p"));
	            cvo1.setJumsuT(rs.getInt("jumsu_t"));
	            cvo1.setJumsuOx(rs.getInt("jumsu_ox"));
	              
	            list.add(cvo1);
	         }
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         DBconn.close(stmt,rs);
	      }
	      return list;
	   }
   
   public List<CheckVO> RankAll(){
      query = "select j.* from  (select nickname, jumsu_p, rank() over (order by jumsu_p desc) \"순위\"" + 
            " from check_info) j where rownum <= 3 ";
      List<CheckVO> list = new ArrayList<CheckVO>();
      try {
         stmt = DBconn.getConnection().createStatement();
         rs = stmt.executeQuery(query);
         while(rs.next()) {
            CheckVO cvo1 = new CheckVO();
            
            cvo1.setNickname(rs.getString("nickname"));
            cvo1.setJumsuP(rs.getInt("jumsu_p"));
              
            list.add(cvo1);
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         DBconn.close(stmt,rs);
      }
      return list;
   }
   

}