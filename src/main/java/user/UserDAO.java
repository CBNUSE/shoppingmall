package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import product.Bean;
import util.DatabaseUtil;

public class UserDAO {
	ResultSet rs; //�����ͺ��̽��� ���̺� ����� ���Ϲ޾� �ڹٿ� �������ִ� ��ü
	
	//�ڹٺ� ����Ͽ� �����͵� �ѹ��� ����
	public void insertCustomer(UserBean mbean) {
		try {
			Connection conn = DatabaseUtil.getConnection();	//�����ͺ��̽��� ������ �� �ֵ��� ����
			String SQL = "INSERT INTO customer VALUES(?,?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(SQL);	//�����ͺ��̽����� ������ ��������ִ� ��ü
			pstmt.setString(1, mbean.cid);
			pstmt.setString(2, mbean.cpassword);
			pstmt.setString(3, mbean.cname);
			pstmt.setString(4, mbean.caddress);
			pstmt.setString(5, mbean.ctel);
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Vector<UserBean> allSelectMember(){
		//�������̷� �����͸� ����
		Vector<UserBean> v = new Vector<>();	
		//�����ͺ��̽��� ����ó���� �ݵ�� �ؾߵ�.
		try {
			//Ŀ�ؼ� ����
			Connection conn = DatabaseUtil.getConnection();	//�����ͺ��̽���ƿ�� ��Ŀ�ؼǸ޼ҵ�
			//���� �غ�
			String sql = "select * from customer";
			//������ ��������ִ� ��ü ����
			PreparedStatement pstmt = conn.prepareStatement(sql);
			//������ ���� ��Ų ����� �����ؼ� �޾���(mysql���̺� �˻��� ����� �ڹٰ�ü�� ����)
			rs = pstmt.executeQuery();
			//�ݺ����� ����ؼ� rs�� ����� �����͸� �����س���
			while(rs.next()) {	//����� ������ ��ŭ���� ����
				UserBean bean = new UserBean();//�÷����� �������� �����͸� ��Ŭ������ �����ϱ�����
				bean.setCid(rs.getString(1));
				bean.setCpassword(rs.getString(2));
				bean.setCname(rs.getString(3));
				bean.setCaddress(rs.getString(4));
				bean.setCtel(rs.getString(5));
				//��Ű¡�� memberbeanŬ������ ���Ϳ� ����
				v.add(bean);//0�������� ������� �����Ͱ� ����
			}
			//�ݾ��ֱ�
			conn.close();
		}catch (Exception e) {
			System.out.println("����");
		}		
		return v;
	}
	
	//�ѻ���� ���� ������ �����ϴ� �޼ҵ�
	public UserBean oneSelectMember(String id) {
		//�ѻ���� ���� ������ �����ϱ⿡ ��Ŭ���� ��ü ����
		UserBean bean = new UserBean();
		
		try {
			//Ŀ�ؼǿ���
			Connection conn = DatabaseUtil.getConnection();
			//�����غ�
			String sql = "select * from customer where cid=?"; //user���̺��� ������ �������µ� � ���̵� �Ѿ���� �𸣱⿡
			PreparedStatement pstmt = conn.prepareStatement(sql);
			//?�� ���� ����
			pstmt.setString(1, id);
			//��������
			rs = pstmt.executeQuery();
			if(rs.next()) {	//���ڵ尡 �ִٸ�
				bean.setCname(rs.getString(1));
				bean.setCpassword(rs.getString(2));
				bean.setCname(rs.getString(3));
				bean.setCaddress(rs.getString(4));
				bean.setCtel(rs.getString(5));
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean;
	}
		
	public UserBean retrieveCustomer(String cid) {
		String sql = "SELECT * FROM customer WHERE cid = ?";
		UserBean ubean = new UserBean();
		try {
			Connection conn = DatabaseUtil.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,cid);

			//��������
			rs = pstmt.executeQuery();			
			if(rs.next()) {
				ubean.setCid(rs.getString(1));
				ubean.setCpassword(rs.getString(2));
				ubean.setCname(rs.getString(3));
				ubean.setCaddress(rs.getString(4));
				ubean.setCtel(rs.getString(5));
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ubean;
	}
	
	//�� ȸ���� �н����带 �����ϴ� �޼ҵ� �ۼ�
	public String getPass(String id) {
		//��Ʈ������ ������ �ؾ��ϱ⿡ ��Ʈ������ ����
		String password="";
		try {
			Connection conn = DatabaseUtil.getConnection();
			//�����غ�
			String sql = "select cpassword from customer where cid=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				password = rs.getString(1); //�н����尪�� ����� �÷��ε���
			}
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}		
		return password;
	}
	
	//�� ȸ���� ������ �����ϴ� �޼ҵ�
	public void updateCustomer(UserBean mbean) {
		Connection conn = DatabaseUtil.getConnection();
		try {
			//�����غ�,,,,���� ���������� �� ���ϰ�ʹٸ� �޸��޸��� �÷������
			String sql = "update customer set caddress=?,ctel=? where cid=?";
			//�������� ��ü ����
			PreparedStatement pstmt = conn.prepareStatement(sql);
			//?�� ���� ����
			pstmt.setString(1, mbean.getCaddress());
			pstmt.setString(2, mbean.getCtel());
			pstmt.setString(3, mbean.getCid());
			//��������
			pstmt.executeUpdate();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//�� ȸ���� �����ϴ� �޼ҵ�
	public void deleteCustomer(String id) {
		Connection conn = DatabaseUtil.getConnection();
		try {
			//�����غ�
			String sql = "delete from customer where cid=?";
			//�������� ��ü ����
			PreparedStatement pstmt = conn.prepareStatement(sql);
			//?�� ���� ����
			pstmt.setString(1, id);
			//��������
			pstmt.executeUpdate();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
