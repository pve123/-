package dbTest;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class RoomDAO {
//insert,delete,update,select
   private Statement stmt;
   private PreparedStatement pstmt;
   private String query;
   private boolean result;
   private RoomVO rvo;
   private ResultSet rs;
   private ArrayList roomarr;
   public ArrayList<RoomVO> roomAll(){
      //select
      query = "select * from room";
      roomarr = new ArrayList<RoomVO>();
      try {
         stmt = DBconn.getConnection().createStatement();
         rs = stmt.executeQuery(query);
         while(rs.next()) {
            rvo = new RoomVO();
            rvo.setRoomNo(rs.getInt("room_no"));
            rvo.setRoomTitle(rs.getString("room_title"));
            rvo.setCountPeople(rs.getInt("count_people"));
            rvo.setRoomSeq(rs.getString("room_seq"));
            roomarr.add(rvo);
         }
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }finally {
         DBconn.close(stmt,rs);
      }
      return roomarr;
   }
   public boolean createRoom(RoomVO rvo) {
      //insert
      query = "INSERT INTO ROOM VALUES(5,?,?,?)";
      try {
         pstmt = DBconn.getConnection().prepareStatement(query);
//         pstmt.setInt(1, x);
         pstmt.setString(1, rvo.getRoomTitle());
         pstmt.setInt(2, rvo.getCountPeople());
         pstmt.setString(3, rvo.getRoomSeq());
         if(pstmt.executeUpdate()==1) {
            result = true;
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }finally {
         DBconn.close(pstmt);
      }return result;
   }
//   public boolean deleteRoom() {
//      //delete
//   }
//   public boolean updateRoom() {
//      //update
//   }
}