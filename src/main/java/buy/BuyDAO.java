package buy;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import user.UserBean;
import util.DatabaseUtil;

public class BuyDAO {
ResultSet rs; //�����ͺ��̽��� ���̺� ����� ���Ϲ޾� �ڹٿ� �������ִ� ��ü

	//������ ���Ź�ȣ �������� �޼ҵ�
	public int getNextBuy() {
		Connection conn = DatabaseUtil.getConnection();
		String SQL = "SELECT bnum FROM buy ORDER BY bnum DESC";
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

	//��ǰ ��� �޼ҵ�
	public int buyProduct(int pnum, String cnum, int bprice, int btotal,Date bday) {
		Connection conn = DatabaseUtil.getConnection();
		String SQL = "INSERT INTO buy VALUES(?,?,?,?,?,?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, getNextBuy());
			pstmt.setInt(2, pnum);
			pstmt.setString(3, cnum);
			pstmt.setInt(4, bprice);
			pstmt.setInt(5, btotal);
			pstmt.setDate(6, bday);
			pstmt.setInt(7,0);
			return pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	//��ǰ ��ǰ �� btag ������Ʈ �޼ҵ�
	public int deleteBuy(int bnum) {
		Connection conn = DatabaseUtil.getConnection();
		String SQL = "update buy set btag=1 where bnum=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, bnum);
			pstmt.executeUpdate();
			return 1;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	//���� ���� ������Ʈ �޼ҵ�
	public int updateBuy(int bnum, int btotal) {
		Connection conn = DatabaseUtil.getConnection();
		String SQL = "update buy set btotal=? where bnum=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, btotal);
			pstmt.setInt(2, bnum);
			pstmt.executeUpdate();
			return 1;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	//���� ���� ���� �޼ҵ�
	public Vector<BuyBean> retrieveBuy(String cid){
		Vector<BuyBean> v = new Vector<>();	
		try {
			Connection conn = DatabaseUtil.getConnection();
			String sql = "select * from buy where cid = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cid);
			rs = pstmt.executeQuery();
			while(rs.next()) {	
				BuyBean bean = new BuyBean();
				bean.setBnum(rs.getInt(1));
				bean.setPnum(rs.getInt(2));
				bean.setCid(rs.getString(3));
				bean.setBprice(rs.getInt(4));
				bean.setBtotal(rs.getInt(5));
				bean.setBday(rs.getDate(6));
				bean.setBtag(rs.getInt(7));
				v.add(bean);
			}
			conn.close();
		}catch (Exception e) {
			System.out.println("����");
		}		
		return v;
	}
	
	//���� ���� �ϳ��� �����ϴ� �޼ҵ�
	public BuyBean oneselectBuy(int bnum){
		try {
			Connection conn = DatabaseUtil.getConnection();
			String sql = "select * from buy where bnum = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bnum);
			rs = pstmt.executeQuery();
			if(rs.next()) {	
				BuyBean bean = new BuyBean();
				bean.setBnum(rs.getInt(1));
				bean.setPnum(rs.getInt(2));
				bean.setCid(rs.getString(3));
				bean.setBprice(rs.getInt(4));
				bean.setBtotal(rs.getInt(5));
				bean.setBday(rs.getDate(6));
				bean.setBtag(rs.getInt(7));
				return bean;
			}
			conn.close();
		}catch (Exception e) {
			System.out.println("����");
		}
		return null;
	}
}