package product;

public class Bean {
	String name;
	String category;
	int price;
	String content;
	String bbsdate;		//상품등록날짜
	int bbsavailable; 	//상품삭제한거 구분
	int id;
	String address;		//상품이미지 저장위치
	String user_id;
	int views;
	
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getBbsdate() {
		return bbsdate;
	}
	public void setBbsdate(String bbsdate) {
		this.bbsdate = bbsdate;
	}
	public int getBbsavailable() {
		return bbsavailable;
	}
	public void setBbsavailable(int bbsavailable) {
		this.bbsavailable = bbsavailable;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
