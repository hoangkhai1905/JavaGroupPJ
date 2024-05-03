package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;

import connectdb.ConnectDB;
import entity.SanPham;

public class SanPham_DAO {


    public SanPham_DAO() {
    }

	public ArrayList<SanPham> getAllSanPham() {
		ArrayList<SanPham> dsSanPham= new ArrayList<>();
	try {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
	
		String sql = "Select * from SanPham";
		Statement st = con.createStatement();
	
		ResultSet rs = st.executeQuery(sql);
	
		while(rs.next()) {
			String maSP = rs.getString("MaSP");
			String tenSP = rs.getString("TenSP");
			String maNCC = rs.getString("MaNCC");
			String moTa = rs.getString("MoTa");
			String maNhom = rs.getString("MaNhom");
			String donViTinh = rs.getString("DonViTinh");
			double giaNhap = rs.getDouble("GiaNhap");
			LocalDate ngayNhap = rs.getDate("NgayNhap").toLocalDate();
			LocalDate ngaySX = rs.getDate("NgaySX").toLocalDate();
			LocalDate ngayHH = rs.getDate("NgayHH").toLocalDate();
			int soTon = rs.getInt("SLTON");
			
			SanPham sp = new SanPham(maSP, tenSP, maNCC, moTa, maNhom, donViTinh, giaNhap, ngayNhap, ngaySX, ngayHH, soTon);
			dsSanPham.add(sp);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return dsSanPham;
	}
        
        public ArrayList<SanPham> getDSSanPhamTheoNhomSP(String maNhomSP) {
            ArrayList<SanPham> dsSanPham = new ArrayList<>();
            try {
                    ConnectDB.getInstance();
                    Connection con = ConnectDB.getConnection();

                    String sql = "Select * from SanPham where MaNhom=?";
                    PreparedStatement ppst = con.prepareStatement(sql);
                    ppst.setString(1, maNhomSP);
                    ResultSet rs = ppst.executeQuery();

                    while(rs.next()) {
                            String maSP = rs.getString("MaSP");
                            String tenSP = rs.getString("TenSP");
                            String maNCC = rs.getString("MaNCC");
                            String moTa = rs.getString("MoTa");
                            String maNhom = rs.getString("MaNhom");
                            String donViTinh = rs.getString("DonViTinh");
                            double giaNhap = rs.getDouble("GiaNhap");
                            LocalDate ngayNhap = rs.getDate("NgayNhap").toLocalDate();
                            LocalDate ngaySX = rs.getDate("NgaySX").toLocalDate();
                            LocalDate ngayHH = rs.getDate("NgayHH").toLocalDate();
                            int soTon = rs.getInt("SLTON");

                            SanPham sp = new SanPham(maSP, tenSP, maNCC, moTa, maNhom, donViTinh, giaNhap, ngayNhap, ngaySX, ngayHH, soTon);
                            dsSanPham.add(sp);
                    }
            } catch (SQLException e) {
                    e.printStackTrace();
            }
            return dsSanPham;
	}

	public boolean create(SanPham sp) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		int n = 0;
		try {
			stm = con.prepareStatement("insert into SanPham values(?,?,?,?,?,?,?,?,?,?,?)");

			stm.setString(1, "");
			stm.setString(2, sp.getTenSP());
			stm.setString(3, sp.getMaNCC());
			stm.setString(4, sp.getMoTa());
			stm.setString(5, sp.getMaNhom());
			stm.setString(6, sp.getDonViTinh());
			stm.setDouble(7, sp.getGiaNhap());

			stm.setDate(8, Date.valueOf(sp.getNgayNhap()));
			stm.setDate(9, Date.valueOf(sp.getNgaySX()));
			stm.setDate(10, Date.valueOf(sp.getNgayHH()));

			stm.setInt(11, sp.getSoTon());

			n = stm.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			if (stm != null) {
				try {
					stm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return n > 0;
	}
	
	public boolean update(SanPham sp) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		int n = 0;

		try {
			stm = con.prepareStatement("update SanPham set TenSP=?, MaNCC=?, MoTa=?, MaNhom=?, "
					+ "DonViTinh=?, GiaNhap=?, NgayNhap=?, NgaySX=?, NgayHH=?, SLTON=? where MaSP=?");
			
			
			stm.setString(1, sp.getTenSP());
			stm.setString(2, sp.getMaNCC());
			stm.setString(3, sp.getMoTa());
			stm.setString(4, sp.getMaNhom());
			stm.setString(5, sp.getDonViTinh());
			stm.setDouble(6, sp.getGiaNhap());
			stm.setDate(7, Date.valueOf(sp.getNgayNhap()));
			stm.setDate(8, Date.valueOf(sp.getNgaySX()));
			stm.setDate(9, Date.valueOf(sp.getNgayHH()));
			stm.setInt(10, sp.getSoTon());
			stm.setString(11, sp.getMaSP());
			
			n = stm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		finally {
			if (stm != null) {
				try {
					stm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return n > 0;
	}
	
	public Date convertToDateViaSqlDate(LocalDate ngay) {
	    return java.sql.Date.valueOf(ngay);
	}
	
	public boolean delete(SanPham sp) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;

		
		int n = 0;
		try {
			stm = con.prepareStatement("delete from SanPham where MaSP=?");

			stm.setString(1, sp.getMaSP());
			
			n = stm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		finally {
			if (stm != null) {
				try {
					stm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return n > 0;
	}




	public ArrayList<SanPham> getDSSanPhamTheoMa(String maSp) {
		ArrayList<SanPham> dsSanPham = new ArrayList<>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "Select * from SanPham where MaSP=?";
			PreparedStatement ppst = con.prepareStatement(sql);
			ppst.setString(1, maSp);
			ResultSet rs = ppst.executeQuery();
			while(rs.next()) {
				String maSP = rs.getString("MaSP");
				String tenSP = rs.getString("TenSP");
				String maNCC = rs.getString("MaNCC");
				String moTa = rs.getString("MoTa");
				String maNhom = rs.getString("MaNhom");
				String donViTinh = rs.getString("DonViTinh");
				double giaNhap = rs.getDouble("GiaNhap");
				LocalDate ngayNhap = rs.getDate("NgayNhap").toLocalDate();
				LocalDate ngaySX = rs.getDate("NgaySX").toLocalDate();
				LocalDate ngayHH = rs.getDate("NgayHH").toLocalDate();
				int soTon = rs.getInt("SLTON");

				SanPham sp = new SanPham(maSP, tenSP, maNCC, moTa, maNhom, donViTinh, giaNhap, ngayNhap, ngaySX, ngayHH, soTon);
				dsSanPham.add(sp);
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return dsSanPham;
	}

	public void updateSoLuongTon(String maSP, int soLuong) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		int n = 0;
		try {
			stm = con.prepareStatement("update SanPham set SLTON = SLTON - ? where MaSP=?");

			stm.setInt(1, soLuong);
			stm.setString(2, maSP);

			n = stm.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}


		finally {
			if (stm != null) {
				try {
					stm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
