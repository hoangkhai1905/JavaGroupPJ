package entity;

import java.util.Objects;


public class CT_HoaDon {
	private HoaDon hoaDon;
	private SanPham sanPham;
	private int soLuong;
	private double chietKhau;

        public CT_HoaDon(HoaDon hoaDon, SanPham sanPham, int soLuong, double chietKhau) {
		this.hoaDon = hoaDon;
                this.sanPham = sanPham;
                this.soLuong = soLuong;
                this.chietKhau = chietKhau;
	}

	public CT_HoaDon(HoaDon hoaDon, SanPham sanPham, int soLuong) {
		setHoaDon(hoaDon);
		setSanPham(sanPham);
		setSoLuong(soLuong);
		setChietKhau();
	}

	public HoaDon getHoaDon() {
		return hoaDon;
	}

	public void setHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
	}

	public SanPham getSanPham() {
		return sanPham;
	}

	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public double getChietKhau() {
		return chietKhau;
	}

	public void setChietKhau() {
		this.chietKhau = Math.random() % 10 * 0.1; // random chiet khau tu 0-20%
	}

        @Override
        public int hashCode() {
            int hash = 3;
            hash = 97 * hash + Objects.hashCode(this.hoaDon);
            hash = 97 * hash + Objects.hashCode(this.sanPham);
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
            final CT_HoaDon other = (CT_HoaDon) obj;
            if (!Objects.equals(this.hoaDon, other.hoaDon)) {
                return false;
            }
            return Objects.equals(this.sanPham, other.sanPham);
        }
}
