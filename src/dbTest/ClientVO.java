package dbTest;

public class ClientVO {

   private String id="^[a-z]{1}[a-z0-9]{3,9}$"; //처음은 소문자로시작 그 후는 숫자랑 소문자 포함 4~10자
   private String nickname="^[a-z가-힣]{1}[a-z0-9가-힣]{2,9}$"; //처음은 한글이나 소문자로 그후는 숫자 소문자 한글 포함 3~10자
   private String pw="^[a-z0-9]{4,10}$";    //소문자 숫자 4~10자
   private String email="^[a-z]{1}[a-z0-9]{3,19}$"; // 첫자는 소문자 그후는 문자 숫자 4~20자
   private String phone="^01(?:0|1|[6-9])[-](\\d{3}|\\d{4})[-](\\d{4})$"; // 0104374424 || 010-4344-4444 가능   
   private String sex;
   private String question;
   private String question_answer;
   private String onlineCheck;
   
   public String getOnlineCheck() {
      return onlineCheck;
   }
   public void setOnlineCheck(String onlineCheck) {
      this.onlineCheck = onlineCheck;
   }
   public String getId() {
      return id;
   }
   public void setId(String id) {
      this.id = id;
   }
   public String getNickname() {
      return nickname;
   }
   public void setNickname(String nickname) {
      this.nickname = nickname;
   }
   public String getPw() {
      return pw;
   }
   public void setPw(String pw) {
      this.pw = pw;
   }
   public String getPhone() {
      return phone;
   }
   public void setPhone(String phone) {
      this.phone = phone;
   }
   public String getEmail() {
      return email;
   }
   public void setEmail(String email) {
      this.email = email;
   }
   public String getSex() {
      return sex;
   }
   public void setSex(String sex) {
      this.sex = sex;
   }
   public String getQuestion() {
      return question;
   }
   public void setQuestion(String question) {
      this.question = question;
   }
   public String getQuestion_answer() {
      return question_answer;
   }
   public void setQuestion_answer(String question_answer) {
      this.question_answer = question_answer;
   }
   
   
}