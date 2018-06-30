package beans;

public class Order {

	private int oid;
	private int ouzid;
	private int ounid;
	private double oprices;
	private int ostatus;
	private int odelete;
	private String username;
	private String shopname;
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	public int getOuzid() {
		return ouzid;
	}
	public void setOuzid(int ouzid) {
		this.ouzid = ouzid;
	}
	public int getOunid() {
		return ounid;
	}
	public void setOunid(int ounid) {
		this.ounid = ounid;
	}
	public double getOprices() {
		return oprices;
	}
	public void setOprices(double oprices) {
		this.oprices = oprices;
	}
	public int getOstatus() {
		return ostatus;
	}
	public void setOstatus(int ostatus) {
		this.ostatus = ostatus;
	}
	public int getOdelete() {
		return odelete;
	}
	public void setOdelete(int odelete) {
		this.odelete = odelete;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getShopname() {
		return shopname;
	}
	public void setShopname(String shopname) {
		this.shopname = shopname;
	}
	
	
}
