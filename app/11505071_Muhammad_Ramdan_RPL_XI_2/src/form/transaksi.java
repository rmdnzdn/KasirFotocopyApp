/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author zidun
 */
public class transaksi extends javax.swing.JFrame {

    public Statement st;
    public ResultSet rs;
    public Connection cn = koneksi.koneksi.getKoneksi();
    public DefaultTableModel tabmodel, tabbarang;
    public Date hariini = new Date();
    public SimpleDateFormat format = new SimpleDateFormat("dd-MM-Y");
    public String hari = format.format(hariini);
    public int sub;
    
    public void judul(){
        Object[] judul = {"No.Transaksi","Kode Barang","Nama Barang","Harga Barang","Jumlah Beli","Subtotal"};
        tabmodel = new DefaultTableModel(null,judul);
        tbl_transaksi.setModel(tabmodel);
    }
    public void judulbarang(){
        Object[] judul = {"KODE BARANG","NAMA BARANG","STOK BARANG","HARGA BARANG"};
        tabbarang = new DefaultTableModel(null,judul);
        tbl_barang.setModel(tabbarang);
    }
    
    public void tampildata(){
        try {
            st = cn.createStatement();
            tabmodel.getDataVector().removeAllElements();
            tabmodel.fireTableDataChanged();
            rs = st.executeQuery("SELECT * FROM qw_transaksi WHERE kode_transaksi = '"+no_transaksi.getText()+"' AND status = '0'");
            while (rs.next()) {
                Object[] data = {
                    rs.getString("kode_transaksi"),
                    rs.getString("kode_barang"),
                    rs.getString("nama_barang"),
                    rs.getInt("harga"),
                    rs.getInt("jumlah"),
                    rs.getInt("subtotal")
                };tabmodel.addRow(data);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void tampilbarang(){
        try {
            st = cn.createStatement();
            tabbarang.getDataVector().removeAllElements();
            tabbarang.fireTableDataChanged();
            rs = st.executeQuery("SELECT * FROM tbl_barang WHERE stok > 0");
            while(rs.next()){
                Object[] data = {
                    rs.getString("kode_barang"),
                    rs.getString("nama_barang"),
                    rs.getInt("stok"),
                    rs.getInt("harga")
                };
                tabbarang.addRow(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void reset(){
        tkode.setText("");
        tnama.setText("");
        tharga.setText("");
        tjumlah.setText("");
        ttotal.setText("");
        bhapus.setVisible(false);
        bedit.setVisible(false);
        bbeli.setVisible(true);
        breset.setVisible(true);
        tkode.requestFocus();
    }
    
    public void aksi(String sql, String pesan){
        try {
            st = cn.createStatement();
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, pesan);
            tampildata();
            reset();
            total();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void hitung(){
        if (tjumlah.getText().isEmpty() || tjumlah.getText().equals("")) {
            tjumlah.setText("");
            ttotal.setText("");
        } else {
            int a = Integer.parseInt(tharga.getText());
            int b  = Integer.parseInt(tjumlah.getText());
            int c = a * b;
            ttotal.setText(String.valueOf(c));
        }   
    }
   
    
    public void total(){
        try {
            st= cn.createStatement();
            rs = st.executeQuery("SELECT SUM(subtotal) as 'sub' FROM tbl_transaksi WHERE kode_transaksi = '"+no_transaksi.getText()+"' AND status = '0'");
            rs.next();
            sub = rs.getInt(1);
            String a = String.valueOf(sub);
            lsubtotal.setText("Rp. "+a);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void autokode(){
        try {
            String tampilkode = "";
            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("YMMdd");
            String tgl = format.format(date);
            st = cn.createStatement();
            rs = st.executeQuery("SELECT MAX(substr(kode_transaksi,9)) FROM tbl_transaksi WHERE status = '1' AND kode_transaksi LIKE '%"+tgl+"%' order by kode_transaksi DESC limit 0,1");
//            rs = st.executeQuery("SELECT RIGHT(kode_barang,3) FROM tbl_barang WHERE kode_barang LIKE '%"+tgl+"%' order by kode_barang DESC limit 0,1");
            if (rs.next()) {
                int no = rs.getInt(1)+1;
                if (no < 10) {
                    tampilkode = tgl+"00"+String.valueOf(no);
                } else if(no > 9 && no <= 99) {
                     tampilkode = tgl+"0"+String.valueOf(no);
                }else{
                    tampilkode = tgl+String.valueOf(no);
                }
                no_transaksi.setText(tampilkode);
            }else{
                no_transaksi.setText(tgl+"001");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Creates new form transaksi
     */
    public transaksi() {
        initComponents();
        judul();
        tampildata();
        judulbarang();
        tampilbarang();
        reset();
        autokode();
        total();
        lkembali.setText("Rp. 0");
        bcek.requestFocus();
        ltgl.setText(hari);  
        bhapus.setVisible(false);
        bedit.setVisible(false);
        bbeli.setVisible(true);
        breset.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popup_barang = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_barang = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tkode = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tnama = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        tharga = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_transaksi = new javax.swing.JTable();
        tbayar = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        tjumlah = new javax.swing.JTextField();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        ttotal = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        lsubtotal = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        tcari = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        no_transaksi = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        ltgl = new javax.swing.JLabel();
        close = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        bbeli = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        bedit = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        breset = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        bhapus = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        bselesai = new javax.swing.JPanel();
        lselesai = new javax.swing.JLabel();
        bcek = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        lsubtotal1 = new javax.swing.JLabel();
        lkembali = new javax.swing.JLabel();

        popup_barang.setUndecorated(true);
        popup_barang.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(74, 131, 122));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbl_barang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbl_barang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_barangMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_barang);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 610, 270));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(232, 146, 61));
        jLabel2.setText("Dafta Barang");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 40, -1, -1));

        popup_barang.getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, 430));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(85, 153, 142));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(44, 62, 80));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("FORM TRANSAKSI PENJUALAN BARANG");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 50, -1, -1));

        tkode.setEditable(false);
        tkode.setBackground(new java.awt.Color(85, 153, 142));
        tkode.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tkode.setForeground(new java.awt.Color(255, 255, 255));
        tkode.setBorder(null);
        tkode.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tkodeMouseClicked(evt);
            }
        });
        tkode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tkodeActionPerformed(evt);
            }
        });
        jPanel2.add(tkode, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 220, 190, 30));
        jPanel2.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 250, 190, 10));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(232, 146, 61));
        jLabel4.setText("Kode Barang");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 190, 140, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(232, 146, 61));
        jLabel5.setText("Nama Barang");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 260, 140, -1));

        tnama.setEditable(false);
        tnama.setBackground(new java.awt.Color(85, 153, 142));
        tnama.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tnama.setForeground(new java.awt.Color(255, 255, 255));
        tnama.setBorder(null);
        jPanel2.add(tnama, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 290, 290, 30));
        jPanel2.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 320, 290, 10));

        tharga.setEditable(false);
        tharga.setBackground(new java.awt.Color(85, 153, 142));
        tharga.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tharga.setForeground(new java.awt.Color(255, 255, 255));
        tharga.setBorder(null);
        jPanel2.add(tharga, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 360, 290, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(232, 146, 61));
        jLabel6.setText("Harga Barang");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 330, 150, -1));
        jPanel2.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 390, 290, 10));

        tbl_transaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbl_transaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_transaksiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_transaksi);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 250, 740, 270));

        tbayar.setBackground(new java.awt.Color(85, 153, 142));
        tbayar.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        tbayar.setForeground(new java.awt.Color(255, 255, 255));
        tbayar.setBorder(null);
        tbayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbayarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tbayarKeyTyped(evt);
            }
        });
        jPanel2.add(tbayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 580, 370, 60));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(232, 146, 61));
        jLabel7.setText("Pencarian");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 180, -1, -1));
        jPanel2.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 640, 370, 20));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(232, 146, 61));
        jLabel8.setText("Jumlah Beli");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 400, 130, -1));

        tjumlah.setBackground(new java.awt.Color(85, 153, 142));
        tjumlah.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tjumlah.setForeground(new java.awt.Color(255, 255, 255));
        tjumlah.setBorder(null);
        tjumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tjumlahActionPerformed(evt);
            }
        });
        tjumlah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tjumlahKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tjumlahKeyTyped(evt);
            }
        });
        jPanel2.add(tjumlah, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 430, 290, 30));
        jPanel2.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 460, 290, 10));
        jPanel2.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 530, 290, 10));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(232, 146, 61));
        jLabel9.setText("Subtotal");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 470, 80, -1));

        ttotal.setEditable(false);
        ttotal.setBackground(new java.awt.Color(85, 153, 142));
        ttotal.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        ttotal.setForeground(new java.awt.Color(255, 255, 255));
        ttotal.setBorder(null);
        jPanel2.add(ttotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 500, 290, 30));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(232, 146, 61));
        jLabel13.setText("BAYAR     :");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 600, -1, -1));

        lsubtotal.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        lsubtotal.setForeground(new java.awt.Color(255, 255, 255));
        lsubtotal.setText("Rp. 1.000.100.100");
        jPanel2.add(lsubtotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 520, -1, -1));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(232, 146, 61));
        jLabel15.setText("KEMBALI :");
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 660, -1, -1));
        jPanel2.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 240, 610, 20));

        tcari.setBackground(new java.awt.Color(85, 153, 142));
        tcari.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tcari.setForeground(new java.awt.Color(255, 255, 255));
        tcari.setBorder(null);
        tcari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tcariKeyReleased(evt);
            }
        });
        jPanel2.add(tcari, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 210, 610, 30));

        jPanel7.setBackground(new java.awt.Color(74, 131, 122));
        jPanel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel7MouseClicked(evt);
            }
        });
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("KEMBALI");
        jPanel7.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, -1, 40));

        jPanel2.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 0, 180, 40));

        jPanel5.setBackground(new java.awt.Color(74, 131, 122));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(232, 146, 61));
        jLabel3.setText("Kode Transaksi :");
        jPanel5.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 210, 60));

        no_transaksi.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        no_transaksi.setForeground(new java.awt.Color(255, 255, 255));
        no_transaksi.setText("20170531001");
        jPanel5.add(no_transaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 0, -1, 60));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(232, 146, 61));
        jLabel12.setText("Tanggal Transaksi :");
        jPanel5.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 0, -1, 60));

        ltgl.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        ltgl.setForeground(new java.awt.Color(255, 255, 255));
        ltgl.setText("31-05-2017");
        jPanel5.add(ltgl, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 0, -1, 60));

        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 100, 1080, 60));

        close.setBackground(new java.awt.Color(74, 131, 122));
        close.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeMouseClicked(evt);
            }
        });
        close.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("X");
        close.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, -1, 40));

        jPanel2.add(close, new org.netbeans.lib.awtextra.AbsoluteConstraints(1270, 0, 110, 40));

        jPanel6.setBackground(new java.awt.Color(74, 131, 122));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel2.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 1080, 40));

        bbeli.setBackground(new java.awt.Color(74, 131, 122));
        bbeli.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bbeli.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bbeliMouseClicked(evt);
            }
        });
        bbeli.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("BELI");
        bbeli.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 0, -1, 50));

        jPanel2.add(bbeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 540, 290, -1));

        bedit.setBackground(new java.awt.Color(74, 131, 122));
        bedit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bedit.setEnabled(false);
        bedit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                beditMouseClicked(evt);
            }
        });
        bedit.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("UBAH");
        bedit.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, -1, 50));

        jPanel2.add(bedit, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 550, 290, -1));

        breset.setBackground(new java.awt.Color(74, 131, 122));
        breset.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        breset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bresetMouseClicked(evt);
            }
        });
        breset.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("RESET");
        breset.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, -1, 50));

        jPanel2.add(breset, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 610, 290, -1));

        bhapus.setBackground(new java.awt.Color(74, 131, 122));
        bhapus.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bhapus.setEnabled(false);
        bhapus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bhapusMouseClicked(evt);
            }
        });
        bhapus.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("HAPUS");
        bhapus.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, -1, 50));

        jPanel2.add(bhapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 620, 290, -1));

        bselesai.setBackground(new java.awt.Color(74, 131, 122));
        bselesai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bselesaiMouseClicked(evt);
            }
        });
        bselesai.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lselesai.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lselesai.setForeground(new java.awt.Color(255, 255, 255));
        lselesai.setText("SELESAI");
        lselesai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lselesaiMouseClicked(evt);
            }
        });
        bselesai.add(lselesai, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 80, 180));

        jPanel2.add(bselesai, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 530, 120, 180));

        bcek.setBackground(new java.awt.Color(74, 131, 122));
        bcek.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bcekMouseClicked(evt);
            }
        });
        bcek.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("TAMPIL");
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel16MouseClicked(evt);
            }
        });
        bcek.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 40));

        jPanel2.add(bcek, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 210, 90, 40));

        jPanel9.setBackground(new java.awt.Color(74, 131, 122));
        jPanel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel9MouseClicked(evt);
            }
        });
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("REFRESH");
        jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel17MouseClicked(evt);
            }
        });
        jPanel9.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, -1, 40));

        jPanel2.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 200, 120, 40));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(232, 146, 61));
        jLabel18.setText("TOTAL     :");
        jPanel2.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 530, -1, -1));

        lsubtotal1.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        lsubtotal1.setForeground(new java.awt.Color(255, 255, 255));
        lsubtotal1.setText("Rp.");
        jPanel2.add(lsubtotal1, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 580, -1, -1));

        lkembali.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        lkembali.setForeground(new java.awt.Color(255, 255, 255));
        lkembali.setText("Rp. 1.000.100.100");
        jPanel2.add(lkembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 650, -1, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 1380, 740));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tkodeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tkodeMouseClicked
        // TODO add your handling code here:
//        popup_barang.setVisible(true);
//        popup_barang.setSize(700,420);
    }//GEN-LAST:event_tkodeMouseClicked
    public int sto;
    private void tbl_barangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_barangMouseClicked
        // TODO add your handling code here:
        tkode.setText(tabbarang.getValueAt(tbl_barang.getSelectedRow(), 0)+"");
        tjumlah.requestFocus();
        popup_barang.dispose();
        tjumlah.setText("");
        ttotal.setText("");
        try {
            st = cn.createStatement();
            rs = st.executeQuery("SELECT * FROM tbl_barang WHERE kode_barang = '"+tkode.getText()+"'");
            while (rs.next()) {                
                tnama.setText(rs.getString("nama_barang"));
                tharga.setText(rs.getString("harga"));
                sto = rs.getInt("stok");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_tbl_barangMouseClicked

    private void tkodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tkodeActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_tkodeActionPerformed

    private void tjumlahKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tjumlahKeyReleased
        // TODO add your handling code here:\
       hitung();
    }//GEN-LAST:event_tjumlahKeyReleased

    private void tjumlahKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tjumlahKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (! ((Character.isDigit(c) || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_TAB) ))){
            evt.consume();
        }
    }//GEN-LAST:event_tjumlahKeyTyped

    private void tbl_transaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_transaksiMouseClicked
        // TODO add your handling code here:
        tkode.setText(tabmodel.getValueAt(tbl_transaksi.getSelectedRow(), 1)+"");
        tnama.setText(tabmodel.getValueAt(tbl_transaksi.getSelectedRow(), 2)+"");
        tharga.setText(tabmodel.getValueAt(tbl_transaksi.getSelectedRow(), 3)+"");
        tjumlah.setText(tabmodel.getValueAt(tbl_transaksi.getSelectedRow(), 4)+"");
        ttotal.setText(tabmodel.getValueAt(tbl_transaksi.getSelectedRow(), 5)+"");
        bcek.setVisible(false);
        bhapus.setVisible(true);
        bedit.setVisible(true);
        bbeli.setVisible(false);
        breset.setVisible(false);
        tjumlah.requestFocus();
    }//GEN-LAST:event_tbl_transaksiMouseClicked

    private void tbayarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbayarKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (! ((Character.isDigit(c) || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_TAB) ))){
            evt.consume();
        }
    }//GEN-LAST:event_tbayarKeyTyped

    private void jPanel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel7MouseClicked
        // TODO add your handling code here:
        utama futama = new utama();
        try {
            st = cn.createStatement();
            rs = st.executeQuery("SELECT COUNT(*) FROM tbl_transaksi WHERE kode_transaksi= '"+no_transaksi.getText()+"' ");
            rs.next();
            int i = rs.getInt(1);
            if (i > 0) {
                String sql = "DELETE FROM tbl_transaksi WHERE kode_transaksi = '"+no_transaksi.getText()+"'";
                aksi(sql, "DATA BARANG TRANSAKSI INI AKAN TERHAPUS");
                futama.setVisible(true);
                 this.dispose();
            }else{
                futama.setVisible(true);
                this.dispose();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
       
    }//GEN-LAST:event_jPanel7MouseClicked

    private void closeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeMouseClicked
        // TODO add your handling code here:
        try {
            st = cn.createStatement();
            rs = st.executeQuery("SELECT COUNT(*) FROM tbl_transaksi WHERE kode_transaksi= '"+no_transaksi.getText()+"' ");
            rs.next();
            int i = rs.getInt(1);
            if (i > 0) {
                String sql = "DELETE FROM tbl_transaksi WHERE kode_transaksi = '"+no_transaksi.getText()+"'";
                aksi(sql, "DATA BARANG TRANSAKSI INI AKAN TERHAPUS");
               System.exit(0);
            }else{
               System.exit(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_closeMouseClicked

    private void bbeliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bbeliMouseClicked
        // TODO add your handling code here:
        if (tkode.getText().isEmpty() || tjumlah.getText().isEmpty() ) {
            JOptionPane.showMessageDialog(null, "LENGKAPI DATA TERLEBIH DAHULU");
        }else if(tjumlah.getText().equals(0) || tjumlah.getText() == "0" ){
            JOptionPane.showMessageDialog(null, "Tidak bisa nol");
        }else {
//            try {
//                st = cn.createStatement();
//                st.executeUpdate("INSERT INTO tbl_transaksi VALUES('"+no_transaksi.getText()+"','"+tkode.getText()+"','"+tjumlah.getText()+"','"+ttotal.getText()+"','0')");
//                tampildata();
//                total();
//                reset();
//                tampilbarang();
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        }
            try{
               st = cn.createStatement();
               rs = st.executeQuery("SELECT * FROM tbl_transaksi WHERE kode_transaksi = '"+no_transaksi.getText()+"' AND kode_barang = '"+tkode.getText()+"'");
               String a = "";
               int jml = 0;
               int subtot = 0;
                if (rs.next()) {
                    a = rs.getString("kode_barang");
                    jml += rs.getInt("jumlah");
                    subtot += rs.getInt("subtotal");
                }   
                jml += Integer.parseInt(tjumlah.getText());
                subtot += Integer.parseInt(ttotal.getText());
                if (tkode.getText().equals(a)) {
                        st = cn.createStatement();
                        st.executeUpdate("UPDATE tbl_transaksi SET jumlah = '"+String.valueOf(jml)+"', subtotal = '"+String.valueOf(subtot)+"' WHERE kode_barang = '"+tkode.getText()+"' AND kode_transaksi = '"+no_transaksi.getText()+"'");
                        tampildata();
                        tampilbarang();
                        total();
                        reset();
                    }else{
                        st = cn.createStatement();
                        st.executeUpdate("INSERT INTO tbl_transaksi VALUES('"+no_transaksi.getText()+"','"+tkode.getText()+"','"+tjumlah.getText()+"','"+ttotal.getText()+"','0')");
                        tampildata();
                        tampilbarang();
                        total();
                        reset();
                    } 
              
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_bbeliMouseClicked

    private void beditMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_beditMouseClicked
        // TODO add your handling code here:
         if (tkode.getText().isEmpty() || tjumlah.getText().isEmpty() ) {
            JOptionPane.showMessageDialog(null, "LENGKAPI DATA TERLEBIH DAHULU");
        }else if(tjumlah.getText().equals(0)){
            JOptionPane.showMessageDialog(null, "Tidak bisa nol");
        }else {
             try {
                 st = cn.createStatement();
                 st.executeUpdate("UPDATE tbl_transaksi SET jumlah = '"+tjumlah.getText()+"', subtotal = '"+ttotal.getText()+"' WHERE kode_transaksi = '"+no_transaksi.getText()+"' AND kode_barang = '"+tkode.getText()+"' ");
                 tampilbarang();
                 tampildata();
                 reset();
                 total();
                 bcek.setVisible(true);
                 bbeli.setVisible(true);
                 breset.setVisible(true);
                 bhapus.setVisible(false);
                 bedit.setVisible(false);
             } catch (Exception e) {
                 e.printStackTrace();
             }
        }

    }//GEN-LAST:event_beditMouseClicked

    private void bresetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bresetMouseClicked
        // TODO add your handling code here:
        reset();
        tkode.requestFocus();
    }//GEN-LAST:event_bresetMouseClicked

    private void bhapusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bhapusMouseClicked
        // TODO add your handling code here:
       int jawab = 0;
      if((jawab = JOptionPane.showConfirmDialog(null, "Anda yakin kan menghapus data ini ?","Konfirmasi", JOptionPane.YES_NO_OPTION))==0){
          String sql = "DELETE FROM tbl_transaksi WHERE kode_transaksi = '"+no_transaksi.getText()+"' AND status = '0' AND kode_barang = '"+tkode.getText()+"' AND jumlah = '"+tjumlah.getText()+"'";
          aksi(sql,"Data Berhasil Dihapus");
           bcek.setVisible(true);
           bbeli.setVisible(true);
           breset.setVisible(true);
           bhapus.setVisible(false);
           bedit.setVisible(false);
      }
        
    }//GEN-LAST:event_bhapusMouseClicked

    private void bselesaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bselesaiMouseClicked
        // TODO add your handling code here:
        if(tbayar.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "MASUKAN BAYAR TERLEBIH DAHULU");
        }else{
            int bayar = Integer.parseInt(tbayar.getText());
            if (bayar == 0 || bayar < sub) {
                JOptionPane.showMessageDialog(null, "UANG ANDA TIDAK CUKUP");
            }else{
                try {
                    st = cn.createStatement();
                    st.executeUpdate("UPDATE tbl_transaksi SET status = '1' WHERE kode_transaksi = '"+no_transaksi.getText()+"' AND status = '0'");
                    JOptionPane.showMessageDialog(null, "TRANSAKSI SELESAI !!!");
                    tampildata();
                    reset();
                    lsubtotal.setText("Rp. 0");
                    lkembali.setText("Rp. 0");
                    tbayar.setText("");
                    autokode();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_bselesaiMouseClicked

    private void tcariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tcariKeyReleased
        // TODO add your handling code here:
       try {
            st = cn.createStatement();
            tabmodel.getDataVector().removeAllElements();
            tabmodel.fireTableDataChanged();
            rs = st.executeQuery("SELECT * FROM qw_transaksi WHERE  kode_transaksi = '"+no_transaksi.getText()+"' AND status = '0' AND nama_barang LIKE '%"+tcari.getText()+"%'");
            while(rs.next()){
                Object[] data = {
                   rs.getString("kode_transaksi"),
                    rs.getString("kode_barang"),
                    rs.getString("nama_barang"),
                    rs.getInt("harga"),
                    rs.getInt("jumlah"),
                    rs.getInt("subtotal")
                };
                tabmodel.addRow(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_tcariKeyReleased

    private void bcekMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bcekMouseClicked
        // TODO add your handling code here:
        popup_barang.setVisible(true);
        popup_barang.setSize(700,420);
    }//GEN-LAST:event_bcekMouseClicked

    private void jLabel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseClicked
        // TODO add your handling code here:
        popup_barang.setVisible(true);
        popup_barang.setSize(700,420);
    }//GEN-LAST:event_jLabel16MouseClicked

    private void lselesaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lselesaiMouseClicked
        // TODO add your handling code here:
        if(tbayar.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "MASUKAN BAYAR TERLEBIH DAHULU");
        }else{
            int bayar = Integer.parseInt(tbayar.getText());
            if (bayar == 0 || bayar < sub) {
                JOptionPane.showMessageDialog(null, "UANG ANDA TIDAK CUKUP");
            }else{
                try {
                    st = cn.createStatement();
                    st.executeUpdate("UPDATE tbl_transaksi SET status = '1' WHERE kode_transaksi = '"+no_transaksi.getText()+"' AND status = '0'");
                    JOptionPane.showMessageDialog(null, "TRANSAKSI SELESAI !!!");
                    tampildata();
                    reset();
                    lsubtotal.setText("Rp. 0");
                    lkembali.setText("Rp. 0");
                    tbayar.setText("");
                    autokode();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        
    }//GEN-LAST:event_lselesaiMouseClicked

    private void jLabel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseClicked
        // TODO add your handling code here:
        tcari.setText("");
        tcariKeyReleased(null);
        tampilbarang();
    }//GEN-LAST:event_jLabel17MouseClicked

    private void jPanel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel9MouseClicked
        // TODO add your handling code here:
        tcari.setText("");
        tcariKeyReleased(null);
        tampilbarang();
    }//GEN-LAST:event_jPanel9MouseClicked

    private void tbayarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbayarKeyReleased
        // TODO add your handling code here:
        int bayar = Integer.parseInt(tbayar.getText());
        if (bayar == 0 || bayar < sub) {
            lkembali.setText("Rp. 0");
        }else{
            int kembali = bayar - sub;
                lkembali.setText("Rp. "+String.valueOf(kembali));
        }      
    }//GEN-LAST:event_tbayarKeyReleased

    private void tjumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tjumlahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tjumlahActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new transaksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bbeli;
    private javax.swing.JPanel bcek;
    private javax.swing.JPanel bedit;
    private javax.swing.JPanel bhapus;
    private javax.swing.JPanel breset;
    private javax.swing.JPanel bselesai;
    private javax.swing.JPanel close;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JLabel lkembali;
    private javax.swing.JLabel lselesai;
    private javax.swing.JLabel lsubtotal;
    private javax.swing.JLabel lsubtotal1;
    private javax.swing.JLabel ltgl;
    private javax.swing.JLabel no_transaksi;
    private javax.swing.JDialog popup_barang;
    private javax.swing.JTextField tbayar;
    private javax.swing.JTable tbl_barang;
    private javax.swing.JTable tbl_transaksi;
    private javax.swing.JTextField tcari;
    private javax.swing.JTextField tharga;
    private javax.swing.JTextField tjumlah;
    private javax.swing.JTextField tkode;
    private javax.swing.JTextField tnama;
    private javax.swing.JTextField ttotal;
    // End of variables declaration//GEN-END:variables
}
