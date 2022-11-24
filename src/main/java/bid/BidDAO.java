package bid;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import user.UserBean;
import util.DatabaseUtil;

public class BidDAO {
	ResultSet rs; //데이터베이스의 테이블 결과를 리턴받아 자바에 저장해주는 객체
	
	//자바빈 사용하여 데이터들 한번에 저장
	public void bidding(int id, int price, String buy_id) {
		try {
			Connection conn = DatabaseUtil.getConnection();	//데이터베이스에 접근할 수 있도록 설정
			String SQL = "INSERT INTO BID VALUES(?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(SQL);	//데이터베이스에서 쿼리를 실행시켜주는 객체
			pstmt.setInt(1, id);
			pstmt.setInt(2, price);
			pstmt.setString(3, buy_id);
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void bidding2(int id, int price, String buy_id) {
		try {
			Connection conn = DatabaseUtil.getConnection();	//데이터베이스에 접근할 수 있도록 설정
			String SQL = "INSERT INTO BID2 VALUES(?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(SQL);	//데이터베이스에서 쿼리를 실행시켜주는 객체
			pstmt.setInt(1, id);
			pstmt.setInt(2, price);
			pstmt.setString(3, buy_id);
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public bid_bean getHighest(int id) {
		bid_bean bean = new bid_bean();
		try {
			Connection conn = DatabaseUtil.getConnection();	//데이터베이스에 접근할 수 있도록 설정
			String SQL = "SELECT MAX(bidprice) FROM BID WHERE id = ?";
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			//?에 값을 맵핑
			pstmt.setInt(1, id);
			//쿼리실행
			rs = pstmt.executeQuery();
			if(rs.next()) {	//레코드가 있다면
				//상품아이디 세팅
				bean.setId(rs.getInt(1));
				pstmt.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean;
	}
	
	//mysql에서 최고 입찰가의 row를 가져오는 메소드
	public bid_bean getHighest2(int id) {

		bid_bean bean = new bid_bean();
		
		try {
			//커넥션연결
			Connection conn = DatabaseUtil.getConnection();
			//쿼리준비SELECT MAX(bidprice) FROM BID WHERE id = ?select * from bid order by bidprice desc limit 1;
			String sql = "select * from bid where id = ? order by bidprice desc limit 1;"; //user테이블에서 쿼리를 가져오는데 어떤 아이디가 넘어올지 모르기에
			PreparedStatement pstmt = conn.prepareStatement(sql);
			//?에 값을 맵핑
			pstmt.setInt(1, id);
			//쿼리실행
			rs = pstmt.executeQuery();
			if(rs.next()) {	//레코드가 있다면
				bean.setId(rs.getInt(1));
				bean.setPrice(rs.getInt(2));
				bean.setBuy_id(rs.getString(3));	
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean;
	}
	
	//mysql에서 최소 입찰가의 row를 가져오는 메소드
	public bid_bean getLowest(int id) {

		bid_bean bean = new bid_bean();
		
		try {
			//커넥션연결
			Connection conn = DatabaseUtil.getConnection();
			//쿼리준비SELECT MAX(bidprice) FROM BID WHERE id = ?select * from bid order by bidprice desc limit 1;
			String sql = "select * from bid2 where id = ? order by bidprice limit 1;"; //user테이블에서 쿼리를 가져오는데 어떤 아이디가 넘어올지 모르기에
			PreparedStatement pstmt = conn.prepareStatement(sql);
			//?에 값을 맵핑
			pstmt.setInt(1, id);
			//쿼리실행
			rs = pstmt.executeQuery();
			if(rs.next()) {	//레코드가 있다면
				bean.setId(rs.getInt(1));
				bean.setPrice(rs.getInt(2));
				bean.setBuy_id(rs.getString(3));	
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean;
	}
}
