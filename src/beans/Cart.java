package beans;

public class Cart {
	private int cid;
	private int cfid;//对应的食物id
	private int cuid;//对应的买家id
	private String fname;
	private double fprice;
	private String fpicture;
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public double getFprice() {
		return fprice;
	}
	public void setFprice(double fprice) {
		this.fprice = fprice;
	}
	public String getFpicture() {
		return fpicture;
	}
	public void setFpicture(String fpicture) {
		this.fpicture = fpicture;
	}
	public int getCuid() {
		return cuid;
	}
	public void setCuid(int cuid) {
		this.cuid = cuid;
	}
	private int cquantities;
	private double cprices;
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getCfid() {
		return cfid;
	}
	public void setCfid(int cfid) {
		this.cfid = cfid;
	}
	public int getCquantities() {
		return cquantities;
	}
	public void setCquantities(int cquantities) {
		this.cquantities = cquantities;
	}
	public double getCprices() {
		return cprices;
	}
	public void setCprices(double cprices) {
		this.cprices = cprices;
	}
	
}
