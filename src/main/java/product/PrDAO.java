package product;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Vector;

import user.UserBean;
import util.DatabaseUtil;

public class PrDAO {
	private ResultSet rs;
	
	public String getDate() {
		Connection conn = DatabaseUtil.getConnection();
		String SQL = "SELECT NOW()";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public int getNext() {
		Connection conn = DatabaseUtil.getConnection();
		String SQL = "SELECT id FROM PRODUCT ORDER BY id DESC";
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
	
	public int getNext2() {
		Connection conn = DatabaseUtil.getConnection();
		String SQL = "SELECT id FROM PRODUCT2 ORDER BY id DESC";
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
	
	public int write(String name, String category, int price, String content,Date date,String path, String user_id) {
		Connection conn = DatabaseUtil.getConnection();
		String SQL = "INSERT INTO PRODUCT VALUES(?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, name);
			pstmt.setString(2, category);
			pstmt.setInt(3, price);
			pstmt.setString(4, content);
			pstmt.setDate(5, date);
			pstmt.setInt(6, 1);
			pstmt.setInt(7,getNext());
			pstmt.setString(8,path);
			pstmt.setString(9,user_id);
			pstmt.setInt(10,0);
			return pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public int write2(String name, String category, int price, String content,Date date,String path, String user_id) {
		Connection conn = DatabaseUtil.getConnection();
		String SQL = "INSERT INTO PRODUCT2 VALUES(?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, name);
			pstmt.setString(2, category);
			pstmt.setInt(3, price);
			pstmt.setString(4, content);
			pstmt.setDate(5, date);
			pstmt.setInt(6, 1);
			pstmt.setInt(7,getNext2());
			pstmt.setString(8,path);
			pstmt.setString(9,user_id);
			pstmt.setInt(10,0);
			return pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public ArrayList<Bean> getList(int pageNumber){
		Connection conn = DatabaseUtil.getConnection();
		String SQL = "SELECT * FROM PRODUCT WHERE id < ? AND bbsavailable = 1 ORDER BY id DESC LIMIT 6";
		ArrayList<Bean> list = new ArrayList<Bean>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1,getNext() - (pageNumber - 1) * 6);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Bean bean = new Bean();
				bean.setName(rs.getString(1));
				bean.setCategory(rs.getString(2));
				bean.setPrice(rs.getInt(3));
				bean.setContent(rs.getString(4));
				bean.setBbsdate(rs.getString(5));
				bean.setBbsavailable(rs.getInt(6));
				bean.setId(rs.getInt(7));
				bean.setAddress(rs.getString(8));
				bean.setUser_id(rs.getString(9));
				bean.setViews(rs.getInt(10));
				list.add(bean);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<Bean> getList2(){
		Connection conn = DatabaseUtil.getConnection();
		String SQL = "SELECT * FROM PRODUCT ORDER BY id DESC";
		ArrayList<Bean> list = new ArrayList<Bean>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Bean bean = new Bean();
				bean.setName(rs.getString(1));
				bean.setCategory(rs.getString(2));
				bean.setPrice(rs.getInt(3));
				bean.setContent(rs.getString(4));
				bean.setBbsdate(rs.getString(5));
				bean.setBbsavailable(rs.getInt(6));
				bean.setId(rs.getInt(7));
				bean.setAddress(rs.getString(8));
				bean.setUser_id(rs.getString(9));
				bean.setViews(rs.getInt(10));
				list.add(bean);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<Bean> getList3(){
		Connection conn = DatabaseUtil.getConnection();
		String SQL = "SELECT * FROM PRODUCT2 ORDER BY id DESC";
		ArrayList<Bean> list = new ArrayList<Bean>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Bean bean = new Bean();
				bean.setName(rs.getString(1));
				bean.setCategory(rs.getString(2));
				bean.setPrice(rs.getInt(3));
				bean.setContent(rs.getString(4));
				bean.setBbsdate(rs.getString(5));
				bean.setBbsavailable(rs.getInt(6));
				bean.setId(rs.getInt(7));
				bean.setAddress(rs.getString(8));
				bean.setUser_id(rs.getString(9));
				bean.setViews(rs.getInt(10));
				list.add(bean);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public boolean nextPage(int pageNumber) {
		Connection conn = DatabaseUtil.getConnection();
		String SQL = "SELECT * FROM PRODUCT WHERE id < ? AND bbsavailable = 1 ";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1,getNext() - (pageNumber - 1) * 6);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false; 
	}
	
	//��ǰ�̹��� ����
	public void updateProductImg(String user_id, String path) {
		Connection conn = DatabaseUtil.getConnection();
		try {
			//�����غ�,,,,���� ���������� �� ���ϰ�ʹٸ� �޸��޸��� �÷������
			String sql = "update product set address=? where user_id=?";
			//�������� ��ü ����
			PreparedStatement pstmt = conn.prepareStatement(sql);
			//?�� ���� ����
			pstmt.setString(1, path);
			pstmt.setString(2, user_id);
			//��������
			pstmt.executeUpdate();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Vector<Bean> sortLatest(String category){
		//�������̷� �����͸� ����
		Vector<Bean> v = new Vector<>();
		
		//�����ͺ��̽��� ����ó���� �ݵ�� �ؾߵ�.
		try {
			//Ŀ�ؼ� ����
			Connection conn = DatabaseUtil.getConnection();	//�����ͺ��̽���ƿ�� ��Ŀ�ؼǸ޼ҵ�
			//���� �غ�
			String sql = "SELECT * FROM product where category = ? order by id desc";
			//������ ��������ִ� ��ü ����
			PreparedStatement pstmt = conn.prepareStatement(sql);
			//?�� ���� ����
			pstmt.setString(1, category);
			//������ ���� ��Ų ����� �����ؼ� �޾���(mysql���̺� �˻��� ����� �ڹٰ�ü�� ����)
			rs = pstmt.executeQuery();
			//�ݺ����� ����ؼ� rs�� ����� �����͸� �����س���
			while(rs.next()) {	//����� ������ ��ŭ���� ����
				Bean bean = new Bean();
				bean.setName(rs.getString(1));
				bean.setCategory(rs.getString(2));
				bean.setPrice(rs.getInt(3));
				bean.setContent(rs.getString(4));
				bean.setBbsdate(rs.getString(5));
				bean.setBbsavailable(rs.getInt(6));
				bean.setId(rs.getInt(7));
				bean.setAddress(rs.getString(8));
				bean.setUser_id(rs.getString(9));
				bean.setViews(rs.getInt(10));
				v.add(bean);//0�������� ������� �����Ͱ� ����
			}
			//�ݾ��ֱ�
			conn.close();
		}catch (Exception e) {
			System.out.println("����");
		}
		return v;
	}
	
	public Vector<Bean> sortLatest3(String category){
		//�������̷� �����͸� ����
		Vector<Bean> v = new Vector<>();
		
		//�����ͺ��̽��� ����ó���� �ݵ�� �ؾߵ�.
		try {
			//Ŀ�ؼ� ����
			Connection conn = DatabaseUtil.getConnection();	//�����ͺ��̽���ƿ�� ��Ŀ�ؼǸ޼ҵ�
			//���� �غ�
			String sql = "SELECT * FROM product2 where category = ? order by id desc";
			//������ ��������ִ� ��ü ����
			PreparedStatement pstmt = conn.prepareStatement(sql);
			//?�� ���� ����
			pstmt.setString(1, category);
			//������ ���� ��Ų ����� �����ؼ� �޾���(mysql���̺� �˻��� ����� �ڹٰ�ü�� ����)
			rs = pstmt.executeQuery();
			//�ݺ����� ����ؼ� rs�� ����� �����͸� �����س���
			while(rs.next()) {	//����� ������ ��ŭ���� ����
				Bean bean = new Bean();
				bean.setName(rs.getString(1));
				bean.setCategory(rs.getString(2));
				bean.setPrice(rs.getInt(3));
				bean.setContent(rs.getString(4));
				bean.setBbsdate(rs.getString(5));
				bean.setBbsavailable(rs.getInt(6));
				bean.setId(rs.getInt(7));
				bean.setAddress(rs.getString(8));
				bean.setUser_id(rs.getString(9));
				bean.setViews(rs.getInt(10));
				v.add(bean);//0�������� ������� �����Ͱ� ����
			}
			//�ݾ��ֱ�
			conn.close();
		}catch (Exception e) {
			System.out.println("����");
		}
		return v;
	}
	
	public Vector<Bean> sortPopularity(String category){
		//�������̷� �����͸� ����
		Vector<Bean> v = new Vector<>();
		
		//�����ͺ��̽��� ����ó���� �ݵ�� �ؾߵ�.
		try {
			//Ŀ�ؼ� ����
			Connection conn = DatabaseUtil.getConnection();	//�����ͺ��̽���ƿ�� ��Ŀ�ؼǸ޼ҵ�
			//���� �غ�
			String sql = "SELECT * FROM product where category = ? order by views desc";
			//������ ��������ִ� ��ü ����
			PreparedStatement pstmt = conn.prepareStatement(sql);
			//?�� ���� ����
			pstmt.setString(1, category);
			//������ ���� ��Ų ����� �����ؼ� �޾���(mysql���̺� �˻��� ����� �ڹٰ�ü�� ����)
			rs = pstmt.executeQuery();
			//�ݺ����� ����ؼ� rs�� ����� �����͸� �����س���
			while(rs.next()) {	//����� ������ ��ŭ���� ����
				Bean bean = new Bean();
				bean.setName(rs.getString(1));
				bean.setCategory(rs.getString(2));
				bean.setPrice(rs.getInt(3));
				bean.setContent(rs.getString(4));
				bean.setBbsdate(rs.getString(5));
				bean.setBbsavailable(rs.getInt(6));
				bean.setId(rs.getInt(7));
				bean.setAddress(rs.getString(8));
				bean.setUser_id(rs.getString(9));
				bean.setViews(rs.getInt(10));
				v.add(bean);//0�������� ������� �����Ͱ� ����
			}
			//�ݾ��ֱ�
			conn.close();
		}catch (Exception e) {
			System.out.println("����");
		}
		return v;
	}
	
	public Vector<Bean> sortPopularity2(){
		//�������̷� �����͸� ����
		Vector<Bean> v = new Vector<>();
		
		//�����ͺ��̽��� ����ó���� �ݵ�� �ؾߵ�.
		try {
			//Ŀ�ؼ� ����
			Connection conn = DatabaseUtil.getConnection();	//�����ͺ��̽���ƿ�� ��Ŀ�ؼǸ޼ҵ�
			//���� �غ�
			String sql = "SELECT * FROM product order by views desc";
			//������ ��������ִ� ��ü ����
			PreparedStatement pstmt = conn.prepareStatement(sql);
			//?�� ���� ����
			//������ ���� ��Ų ����� �����ؼ� �޾���(mysql���̺� �˻��� ����� �ڹٰ�ü�� ����)
			rs = pstmt.executeQuery();
			//�ݺ����� ����ؼ� rs�� ����� �����͸� �����س���
			while(rs.next()) {	//����� ������ ��ŭ���� ����
				Bean bean = new Bean();
				bean.setName(rs.getString(1));
				bean.setCategory(rs.getString(2));
				bean.setPrice(rs.getInt(3));
				bean.setContent(rs.getString(4));
				bean.setBbsdate(rs.getString(5));
				bean.setBbsavailable(rs.getInt(6));
				bean.setId(rs.getInt(7));
				bean.setAddress(rs.getString(8));
				bean.setUser_id(rs.getString(9));
				bean.setViews(rs.getInt(10));
				v.add(bean);//0�������� ������� �����Ͱ� ����
			}
			//�ݾ��ֱ�
			conn.close();
		}catch (Exception e) {
			System.out.println("����");
		}
		return v;
	}
	
	public Vector<Bean> sortPopularity3(String category){
		//�������̷� �����͸� ����
		Vector<Bean> v = new Vector<>();
		
		//�����ͺ��̽��� ����ó���� �ݵ�� �ؾߵ�.
		try {
			//Ŀ�ؼ� ����
			Connection conn = DatabaseUtil.getConnection();	//�����ͺ��̽���ƿ�� ��Ŀ�ؼǸ޼ҵ�
			//���� �غ�
			String sql = "SELECT * FROM product2 where category = ? order by views desc";
			//������ ��������ִ� ��ü ����
			PreparedStatement pstmt = conn.prepareStatement(sql);
			//?�� ���� ����
			pstmt.setString(1, category);
			//������ ���� ��Ų ����� �����ؼ� �޾���(mysql���̺� �˻��� ����� �ڹٰ�ü�� ����)
			rs = pstmt.executeQuery();
			//�ݺ����� ����ؼ� rs�� ����� �����͸� �����س���
			while(rs.next()) {	//����� ������ ��ŭ���� ����
				Bean bean = new Bean();
				bean.setName(rs.getString(1));
				bean.setCategory(rs.getString(2));
				bean.setPrice(rs.getInt(3));
				bean.setContent(rs.getString(4));
				bean.setBbsdate(rs.getString(5));
				bean.setBbsavailable(rs.getInt(6));
				bean.setId(rs.getInt(7));
				bean.setAddress(rs.getString(8));
				bean.setUser_id(rs.getString(9));
				bean.setViews(rs.getInt(10));
				v.add(bean);//0�������� ������� �����Ͱ� ����
			}
			//�ݾ��ֱ�
			conn.close();
		}catch (Exception e) {
			System.out.println("����");
		}
		return v;
	}
	
	public Vector<Bean> sortPopularity4(){
		//�������̷� �����͸� ����
		Vector<Bean> v = new Vector<>();
		
		//�����ͺ��̽��� ����ó���� �ݵ�� �ؾߵ�.
		try {
			//Ŀ�ؼ� ����
			Connection conn = DatabaseUtil.getConnection();	//�����ͺ��̽���ƿ�� ��Ŀ�ؼǸ޼ҵ�
			//���� �غ�
			String sql = "SELECT * FROM product2 order by views desc";
			//������ ��������ִ� ��ü ����
			PreparedStatement pstmt = conn.prepareStatement(sql);
			//?�� ���� ����
			//������ ���� ��Ų ����� �����ؼ� �޾���(mysql���̺� �˻��� ����� �ڹٰ�ü�� ����)
			rs = pstmt.executeQuery();
			//�ݺ����� ����ؼ� rs�� ����� �����͸� �����س���
			while(rs.next()) {	//����� ������ ��ŭ���� ����
				Bean bean = new Bean();
				bean.setName(rs.getString(1));
				bean.setCategory(rs.getString(2));
				bean.setPrice(rs.getInt(3));
				bean.setContent(rs.getString(4));
				bean.setBbsdate(rs.getString(5));
				bean.setBbsavailable(rs.getInt(6));
				bean.setId(rs.getInt(7));
				bean.setAddress(rs.getString(8));
				bean.setUser_id(rs.getString(9));
				bean.setViews(rs.getInt(10));
				v.add(bean);//0�������� ������� �����Ͱ� ����
			}
			//�ݾ��ֱ�
			conn.close();
		}catch (Exception e) {
			System.out.println("����");
		}
		return v;
	}
	
	public Vector<Bean> sortLatest2(){
		//�������̷� �����͸� ����
		Vector<Bean> v = new Vector<>();
		
		//�����ͺ��̽��� ����ó���� �ݵ�� �ؾߵ�.
		try {
			//Ŀ�ؼ� ����
			Connection conn = DatabaseUtil.getConnection();	//�����ͺ��̽���ƿ�� ��Ŀ�ؼǸ޼ҵ�
			//���� �غ�
			String sql = "SELECT * FROM product order by id desc";
			//������ ��������ִ� ��ü ����
			PreparedStatement pstmt = conn.prepareStatement(sql);
			//?�� ���� ����
			//������ ���� ��Ų ����� �����ؼ� �޾���(mysql���̺� �˻��� ����� �ڹٰ�ü�� ����)
			rs = pstmt.executeQuery();
			//�ݺ����� ����ؼ� rs�� ����� �����͸� �����س���
			while(rs.next()) {	//����� ������ ��ŭ���� ����
				Bean bean = new Bean();
				bean.setName(rs.getString(1));
				bean.setCategory(rs.getString(2));
				bean.setPrice(rs.getInt(3));
				bean.setContent(rs.getString(4));
				bean.setBbsdate(rs.getString(5));
				bean.setBbsavailable(rs.getInt(6));
				bean.setId(rs.getInt(7));
				bean.setAddress(rs.getString(8));
				bean.setUser_id(rs.getString(9));
				bean.setViews(rs.getInt(10));
				v.add(bean);//0�������� ������� �����Ͱ� ����
			}
			//�ݾ��ֱ�
			conn.close();
		}catch (Exception e) {
			System.out.println("����");
		}
		return v;
	}
	
	public Vector<Bean> sortLatest4(){
		//�������̷� �����͸� ����
		Vector<Bean> v = new Vector<>();
		
		//�����ͺ��̽��� ����ó���� �ݵ�� �ؾߵ�.
		try {
			//Ŀ�ؼ� ����
			Connection conn = DatabaseUtil.getConnection();	//�����ͺ��̽���ƿ�� ��Ŀ�ؼǸ޼ҵ�
			//���� �غ�
			String sql = "SELECT * FROM product2 order by id desc";
			//������ ��������ִ� ��ü ����
			PreparedStatement pstmt = conn.prepareStatement(sql);
			//?�� ���� ����
			//������ ���� ��Ų ����� �����ؼ� �޾���(mysql���̺� �˻��� ����� �ڹٰ�ü�� ����)
			rs = pstmt.executeQuery();
			//�ݺ����� ����ؼ� rs�� ����� �����͸� �����س���
			while(rs.next()) {	//����� ������ ��ŭ���� ����
				Bean bean = new Bean();
				bean.setName(rs.getString(1));
				bean.setCategory(rs.getString(2));
				bean.setPrice(rs.getInt(3));
				bean.setContent(rs.getString(4));
				bean.setBbsdate(rs.getString(5));
				bean.setBbsavailable(rs.getInt(6));
				bean.setId(rs.getInt(7));
				bean.setAddress(rs.getString(8));
				bean.setUser_id(rs.getString(9));
				bean.setViews(rs.getInt(10));
				v.add(bean);//0�������� ������� �����Ͱ� ����
			}
			//�ݾ��ֱ�
			conn.close();
		}catch (Exception e) {
			System.out.println("����");
		}
		return v;
	}
	
	public Vector<Bean> categorySelectProduct(String category){
		//�������̷� �����͸� ����
		Vector<Bean> v = new Vector<>();
		
		//�����ͺ��̽��� ����ó���� �ݵ�� �ؾߵ�.
		try {
			//Ŀ�ؼ� ����
			Connection conn = DatabaseUtil.getConnection();	//�����ͺ��̽���ƿ�� ��Ŀ�ؼǸ޼ҵ�
			//���� �غ�
			String sql = "select * from product where category=?";
			//������ ��������ִ� ��ü ����
			PreparedStatement pstmt = conn.prepareStatement(sql);
			//?�� ���� ����
			pstmt.setString(1, category);
			//������ ���� ��Ų ����� �����ؼ� �޾���(mysql���̺� �˻��� ����� �ڹٰ�ü�� ����)
			rs = pstmt.executeQuery();
			//�ݺ����� ����ؼ� rs�� ����� �����͸� �����س���
			while(rs.next()) {	//����� ������ ��ŭ���� ����
				Bean bean = new Bean();
				bean.setName(rs.getString(1));
				bean.setCategory(rs.getString(2));
				bean.setPrice(rs.getInt(3));
				bean.setContent(rs.getString(4));
				bean.setBbsdate(rs.getString(5));
				bean.setBbsavailable(rs.getInt(6));
				bean.setId(rs.getInt(7));
				bean.setAddress(rs.getString(8));
				bean.setUser_id(rs.getString(9));
				bean.setViews(rs.getInt(10));
				v.add(bean);//0�������� ������� �����Ͱ� ����
			}
			//�ݾ��ֱ�
			conn.close();
		}catch (Exception e) {
			System.out.println("����");
		}
		return v;
	}
	
	public Vector<Bean> categorySelectProduct2(String category){
		//�������̷� �����͸� ����
		Vector<Bean> v = new Vector<>();
		
		//�����ͺ��̽��� ����ó���� �ݵ�� �ؾߵ�.
		try {
			//Ŀ�ؼ� ����
			Connection conn = DatabaseUtil.getConnection();	//�����ͺ��̽���ƿ�� ��Ŀ�ؼǸ޼ҵ�
			//���� �غ�
			String sql = "select * from product2 where category=?";
			//������ ��������ִ� ��ü ����
			PreparedStatement pstmt = conn.prepareStatement(sql);
			//?�� ���� ����
			pstmt.setString(1, category);
			//������ ���� ��Ų ����� �����ؼ� �޾���(mysql���̺� �˻��� ����� �ڹٰ�ü�� ����)
			rs = pstmt.executeQuery();
			//�ݺ����� ����ؼ� rs�� ����� �����͸� �����س���
			while(rs.next()) {	//����� ������ ��ŭ���� ����
				Bean bean = new Bean();
				bean.setName(rs.getString(1));
				bean.setCategory(rs.getString(2));
				bean.setPrice(rs.getInt(3));
				bean.setContent(rs.getString(4));
				bean.setBbsdate(rs.getString(5));
				bean.setBbsavailable(rs.getInt(6));
				bean.setId(rs.getInt(7));
				bean.setAddress(rs.getString(8));
				bean.setUser_id(rs.getString(9));
				bean.setViews(rs.getInt(10));
				v.add(bean);//0�������� ������� �����Ͱ� ����
			}
			//�ݾ��ֱ�
			conn.close();
		}catch (Exception e) {
			System.out.println("����");
		}
		return v;
	}
	
	//��ǰ �˻� �޼ҵ�
	public Vector<Bean> search(String name){
		//�������̷� �����͸� ����
		Vector<Bean> v = new Vector<>();
		
		//�����ͺ��̽��� ����ó���� �ݵ�� �ؾߵ�.
		try {
			//Ŀ�ؼ� ����
			Connection conn = DatabaseUtil.getConnection();	//�����ͺ��̽���ƿ�� ��Ŀ�ؼǸ޼ҵ�
			//���� �غ�
			String sql = "SELECT * FROM product WHERE name LIKE ?";
			//������ ��������ִ� ��ü ����
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			//?�� ���� ����
			pstmt.setString(1, "%" + name + "%");
			//pstmt.setString(1, name);
			//������ ���� ��Ų ����� �����ؼ� �޾���(mysql���̺� �˻��� ����� �ڹٰ�ü�� ����)
			rs = pstmt.executeQuery();
			//�ݺ����� ����ؼ� rs�� ����� �����͸� �����س���
			while(rs.next()) {	//����� ������ ��ŭ���� ����
				Bean bean = new Bean();
				bean.setName(rs.getString(1));
				bean.setCategory(rs.getString(2));
				bean.setPrice(rs.getInt(3));
				bean.setContent(rs.getString(4));
				bean.setBbsdate(rs.getString(5));
				bean.setBbsavailable(rs.getInt(6));
				bean.setId(rs.getInt(7));
				bean.setAddress(rs.getString(8));
				bean.setUser_id(rs.getString(9));
				bean.setViews(rs.getInt(10));
				v.add(bean);//0�������� ������� �����Ͱ� ����
			}
			//�ݾ��ֱ�
			conn.close();
		}catch (Exception e) {
			System.out.println("����");
		}
		return v;
	}
	
	//��ǰ �˻� �޼ҵ�
		public Vector<Bean> search2(String name){
			//�������̷� �����͸� ����
			Vector<Bean> v = new Vector<>();
			
			//�����ͺ��̽��� ����ó���� �ݵ�� �ؾߵ�.
			try {
				//Ŀ�ؼ� ����
				Connection conn = DatabaseUtil.getConnection();	//�����ͺ��̽���ƿ�� ��Ŀ�ؼǸ޼ҵ�
				//���� �غ�
				String sql = "SELECT * FROM product2 WHERE name LIKE ?";
				//������ ��������ִ� ��ü ����
				
				PreparedStatement pstmt = conn.prepareStatement(sql);
				//?�� ���� ����
				pstmt.setString(1, "%" + name + "%");
				//pstmt.setString(1, name);
				//������ ���� ��Ų ����� �����ؼ� �޾���(mysql���̺� �˻��� ����� �ڹٰ�ü�� ����)
				rs = pstmt.executeQuery();
				//�ݺ����� ����ؼ� rs�� ����� �����͸� �����س���
				while(rs.next()) {	//����� ������ ��ŭ���� ����
					Bean bean = new Bean();
					bean.setName(rs.getString(1));
					bean.setCategory(rs.getString(2));
					bean.setPrice(rs.getInt(3));
					bean.setContent(rs.getString(4));
					bean.setBbsdate(rs.getString(5));
					bean.setBbsavailable(rs.getInt(6));
					bean.setId(rs.getInt(7));
					bean.setAddress(rs.getString(8));
					bean.setUser_id(rs.getString(9));
					bean.setViews(rs.getInt(10));
					v.add(bean);//0�������� ������� �����Ͱ� ����
				}
				//�ݾ��ֱ�
				conn.close();
			}catch (Exception e) {
				System.out.println("����");
			}
			return v;
		}
	
	public Bean getPro(int id) {
		String SQL = "SELECT * FROM product WHERE id = ?";
		try {
			Connection conn = DatabaseUtil.getConnection();	
			
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1,id);
			
			rs = pstmt.executeQuery();	
			if(rs.next()) {
				Bean bean = new Bean();
				bean.setName(rs.getString(1));
				bean.setCategory(rs.getString(2));
				bean.setPrice(rs.getInt(3));
				bean.setContent(rs.getString(4));
				bean.setBbsdate(rs.getString(5));
				bean.setBbsavailable(rs.getInt(6));
				bean.setId(rs.getInt(7));
				bean.setAddress(rs.getString(8));
				bean.setUser_id(rs.getString(9));
				bean.setViews(rs.getInt(10));
				return bean;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	public Bean getPro2(int id) {
		String SQL = "SELECT * FROM product2 WHERE id = ?";
		try {
			Connection conn = DatabaseUtil.getConnection();	
			
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1,id);
			
			rs = pstmt.executeQuery();	
			if(rs.next()) {
				Bean bean = new Bean();
				bean.setName(rs.getString(1));
				bean.setCategory(rs.getString(2));
				bean.setPrice(rs.getInt(3));
				bean.setContent(rs.getString(4));
				bean.setBbsdate(rs.getString(5));
				bean.setBbsavailable(rs.getInt(6));
				bean.setId(rs.getInt(7));
				bean.setAddress(rs.getString(8));
				bean.setUser_id(rs.getString(9));
				bean.setViews(rs.getInt(10));
				return bean;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	public Bean getProduct(int id) {
		try {
			String SQL = "SELECT * FROM product WHERE id = ?";
			Connection conn = DatabaseUtil.getConnection();	
			
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1,id);
			
			rs = pstmt.executeQuery();	
			if(rs.next()) {
				Bean bean = new Bean();
				bean.setName(rs.getString(1));
				bean.setCategory(rs.getString(2));
				bean.setPrice(rs.getInt(3));
				bean.setContent(rs.getString(4));
				bean.setBbsdate(rs.getString(5));
				bean.setBbsavailable(rs.getInt(6));
				bean.setId(rs.getInt(7));
				bean.setAddress(rs.getString(8));
				bean.setUser_id(rs.getString(9));
				bean.setViews(rs.getInt(10));
				return bean;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	public Bean getProduct2(int id) {
		try {
			String SQL = "SELECT * FROM product2 WHERE id = ?";
			Connection conn = DatabaseUtil.getConnection();	
			
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1,id);
			
			rs = pstmt.executeQuery();	
			if(rs.next()) {
				Bean bean = new Bean();
				bean.setName(rs.getString(1));
				bean.setCategory(rs.getString(2));
				bean.setPrice(rs.getInt(3));
				bean.setContent(rs.getString(4));
				bean.setBbsdate(rs.getString(5));
				bean.setBbsavailable(rs.getInt(6));
				bean.setId(rs.getInt(7));
				bean.setAddress(rs.getString(8));
				bean.setUser_id(rs.getString(9));
				bean.setViews(rs.getInt(10));
				return bean;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	//��ǰ �����ð� �ʰ� �� ���̵� �޾Ƽ� �ش��ǰ ����
	public int delete(int id) {
		String SQL = "UPDATE PRODUCT SET bbsAvailable = 0 WHERE id = ?";
		Connection conn = DatabaseUtil.getConnection();
		try {		
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, id);
			return pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	//��ǰ �����ð� �ʰ� �� ���̵� �޾Ƽ� �ش��ǰ ����
		public int delete2(int id) {
			String SQL = "UPDATE PRODUCT2 SET bbsAvailable = 0 WHERE id = ?";
			Connection conn = DatabaseUtil.getConnection();
			try {		
				PreparedStatement pstmt = conn.prepareStatement(SQL);
				pstmt.setInt(1, id);
				return pstmt.executeUpdate();
			}catch(Exception e) {
				e.printStackTrace();
			}
			return -1;
		}
	
	//������ ������ ������Ű�� �޼ҵ�
	public void incleaseViews(int id) {
		Connection conn = DatabaseUtil.getConnection();
		try {
			String sql = "update product set views = views + 1 where id = ?;";
			//�������� ��ü ����
			PreparedStatement pstmt = conn.prepareStatement(sql);
			//?�� ���� ����
			pstmt.setInt(1, id);
			//��������
			pstmt.executeUpdate();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void incleaseViews2(int id) {
		Connection conn = DatabaseUtil.getConnection();
		try {
			String sql = "update product2 set views = views + 1 where id = ?;";
			//�������� ��ü ����
			PreparedStatement pstmt = conn.prepareStatement(sql);
			//?�� ���� ����
			pstmt.setInt(1, id);
			//��������
			pstmt.executeUpdate();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//��������ǰ �з����� �޼ҵ�
	public int finishInsert(String user_id, String buy_id, int product_id, int price) {
		Connection conn = DatabaseUtil.getConnection();
		String SQL = "INSERT INTO finish VALUES(?,?,?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, user_id);
			pstmt.setString(2, buy_id);
			pstmt.setInt(3, product_id);
			pstmt.setInt(4, price);
			return pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	//��������ǰ �з����� �޼ҵ�
	public int finishInsert2(String user_id, String buy_id, int product_id, int price) {
		Connection conn = DatabaseUtil.getConnection();
		String SQL = "INSERT INTO finish2 VALUES(?,?,?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, user_id);
			pstmt.setString(2, buy_id);
			pstmt.setInt(3, product_id);
			pstmt.setInt(4, price);
			return pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public Vector<ProductBean> finishallselect(){
		Vector<ProductBean> v = new Vector<>();	
		//�����ͺ��̽��� ����ó���� �ݵ�� �ؾߵ�.
		try {
			//Ŀ�ؼ� ����
			Connection conn = DatabaseUtil.getConnection();
			//���� �غ�
			String sql = "select * from finish";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			//�ݺ����� ����ؼ� rs�� ����� �����͸� �����س���
			while(rs.next()) {	//����� ������ ��ŭ���� ����
				ProductBean pbean = new ProductBean();
				pbean.setUser_id(rs.getString(1));
				pbean.setBuy_id(rs.getString(2));
				pbean.setProduct_id(rs.getInt(3));
				pbean.setPrice(rs.getInt(4));
				v.add(pbean);//0�������� ������� �����Ͱ� ����
			}
			//�ݾ��ֱ�
			conn.close();
		}catch (Exception e) {
			System.out.println("����");
		}		
		return v;
	}
	
	public Vector<ProductBean> finishallselect2(){
		Vector<ProductBean> v = new Vector<>();	
		//�����ͺ��̽��� ����ó���� �ݵ�� �ؾߵ�.
		try {
			//Ŀ�ؼ� ����
			Connection conn = DatabaseUtil.getConnection();
			//���� �غ�
			String sql = "select * from finish2";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			//�ݺ����� ����ؼ� rs�� ����� �����͸� �����س���
			while(rs.next()) {	//����� ������ ��ŭ���� ����
				ProductBean pbean = new ProductBean();
				pbean.setUser_id(rs.getString(1));
				pbean.setBuy_id(rs.getString(2));
				pbean.setProduct_id(rs.getInt(3));
				pbean.setPrice(rs.getInt(4));
				v.add(pbean);//0�������� ������� �����Ͱ� ����
			}
			//�ݾ��ֱ�
			conn.close();
		}catch (Exception e) {
			System.out.println("����");
		}		
		return v;
	}
}