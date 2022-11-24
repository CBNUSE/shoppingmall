package bid;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import user.UserBean;
import util.DatabaseUtil;

public class BidDAO {
	ResultSet rs; //�����ͺ��̽��� ���̺� ����� ���Ϲ޾� �ڹٿ� �������ִ� ��ü
	
	//�ڹٺ� ����Ͽ� �����͵� �ѹ��� ����
	public void bidding(int id, int price, String buy_id) {
		try {
			Connection conn = DatabaseUtil.getConnection();	//�����ͺ��̽��� ������ �� �ֵ��� ����
			String SQL = "INSERT INTO BID VALUES(?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(SQL);	//�����ͺ��̽����� ������ ��������ִ� ��ü
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
			Connection conn = DatabaseUtil.getConnection();	//�����ͺ��̽��� ������ �� �ֵ��� ����
			String SQL = "INSERT INTO BID2 VALUES(?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(SQL);	//�����ͺ��̽����� ������ ��������ִ� ��ü
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
			Connection conn = DatabaseUtil.getConnection();	//�����ͺ��̽��� ������ �� �ֵ��� ����
			String SQL = "SELECT MAX(bidprice) FROM BID WHERE id = ?";
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			//?�� ���� ����
			pstmt.setInt(1, id);
			//��������
			rs = pstmt.executeQuery();
			if(rs.next()) {	//���ڵ尡 �ִٸ�
				//��ǰ���̵� ����
				bean.setId(rs.getInt(1));
				pstmt.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean;
	}
	
	//mysql���� �ְ� �������� row�� �������� �޼ҵ�
	public bid_bean getHighest2(int id) {

		bid_bean bean = new bid_bean();
		
		try {
			//Ŀ�ؼǿ���
			Connection conn = DatabaseUtil.getConnection();
			//�����غ�SELECT MAX(bidprice) FROM BID WHERE id = ?select * from bid order by bidprice desc limit 1;
			String sql = "select * from bid where id = ? order by bidprice desc limit 1;"; //user���̺��� ������ �������µ� � ���̵� �Ѿ���� �𸣱⿡
			PreparedStatement pstmt = conn.prepareStatement(sql);
			//?�� ���� ����
			pstmt.setInt(1, id);
			//��������
			rs = pstmt.executeQuery();
			if(rs.next()) {	//���ڵ尡 �ִٸ�
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
	
	//mysql���� �ּ� �������� row�� �������� �޼ҵ�
	public bid_bean getLowest(int id) {

		bid_bean bean = new bid_bean();
		
		try {
			//Ŀ�ؼǿ���
			Connection conn = DatabaseUtil.getConnection();
			//�����غ�SELECT MAX(bidprice) FROM BID WHERE id = ?select * from bid order by bidprice desc limit 1;
			String sql = "select * from bid2 where id = ? order by bidprice limit 1;"; //user���̺��� ������ �������µ� � ���̵� �Ѿ���� �𸣱⿡
			PreparedStatement pstmt = conn.prepareStatement(sql);
			//?�� ���� ����
			pstmt.setInt(1, id);
			//��������
			rs = pstmt.executeQuery();
			if(rs.next()) {	//���ڵ尡 �ִٸ�
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
