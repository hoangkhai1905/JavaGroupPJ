package entity;

public class NhanVien {
	private String maNV;
	private String ho;
	private String ten;
	private int tuoi;
	private boolean phai;
	private String diaChi;
	private String sdt;
	private double luong;
	private String ca;
	
	
	public NhanVien(String maNV, String ho, String ten, int tuoi, boolean phai,
					String diaChi, String sdt, double luong, String ca) {
		this.maNV = maNV;
		setHo(ho);
		setTen(ten);
		setTuoi(tuoi);
		setPhai(phai);
		setDiaChi(diaChi);
		setSdt(sdt);
		setLuong(luong);
		setCa(ca);
	}
	
	public NhanVien(String maNV) {
		super();
		this.maNV = maNV;
	}

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		if (!maNV.trim().isEmpty()) {
			this.maNV = maNV;
		} else {
			throw new IllegalArgumentException("Mã nhân viên không được rỗng !");
		}
	}

	public String getHo() {
		return ho;
	}

	public void setHo(String ho) {
		this.ho = ho;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public int getTuoi() {
		return tuoi;
	}

	public void setTuoi(int tuoi) {
		this.tuoi = tuoi;
	}

	public boolean isPhai() {
		return phai;
	}

	public void setPhai(boolean phai) {
		this.phai = phai;
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

	public double getLuong() {
		return luong;
	}


	public void setLuong(double luong) {
		this.luong = luong;
	}


	public String getCa() {
		return ca;
	}


	public void setCa(String ca) {
		this.ca = ca;
	}
	
	
}
