package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import connectdb.ConnectDB;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;

public class HoaDon_DAO {
	private Date ngaylap;

	public HoaDon_DAO() {
	}

	public ArrayList<HoaDon> getAllHoaDon() {
			ArrayList<HoaDon> dsHoaDon = new ArrayList<>();
	try {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		
		String sql = "Select * from HoaDon";
		Statement st = con.createStatement();
		
		ResultSet rs = st.executeQuery(sql);
		
		while(rs.next()) {
			String maHD = rs.getString("MaHD");	
			LocalDate ngayLapHD = rs.getDate(2).toLocalDate(); 
			
			String maKH = rs.getString(3);
			KhachHang mkh = new KhachHang_DAO().getKhachHangTheoMaKH(maKH);
			
			String maNV = rs.getString(4);
			NhanVien mnv = new NhanVien_DAO().getNhanVienTheoMaNV(maNV);


			HoaDon hd;
			try {
				hd = new HoaDon(maHD, ngayLapHD, mkh, mnv);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			dsHoaDon.add(hd);
			}	
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return dsHoaDon;
	}

	public Date convertToDateViaSqlDate(LocalDate ngay) {
	    return java.sql.Date.valueOf(ngay);
	}
	
	public boolean create(HoaDon hd) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		int n = 0;
		
		ngaylap = convertToDateViaSqlDate(hd.getNgayLapHD());
		
		try {
			stm = con.prepareStatement("insert into" + " HoaDon values(?,?,?,?)");
			
			stm.setString(1, hd.getMaHD());
			stm.setDate(2, ngaylap);
			stm.setString(4, String.valueOf(hd.getNv().getMaNV()));
			stm.setString(3, String.valueOf(hd.getKH().getMaKH()));
			
			n = stm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		finally {
			try {
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return n > 0;
	}
	
	public boolean update(HoaDon hd) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		int n = 0;
		try {
			stm = con.prepareStatement("update HoaDon set NgayLapHD=?, MaKH=?, MaNV=? where MaHD=?");
			
			stm.setDate(1, ngaylap);
			stm.setString(2, String.valueOf(hd.getKH().getMaKH()));
			stm.setString(3, String.valueOf(hd.getNv().getMaNV()));
                        stm.setString(4, String.valueOf(hd.getMaHD()));
			
			n = stm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		finally {
			try {
                assert stm != null;
                stm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return n > 0;
	}
	
	public boolean delete(HoaDon hd) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		
		int n = 0;
		try {
			stm = con.prepareStatement("delete from HoaDon where MaHD=?");
			
			stm.setString(1, hd.getMaHD());
			
			n = stm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		finally {
			try {
                assert stm != null;
                stm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return n > 0;
	}
	
	public HoaDon getHoaDonTheoMaHD(String id) {
		HoaDon hd = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm =null;
		try {
			String sql = "Select * from HoaDon where MaHD = ?";
			stm=con.prepareStatement(sql);
			stm.setString(1, id);
	
			ResultSet rs = stm.executeQuery();
	
			while (rs.next()) {
				
				String maHD = rs.getString("MaHD");	
				
				LocalDate ngayLapHD = rs.getDate(2).toLocalDate();    
				
				String maKH = rs.getString(3);
				KhachHang mkh = new KhachHang(maKH);
				
				String maNV = rs.getString(4);
				NhanVien mnv = new NhanVien(maNV);

				try {
					hd = new HoaDon(maHD, ngayLapHD, mkh, mnv);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
		try {
            assert stm != null;
            stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
			} 
		}
		return hd;
	}

	public double getTongTienTheoMaHD(String maHD) {
		double tongTien = 0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();

			String sql = "Select sum(CT_HoaDon.SoLuong*SanPham.GiaNhap*CT_HoaDon.ChietKhau) as TongTien from CT_HoaDon, SanPham where CT_HoaDon.MaSP = SanPham.MaSP and MaHD=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, maHD);

			ResultSet rs = st.executeQuery();

			while(rs.next()) {
				tongTien = rs.getDouble(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tongTien + (tongTien * 0.1);
	}
        
        public HoaDon getHoaDonTaoGanNhat() {
            HoaDon hd = null;
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            Statement stm =null;
            try {
                String sql = "Select TOP 1 * from HoaDon order by MaHD desc";
                stm=con.createStatement();

                ResultSet rs = stm.executeQuery(sql);

                while (rs.next()) {

                    String maHD = rs.getString("MaHD");	

                    LocalDate ngayLapHD = rs.getDate(2).toLocalDate();    

                    String maKH = rs.getString(3);
                    KhachHang mkh = new KhachHang(maKH);

                    String maNV = rs.getString(4);
                    NhanVien mnv = new NhanVien(maNV);

                    try {
                        hd = new HoaDon(maHD, ngayLapHD, mkh, mnv);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }

            } catch (SQLException e) {
                    e.printStackTrace();
            } finally {
                try {
                    assert stm != null;
                    stm.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } 
            }
            
            return hd;
        }
}
