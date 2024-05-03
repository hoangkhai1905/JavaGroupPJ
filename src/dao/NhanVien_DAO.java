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
import entity.NhanVien;

public class NhanVien_DAO {

	public NhanVien_DAO() {
	}

	public ArrayList<NhanVien> getAllNhanVien() {
		ArrayList<NhanVien> dsNhanVien = new ArrayList<>();
	try {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		
		String sql = "Select * from NhanVien";
		Statement st = con.createStatement();
		
		ResultSet rs = st.executeQuery(sql);
		
		while(rs.next()) {
			String maNV = rs.getString("MaNV");
			String ho = rs.getString(2);
			String ten = rs.getString(3);
			int tuoi = rs.getInt(4);
			boolean phai = rs.getBoolean(5);
			String diaChi = rs.getString(6);
			String sdt = rs.getString(7);
			double luong = rs.getDouble(8);
			String ca = rs.getString(9);

			NhanVien nv;
			try {
				nv = new NhanVien(maNV, ho, ten, tuoi, phai, diaChi, sdt, luong, ca);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			dsNhanVien.add(nv);
		}	
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return dsNhanVien;
	}
	
	public boolean create(NhanVien nv) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		int n = 0;
		try {
                    stm = con.prepareStatement("insert into" + " NhanVien values(?,?,?,?,?,?,?,?,?)");


                    stm.setString(1, "");
                    stm.setString(2, nv.getHo());
                    stm.setString(3, nv.getTen());
                    stm.setInt(4, nv.getTuoi());
                    stm.setBoolean(5, nv.isPhai());
                    stm.setString(6, nv.getDiaChi());
                    stm.setString(7, nv.getSdt());
                    stm.setDouble(8, nv.getLuong());
                    stm.setString(9, nv.getCa());

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
	
	public boolean update(NhanVien nv) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		int n = 0;
		try {
			stm = con.prepareStatement("update NhanVien set Ho=?, Ten=?, Tuoi=?, "
					+ "Phai=?, DiaChi=?, SoDT=?, TienLuong=?, Ca=? where MaNV=?");
			
			stm.setString(9, nv.getMaNV());
			stm.setString(1, nv.getHo());
			stm.setString(2, nv.getTen());
			stm.setInt(3, nv.getTuoi());
			stm.setBoolean(4, nv.isPhai());
			stm.setString(5, nv.getDiaChi());
			stm.setString(6, nv.getSdt());
			stm.setDouble(7, nv.getLuong());
			stm.setString(8, nv.getCa());
			
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
	
	public boolean delete(NhanVien nv) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm = null;
		
		int n = 0;
		try {
			stm = con.prepareStatement("delete from NhanVien where MaNV=?");
			
			stm.setString(1, nv.getMaNV());
			
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
	
	public NhanVien getNhanVienTheoMaNV(String id) {
		NhanVien nv = null;
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stm =null;
		try {
			String sql = "Select * from NhanVien where MaNV = ?";
			stm=con.prepareStatement(sql);
			stm.setString(1, id);
	
			ResultSet rs = stm.executeQuery();
	
			while (rs.next()) {
				String maNV = rs.getString("MaNV");
				String ho = rs.getString(2);
				String ten = rs.getString(3);
				int tuoi = rs.getInt(4);
				boolean phai = rs.getBoolean(5);
				String diaChi = rs.getString(6);
				String sdt = rs.getString(7);
				double luong = rs.getDouble(8);
				String ca = rs.getString(9);

				try {
					nv = new NhanVien(maNV, ho, ten, tuoi, phai, diaChi, sdt, luong, ca);
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
		return nv;
	}
}
