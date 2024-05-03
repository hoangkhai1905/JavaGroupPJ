/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import dao.*;
import entity.*;

import java.awt.event.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Thien Long
 */
public class Panel_BanHang extends JPanel {

    private ArrayList<SanPham> dsSp = new ArrayList<>();
    private SanPham_DAO spDAO;
    private KhachHang_DAO khDao;
    private NhanVien_DAO nvDao;
    private HoaDon_DAO hdDao;
    private CT_HoaDon_DAO cthdDao;
    double subTotal = 0;



    private DefaultTableModel tblModel_products;
    private DefaultTableModel tblModel_carts;
    private ArrayList<CT_HoaDon> gioHang = new ArrayList<>();

    NhanVien nv;
    KhachHang kh;
    /**
     * Creates new form BanHang
     */
    public Panel_BanHang(NhanVien nv) {
        this.nv = nv;

        initDaoObject();
        initTableModel();
        initComponents();
        dsSp = spDAO.getAllSanPham();
        render(dsSp);

        makh_txt.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String maKH = makh_txt.getText();
                if (!maKH.isEmpty()) {
                    kh = khDao.getKhachHangTheoMaKH(maKH);
                    if (kh != null) {
                        tenkh_txt.setText(kh.getTenKH());
                        sdt_txt.setText(kh.getSdt());
                    } else {
                        tenkh_txt.setText("");
                        sdt_txt.setText("");
                    }
                } else {
                    tenkh_txt.setText("");
                    sdt_txt.setText("");
                }
            }
        });

        sdt_txt.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String sdt = sdt_txt.getText();
                if (!sdt.isEmpty()) {
                    kh = khDao.getKHTheoSdt(sdt);
                    if (kh != null) {
                        makh_txt.setText(kh.getMaKH());
                        tenkh_txt.setText(kh.getTenKH());
                    } else {
                        makh_txt.setText("");
                        tenkh_txt.setText("");
                    }
                } else {
                    tenkh_txt.setText("");
                    sdt_txt.setText("");
                }
            }
        });
        
        tienNhan_txtField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                double tien_thua =0;
                if (!tienNhan_txtField.getText().isEmpty()) {
                    tien_thua = Double.parseDouble(String.format("%.0f", Double.valueOf(tienNhan_txtField.getText()))) - Double.parseDouble(total_txtField.getText());
                }
                if (tien_thua >= 0) {
                    tienThua_txt.setText(String.format("%.0f", tien_thua));
                } else {
                    tienThua_txt.setText("Số tiền không đủ để thanh toán");
                }
            }
        });
    }

    public void initDaoObject(){
        spDAO = new SanPham_DAO();
        khDao = new KhachHang_DAO();
        nvDao = new NhanVien_DAO();
        hdDao = new HoaDon_DAO();
        cthdDao = new CT_HoaDon_DAO();
    }

    public void initTableModel(){
        tblModel_products =  new DefaultTableModel(new String[]{"Mã SP", "Tên SP", "Nhà Cung Cấp", "Số Lượng", "Giá Bán"}, 0);
        tblModel_carts = new DefaultTableModel(new String[]{"Mã SP", "Tên SP", "Số Lượng", "Đơn Giá", "Tổng"}, 0);
    }

    public void showMessageFocus(String msg, JTextField txt) {
        JOptionPane.showMessageDialog(this, msg);
        txt.selectAll();
        txt.requestFocus();
    }

    public void render(ArrayList<SanPham> dsSp){
        tblModel_products = (DefaultTableModel) tblSanPham.getModel();
        tblModel_carts = (DefaultTableModel) jTable2.getModel();

        tblModel_products.setRowCount(0);
        xoaHetDuLieuTrenTBSP();
        for(SanPham sp : dsSp){
            tblModel_products.addRow(new Object[]{
                sp.getMaSP(), sp.getTenSP(), sp.getMaNCC(), sp.getSoTon(), sp.getGiaNhap()
            });
        }
    }

    public void renderCart(){
        ArrayList<CT_HoaDon> list = gioHang;
        tblModel_carts.setRowCount(0);
        subTotal = 0;
        for(CT_HoaDon ct : list){
            SanPham sp = ct.getSanPham();
            double giaBan = sp.getGiaNhap() - (sp.getGiaNhap() * ct.getChietKhau());
            tblModel_carts.addRow(new Object[]{
                sp.getMaSP(), sp.getTenSP(), ct.getSoLuong(), String.format("%.0f", giaBan), String.format("%.0f", ct.getSoLuong() * giaBan)
            });
            subTotal += ct.getSoLuong() * giaBan;
        }
    }

    public void addCart(){
        String quantity_txt = JOptionPane.showInputDialog(this, "Nhập số lượng: ", 1);

        try {

            int row = tblSanPham.getSelectedRow();

            if(row >= 0){
                int quantity = Integer.parseInt(quantity_txt);
                System.out.println(quantity);
                if (quantity <= 0) {
                    JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn 0");
                    return;
                }
                String maSP = (String) tblModel_products.getValueAt(row, 0);
                SanPham sp = spDAO.getDSSanPhamTheoMa(maSP).get(0);
                //SanPham sp = dsSp.get(row);

                int tonKho = sp.getSoTon();
                if (quantity > tonKho) {
                    JOptionPane.showMessageDialog(this, "Số lượng sản phẩm trong kho không đủ");
                    return;
                }
                CT_HoaDon ct = new CT_HoaDon(new HoaDon(""), sp, quantity);

                if (gioHang.contains(ct)) {
                    int prevSl = gioHang.get(gioHang.indexOf(ct)).getSoLuong();

                    if (prevSl + quantity > tonKho) {
                        JOptionPane.showMessageDialog(this, "Số lượng sản phẩm trong kho không đủ");
                        return;
                    }
                    gioHang.get(gioHang.indexOf(ct)).setSoLuong(prevSl + quantity);
                    renderCart();
                } else {
                    System.out.println(gioHang.add(ct));
                    renderCart();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Số lượng không hợp lệ");
            //e.printStackTrace();
        }
        if (subTotal > 0) {
            total_txtField.setText(String.format("%.0f", subTotal*1.1));
        }
    }
    
    public void clearCart(){
        gioHang.remove(jTable2.getSelectedRow());
        renderCart();
    }

    public void clearAllCart(){
        gioHang.clear();
        renderCart();
    }

    public void clear(){
        searchTxt.setText("");
        render(dsSp);
    }


    //tìm sản phẩm
    public void search(){
        ArrayList<SanPham> dsTim = new ArrayList<>();
        String input = searchTxt.getText();
        if(!input.isEmpty()) {
            for(SanPham sp : dsSp){
                if(sp.getMaSP().toLowerCase().contains(input.toLowerCase())){
                    dsTim.add(sp);
                }
            }
            render(dsTim);
        } else {
            render(dsSp);
        }
    }

    public void handleTienKhachDua() {
        String tienKhachDua = tienNhan_txtField.getText();
        if (!tienNhan_txtField.getText().trim().isEmpty()) {
            try {
                double khachDua = Double.parseDouble(tienKhachDua);
                double tienThoi = khachDua - Double.parseDouble(total_txtField.getText());
                if (tienThoi < 0) {
                    showMessageFocus("Tiền khách đưa không đủ", tienNhan_txtField);
                }
            } catch (Exception e) {
                showMessageFocus("Tiền khách đưa không hợp lệ", tienNhan_txtField);
            }
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        searchTxt = new javax.swing.JTextField();
        searchBtn = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        clearCartBtn = new javax.swing.JButton();
        clearAllCart_Btn = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        sdt_txt = new javax.swing.JTextField();
        tenkh_txt = new javax.swing.JTextField();
        cmb_PhuongThuc = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        total_txtField = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        tienNhan_txtField = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        cancel_Btn = new javax.swing.JButton();
        print_Btn = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        makh_txt = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        tienThua_txt = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        clearBtn = new javax.swing.JButton();
        addCartBtn = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1000, 700));
        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));
        jPanel1.setPreferredSize(new java.awt.Dimension(918, 50));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Mã Sản Phẩm");

        searchBtn.setBackground(new java.awt.Color(0, 102, 102));
        searchBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        searchBtn.setForeground(new java.awt.Color(255, 255, 255));
        searchBtn.setText("Tìm Kiếm");
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });

        jPanel9.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(searchTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)
                .addGap(12, 12, 12)
                .addComponent(searchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(133, 133, 133))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(124, 124, 124)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(searchBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setBackground(new java.awt.Color(0, 102, 102));
        jPanel2.setForeground(new java.awt.Color(0, 102, 102));
        jPanel2.setPreferredSize(new java.awt.Dimension(400, 420));

        jTable2.setModel(tblModel_carts);
        jScrollPane2.setViewportView(jTable2);

        jLabel3.setText("Danh Sách Giỏ hàng");

        clearCartBtn.setBackground(new java.awt.Color(0, 102, 102));
        clearCartBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        clearCartBtn.setForeground(new java.awt.Color(255, 255, 255));
        clearCartBtn.setText("Xóa Khỏi Giỏ Hàng");
        clearCartBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearCartBtnActionPerformed(evt);
            }
        });

        clearAllCart_Btn.setBackground(new java.awt.Color(0, 102, 102));
        clearAllCart_Btn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        clearAllCart_Btn.setForeground(new java.awt.Color(255, 255, 255));
        clearAllCart_Btn.setText("Xóa tất cả");
        clearAllCart_Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearAllCart_BtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(clearCartBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(clearAllCart_Btn, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clearCartBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(clearAllCart_Btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabel5.setText("Số điện thoại");

        jLabel6.setText("Tên khách hàng");

        jLabel10.setText("Hình thức thanh toán");

        cmb_PhuongThuc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tiền mặt", "Chuyển khoản"}));
        cmb_PhuongThuc.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_PhuongThucItemStateChanged(evt);
            }
        });

        jLabel12.setText("Thành Tiền(đã bao gồm thuế)");

        jLabel13.setText("Tiền Khách Đưa");

        cancel_Btn.setBackground(new java.awt.Color(255, 102, 0));
        cancel_Btn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cancel_Btn.setForeground(new java.awt.Color(255, 255, 255));
        cancel_Btn.setText("Hủy Bỏ");
        cancel_Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancel_BtnActionPerformed(evt);
            }
        });

        print_Btn.setBackground(new java.awt.Color(0, 102, 102));
        print_Btn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        print_Btn.setForeground(new java.awt.Color(255, 255, 255));
        print_Btn.setText("Xuất Hóa Đơn");
        print_Btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                print_BtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cancel_Btn, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(print_Btn, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancel_Btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(print_Btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabel4.setText("THÔNG TIN NHẬP ĐƠN");

        jLabel9.setText("Mã khách hàng");

        jLabel14.setText("Tiền thừa");

        tienThua_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tienThua_txtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(115, 115, 115)
                        .addComponent(jLabel4))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                    .addGap(55, 55, 55)
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel9)))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING))))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel8)))
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(126, 126, 126)
                                .addComponent(jLabel11))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmb_PhuongThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(makh_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(sdt_txt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(total_txtField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tienNhan_txtField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tienThua_txt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(tenkh_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(makh_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sdt_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tenkh_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(9, 9, 9)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(50, 50, 50)
                        .addComponent(jLabel7))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tienNhan_txtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmb_PhuongThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(total_txtField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(9, 9, 9)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(tienThua_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(89, Short.MAX_VALUE))
        );

        add(jPanel2, java.awt.BorderLayout.LINE_END);

        jPanel5.setLayout(new java.awt.BorderLayout());

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setPreferredSize(new java.awt.Dimension(518, 400));

        jLabel2.setText("Danh Sách Sản Phẩm");

        tblSanPham.setModel(tblModel_products);
        jScrollPane3.setViewportView(tblSanPham);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(456, Short.MAX_VALUE))
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE))
        );

        jPanel5.add(jPanel6, java.awt.BorderLayout.PAGE_START);

        jPanel10.setLayout(new java.awt.BorderLayout());

        jPanel11.setLayout(new java.awt.BorderLayout());

        clearBtn.setBackground(new java.awt.Color(0, 102, 102));
        clearBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        clearBtn.setForeground(new java.awt.Color(255, 255, 255));
        clearBtn.setText("Làm Mới");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });
        jPanel11.add(clearBtn, java.awt.BorderLayout.CENTER);

        addCartBtn.setBackground(new java.awt.Color(0, 102, 102));
        addCartBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        addCartBtn.setForeground(new java.awt.Color(255, 255, 255));
        addCartBtn.setText("Thêm vào giỏ hàng");
        addCartBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCartBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(375, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(addCartBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addCartBtn))
                .addContainerGap(215, Short.MAX_VALUE))
        );

        jPanel5.add(jPanel3, java.awt.BorderLayout.CENTER);

        add(jPanel5, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void addCartBtnActionPerformed(ActionEvent evt) {//GEN-FIRST:event_addCartBtnActionPerformed
            // TODO add your handling code here:
        int row = tblSanPham.getSelectedRow();
        if(row >= 0) {
            addCart();
        } else {
            JOptionPane.showMessageDialog(this, "Chọn sản phẩm cần thêm vào giỏ hàng");
        }
    }//GEN-LAST:event_addCartBtnActionPerformed

    private void clearBtnActionPerformed(ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_clearBtnActionPerformed

    private void clearCartBtnActionPerformed(ActionEvent evt) {//GEN-FIRST:event_clearCartBtnActionPerformed
        // TODO add your handling code here:
        int row = jTable2.getSelectedRow();
        if(row >= 0) {
            clearCart();
        } else {
            JOptionPane.showMessageDialog(this, "Chọn sản phẩm cần xóa khỏi giỏ hàng");
        }
    }//GEN-LAST:event_clearCartBtnActionPerformed

    private void cancel_BtnActionPerformed(ActionEvent evt) {//GEN-FIRST:event_cancel_BtnActionPerformed
        // TODO add your handling code here:
        clearAllCart();
        makh_txt.setText("");
        tenkh_txt.setText("");
        sdt_txt.setText("");
        tienNhan_txtField.setText("");
        total_txtField.setText("");
        tienThua_txt.setText("");
        cmb_PhuongThuc.setSelectedIndex(0);
//        maHD = sinhMaHD();
        subTotal = 0;
        render(dsSp);

    }//GEN-LAST:event_cancel_BtnActionPerformed

    private void clearAllCart_BtnActionPerformed(ActionEvent evt) {//GEN-FIRST:event_clearAllCart_BtnActionPerformed
        // TODO add your handling code here:
        clearAllCart();
    }//GEN-LAST:event_clearAllCart_BtnActionPerformed

    private void searchBtnActionPerformed(ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        // TODO add your handling code here:
        if (!searchTxt.getText().isEmpty()) {
            search();
        } else {
            JOptionPane.showMessageDialog(this, "Nhập mã sản phẩm cần tìm");
            searchTxt.grabFocus();
        }
    }//GEN-LAST:event_searchBtnActionPerformed

    private void print_BtnActionPerformed(ActionEvent evt) {//GEN-FIRST:event_print_BtnActionPerformed
        // TODO add your handling code here:
        String maKH;
        String sdt;
        double tienNhan;
        double tienThua;
        int diemTichLuy = 0;
        if(gioHang.isEmpty()){
            JOptionPane.showMessageDialog(this, "Giỏ hàng trống");
            return;
        }
        if(makh_txt.getText().isEmpty() || tenkh_txt.getText().isEmpty() || sdt_txt.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Nhập thông tin khách hàng");
            makh_txt.requestFocus();
            return;
        }
        //tính thuế
        subTotal += (subTotal * 0.1);
        //tính tiền khách đưa
        if (tienNhan_txtField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nhập tiền khách đưa");
            tienNhan_txtField.requestFocus();
            return;
        } else {
            handleTienKhachDua();
            tienNhan = Double.parseDouble(tienNhan_txtField.getText());
            tienThua = Double.parseDouble(tienThua_txt.getText());
        }
        //tính điểm tích lũy
        if (kh.getDiemTL() > 0) {
            if (JOptionPane.showConfirmDialog(this, String.format("Bạn có %s điểm, có muốn sử dụng", kh.getDiemTL()), "Xác nhận", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                diemTichLuy = kh.getDiemTL();
                total_txtField.setText(String.format("%.0f", subTotal - kh.getDiemTL()));
                tienThua = tienNhan - subTotal + kh.getDiemTL();
                tienThua_txt.setText(String.format("%.0f", tienThua));
                subTotal -= kh.getDiemTL();
                kh.setDiemTL(0);
            
            }
        }
        HoaDon hd = new HoaDon("", LocalDate.now(), kh, nv);

        //cập nhật lại số lượng
        for(CT_HoaDon ct : gioHang){
            SanPham sp = ct.getSanPham();
            int soLuong = sp.getSoTon() - ct.getSoLuong();
            sp.setSoTon(soLuong);
            spDAO.update(sp);
        }

        if(hdDao.create(hd)){
            HoaDon invoice = hdDao.getHoaDonTaoGanNhat();
            for(CT_HoaDon ct : gioHang){
                ct.setHoaDon(invoice);
                cthdDao.create(ct);
            }
            JOptionPane.showMessageDialog(this, "Thêm hóa đơn thành công");
        } else {
            JOptionPane.showMessageDialog(this, "Thêm hóa đơn thất bại");
        }
        new Frame_HoaDon(hd, gioHang, nv, subTotal, tienNhan, tienThua, diemTichLuy).setVisible(true);
//        maHD = sinhMaHD();
        dsSp = spDAO.getAllSanPham();
        render(dsSp);
        gioHang.clear();
        renderCart();
        subTotal = 0;
        cancel_BtnActionPerformed(evt);
    }//GEN-LAST:event_print_BtnActionPerformed

    private void tienThua_txtActionPerformed(ActionEvent evt) {//GEN-FIRST:event_tienThua_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tienThua_txtActionPerformed

    private void cmb_PhuongThucItemStateChanged(ItemEvent evt) {//GEN-FIRST:event_cmb_PhuongThucItemStateChanged
        // TODO add your handling code here:
        String selectedItem = Objects.requireNonNull(cmb_PhuongThuc.getSelectedItem()).toString();
        
        if (selectedItem == null || selectedItem.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chọn hình thức thanh toán");
            cmb_PhuongThuc.requestFocus();
        } else {
            if (selectedItem.equals("Chuyển khoản")) {
                tienNhan_txtField.setText(total_txtField.getText());
            } else {
                tienNhan_txtField.setText("");
            }
        }
    }//GEN-LAST:event_cmb_PhuongThucItemStateChanged

    public void xoaHetDuLieuTrenTBSP() {
        DefaultTableModel dfm = (DefaultTableModel)tblSanPham.getModel();
        dfm.getDataVector().removeAllElements();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addCartBtn;
    private javax.swing.JButton cancel_Btn;
    private javax.swing.JButton clearAllCart_Btn;
    private javax.swing.JButton clearBtn;
    private javax.swing.JButton clearCartBtn;
    private javax.swing.JComboBox<String> cmb_PhuongThuc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField makh_txt;
    private javax.swing.JButton print_Btn;
    private javax.swing.JTextField sdt_txt;
    private javax.swing.JButton searchBtn;
    private javax.swing.JTextField searchTxt;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField tenkh_txt;
    private javax.swing.JTextField tienNhan_txtField;
    private javax.swing.JTextField tienThua_txt;
    private javax.swing.JTextField total_txtField;
    // End of variables declaration//GEN-END:variables

    public static void main(String[] args) {
        JFrame frame = new JFrame();

        frame.add(new Panel_BanHang(new NhanVien("NV001")));
        frame.setSize(1000, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
