package product;

import java.sql.Date;

public class ProductBean {
	String pname;
	String pcategory;
	int pprice;
	String pcontent;
	Date pday;
	String paddress;		//상품이미지 저장위치
	int views;
	int ptotal;
	
	public int getPtotal() {
		return ptotal;
	}
	public void setPtotal(int ptotal) {
		this.ptotal = ptotal;
	}
	public String getPaddress() {
		return paddress;
	}
	public void setPaddress(String paddress) {
		this.paddress = paddress;
	}
	int pnum;
	
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getPcategory() {
		return pcategory;
	}
	public void setPcategory(String pcategory) {
		this.pcategory = pcategory;
	}
	public int getPprice() {
		return pprice;
	}
	public void setPprice(int pprice) {
		this.pprice = pprice;
	}
	public String getPcontent() {
		return pcontent;
	}
	public void setPcontent(String pcontent) {
		this.pcontent = pcontent;
	}

	public Date getPday() {
		return pday;
	}
	public void setPday(Date pday) {
		this.pday = pday;
	}
	
	public int getPnum() {
		return pnum;
	}
	public void setPnum(int pnum) {
		this.pnum = pnum;
	}

	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
}
