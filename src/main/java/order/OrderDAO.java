package order;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Vector;

import order.OrderBEAN;
import util.DatabaseUtil;

public class OrderDAO {
	private ResultSet rs;
	//삽입
	public int insertOrder(int plistnum,String pname, int count, int osprice, int sid ) {
		Connection conn = DatabaseUtil.getConnection();
		String SQL = "INSERT INTO oders (plistnum, pname, count, osprice, sid) VALUES(?,?,?,?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, plistnum);
			pstmt.setString(2, pname);
			pstmt.setInt(3, count);
			pstmt.setInt(4, osprice);
			pstmt.setInt(5, sid);
			return pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	//삭제
	public int deleteOrders(int osnum) {
		String SQL = "delete from product WHERE osnum = ?";
		Connection conn = DatabaseUtil.getConnection();
		try {		
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, osnum);
			pstmt.executeUpdate();
			return 1;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	//수정
	public void updateOrders(int osnum,int count) {
		Connection conn = DatabaseUtil.getConnection();
		try {
			String sql = "update product set count=? where pnum=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, count);
			pstmt.setInt(2, osnum);
			pstmt.executeUpdate();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//검색
	public OrderBEAN retrieveOrder(int osnum) {
		try {
			String SQL = "SELECT * FROM oders WHERE osnum = ?";
			Connection conn = DatabaseUtil.getConnection();	
			
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1,osnum);
			
			rs = pstmt.executeQuery();	
			if(rs.next()) {
				OrderBEAN bean = new OrderBEAN();
				bean.setOsnum(rs.getInt(1));
				bean.setEid(rs.getInt(2));
				bean.setPlistnum(rs.getInt(3));
				bean.setOsprice(rs.getInt(4));
				bean.setOfrom(rs.getDate(5));
				bean.setSto(rs.getDate(6));
				bean.setOstag(rs.getInt(7));
				bean.setSid(rs.getInt(8));
				bean.setCount(rs.getInt(9));
				bean.setPname(rs.getString(9));
				return bean;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null; 
	}
	
}
