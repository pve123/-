package dbTest;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReportCDAO {
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private String query;
	private boolean result;
	
	public List<ReportCVO> clientReport(){
		query = "SELECT report_no, report_id, content FROM report_client";
		List<ReportCVO> list = new ArrayList<ReportCVO>();
		try {
			stmt = DBconn.getConnection().createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				ReportCVO cvo = new ReportCVO();
				cvo.setReportNo(rs.getInt("report_no"));
				cvo.setReportId(rs.getString("report_id"));
				cvo.setContent(rs.getString("content"));
				list.add(cvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBconn.close(stmt,rs);
		}
		return list;
	}
	
	
}
