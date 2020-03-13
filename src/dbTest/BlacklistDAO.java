package dbTest;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BlacklistDAO {
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private String query;
	private boolean result;
	
	public boolean blacklist(BlacklistVO bvo) {
		result = false;
		query = 
		"INSERT INTO blacklist(report_id) VALUES(?)";
		try {
			pstmt = DBconn.getConnection().prepareStatement(query);
			pstmt.setString(1,bvo.getReportId());
			if(pstmt.executeUpdate() == 1) {
				result = true;
			}
			else {
				result = false;
			}
		} catch (SQLIntegrityConstraintViolationException e) {
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		finally {
			DBconn.close(pstmt);
		}
		return result;

	}
	
	
	public List<BlacklistVO> showBlacklist(){
		query = "SELECT * FROM blacklist";
		List<BlacklistVO> list = new ArrayList<BlacklistVO>();
		try {
			stmt = DBconn.getConnection().createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				BlacklistVO bvo = new BlacklistVO();
				bvo.setReportNo(rs.getInt("report_no"));
				bvo.setReportId(rs.getString("report_id"));
				list.add(bvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBconn.close(stmt,rs);
		}
		return list;
	}
	
	public boolean getOne(String id) {
		query = "select id from client_info where id='"+id+"'";
		try {
			stmt = DBconn.getConnection().createStatement();
			rs = stmt.executeQuery(query);
			if(rs.next()) {
				result = true;
			}
			else result = false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(result);
		return result;
	}
	
}
