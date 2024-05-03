package entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class HoaDon {
	private String maHD;
	private	LocalDate ngayLapHD;
	private KhachHang kh;
	private NhanVien nv;

	public HoaDon(String maHD) {
		this.maHD = maHD;
	}

	public HoaDon(String maHD, LocalDate ngayLapHD, KhachHang kh, NhanVien nv) {
		setMaHD(maHD);
		setNgayLapHD(ngayLapHD);
		setKH(kh);
		setNv(nv);
	}

	public String getMaHD() {
		return maHD;
	}

	public void setMaHD(String maHD) {
		this.maHD = maHD;
	}

	public LocalDate getNgayLapHD() {
		return ngayLapHD;
	}

	public void setNgayLapHD(LocalDate ngayLapHD) {
		this.ngayLapHD = ngayLapHD;
	}

	public KhachHang getKH() {
		return kh;
	}

	public void setKH(KhachHang kh) {
		this.kh = kh;
	}

	public NhanVien getNv() {
		return nv;
	}

	public void setNv(NhanVien nv) {
		this.nv = nv;
	}

        @Override
        public int hashCode() {
            int hash = 5;
            hash = 41 * hash + Objects.hashCode(this.maHD);
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
            final HoaDon other = (HoaDon) obj;
            return Objects.equals(this.maHD, other.maHD);
        }
                           
}
