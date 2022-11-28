package refund;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import buy.BuyBean;
import util.DatabaseUtil;

public class RefundDAO {
	ResultSet rs; //데이터베이스의 테이블 결과를 리턴받아 자바에 저장해주는 객체

	//마지막 환불 번호 가져오는 메소드
	public int getNextRefund() {
		Connection conn = DatabaseUtil.getConnection();
		String SQL = "SELECT rnum FROM refund ORDER BY rnum DESC";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1) + 1;
			}
			return 1;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	//환불 등록 메소드
	public int insertRefund(String cid, int bnum,Date rday, String reason) {
		Connection conn = DatabaseUtil.getConnection();
		String SQL = "INSERT INTO refund VALUES(?,?,?,?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, getNextRefund());
			pstmt.setString(2, cid);
			pstmt.setInt(3, bnum);
			pstmt.setDate(4, rday);
			pstmt.setString(5, reason);
			pstmt.executeUpdate();
			return 1;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	//상품 환불 취소 메소드
	public int deleteRefund(int rnum) {
		Connection conn = DatabaseUtil.getConnection();
		String SQL = "delete refund where rnum=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, rnum);
			pstmt.executeUpdate();
			return 1;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	//환불 정보 업데이트 메소드
	public int updateRefund(int rnum, String reason) {
		Connection conn = DatabaseUtil.getConnection();
		String SQL = "update refund set reason=? where rnum=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, reason);
			pstmt.setInt(2, rnum);
			pstmt.executeUpdate();
			return 1;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	//환불 정보 리턴 메소드
	public Vector<RefundBean> retrieveRefund(String cid){
		Vector<RefundBean> v = new Vector<>();	
		try {
			Connection conn = DatabaseUtil.getConnection();
			String sql = "select * from refund where cid = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cid);
			rs = pstmt.executeQuery();
			while(rs.next()) {	
				RefundBean bean = new RefundBean();
				bean.setRnum(rs.getInt(1));
				bean.setBnum(rs.getInt(2));
				bean.setCid(rs.getString(3));
				bean.setRday(rs.getDate(4));
				bean.setReason(rs.getString(5));
				v.add(bean);
			}
			conn.close();
		}catch (Exception e) {
			System.out.println("오류");
		}		
		return v;
	}
}
