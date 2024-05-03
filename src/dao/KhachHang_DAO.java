package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectdb.ConnectDB;
import entity.KhachHang;


public class KhachHang_DAO {

	public KhachHang_DAO() {
	}

	public ArrayList<KhachHang> getAllKhachHang() {
		ArrayList<KhachHang> dsKhachHang = new ArrayList<>();
	try {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		
		String sql = "Select * from KhachHang";
		Statement st = con.createStatement();
		
		ResultSet rs = st.executeQuery(sql);
		
		while(rs.next()) {
			String maKH = rs.getString("MaKH");
			String tenKH = rs.getString(2);
			String diaChi = rs.getString(3);
			String sdt = rs.getString(4);
			String email = rs.getString(5);
			int diemTL = rs.getInt(6);

			KhachHang kh;
			try {
				kh = new KhachHang(maKH, tenKH, diaChi, sdt, email, diemTL);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			dsKhachHang.add(kh);
		}	
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return dsKhachHang;
	}
        
        public boolean create(KhachHang kh) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		int n = 0;
		try {
			stm = con.prepareStatement("insert into" + " KhachHang values('',?,?,?,?,?)");
			
			stm.setString(1, kh.getTenKH());
			stm.setString(2, kh.getDiaChi());
			stm.setString(3, kh.getSdt());
			stm.setString(4, kh.getEmail());
			stm.setInt(5, kh.getDiemTL());
			
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
	
	public boolean update(KhachHang kh) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		int n = 0;
		try {
			stm = con.prepareStatement("update KhachHang set TenKH=?, "
					+ "DiaChi=?, SoDT=?, DCMail=?, DiemTL=? where MaKH=?");
			
			stm.setString(6, kh.getMaKH());
			stm.setString(1, kh.getTenKH());
			stm.setString(2, kh.getDiaChi());
			stm.setString(3, kh.getSdt());
			stm.setString(4, kh.getEmail());
			stm.setInt(5, kh.getDiemTL());
			
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
	
	public boolean delete(KhachHang kh) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		
		int n = 0;
		try {
			stm = con.prepareStatement("delete from KhachHang where MaKH=?");
			
			stm.setString(1, kh.getMaKH());
			
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
	
	public KhachHang getKhachHangTheoMaKH(String id) {
		KhachHang kh = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm =null;
		try {
			String sql = "Select * from KhachHang where MaKH = ?";
			stm=con.prepareStatement(sql);
			stm.setString(1, id);
	
			ResultSet rs = stm.executeQuery();
	
			while (rs.next()) {
				String maKH = rs.getString("MaKH");
				String tenKH = rs.getString(2);
				String diaChi = rs.getString(3);
				String sdt = rs.getString(4);
				String email = rs.getString(5);
				int diemTL = rs.getInt(6);


				try {
					kh = new KhachHang(maKH, tenKH, diaChi, sdt, email, diemTL);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}

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
		return kh;
	}

	public KhachHang getKHTheoSdt(String sdt) {
		KhachHang kh = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		try {
			String sql = "Select * from KhachHang where SoDT = ?";
			stm = con.prepareStatement(sql);
			stm.setString(1, sdt);

			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				String maKH = rs.getString("MaKH");
				String tenKH = rs.getString(2);
				String diaChi = rs.getString(3);
				String email = rs.getString(5);
				int diemTL = rs.getInt(6);

				try {
					kh = new KhachHang(maKH, tenKH, diaChi, sdt, email, diemTL);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}

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
		return kh;
	}
}
