package entity;

import java.util.Objects;

public class KhachHang {
	private String maKH;
	private String tenKH;
	private String diaChi;
	private String sdt;
	private String email;
	private int diemTL;
	
	public KhachHang(String maKH, String tenKH, String diaChi, String sdt, String email, int diemTL) {
		this.maKH = maKH;
		setTenKH(tenKH);
		setDiaChi(diaChi);
		setSdt(sdt);
		setEmail(email);
		setDiemTL(diemTL);
	}

	public KhachHang(String maKH) {
		super();
		this.maKH = maKH;
	}

	public String getMaKH() {
		return maKH;
	}

	public void setMaKH(String maKH) {
		if(!maKH.trim().isEmpty())
			this.maKH = maKH;
		else
			throw new IllegalArgumentException("Mã khách hàng không được rỗng !");
	}

	public String getTenKH() {
		return tenKH;
	}

	public void setTenKH(String tenKH) {
		this.tenKH = tenKH;
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
		this.sdt = sdt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getDiemTL() {
		return diemTL;
	}

	public void setDiemTL(int diemTL) {
        this.diemTL = Math.max(diemTL, 0);
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 29 * hash + Objects.hashCode(this.maKH);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final KhachHang other = (KhachHang) obj;
		return Objects.equals(this.maKH, other.maKH);
	}
}
