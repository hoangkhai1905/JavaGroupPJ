package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

import connectdb.ConnectDB;
import entity.SanPham;

public class ThongKe_DAO {
	
    public ArrayList<SanPham> get5SanPhamBanChay() {
        int thang = LocalDate.now().getMonthValue();
        int nam = LocalDate.now().getYear();
        ArrayList<SanPham> result = new ArrayList<>();

        try {
            PreparedStatement st = ConnectDB.connection.prepareStatement("""
                                                                   select top 5 ct.MaSP
                                                                   from CT_HoaDon as ct join HoaDon as hd on ct.MaHD = hd.MaHD
                                                                         join SanPham as sp on ct.MaSP = sp.MaSP
                                                                   where MONTH(ngayLapHD) = ? and Year(NgayLapHD) = ?
                                                                   group by ct.MaSP
                                                                   order by SUM(SoLuong*GiaNhap*chietKhau) desc
                                                                   """);
            st.setInt(1, thang);
            st.setInt(2, nam);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                String maSP = rs.getString(1);
                result.add(
                        new SanPham_DAO().getDSSanPhamTheoMa(maSP).get(0));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
    

    public ArrayList<SanPham> get5SanPhamBanChayTheoThang(int thang) {
        int nam = LocalDate.now().getYear();
        ArrayList<SanPham> result = new ArrayList<>();

        try {
            PreparedStatement st = ConnectDB.connection.prepareStatement("""
                                                                   select top 5 sp.MaSP
                                                                   from CT_HoaDon as ct join HoaDon as hd on ct.MaHD = hd.MaHD
                                                                         join SanPham as sp on ct.MaSP = sp.MaSP
                                                                   where MONTH(ngayLapHD) = ? and Year(NgayLapHD) = ?
                                                                   group by MONTH
                                                                   order by SUM(soLuong*giaNhap*chietKhau) desc
                                                                   """);
            st.setInt(1, thang);
            st.setInt(2, nam);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                String maSP = rs.getString(1);
                result.add(
                        new SanPham_DAO().getDSSanPhamTheoMa(maSP).get(0));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
    
        public ArrayList<SanPham> get5SanPhamBanChayTheoThangNam(int thang, int nam) {
        ArrayList<SanPham> result = new ArrayList<>();

        try {
            PreparedStatement st = ConnectDB.connection.prepareStatement("""
                                                                   select top 5 ct.MaSP, TenSP,sum(Soluong) as sl, SUM(soluong * GiaNhap*(1- chietKhau)) as total
                                                                   from CT_HoaDon as ct join HoaDon as hd on ct.MaHD = hd.MaHD
                                                                           join SanPham as sp on ct.MaSP = sp.MaSP
                                                                   where MONTH(ngayLapHD) = ? and Year(NgayLapHD) = ?
                                                                   group by ct.MaSP, TenSP
                                                                   order by SUM(SoLuong*GiaNhap*chietKhau) desc
                                                                   """);
            st.setInt(1, thang);
            st.setInt(2, nam);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                String maSP = rs.getString(1);
                result.add(
                        new SanPham_DAO().getDSSanPhamTheoMa(maSP).get(0));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }




        public int getSoLuongBanSanPham(String maSP) {
        int result = 0;
        int thang = LocalDate.now().getMonthValue();
        int nam = LocalDate.now().getYear();
        try {
            PreparedStatement st = ConnectDB.connection.prepareStatement("""
                                                                   select SUM(soLuong) as sl
                                                                   from CT_HoaDon as ct join HoaDon as hd on ct.MaHD = hd.MaHD
                                                                   where MONTH(ngayLapHD) = ? and Year(NgayLapHD) = ? and MaSP = ?
                                                                   group by MaSP
                                                                   order by SUM(soLuong) desc
                                                                   """);
            st.setInt(1, thang);
            st.setInt(2, nam);
            st.setString(3, maSP);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                result = rs.getInt("sl");
   
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
    public int getSoLuongBanSanPhamTheoThang(String maSP, int thang) {
        int nam = LocalDate.now().getYear();
        int result = 0;

        try {
            PreparedStatement st = ConnectDB.connection.prepareStatement("""
                                                                   select SUM(soLuong) as sl
                                                                   from CT_HoaDon as ct join HoaDon as hd on ct.MaHD = hd.MaHD
                                                                   where MONTH(ngayLapHD) = ? and Year(NgayLapHD) = ? and MaSP = ?
                                                                   group by MaSP
                                                                   order by SUM(soLuong) desc
                                                                   """);	
            st.setInt(1, thang);
            st.setInt(2, nam);
            st.setString(3, maSP);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                result = rs.getInt("sl");
   
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
        
    public int getSoLuongBanSanPhamTheoThangNam(String maSP, int thang, int nam) {
        int result = 0;

        try {
            PreparedStatement st = ConnectDB.connection.prepareStatement("""
                                                                   select SUM(soLuong) as sl
                                                                   from CT_HoaDon as ct join HoaDon as hd on ct.MaHD = hd.MaHD
                                                                   where MONTH(ngayLapHD) = ? and Year(NgayLapHD) = ? and MaSP = ?
                                                                   group by MaSP
                                                                   order by SUM(soLuong) desc
                                                                   """);
            st.setInt(1, thang);
            st.setInt(2, nam);
            st.setString(3, maSP);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                result = rs.getInt("sl");
   
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
    
    public ArrayList<SanPham> getSanPham(int thang, int nam) {
        ArrayList<SanPham> result = new ArrayList<>();

        try {
            PreparedStatement st = ConnectDB.connection.prepareStatement("""
                                                                   select ct.MaSP
                                                                   from CT_HoaDon as ct join HoaDon as hd on ct.MaHD = hd.MaHD
                                                                         join SanPham as sp on ct.MaSP = sp.MaSP
                                                                   where MONTH(ngayLapHD) = ? and Year(NgayLapHD) = ?
                                                                   group by ct.MaSP
                                                                   order by SUM(soLuong*giaNhap*chietKhau) desc
                                                                   """);
            st.setInt(1, thang);
            st.setInt(2, nam);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                String maSP = rs.getString(1);
                result.add(
                        new SanPham_DAO().getDSSanPhamTheoMa(maSP).get(0));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public double getDoanhThuSanPham(String maSP) {
        int thang = LocalDate.now().getMonthValue();
        int nam = LocalDate.now().getYear();
        try {
            PreparedStatement st = ConnectDB.connection.prepareStatement("""
                                                                   select sum(soLuong*giaNhap*chietKhau)
                                                                   from CT_HoaDon as ct join HoaDon as hd on ct.MaHD= hd.MaHD
                                                                         join SanPham as sp on ct.MaSP = sp.MaSP
                                                                   where ct.MaSP = ? and MONTH(ngayLapHD) = ? and Year(NgayLapHD) = ?
                                                                   group by ct.MaSP                                           
                                                                   """);
            st.setString(1, maSP);
            st.setInt(2, thang);
            st.setInt(3, nam);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public double getDoanhThuSanPhamTheoThang(String maSP, int thang) {
        int nam = LocalDate.now().getYear();
        try {
            PreparedStatement st = ConnectDB.connection.prepareStatement("""
                                                                   select sum(soLuong*giaNhap*chieKhau)
                                                                   from CT_HoaDon as ct join HoaDon as hd on ct.MaHD = hd.MaHD
                                                                         join SanPham as sp on ct.MaSP = sp.MaSP
                                                                   where ct.MaSP = ? and MONTH(ngayLapHD) = ? and Year(NgayLapHD) = ?
                                                                   group by ct.MaSP                                              
                                                                   """);
            st.setString(1, maSP);
            st.setInt(2, thang);
            st.setInt(3, nam);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public double getDoanhThuSanPhamTheoThangNam(String maSP, int thang, int nam) {
        try {
            PreparedStatement st = ConnectDB.connection.prepareStatement("""
                                                                   select sum(soLuong*giaNhap*chietKhau)
                                                                   from CT_HoaDon as ct join HoaDon as hd on ct.MaHD = hd.MaHD
                                                                         join SanPham as sp on ct.MaSP = sp.MaSP
                                                                   where ct.MaSP = ? and MONTH(ngayLapHD) = ? and Year(NgayLapHD) = ?
                                                                   group by ct.MaSP             
                                                                   """);
            st.setString(1, maSP);
            st.setInt(2, thang);
            st.setInt(3, nam);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public double[] getDoanhThu12Thang() {
        double[] result = new double[12];

        for (int i = 0; i < result.length; i++) {
            result[i] = 0;
        }

        int nam = LocalDate.now().getYear();

        try {
            PreparedStatement st = ConnectDB.connection.prepareStatement("select MONTH(ngayLapHD) as thang, "
            		+ "sum(soLuong*giaNhap*chietKhau) as tong from CT_HoaDon as ct join HoaDon as hd on ct.MaHD = hd.MaHD"
                        + " join SanPham as sp on ct.MaSP = sp.MaSP"
            		+ " where YEAR(ngayLapHD) = ? group by MONTH(ngayLapHD)");
            st.setInt(1, nam);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int thang = rs.getInt("thang");
                double tong = rs.getDouble("tong");

                result[thang - 1] = tong;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public double[] getDoanhThu12ThangTheoNam(int year) {
        double[] result = new double[12];

        for (int i = 0; i < result.length; i++) {
            result[i] = 0;
        }

        try {
            PreparedStatement st = ConnectDB.connection.prepareStatement("select MONTH(ngayLapHD) as thang, "
            		+ "sum(soLuong*giaNhap*(1-chietKhau)) as tong from CT_HoaDon as ct join HoaDon as hd on ct.MaHD = hd.MaHD "
                        + "join SanPham as sp on ct.MaSP = sp.MaSP"
            		+ " where YEAR(ngayLapHD) = ? group by MONTH(ngayLapHD)");
            st.setInt(1, year);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                int thang = rs.getInt("thang");
                double tong = rs.getDouble("tong");

                result[thang - 1] = tong;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

}
