package entity;

public class NhomSanPham {
	private String maNhom;
	private String tenNhom;
	
	public NhomSanPham(String maNhom, String tenNhom) {
		setMaNhom(maNhom);
		setTenNhom(tenNhom);
	}

	public String getMaNhom() {
		return maNhom;
	}

	public void setMaNhom(String maNhom) {
		if (!maNhom.trim().isEmpty())
			this.maNhom = maNhom;
		else
			throw new IllegalArgumentException("Mã nhóm không được rỗng !");
	}

	public String getTenNhom() {
		return tenNhom;
	}

	public void setTenNhom(String tenNhom) {
		this.tenNhom = tenNhom;
	}
	
}
