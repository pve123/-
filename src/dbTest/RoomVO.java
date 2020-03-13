package dbTest;

public class RoomVO {
   private int roomNo;
   private String roomTitle;
   private int countPeople;
   private String roomSeq;
   public String getRoomSeq() {
      return roomSeq;
   }
   public void setRoomSeq(String roomSeq) {
      this.roomSeq = roomSeq;
   }
   public int getRoomNo() {
      return roomNo;
   }
   public void setRoomNo(int roomNo) {
      this.roomNo = roomNo;
   }
   public String getRoomTitle() {
      return roomTitle;
   }
   public void setRoomTitle(String rommTitle) {
      this.roomTitle = rommTitle;
   }
   public int getCountPeople() {
      return countPeople;
   }
   public void setCountPeople(int countPeople) {
      this.countPeople = countPeople;
   }
   
}