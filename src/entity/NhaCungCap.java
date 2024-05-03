package entity;

public class NhaCungCap {
	private String maNCC;
	private String tenNCC;
	private String diaChi;
	private String sdt;
	private String email;
	public NhaCungCap(String maNCC, String tenNCC, String diaChi, String sdt, String email) {
		setMaNCC(maNCC);
		setTenNCC(tenNCC);
		setDiaChi(diaChi);
		setSdt(sdt);
		setEmail(email);
	}
	public String getMaNCC() {
		return maNCC;
	}
	public void setMaNCC(String maNCC) {
		if (!maNCC.trim().isEmpty()) {
			this.maNCC = maNCC;
		} else {
			throw new IllegalArgumentException("Mã nhà cung cấp không được rỗng !");
		}
	}
	public String getTenNCC() {
		return tenNCC;
	}
	public void setTenNCC(String tenNCC) {
		this.tenNCC = tenNCC;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		if (sdt.length() == 10) {
			this.sdt = sdt;
		} else {
			throw new IllegalArgumentException("Số điện thoại phải có 10 chữ số");
		}
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
