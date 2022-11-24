package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import product.Bean;
import util.DatabaseUtil;

public class UserDAO {
	ResultSet rs; //데이터베이스의 테이블 결과를 리턴받아 자바에 저장해주는 객체
	
	//자바빈 사용하여 데이터들 한번에 저장
	public void insertCustomer(UserBean mbean) {
		try {
			Connection conn = DatabaseUtil.getConnection();	//데이터베이스에 접근할 수 있도록 설정
			String SQL = "INSERT INTO customer VALUES(?,?,?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(SQL);	//데이터베이스에서 쿼리를 실행시켜주는 객체
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
		//가변길이로 데이터를 저장
		Vector<UserBean> v = new Vector<>();	
		//데이터베이스는 예외처리를 반드시 해야됨.
		try {
			//커넥션 연결
			Connection conn = DatabaseUtil.getConnection();	//데이터베이스유틸의 겟커넥션메소드
			//쿼리 준비
			String sql = "select * from customer";
			//쿼리를 실행시켜주는 객체 선언
			PreparedStatement pstmt = conn.prepareStatement(sql);
			//쿼리를 실행 시킨 결과를 리턴해서 받아줌(mysql테이블에 검색된 결과를 자바객체에 저장)
			rs = pstmt.executeQuery();
			//반복문을 사용해서 rs에 저장된 데이터를 추출해놓음
			while(rs.next()) {	//저장된 데이터 만큼까지 돌음
				UserBean bean = new UserBean();//컬럼으로 나뉘어진 데이터를 빈클래스에 저정하기위함
				bean.setCid(rs.getString(1));
				bean.setCpassword(rs.getString(2));
				bean.setCname(rs.getString(3));
				bean.setCaddress(rs.getString(4));
				bean.setCtel(rs.getString(5));
				//패키징된 memberbean클래스를 벡터에 저장
				v.add(bean);//0번지부터 순서대로 데이터가 저장
			}
			//닫아주기
			conn.close();
		}catch (Exception e) {
			System.out.println("오류");
		}		
		return v;
	}
	
	//한사람에 대한 정보를 리턴하는 메소드
	public UserBean oneSelectMember(String id) {
		//한사람에 대한 정보만 리턴하기에 빈클래스 객체 생성
		UserBean bean = new UserBean();
		
		try {
			//커넥션연결
			Connection conn = DatabaseUtil.getConnection();
			//쿼리준비
			String sql = "select * from customer where cid=?"; //user테이블에서 쿼리를 가져오는데 어떤 아이디가 넘어올지 모르기에
			PreparedStatement pstmt = conn.prepareStatement(sql);
			//?에 값을 맵핑
			pstmt.setString(1, id);
			//쿼리실행
			rs = pstmt.executeQuery();
			if(rs.next()) {	//레코드가 있다면
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

			//쿼리실행
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
	
	//한 회원의 패스워드를 리턴하는 메소드 작성
	public String getPass(String id) {
		//스트링으로 리턴을 해야하기에 스트링변수 선언
		String password="";
		try {
			Connection conn = DatabaseUtil.getConnection();
			//쿼리준비
			String sql = "select cpassword from customer where cid=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				password = rs.getString(1); //패스워드값이 저장된 컬럼인덱스
			}
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}		
		return password;
	}
	
	//한 회원의 정보를 수정하는 메소드
	public void updateCustomer(UserBean mbean) {
		Connection conn = DatabaseUtil.getConnection();
		try {
			//쿼리준비,,,,만약 수정사항을 더 더하고싶다면 콤마콤마로 늘려가면됨
			String sql = "update customer set caddress=?,ctel=? where cid=?";
			//쿼리실행 객체 선언
			PreparedStatement pstmt = conn.prepareStatement(sql);
			//?에 값을 맵핑
			pstmt.setString(1, mbean.getCaddress());
			pstmt.setString(2, mbean.getCtel());
			pstmt.setString(3, mbean.getCid());
			//쿼리실행
			pstmt.executeUpdate();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//한 회원을 삭제하는 메소드
	public void deleteCustomer(String id) {
		Connection conn = DatabaseUtil.getConnection();
		try {
			//쿼리준비
			String sql = "delete from customer where cid=?";
			//쿼리실행 객체 선언
			PreparedStatement pstmt = conn.prepareStatement(sql);
			//?에 값을 맵핑
			pstmt.setString(1, id);
			//쿼리실행
			pstmt.executeUpdate();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
