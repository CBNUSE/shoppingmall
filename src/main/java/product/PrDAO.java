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
	
	//상품이미지 수정
	public void updateProductImg(String user_id, String path) {
		Connection conn = DatabaseUtil.getConnection();
		try {
			//쿼리준비,,,,만약 수정사항을 더 더하고싶다면 콤마콤마로 늘려가면됨
			String sql = "update product set address=? where user_id=?";
			//쿼리실행 객체 선언
			PreparedStatement pstmt = conn.prepareStatement(sql);
			//?에 값을 맵핑
			pstmt.setString(1, path);
			pstmt.setString(2, user_id);
			//쿼리실행
			pstmt.executeUpdate();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Vector<Bean> sortLatest(String category){
		//가변길이로 데이터를 저장
		Vector<Bean> v = new Vector<>();
		
		//데이터베이스는 예외처리를 반드시 해야됨.
		try {
			//커넥션 연결
			Connection conn = DatabaseUtil.getConnection();	//데이터베이스유틸의 겟커넥션메소드
			//쿼리 준비
			String sql = "SELECT * FROM product where category = ? order by id desc";
			//쿼리를 실행시켜주는 객체 선언
			PreparedStatement pstmt = conn.prepareStatement(sql);
			//?에 값을 맵핑
			pstmt.setString(1, category);
			//쿼리를 실행 시킨 결과를 리턴해서 받아줌(mysql테이블에 검색된 결과를 자바객체에 저장)
			rs = pstmt.executeQuery();
			//반복문을 사용해서 rs에 저장된 데이터를 추출해놓음
			while(rs.next()) {	//저장된 데이터 만큼까지 돌음
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
				v.add(bean);//0번지부터 순서대로 데이터가 저장
			}
			//닫아주기
			conn.close();
		}catch (Exception e) {
			System.out.println("오류");
		}
		return v;
	}
	
	public Vector<Bean> sortLatest3(String category){
		//가변길이로 데이터를 저장
		Vector<Bean> v = new Vector<>();
		
		//데이터베이스는 예외처리를 반드시 해야됨.
		try {
			//커넥션 연결
			Connection conn = DatabaseUtil.getConnection();	//데이터베이스유틸의 겟커넥션메소드
			//쿼리 준비
			String sql = "SELECT * FROM product2 where category = ? order by id desc";
			//쿼리를 실행시켜주는 객체 선언
			PreparedStatement pstmt = conn.prepareStatement(sql);
			//?에 값을 맵핑
			pstmt.setString(1, category);
			//쿼리를 실행 시킨 결과를 리턴해서 받아줌(mysql테이블에 검색된 결과를 자바객체에 저장)
			rs = pstmt.executeQuery();
			//반복문을 사용해서 rs에 저장된 데이터를 추출해놓음
			while(rs.next()) {	//저장된 데이터 만큼까지 돌음
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
				v.add(bean);//0번지부터 순서대로 데이터가 저장
			}
			//닫아주기
			conn.close();
		}catch (Exception e) {
			System.out.println("오류");
		}
		return v;
	}
	
	public Vector<Bean> sortPopularity(String category){
		//가변길이로 데이터를 저장
		Vector<Bean> v = new Vector<>();
		
		//데이터베이스는 예외처리를 반드시 해야됨.
		try {
			//커넥션 연결
			Connection conn = DatabaseUtil.getConnection();	//데이터베이스유틸의 겟커넥션메소드
			//쿼리 준비
			String sql = "SELECT * FROM product where category = ? order by views desc";
			//쿼리를 실행시켜주는 객체 선언
			PreparedStatement pstmt = conn.prepareStatement(sql);
			//?에 값을 맵핑
			pstmt.setString(1, category);
			//쿼리를 실행 시킨 결과를 리턴해서 받아줌(mysql테이블에 검색된 결과를 자바객체에 저장)
			rs = pstmt.executeQuery();
			//반복문을 사용해서 rs에 저장된 데이터를 추출해놓음
			while(rs.next()) {	//저장된 데이터 만큼까지 돌음
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
				v.add(bean);//0번지부터 순서대로 데이터가 저장
			}
			//닫아주기
			conn.close();
		}catch (Exception e) {
			System.out.println("오류");
		}
		return v;
	}
	
	public Vector<Bean> sortPopularity2(){
		//가변길이로 데이터를 저장
		Vector<Bean> v = new Vector<>();
		
		//데이터베이스는 예외처리를 반드시 해야됨.
		try {
			//커넥션 연결
			Connection conn = DatabaseUtil.getConnection();	//데이터베이스유틸의 겟커넥션메소드
			//쿼리 준비
			String sql = "SELECT * FROM product order by views desc";
			//쿼리를 실행시켜주는 객체 선언
			PreparedStatement pstmt = conn.prepareStatement(sql);
			//?에 값을 맵핑
			//쿼리를 실행 시킨 결과를 리턴해서 받아줌(mysql테이블에 검색된 결과를 자바객체에 저장)
			rs = pstmt.executeQuery();
			//반복문을 사용해서 rs에 저장된 데이터를 추출해놓음
			while(rs.next()) {	//저장된 데이터 만큼까지 돌음
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
				v.add(bean);//0번지부터 순서대로 데이터가 저장
			}
			//닫아주기
			conn.close();
		}catch (Exception e) {
			System.out.println("오류");
		}
		return v;
	}
	
	public Vector<Bean> sortPopularity3(String category){
		//가변길이로 데이터를 저장
		Vector<Bean> v = new Vector<>();
		
		//데이터베이스는 예외처리를 반드시 해야됨.
		try {
			//커넥션 연결
			Connection conn = DatabaseUtil.getConnection();	//데이터베이스유틸의 겟커넥션메소드
			//쿼리 준비
			String sql = "SELECT * FROM product2 where category = ? order by views desc";
			//쿼리를 실행시켜주는 객체 선언
			PreparedStatement pstmt = conn.prepareStatement(sql);
			//?에 값을 맵핑
			pstmt.setString(1, category);
			//쿼리를 실행 시킨 결과를 리턴해서 받아줌(mysql테이블에 검색된 결과를 자바객체에 저장)
			rs = pstmt.executeQuery();
			//반복문을 사용해서 rs에 저장된 데이터를 추출해놓음
			while(rs.next()) {	//저장된 데이터 만큼까지 돌음
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
				v.add(bean);//0번지부터 순서대로 데이터가 저장
			}
			//닫아주기
			conn.close();
		}catch (Exception e) {
			System.out.println("오류");
		}
		return v;
	}
	
	public Vector<Bean> sortPopularity4(){
		//가변길이로 데이터를 저장
		Vector<Bean> v = new Vector<>();
		
		//데이터베이스는 예외처리를 반드시 해야됨.
		try {
			//커넥션 연결
			Connection conn = DatabaseUtil.getConnection();	//데이터베이스유틸의 겟커넥션메소드
			//쿼리 준비
			String sql = "SELECT * FROM product2 order by views desc";
			//쿼리를 실행시켜주는 객체 선언
			PreparedStatement pstmt = conn.prepareStatement(sql);
			//?에 값을 맵핑
			//쿼리를 실행 시킨 결과를 리턴해서 받아줌(mysql테이블에 검색된 결과를 자바객체에 저장)
			rs = pstmt.executeQuery();
			//반복문을 사용해서 rs에 저장된 데이터를 추출해놓음
			while(rs.next()) {	//저장된 데이터 만큼까지 돌음
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
				v.add(bean);//0번지부터 순서대로 데이터가 저장
			}
			//닫아주기
			conn.close();
		}catch (Exception e) {
			System.out.println("오류");
		}
		return v;
	}
	
	public Vector<Bean> sortLatest2(){
		//가변길이로 데이터를 저장
		Vector<Bean> v = new Vector<>();
		
		//데이터베이스는 예외처리를 반드시 해야됨.
		try {
			//커넥션 연결
			Connection conn = DatabaseUtil.getConnection();	//데이터베이스유틸의 겟커넥션메소드
			//쿼리 준비
			String sql = "SELECT * FROM product order by id desc";
			//쿼리를 실행시켜주는 객체 선언
			PreparedStatement pstmt = conn.prepareStatement(sql);
			//?에 값을 맵핑
			//쿼리를 실행 시킨 결과를 리턴해서 받아줌(mysql테이블에 검색된 결과를 자바객체에 저장)
			rs = pstmt.executeQuery();
			//반복문을 사용해서 rs에 저장된 데이터를 추출해놓음
			while(rs.next()) {	//저장된 데이터 만큼까지 돌음
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
				v.add(bean);//0번지부터 순서대로 데이터가 저장
			}
			//닫아주기
			conn.close();
		}catch (Exception e) {
			System.out.println("오류");
		}
		return v;
	}
	
	public Vector<Bean> sortLatest4(){
		//가변길이로 데이터를 저장
		Vector<Bean> v = new Vector<>();
		
		//데이터베이스는 예외처리를 반드시 해야됨.
		try {
			//커넥션 연결
			Connection conn = DatabaseUtil.getConnection();	//데이터베이스유틸의 겟커넥션메소드
			//쿼리 준비
			String sql = "SELECT * FROM product2 order by id desc";
			//쿼리를 실행시켜주는 객체 선언
			PreparedStatement pstmt = conn.prepareStatement(sql);
			//?에 값을 맵핑
			//쿼리를 실행 시킨 결과를 리턴해서 받아줌(mysql테이블에 검색된 결과를 자바객체에 저장)
			rs = pstmt.executeQuery();
			//반복문을 사용해서 rs에 저장된 데이터를 추출해놓음
			while(rs.next()) {	//저장된 데이터 만큼까지 돌음
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
				v.add(bean);//0번지부터 순서대로 데이터가 저장
			}
			//닫아주기
			conn.close();
		}catch (Exception e) {
			System.out.println("오류");
		}
		return v;
	}
	
	public Vector<Bean> categorySelectProduct(String category){
		//가변길이로 데이터를 저장
		Vector<Bean> v = new Vector<>();
		
		//데이터베이스는 예외처리를 반드시 해야됨.
		try {
			//커넥션 연결
			Connection conn = DatabaseUtil.getConnection();	//데이터베이스유틸의 겟커넥션메소드
			//쿼리 준비
			String sql = "select * from product where category=?";
			//쿼리를 실행시켜주는 객체 선언
			PreparedStatement pstmt = conn.prepareStatement(sql);
			//?에 값을 맵핑
			pstmt.setString(1, category);
			//쿼리를 실행 시킨 결과를 리턴해서 받아줌(mysql테이블에 검색된 결과를 자바객체에 저장)
			rs = pstmt.executeQuery();
			//반복문을 사용해서 rs에 저장된 데이터를 추출해놓음
			while(rs.next()) {	//저장된 데이터 만큼까지 돌음
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
				v.add(bean);//0번지부터 순서대로 데이터가 저장
			}
			//닫아주기
			conn.close();
		}catch (Exception e) {
			System.out.println("오류");
		}
		return v;
	}
	
	public Vector<Bean> categorySelectProduct2(String category){
		//가변길이로 데이터를 저장
		Vector<Bean> v = new Vector<>();
		
		//데이터베이스는 예외처리를 반드시 해야됨.
		try {
			//커넥션 연결
			Connection conn = DatabaseUtil.getConnection();	//데이터베이스유틸의 겟커넥션메소드
			//쿼리 준비
			String sql = "select * from product2 where category=?";
			//쿼리를 실행시켜주는 객체 선언
			PreparedStatement pstmt = conn.prepareStatement(sql);
			//?에 값을 맵핑
			pstmt.setString(1, category);
			//쿼리를 실행 시킨 결과를 리턴해서 받아줌(mysql테이블에 검색된 결과를 자바객체에 저장)
			rs = pstmt.executeQuery();
			//반복문을 사용해서 rs에 저장된 데이터를 추출해놓음
			while(rs.next()) {	//저장된 데이터 만큼까지 돌음
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
				v.add(bean);//0번지부터 순서대로 데이터가 저장
			}
			//닫아주기
			conn.close();
		}catch (Exception e) {
			System.out.println("오류");
		}
		return v;
	}
	
	//상품 검색 메소드
	public Vector<Bean> search(String name){
		//가변길이로 데이터를 저장
		Vector<Bean> v = new Vector<>();
		
		//데이터베이스는 예외처리를 반드시 해야됨.
		try {
			//커넥션 연결
			Connection conn = DatabaseUtil.getConnection();	//데이터베이스유틸의 겟커넥션메소드
			//쿼리 준비
			String sql = "SELECT * FROM product WHERE name LIKE ?";
			//쿼리를 실행시켜주는 객체 선언
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			//?에 값을 맵핑
			pstmt.setString(1, "%" + name + "%");
			//pstmt.setString(1, name);
			//쿼리를 실행 시킨 결과를 리턴해서 받아줌(mysql테이블에 검색된 결과를 자바객체에 저장)
			rs = pstmt.executeQuery();
			//반복문을 사용해서 rs에 저장된 데이터를 추출해놓음
			while(rs.next()) {	//저장된 데이터 만큼까지 돌음
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
				v.add(bean);//0번지부터 순서대로 데이터가 저장
			}
			//닫아주기
			conn.close();
		}catch (Exception e) {
			System.out.println("오류");
		}
		return v;
	}
	
	//상품 검색 메소드
		public Vector<Bean> search2(String name){
			//가변길이로 데이터를 저장
			Vector<Bean> v = new Vector<>();
			
			//데이터베이스는 예외처리를 반드시 해야됨.
			try {
				//커넥션 연결
				Connection conn = DatabaseUtil.getConnection();	//데이터베이스유틸의 겟커넥션메소드
				//쿼리 준비
				String sql = "SELECT * FROM product2 WHERE name LIKE ?";
				//쿼리를 실행시켜주는 객체 선언
				
				PreparedStatement pstmt = conn.prepareStatement(sql);
				//?에 값을 맵핑
				pstmt.setString(1, "%" + name + "%");
				//pstmt.setString(1, name);
				//쿼리를 실행 시킨 결과를 리턴해서 받아줌(mysql테이블에 검색된 결과를 자바객체에 저장)
				rs = pstmt.executeQuery();
				//반복문을 사용해서 rs에 저장된 데이터를 추출해놓음
				while(rs.next()) {	//저장된 데이터 만큼까지 돌음
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
					v.add(bean);//0번지부터 순서대로 데이터가 저장
				}
				//닫아주기
				conn.close();
			}catch (Exception e) {
				System.out.println("오류");
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
	
	//상품 마감시간 초과 시 아이디 받아서 해당상품 삭제
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
	
	//상품 마감시간 초과 시 아이디 받아서 해당상품 삭제
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
	
	//입찰시 입찰수 증가시키는 메소드
	public void incleaseViews(int id) {
		Connection conn = DatabaseUtil.getConnection();
		try {
			String sql = "update product set views = views + 1 where id = ?;";
			//쿼리실행 객체 선언
			PreparedStatement pstmt = conn.prepareStatement(sql);
			//?에 값을 맵핑
			pstmt.setInt(1, id);
			//쿼리실행
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
			//쿼리실행 객체 선언
			PreparedStatement pstmt = conn.prepareStatement(sql);
			//?에 값을 맵핑
			pstmt.setInt(1, id);
			//쿼리실행
			pstmt.executeUpdate();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//경매종료상품 분류위한 메소드
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
	
	//경매종료상품 분류위한 메소드
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
		//데이터베이스는 예외처리를 반드시 해야됨.
		try {
			//커넥션 연결
			Connection conn = DatabaseUtil.getConnection();
			//쿼리 준비
			String sql = "select * from finish";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			//반복문을 사용해서 rs에 저장된 데이터를 추출해놓음
			while(rs.next()) {	//저장된 데이터 만큼까지 돌음
				ProductBean pbean = new ProductBean();
				pbean.setUser_id(rs.getString(1));
				pbean.setBuy_id(rs.getString(2));
				pbean.setProduct_id(rs.getInt(3));
				pbean.setPrice(rs.getInt(4));
				v.add(pbean);//0번지부터 순서대로 데이터가 저장
			}
			//닫아주기
			conn.close();
		}catch (Exception e) {
			System.out.println("오류");
		}		
		return v;
	}
	
	public Vector<ProductBean> finishallselect2(){
		Vector<ProductBean> v = new Vector<>();	
		//데이터베이스는 예외처리를 반드시 해야됨.
		try {
			//커넥션 연결
			Connection conn = DatabaseUtil.getConnection();
			//쿼리 준비
			String sql = "select * from finish2";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			//반복문을 사용해서 rs에 저장된 데이터를 추출해놓음
			while(rs.next()) {	//저장된 데이터 만큼까지 돌음
				ProductBean pbean = new ProductBean();
				pbean.setUser_id(rs.getString(1));
				pbean.setBuy_id(rs.getString(2));
				pbean.setProduct_id(rs.getInt(3));
				pbean.setPrice(rs.getInt(4));
				v.add(pbean);//0번지부터 순서대로 데이터가 저장
			}
			//닫아주기
			conn.close();
		}catch (Exception e) {
			System.out.println("오류");
		}		
		return v;
	}
}