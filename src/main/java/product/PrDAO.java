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
	
	//������ ��ǰ��ȣ �������� �޼ҵ�
	public int getNext() {
		Connection conn = DatabaseUtil.getConnection();
		String SQL = "SELECT pnum FROM product ORDER BY pnum DESC";
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
	public int insertProduct(String name, String category, int price, String content,Date date,String path, int total) {
		Connection conn = DatabaseUtil.getConnection();
		String SQL = "INSERT INTO product VALUES(?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, getNext());
			pstmt.setString(2, name);
			pstmt.setString(3, category);
			pstmt.setInt(4, price);
			pstmt.setString(5, content);
			pstmt.setDate(6, date);
			pstmt.setString(7,path);
			pstmt.setInt(8,0);
			pstmt.setInt(9,total);
			return pstmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	//��� ��ǰ ���� �������� �޼ҵ�
	public ArrayList<ProductBean> getAllProduct(){
		Connection conn = DatabaseUtil.getConnection();
		String SQL = "SELECT * FROM product ORDER BY pnum DESC";
		ArrayList<ProductBean> list = new ArrayList<ProductBean>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ProductBean bean = new ProductBean();
				bean.setPnum(rs.getInt(1));
				bean.setPname(rs.getString(2));
				bean.setPcategory(rs.getString(3));
				bean.setPprice(rs.getInt(4));
				bean.setPcontent(rs.getString(5));
				bean.setPday(rs.getDate(6));
				bean.setPaddress(rs.getString(7));
				bean.setViews(rs.getInt(8));
				list.add(bean);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//��ǰ�̹��� ���� �޼ҵ�
	public void updateProductImg(String user_id, String path) {
		Connection conn = DatabaseUtil.getConnection();
		try {
			String sql = "update product set address=? where user_id=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, path);
			pstmt.setString(2, user_id);
			pstmt.executeUpdate();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//��ǰ ī�װ� �� �ֽ� �� ����
	public Vector<ProductBean> sortLatest(String category){
		Vector<ProductBean> v = new Vector<>();
		
		try {
			Connection conn = DatabaseUtil.getConnection();
			String sql = "SELECT * FROM product where pcategory = ? order by pnum desc";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, category);
			rs = pstmt.executeQuery();
			while(rs.next()) {	
				ProductBean bean = new ProductBean();
				bean.setPnum(rs.getInt(1));
				bean.setPname(rs.getString(2));
				bean.setPcategory(rs.getString(3));
				bean.setPprice(rs.getInt(4));
				bean.setPcontent(rs.getString(5));
				bean.setPday(rs.getDate(6));
				bean.setPaddress(rs.getString(7));
				bean.setViews(rs.getInt(8));
				v.add(bean);
			}
			conn.close();
		}catch (Exception e) {
			System.out.println("����");
		}
		return v;
	}
	
	//��ǰ ī�װ� �� �α� �� ����
	public Vector<ProductBean> sortPopularity(String category){
		Vector<ProductBean> v = new Vector<>();
		
		try {
			Connection conn = DatabaseUtil.getConnection();	
			String sql = "SELECT * FROM product where pcategory = ? order by views desc";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, category);
			rs = pstmt.executeQuery();
			while(rs.next()) {	
				ProductBean bean = new ProductBean();
				bean.setPnum(rs.getInt(1));
				bean.setPname(rs.getString(2));
				bean.setPcategory(rs.getString(3));
				bean.setPprice(rs.getInt(4));
				bean.setPcontent(rs.getString(5));
				bean.setPday(rs.getDate(6));
				bean.setPaddress(rs.getString(7));
				bean.setViews(rs.getInt(8));
				v.add(bean);
			}
			conn.close();
		}catch (Exception e) {
			System.out.println("����");
		}
		return v;
	}
	
	//��ǰ �α� �� ����(ī�װ� ����X)
	public Vector<ProductBean> sortPopularity2(){
		Vector<ProductBean> v = new Vector<>();
		
		try {
			Connection conn = DatabaseUtil.getConnection();	
			String sql = "SELECT * FROM product order by views desc";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {	
				ProductBean bean = new ProductBean();
				bean.setPnum(rs.getInt(1));
				bean.setPname(rs.getString(2));
				bean.setPcategory(rs.getString(3));
				bean.setPprice(rs.getInt(4));
				bean.setPcontent(rs.getString(5));
				bean.setPday(rs.getDate(6));
				bean.setPaddress(rs.getString(7));
				bean.setViews(rs.getInt(8));
				v.add(bean);
			}
			conn.close();
		}catch (Exception e) {
			System.out.println("����");
		}
		return v;
	}
	
	//��� ��ǰ �ֽż� ����(ī�װ�X)
	public Vector<ProductBean> sortLatest2(){
		//�������̷� �����͸� ����
		Vector<ProductBean> v = new Vector<>();
		
		//�����ͺ��̽��� ����ó���� �ݵ�� �ؾߵ�.
		try {
			//Ŀ�ؼ� ����
			Connection conn = DatabaseUtil.getConnection();	//�����ͺ��̽���ƿ�� ��Ŀ�ؼǸ޼ҵ�
			//���� �غ�
			String sql = "SELECT * FROM product order by pnum desc";
			//������ ��������ִ� ��ü ����
			PreparedStatement pstmt = conn.prepareStatement(sql);
			//?�� ���� ����
			//������ ���� ��Ų ����� �����ؼ� �޾���(mysql���̺� �˻��� ����� �ڹٰ�ü�� ����)
			rs = pstmt.executeQuery();
			//�ݺ����� ����ؼ� rs�� ����� �����͸� �����س���
			while(rs.next()) {	//����� ������ ��ŭ���� ����
				ProductBean bean = new ProductBean();
				bean.setPnum(rs.getInt(1));
				bean.setPname(rs.getString(2));
				bean.setPcategory(rs.getString(3));
				bean.setPprice(rs.getInt(4));
				bean.setPcontent(rs.getString(5));
				bean.setPday(rs.getDate(6));
				bean.setPaddress(rs.getString(7));
				bean.setViews(rs.getInt(8));
				v.add(bean);//0�������� ������� �����Ͱ� ����
			}
			//�ݾ��ֱ�
			conn.close();
		}catch (Exception e) {
			System.out.println("����");
		}
		return v;
	}
	
	//ī�װ� �� ��ǰ �˻� �޼ҵ�
	public Vector<ProductBean> categorySelectProduct(String category){
		Vector<ProductBean> v = new Vector<>();
		try {
			Connection conn = DatabaseUtil.getConnection();
			String sql = "select * from product where pcategory=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, category);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ProductBean bean = new ProductBean();
				bean.setPnum(rs.getInt(1));
				bean.setPname(rs.getString(2));
				bean.setPcategory(rs.getString(3));
				bean.setPprice(rs.getInt(4));
				bean.setPcontent(rs.getString(5));
				bean.setPday(rs.getDate(6));
				bean.setPaddress(rs.getString(7));
				bean.setViews(rs.getInt(8));
				v.add(bean);
			}
			conn.close();
		}catch (Exception e) {
			System.out.println("����");
		}
		return v;
	}
	
	//��ǰ �˻� �޼ҵ�
	public Vector<ProductBean> searchProduct(String name){
		Vector<ProductBean> v = new Vector<>();

		try {
			Connection conn = DatabaseUtil.getConnection();	

			String sql = "SELECT * FROM product WHERE pname LIKE ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + name + "%");
			rs = pstmt.executeQuery();

			while(rs.next()) {	
				ProductBean bean = new ProductBean();
				bean.setPnum(rs.getInt(1));
				bean.setPname(rs.getString(2));
				bean.setPcategory(rs.getString(3));
				bean.setPprice(rs.getInt(4));
				bean.setPcontent(rs.getString(5));
				bean.setPday(rs.getDate(6));
				bean.setPaddress(rs.getString(7));
				bean.setViews(rs.getInt(8));
				v.add(bean);
			}
			conn.close();
		}catch (Exception e) {
			System.out.println("����");
		}
		return v;
	}

	//��ǰ ���̵�� �ش� ��ǰ ��ȯ �޼ҵ�
	public ProductBean retrieveProduct(int id) {
		try {
			String SQL = "SELECT * FROM product WHERE pnum = ?";
			Connection conn = DatabaseUtil.getConnection();	
			
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1,id);
			
			rs = pstmt.executeQuery();	
			if(rs.next()) {
				ProductBean bean = new ProductBean();
				bean.setPnum(rs.getInt(1));
				bean.setPname(rs.getString(2));
				bean.setPcategory(rs.getString(3));
				bean.setPprice(rs.getInt(4));
				bean.setPcontent(rs.getString(5));
				bean.setPday(rs.getDate(6));
				bean.setPaddress(rs.getString(7));
				bean.setViews(rs.getInt(8));
				return bean;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	//��ǰ �����ð� �ʰ� �� ���̵� �޾Ƽ� �ش��ǰ ����
	public int deleteProduct(int id) {
		String SQL = "delete from product WHERE pnum = ?";
		Connection conn = DatabaseUtil.getConnection();
		try {		
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			return 1;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	//���� �� ���ż� ������Ű�� �޼ҵ�
	public void incleaseViews(int id) {
		Connection conn = DatabaseUtil.getConnection();
		try {
			String sql = "update product set views = views + 1 where p = ?;";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}