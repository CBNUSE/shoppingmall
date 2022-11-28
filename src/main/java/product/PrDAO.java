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
	
	//마지막 상품번호 가져오는 메소드
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
	
	//상품 등록 메소드
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
	
	//상품 카테고리 별 최신 순 정렬
	public Vector<ProductBean> sortLatest(String category){
		//가변길이로 데이터를 저장
		Vector<ProductBean> v = new Vector<>();
		
		//데이터베이스는 예외처리를 반드시 해야됨.
		try {
			//커넥션 연결
			Connection conn = DatabaseUtil.getConnection();	//데이터베이스유틸의 겟커넥션메소드
			//쿼리 준비
			String sql = "SELECT * FROM product where pcategory = ? order by pnum desc";
			//쿼리를 실행시켜주는 객체 선언
			PreparedStatement pstmt = conn.prepareStatement(sql);
			//?에 값을 맵핑
			pstmt.setString(1, category);
			//쿼리를 실행 시킨 결과를 리턴해서 받아줌(mysql테이블에 검색된 결과를 자바객체에 저장)
			rs = pstmt.executeQuery();
			//반복문을 사용해서 rs에 저장된 데이터를 추출해놓음
			while(rs.next()) {	//저장된 데이터 만큼까지 돌음
				ProductBean bean = new ProductBean();
				bean.setPnum(rs.getInt(1));
				bean.setPname(rs.getString(2));
				bean.setPcategory(rs.getString(3));
				bean.setPprice(rs.getInt(4));
				bean.setPcontent(rs.getString(5));
				bean.setPday(rs.getDate(6));
				bean.setPaddress(rs.getString(7));
				bean.setViews(rs.getInt(8));
				v.add(bean);//0번지부터 순서대로 데이터가 저장
			}
			//닫아주기
			conn.close();
		}catch (Exception e) {
			System.out.println("오류");
		}
		return v;
	}
	
	
	//상품 카테고리 별 인기 순 정렬
	public Vector<ProductBean> sortPopularity(String category){
		//가변길이로 데이터를 저장
		Vector<ProductBean> v = new Vector<>();
		
		//데이터베이스는 예외처리를 반드시 해야됨.
		try {
			//커넥션 연결
			Connection conn = DatabaseUtil.getConnection();	//데이터베이스유틸의 겟커넥션메소드
			//쿼리 준비
			String sql = "SELECT * FROM product where pcategory = ? order by views desc";
			//쿼리를 실행시켜주는 객체 선언
			PreparedStatement pstmt = conn.prepareStatement(sql);
			//?에 값을 맵핑
			pstmt.setString(1, category);
			//쿼리를 실행 시킨 결과를 리턴해서 받아줌(mysql테이블에 검색된 결과를 자바객체에 저장)
			rs = pstmt.executeQuery();
			//반복문을 사용해서 rs에 저장된 데이터를 추출해놓음
			while(rs.next()) {	//저장된 데이터 만큼까지 돌음
				ProductBean bean = new ProductBean();
				bean.setPnum(rs.getInt(1));
				bean.setPname(rs.getString(2));
				bean.setPcategory(rs.getString(3));
				bean.setPprice(rs.getInt(4));
				bean.setPcontent(rs.getString(5));
				bean.setPday(rs.getDate(6));
				bean.setPaddress(rs.getString(7));
				bean.setViews(rs.getInt(8));
				v.add(bean);//0번지부터 순서대로 데이터가 저장
			}
			//닫아주기
			conn.close();
		}catch (Exception e) {
			System.out.println("오류");
		}
		return v;
	}
	
	//상품 인기 순 정렬(카테고리 포함X)
	public Vector<ProductBean> sortPopularity2(){
		//가변길이로 데이터를 저장
		Vector<ProductBean> v = new Vector<>();
		
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
				ProductBean bean = new ProductBean();
				bean.setPnum(rs.getInt(1));
				bean.setPname(rs.getString(2));
				bean.setPcategory(rs.getString(3));
				bean.setPprice(rs.getInt(4));
				bean.setPcontent(rs.getString(5));
				bean.setPday(rs.getDate(6));
				bean.setPaddress(rs.getString(7));
				bean.setViews(rs.getInt(8));
				v.add(bean);//0번지부터 순서대로 데이터가 저장
			}
			//닫아주기
			conn.close();
		}catch (Exception e) {
			System.out.println("오류");
		}
		return v;
	}
	
	public Vector<ProductBean> sortLatest2(){
		//가변길이로 데이터를 저장
		Vector<ProductBean> v = new Vector<>();
		
		//데이터베이스는 예외처리를 반드시 해야됨.
		try {
			//커넥션 연결
			Connection conn = DatabaseUtil.getConnection();	//데이터베이스유틸의 겟커넥션메소드
			//쿼리 준비
			String sql = "SELECT * FROM product order by pnum desc";
			//쿼리를 실행시켜주는 객체 선언
			PreparedStatement pstmt = conn.prepareStatement(sql);
			//?에 값을 맵핑
			//쿼리를 실행 시킨 결과를 리턴해서 받아줌(mysql테이블에 검색된 결과를 자바객체에 저장)
			rs = pstmt.executeQuery();
			//반복문을 사용해서 rs에 저장된 데이터를 추출해놓음
			while(rs.next()) {	//저장된 데이터 만큼까지 돌음
				ProductBean bean = new ProductBean();
				bean.setPnum(rs.getInt(1));
				bean.setPname(rs.getString(2));
				bean.setPcategory(rs.getString(3));
				bean.setPprice(rs.getInt(4));
				bean.setPcontent(rs.getString(5));
				bean.setPday(rs.getDate(6));
				bean.setPaddress(rs.getString(7));
				bean.setViews(rs.getInt(8));
				v.add(bean);//0번지부터 순서대로 데이터가 저장
			}
			//닫아주기
			conn.close();
		}catch (Exception e) {
			System.out.println("오류");
		}
		return v;
	}
	
	public Vector<ProductBean> categorySelectProduct(String category){
		//가변길이로 데이터를 저장
		Vector<ProductBean> v = new Vector<>();
		
		//데이터베이스는 예외처리를 반드시 해야됨.
		try {
			//커넥션 연결
			Connection conn = DatabaseUtil.getConnection();	//데이터베이스유틸의 겟커넥션메소드
			//쿼리 준비
			String sql = "select * from product where pcategory=?";
			//쿼리를 실행시켜주는 객체 선언
			PreparedStatement pstmt = conn.prepareStatement(sql);
			//?에 값을 맵핑
			pstmt.setString(1, category);
			//쿼리를 실행 시킨 결과를 리턴해서 받아줌(mysql테이블에 검색된 결과를 자바객체에 저장)
			rs = pstmt.executeQuery();
			//반복문을 사용해서 rs에 저장된 데이터를 추출해놓음
			while(rs.next()) {	//저장된 데이터 만큼까지 돌음
				ProductBean bean = new ProductBean();
				bean.setPnum(rs.getInt(1));
				bean.setPname(rs.getString(2));
				bean.setPcategory(rs.getString(3));
				bean.setPprice(rs.getInt(4));
				bean.setPcontent(rs.getString(5));
				bean.setPday(rs.getDate(6));
				bean.setPaddress(rs.getString(7));
				bean.setViews(rs.getInt(8));
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
	public Vector<ProductBean> search(String name){
		//가변길이로 데이터를 저장
		Vector<ProductBean> v = new Vector<>();
		
		//데이터베이스는 예외처리를 반드시 해야됨.
		try {
			//커넥션 연결
			Connection conn = DatabaseUtil.getConnection();	//데이터베이스유틸의 겟커넥션메소드
			//쿼리 준비
			String sql = "SELECT * FROM product WHERE pname LIKE ?";
			//쿼리를 실행시켜주는 객체 선언
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			//?에 값을 맵핑
			pstmt.setString(1, "%" + name + "%");
			//pstmt.setString(1, name);
			//쿼리를 실행 시킨 결과를 리턴해서 받아줌(mysql테이블에 검색된 결과를 자바객체에 저장)
			rs = pstmt.executeQuery();
			//반복문을 사용해서 rs에 저장된 데이터를 추출해놓음
			while(rs.next()) {	//저장된 데이터 만큼까지 돌음
				ProductBean bean = new ProductBean();
				bean.setPnum(rs.getInt(1));
				bean.setPname(rs.getString(2));
				bean.setPcategory(rs.getString(3));
				bean.setPprice(rs.getInt(4));
				bean.setPcontent(rs.getString(5));
				bean.setPday(rs.getDate(6));
				bean.setPaddress(rs.getString(7));
				bean.setViews(rs.getInt(8));
				v.add(bean);//0번지부터 순서대로 데이터가 저장
			}
			//닫아주기
			conn.close();
		}catch (Exception e) {
			System.out.println("오류");
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
	
	//입찰시 입찰수 증가시키는 메소드
	public void incleaseViews(int id) {
		Connection conn = DatabaseUtil.getConnection();
		try {
			String sql = "update product set views = views + 1 where p = ?;";
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
	
	/*
	 * public Vector<ProductBean> finishallselect(){ Vector<ProductBean> v = new
	 * Vector<>(); //데이터베이스는 예외처리를 반드시 해야됨. try { //커넥션 연결 Connection conn =
	 * DatabaseUtil.getConnection(); //쿼리 준비 String sql = "select * from finish";
	 * PreparedStatement pstmt = conn.prepareStatement(sql); rs =
	 * pstmt.executeQuery(); //반복문을 사용해서 rs에 저장된 데이터를 추출해놓음 while(rs.next()) { //저장된
	 * 데이터 만큼까지 돌음 ProductBean pbean = new ProductBean();
	 * pbean.setUser_id(rs.getString(1)); pbean.setBuy_id(rs.getString(2));
	 * pbean.setProduct_id(rs.getInt(3)); pbean.setPrice(rs.getInt(4));
	 * v.add(pbean);//0번지부터 순서대로 데이터가 저장 } //닫아주기 conn.close(); }catch (Exception e)
	 * { System.out.println("오류"); } return v; }
	 */
}