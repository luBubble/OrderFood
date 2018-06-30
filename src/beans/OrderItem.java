package beans;

public class OrderItem {
	private int oiid;
	private int oioid;//所属订单
	private int oifid;//对应food的id
	private int oiquantity;
	private double oiprices;
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
	public int getOiid() {
		return oiid;
	}
	public void setOiid(int oiid) {
		this.oiid = oiid;
	}
	public int getOioid() {
		return oioid;
	}
	public void setOioid(int oioid) {
		this.oioid = oioid;
	}
	public int getOifid() {
		return oifid;
	}
	public void setOifid(int oifid) {
		this.oifid = oifid;
	}
	public int getOiquantity() {
		return oiquantity;
	}
	public void setOiquantity(int oiquantity) {
		this.oiquantity = oiquantity;
	}
	public double getOiprices() {
		return oiprices;
	}
	public void setOiprices(double oiprices) {
		this.oiprices = oiprices;
	}
	
}
