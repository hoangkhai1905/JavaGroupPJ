package entity;

import java.time.LocalDate;
import java.util.Objects;

public class SanPham {
	private String maSP;
	private String tenSP;
	private String maNCC;
	private String moTa;
	private String maNhom;
	private String donViTinh;
	private double giaNhap;
	private LocalDate ngayNhap;
	private LocalDate ngaySX;
	private LocalDate ngayHH;
	private int soTon;
	
	public SanPham(String maSP, String tenSP, String maNCC, String moTa, String maNhom, String donViTinh,
			double giaNhap, LocalDate ngayNhap, LocalDate ngaySX, LocalDate ngayHH, int soTon) {
		setMaSP(maSP);
		setTenSP(tenSP);
		setMaNCC(maNCC);
		setMoTa(moTa);
		setMaNhom(maNhom);
		setDonViTinh(donViTinh);
		setGiaNhap(giaNhap);
		setNgayNhap(ngayNhap);
		setNgaySX(ngaySX);
		setNgayHH(ngayHH);
		setSoTon(soTon);
	}
	
	public SanPham(String maSP) {
		super();
		this.maSP = maSP;
	}

	public String getMaSP() {
		return maSP;
	}

	public void setMaSP(String maSP) {
		this.maSP = maSP;
	}

	public String getTenSP() {
		return tenSP;
	}

	public void setTenSP(String tenSP) {
		this.tenSP = tenSP;
	}

	public String getMaNCC() {
		return maNCC;
	}

	public void setMaNCC(String maNCC) {
		this.maNCC = maNCC;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public String getMaNhom() {
		return maNhom;
	}

	public void setMaNhom(String maNhom) {
		this.maNhom = maNhom;
	}

	public String getDonViTinh() {
		return donViTinh;
	}

	public void setDonViTinh(String donViTinh) {
		this.donViTinh = donViTinh;
	}

	public double getGiaNhap() {
		return giaNhap;
	}

	public void setGiaNhap(double giaNhap) {
		this.giaNhap = giaNhap;
	}

	public LocalDate getNgayNhap() {
		return ngayNhap;
	}

	public void setNgayNhap(LocalDate ngayNhap) {
		this.ngayNhap = ngayNhap;
	}

	public LocalDate getNgaySX() {
		return ngaySX;
	}

	public void setNgaySX(LocalDate ngaySX) {
		this.ngaySX = ngaySX;
	}

	public LocalDate getNgayHH() {
		return ngayHH;
	}

	public void setNgayHH(LocalDate ngayHH) {
		this.ngayHH = ngayHH;
	}

	public int getSoTon() {
		return soTon;
	}

	public void setSoTon(int soTon) {
		this.soTon = soTon;
	}

        @Override
        public int hashCode() {
            int hash = 3;
            hash = 29 * hash + Objects.hashCode(this.maSP);
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
            final SanPham other = (SanPham) obj;
            return Objects.equals(this.maSP, other.maSP);
        }
	
	
}
