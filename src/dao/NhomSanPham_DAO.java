package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectdb.ConnectDB;
import entity.NhomSanPham;

public class NhomSanPham_DAO {

	public NhomSanPham_DAO() {
		ConnectDB.getInstance();
	}
	public ArrayList<NhomSanPham> getAllNSP() {
		ArrayList<NhomSanPham> dsNSP= new ArrayList<>();
	try {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
	
		String sql = "Select * from NhomSanPham";
		Statement st = con.createStatement();
	
		ResultSet rs = st.executeQuery(sql);
	
		while(rs.next()) {
			String maNhom = rs.getString("MaNhom");
			String tenNhom = rs.getString(2);
			
			NhomSanPham nsp = new NhomSanPham(maNhom, tenNhom);
			dsNSP.add(nsp);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return dsNSP;
	}
	
	public boolean create(NhomSanPham nsp) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		int n = 0;
		try {
			stm = con.prepareStatement("insert into" + " NhomSanPham values(?,?)");
			
			stm.setString(1, nsp.getMaNhom());
			stm.setString(2, nsp.getTenNhom());
			
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
	
	public boolean update(NhomSanPham nsp) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		int n = 0;
		try {
			stm = con.prepareStatement("update NhomSanPham set TenNhom=? where MaNhom=?");
			
			stm.setString(1, nsp.getMaNhom());
			stm.setString(2, nsp.getTenNhom());
			
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
	
	public boolean delete(NhomSanPham nsp) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		
		int n = 0;
		try {
			stm = con.prepareStatement("delete from NhomSanPham where MaNhom=?");
			
			stm.setString(1, nsp.getMaNhom());
			
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
	
	public ArrayList<NhomSanPham> getNhomSanPhamTheoMaNSP(String id) {
		ArrayList<NhomSanPham> dsNSP = new ArrayList<>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm =null;
		try {
			String sql = "Select * from NhomSanPham where MaNhom = ?";
			stm=con.prepareStatement(sql);
			stm.setString(1, id);
	
			ResultSet rs = stm.executeQuery(sql);
	
			while (rs.next()) {
				
				String maNhom = rs.getString("MaNhom");
				String tenNhom = rs.getString(2);
				
				NhomSanPham nsp = new NhomSanPham(maNhom, tenNhom);
				dsNSP.add(nsp);
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
		return dsNSP;
	}
}
