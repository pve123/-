package dbTest;

import java.sql.*;
import java.util.*;

import dbTest.ReportQVO;

public class ReportQDAO {
	
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private String query;
	private boolean result;
	
	public List<ReportQVO> reportAll(){
		query = "SELECT * FROM report_question";
		List<ReportQVO> list = new ArrayList<ReportQVO>();
		try {
			stmt = DBconn.getConnection().createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				ReportQVO rvo = new ReportQVO();
				rvo.setQuizNum(rs.getInt("quiz_num"));
				rvo.setContent(rs.getString("content"));
				list.add(rvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBconn.close(stmt,rs);
		}
		return list;
	}
	
	
}
