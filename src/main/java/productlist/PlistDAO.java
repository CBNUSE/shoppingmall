package productlist;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import util.DatabaseUtil;

public class PlistDAO {
	ResultSet rs; //�����ͺ��̽��� ���̺� ����� ���Ϲ޾� �ڹٿ� �������ִ� ��ü

	//������ ��ǰ����Ʈ ��ȣ �������� �޼ҵ�
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

	//��ǰ����Ʈ ��� �޼ҵ�
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
	
	//��ǰ����Ʈ ���� �޼ҵ�
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
	
	//��ǰ����Ʈ ���� ������Ʈ �޼ҵ�
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
	
	//��ǰ����Ʈ ���� ���� �޼ҵ�
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
			System.out.println("����");
		}		
		return v;
	}
}
