package order;

import java.sql.Date;

public class OrderBEAN {
	
	private int osnum;
	private int eid;
	private int plistnum;
	private String pname;
	private int count;
	private int osprice;
	private int sid;
	private Date ofrom;
	private Date sto;
	private int ostag;
	
	
	//setter&getter
	public int getOsnum() {
		return osnum;
	}
	public void setOsnum(int osnum) {
		this.osnum = osnum;
	}
	
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	
	public int getPlistnum() {
		return plistnum;
	}
	public void setPlistnum(int plistnum) {
		this.plistnum = plistnum;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getOsprice() {
		return osprice;
	}
	public void setOsprice(int osprice) {
		this.osprice = osprice;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public Date getOfrom() {
		return ofrom;
	}
	public void setOfrom(Date ofrom) {
		this.ofrom = ofrom;
	}
	public Date getSto() {
		return sto;
	}
	public void setSto(Date sto) {
		this.sto = sto;
	}
	public int getOstag() {
		return ostag;
	}
	public void setOstag(int ostag) {
		this.ostag = ostag;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}

}
