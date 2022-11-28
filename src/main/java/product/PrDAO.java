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

	public ArrayList<ProductBean> getList(int pageNumber){
		Connection conn = DatabaseUtil.getConnection();
		String SQL = "SELECT * FROM product WHERE pnum < ? ORDER BY id DESC LIMIT 6";
		ArrayList<ProductBean> list = new ArrayList<ProductBean>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1,getNext() - (pageNumber - 1) * 6);
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
	
	public ArrayList<ProductBean> getList2(){
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
	
	public boolean nextPage(int pageNumber) {
		Connection conn = DatabaseUtil.getConnection();
		String SQL = "SELECT * FROM product WHERE pnum < ?";
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
	
	//��ǰ ī�װ� �� �ֽ� �� ����
	public Vector<ProductBean> sortLatest(String category){
		//�������̷� �����͸� ����
		Vector<ProductBean> v = new Vector<>();
		
		//�����ͺ��̽��� ����ó���� �ݵ�� �ؾߵ�.
		try {
			//Ŀ�ؼ� ����
			Connection conn = DatabaseUtil.getConnection();	//�����ͺ��̽���ƿ�� ��Ŀ�ؼǸ޼ҵ�
			//���� �غ�
			String sql = "SELECT * FROM product where pcategory = ? order by pnum desc";
			//������ ��������ִ� ��ü ����
			PreparedStatement pstmt = conn.prepareStatement(sql);
			//?�� ���� ����
			pstmt.setString(1, category);
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
	
	
	//��ǰ ī�װ� �� �α� �� ����
	public Vector<ProductBean> sortPopularity(String category){
		//�������̷� �����͸� ����
		Vector<ProductBean> v = new Vector<>();
		
		//�����ͺ��̽��� ����ó���� �ݵ�� �ؾߵ�.
		try {
			//Ŀ�ؼ� ����
			Connection conn = DatabaseUtil.getConnection();	//�����ͺ��̽���ƿ�� ��Ŀ�ؼǸ޼ҵ�
			//���� �غ�
			String sql = "SELECT * FROM product where pcategory = ? order by views desc";
			//������ ��������ִ� ��ü ����
			PreparedStatement pstmt = conn.prepareStatement(sql);
			//?�� ���� ����
			pstmt.setString(1, category);
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
	
	//��ǰ �α� �� ����(ī�װ� ����X)
	public Vector<ProductBean> sortPopularity2(){
		//�������̷� �����͸� ����
		Vector<ProductBean> v = new Vector<>();
		
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
	
	public Vector<ProductBean> categorySelectProduct(String category){
		//�������̷� �����͸� ����
		Vector<ProductBean> v = new Vector<>();
		
		//�����ͺ��̽��� ����ó���� �ݵ�� �ؾߵ�.
		try {
			//Ŀ�ؼ� ����
			Connection conn = DatabaseUtil.getConnection();	//�����ͺ��̽���ƿ�� ��Ŀ�ؼǸ޼ҵ�
			//���� �غ�
			String sql = "select * from product where pcategory=?";
			//������ ��������ִ� ��ü ����
			PreparedStatement pstmt = conn.prepareStatement(sql);
			//?�� ���� ����
			pstmt.setString(1, category);
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
	
	//��ǰ �˻� �޼ҵ�
	public Vector<ProductBean> search(String name){
		//�������̷� �����͸� ����
		Vector<ProductBean> v = new Vector<>();
		
		//�����ͺ��̽��� ����ó���� �ݵ�� �ؾߵ�.
		try {
			//Ŀ�ؼ� ����
			Connection conn = DatabaseUtil.getConnection();	//�����ͺ��̽���ƿ�� ��Ŀ�ؼǸ޼ҵ�
			//���� �غ�
			String sql = "SELECT * FROM product WHERE pname LIKE ?";
			//������ ��������ִ� ��ü ����
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			//?�� ���� ����
			pstmt.setString(1, "%" + name + "%");
			//pstmt.setString(1, name);
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
	
	
	public ProductBean getPro(int id) {
		String SQL = "SELECT * FROM product WHERE pnum = ?";
		try {
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
	
	public ProductBean getProduct(int id) {
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
	
	//������ ������ ������Ű�� �޼ҵ�
	public void incleaseViews(int id) {
		Connection conn = DatabaseUtil.getConnection();
		try {
			String sql = "update product set views = views + 1 where p = ?;";
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
	
	/*
	 * public Vector<ProductBean> finishallselect(){ Vector<ProductBean> v = new
	 * Vector<>(); //�����ͺ��̽��� ����ó���� �ݵ�� �ؾߵ�. try { //Ŀ�ؼ� ���� Connection conn =
	 * DatabaseUtil.getConnection(); //���� �غ� String sql = "select * from finish";
	 * PreparedStatement pstmt = conn.prepareStatement(sql); rs =
	 * pstmt.executeQuery(); //�ݺ����� ����ؼ� rs�� ����� �����͸� �����س��� while(rs.next()) { //�����
	 * ������ ��ŭ���� ���� ProductBean pbean = new ProductBean();
	 * pbean.setUser_id(rs.getString(1)); pbean.setBuy_id(rs.getString(2));
	 * pbean.setProduct_id(rs.getInt(3)); pbean.setPrice(rs.getInt(4));
	 * v.add(pbean);//0�������� ������� �����Ͱ� ���� } //�ݾ��ֱ� conn.close(); }catch (Exception e)
	 * { System.out.println("����"); } return v; }
	 */
}