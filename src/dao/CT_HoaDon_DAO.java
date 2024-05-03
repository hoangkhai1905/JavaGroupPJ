package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectdb.ConnectDB;
import entity.CT_HoaDon;
import entity.HoaDon;
import entity.SanPham;

public class CT_HoaDon_DAO {
	@SuppressWarnings("unused")
	private SanPham_DAO sanPham_dao = new SanPham_DAO();

	public CT_HoaDon_DAO() {
	}

	public ArrayList<CT_HoaDon> getAllCTHD() {
		ArrayList<CT_HoaDon> dsCTHD = new ArrayList<>();
	try {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		
		String sql = "Select * from CT_HoaDon";
		Statement st = con.createStatement();
		
		ResultSet rs = st.executeQuery(sql);
		
		while(rs.next()) {
			
			String maHD = rs.getString("MaHD");
			HoaDon mhd = new HoaDon_DAO().getHoaDonTheoMaHD(maHD);
			
			String maSP = rs.getString(2);
			//SanPham msp = new SanPham(maSP);
			SanPham sp = sanPham_dao.getDSSanPhamTheoMa(maSP).get(0);
			
			int soLuong = rs.getInt(3);
			
			double chietKhau = rs.getDouble(4);
			
			CT_HoaDon ct = new CT_HoaDon(mhd, sp, soLuong, chietKhau);
			dsCTHD.add(ct);
		}	
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return dsCTHD;
	}

	public boolean create(CT_HoaDon cthd) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		int n = 0;
		try {
			stm = con.prepareStatement("insert into CT_HoaDon values(?,?,?,?)");

			stm.setString(1, cthd.getHoaDon().getMaHD());
			stm.setString(2, cthd.getSanPham().getMaSP());
			stm.setInt(3, cthd.getSoLuong());
			stm.setDouble(4, cthd.getChietKhau());

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
	
	public boolean update(CT_HoaDon cthd) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		int n = 0;
		try {
			stm = con.prepareStatement("update CT_HoaDon set soLuong=? where MaHD=?");
			
			stm.setInt(1, cthd.getSoLuong());
			stm.setString(2, cthd.getHoaDon().getMaHD());
			
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
	
	public boolean delete(CT_HoaDon cthd) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		
		int n = 0;
		try {
			stm = con.prepareStatement("delete from CT_HoaDon where MaHD=?");
			
			stm.setString(1, cthd.getHoaDon().getMaHD());
			
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

	public ArrayList<CT_HoaDon> getALlCTHDTheoMa(String maHD) {
		ArrayList<CT_HoaDon> dsCTHD = new ArrayList<>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();

			String sql = "Select * from CT_HoaDon where MaHD=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, maHD);

			ResultSet rs = st.executeQuery();

			while(rs.next()) {

				HoaDon mhd = new HoaDon_DAO().getHoaDonTheoMaHD(maHD);

				String maSP = rs.getString(2);
				SanPham sp = sanPham_dao.getDSSanPhamTheoMa(maSP).get(0);

				int soLuong = rs.getInt(3);

				double chietKhau = rs.getDouble(4);

				CT_HoaDon ct = new CT_HoaDon(mhd, sp, soLuong, chietKhau);
				dsCTHD.add(ct);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsCTHD;
	}
        
        public boolean xoaCTHoaDonTheoMa(String maHD) {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            PreparedStatement stm = null;

            int n = 0;
            try {
                stm = con.prepareStatement("delete from CT_HoaDon where MaHD=?");

                stm.setString(1, maHD);

                n = stm.executeUpdate();

            } catch (SQLException e) {
                    e.printStackTrace();
            } finally {
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
}
