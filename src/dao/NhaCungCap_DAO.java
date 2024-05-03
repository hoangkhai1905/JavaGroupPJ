package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectdb.ConnectDB;
import entity.NhaCungCap;

public class NhaCungCap_DAO {
	public ArrayList<NhaCungCap> getallNCC() {
		ArrayList<NhaCungCap> dsNCC = new ArrayList<>();
	try {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
	
		String sql = "Select * from NhaCungCap";
		Statement st = con.createStatement();
	
		ResultSet rs = st.executeQuery(sql);
	
		while(rs.next()) {
			String maNCC = rs.getString("MaNCC");
			String tenNCC = rs.getString(2);
			String diaChi = rs.getString(3);
			String sdt = rs.getString(4);
			String email = rs.getString(5);
			
			NhaCungCap ncc = new NhaCungCap(maNCC, tenNCC, diaChi, sdt, email);
			dsNCC.add(ncc);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return dsNCC;
	}
	
	public boolean create(NhaCungCap ncc) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		int n = 0;
		try {
			stm = con.prepareStatement("insert into" + "NhaCungCap values(?,?,?,?,?)");
			
			stm.setString(1, ncc.getMaNCC());
			stm.setString(2, ncc.getTenNCC());
			stm.setString(3, ncc.getDiaChi());
			stm.setString(4, ncc.getSdt());
			stm.setString(5, ncc.getEmail());
			
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
	
	public boolean update(NhaCungCap ncc) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		int n = 0;
		try {
			stm = con.prepareStatement("update NhaCungCap set TenNCC=?, DiaChi=?"
					+ "SoDT=?, Email=? where MaNhom=?");
			
			stm.setString(1, ncc.getMaNCC());
			stm.setString(2, ncc.getTenNCC());
			stm.setString(3, ncc.getDiaChi());
			stm.setString(4, ncc.getSdt());
			stm.setString(5, ncc.getEmail());
			
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
	
	public boolean delete(NhaCungCap ncc) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		
		int n = 0;
		try {
			stm = con.prepareStatement("delete from NhaCungCap where MaNhom=?");
			
			stm.setString(1, ncc.getMaNCC());
			
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
	
	public ArrayList<NhaCungCap> getNhaCCTheoMaNCC(String id) {
		ArrayList<NhaCungCap> dsNCC = new ArrayList<>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm =null;
		try {
			String sql = "Select * from NhaCungCap where MaNCC = ?";
			stm=con.prepareStatement(sql);
			stm.setString(1, id);
	
			ResultSet rs = stm.executeQuery(sql);
	
			while (rs.next()) {
				
				String maNCC = rs.getString("MaNCC");
				String tenNCC = rs.getString(2);
				String diaChi = rs.getString(3);
				String sdt = rs.getString(4);
				String email = rs.getString(5);
				
				NhaCungCap ncc = new NhaCungCap(maNCC, tenNCC, diaChi, sdt, email);
				dsNCC.add(ncc);
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
		return dsNCC;
	}

}
