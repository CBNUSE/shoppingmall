package productlist;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import util.DatabaseUtil;

public class PlistDAO {
	ResultSet rs; //데이터베이스의 테이블 결과를 리턴받아 자바에 저장해주는 객체

	//마지막 상품리스트 번호 가져오는 메소드
	public int getNextPlist() {
		Connection conn = DatabaseUtil.getConnection();
		String SQL = "SELECT plnum FROM productlist ORDER BY plnum DESC";
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

	//상품리스트 등록 메소드
	public int insertPlist(String plname, String plcategory,String plcompany, int price) {
		Connection conn = DatabaseUtil.getConnection();
		String SQL = "INSERT INTO productlist VALUES(?,?,?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, getNextPlist());
			pstmt.setString(2, plname);
			pstmt.setString(3, plcategory);
			pstmt.setString(4, plcompany);
			pstmt.setInt(5, price);
			pstmt.executeUpdate();
			return 1;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	//상품리스트 삭제 메소드
	public int deleteRefund(int plnum) {
		Connection conn = DatabaseUtil.getConnection();
		String SQL = "delete productlist where plnum=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, plnum);
			pstmt.executeUpdate();
			return 1;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	//상품리스트 정보 업데이트 메소드
	public int updateRefund(int plnum, int plprice) {
		Connection conn = DatabaseUtil.getConnection();
		String SQL = "update productlist set plprice=? where plnum=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, plprice);
			pstmt.setInt(2, plnum);
			pstmt.executeUpdate();
			return 1;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	//상품리스트 정보 리턴 메소드
	public Vector<ProductListBean> retrieveRefund(String cid){
		Vector<ProductListBean> v = new Vector<>();	
		try {
			Connection conn = DatabaseUtil.getConnection();
			String sql = "select * from productlist";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {	
				ProductListBean bean = new ProductListBean();
				bean.setPlnum(rs.getInt(1));
				bean.setPlname(rs.getString(2));
				bean.setPlcategory(rs.getString(3));
				bean.setPlcompany(rs.getString(4));
				bean.setPlprice(rs.getInt(5));
				v.add(bean);
			}
			conn.close();
		}catch (Exception e) {
			System.out.println("오류");
		}		
		return v;
	}
}
