/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Dialog;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import koneksi.Koneksi;

public class Data_Barang extends javax.swing.JInternalFrame {

    public final Connection conn = new Koneksi().connect();
    private DefaultTableModel tabmode;
    private DefaultTableModel tabmode2;
    private DefaultTableModel tabmode3;

    private void aktif() {
        inpKodeBarang.setEnabled(true);
        inpNamaBarang.setEnabled(true);
        inpKodeKat.setEnabled(true);
        inpJumlah.setEnabled(true);
        inpKetKat.setEnabled(true);
    }

    protected void kosong() {
        tanggal();
        inpKodeBarang.setText(null);
        inpNamaBarang.setText(null);
        inpKodeKat.setText(null);
        inpNamaPemasok.setText(null);
        inpJumlah.setText(null);
        inpKetKat.setText(null);
    }

    public void noTable() {
        int Baris = tabmode.getRowCount();
        for (int a = 0; a < Baris; a++) {
            String nomor = String.valueOf(a + 1);
            tabmode.setValueAt(nomor + ".", a, 0);
        }
    }

    public void noTable2() {
        int Baris = tabmode2.getRowCount();
        for (int a = 0; a < Baris; a++) {
            String nomor = String.valueOf(a + 1);
            tabmode2.setValueAt(nomor + ".", a, 0);
        }
    }

    public void noTable3() {
        int Baris = tabmode3.getRowCount();
        for (int a = 0; a < Baris; a++) {
            String nomor = String.valueOf(a + 1);
            tabmode3.setValueAt(nomor + ".", a, 0);
        }
    }

    public void tanggal() {
        Date tgl = new Date();
        btnTanggal.setDate(null);
    }

    public void lebarKolom() {
        TableColumn kolom;
        tabelBarang.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        kolom = tabelBarang.getColumnModel().getColumn(0);
        kolom.setPreferredWidth(40);
        kolom = tabelBarang.getColumnModel().getColumn(1);
        kolom.setPreferredWidth(100);
        kolom = tabelBarang.getColumnModel().getColumn(2);
        kolom.setPreferredWidth(100);
        kolom = tabelBarang.getColumnModel().getColumn(3);
        kolom.setPreferredWidth(120);
        kolom = tabelBarang.getColumnModel().getColumn(4);
        kolom.setPreferredWidth(130);
    }

    public void lebarKolom2() {
        TableColumn kolom2;
        tabelKategori.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        kolom2 = tabelKategori.getColumnModel().getColumn(0);
        kolom2.setPreferredWidth(40);
        kolom2 = tabelKategori.getColumnModel().getColumn(1);
        kolom2.setPreferredWidth(150);
        kolom2 = tabelKategori.getColumnModel().getColumn(2);
        kolom2.setPreferredWidth(200);
    }

    public void lebarKolom3() {
        TableColumn kolom3;
        tabelKategori.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        kolom3 = tabelPemasok.getColumnModel().getColumn(0);
        kolom3.setPreferredWidth(40);
        kolom3 = tabelPemasok.getColumnModel().getColumn(1);
        kolom3.setPreferredWidth(150);
        kolom3 = tabelPemasok.getColumnModel().getColumn(2);
        kolom3.setPreferredWidth(200);
    }

    public void dataTable() {
        Object[] Baris = {"No", "Tanggal", "Kode Barang", "Nama Barang", "Pemasok", "Kode Kategori", "Jumlah", "Keterangan"};
        tabmode = new DefaultTableModel(null, Baris);
        tabelBarang.setModel(tabmode);
        String sql = "SELECT * FROM tb_barang ORDER BY kode_barang ASC";
        try {
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()) {
                String tanggal = hasil.getString("tanggal");
                String kode_barang = hasil.getString("kode_barang");
                String nama_barang = hasil.getString("nama_barang");
                String nama_pemasok = hasil.getString("pemasok");
                String kategori = hasil.getString("kategori");
                String jumlah = hasil.getString("jumlah");
                String keterangan = hasil.getString("keterangan");
                String[] data = {"", tanggal, kode_barang, nama_barang, nama_pemasok, kategori, jumlah, keterangan};
                tabmode.addRow(data);
                noTable();
                lebarKolom();
            }
        } catch (Exception e) {
        }
    }

    //tombol cari kode kategori di data barang
    public void dataTable2() {
        Object[] Baris = {"No", "Kode Kategori", "Nama Kategori"};
        tabmode2 = new DefaultTableModel(null, Baris);
        tabelKategori.setModel(tabmode2);
        String sql = "SELECT * FROM tb_kategori ORDER BY kode_kategori ASC";
        try {
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);

            System.out.println(hasil);
            while (hasil.next()) {
                String kode_kategori = hasil.getString("kode_kategori");
                String nama_kategori = hasil.getString("nama_kategori");
                String[] data = {"", kode_kategori, nama_kategori};
                tabmode2.addRow(data);
                noTable2();
                lebarKolom2();
            }
        } catch (Exception e) {
        }
    }

    public void dataTable3() {
        Object[] Baris = {"No", "Kode", "Nama"};
        tabmode3 = new DefaultTableModel(null, Baris);
        tabelPemasok.setModel(tabmode3);
        String sql = "SELECT * FROM tb_supplier ORDER BY kode ASC";
        try {
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);

            System.out.println(hasil);
            while (hasil.next()) {
                String kodePemasok = hasil.getString("kode");
                String namaPemasok = hasil.getString("nama");
                String[] data = {"", kodePemasok, namaPemasok};
                tabmode3.addRow(data);
                noTable3();
                lebarKolom3();
            }
        } catch (Exception e) {

        }
    }

    public void pencarian(String sql) {
        Object[] Baris = {"No", "Tanggal", "Kode Barang", "Nama Barang", "Kode Kategori", "Jumlah", "Keterangan"};
        tabmode = new DefaultTableModel(null, Baris);
        tabelBarang.setModel(tabmode);
        int brs = tabelBarang.getRowCount();
        for (int i = 0; 1 < brs; i++) {
            tabmode.removeRow(1);
        }
        try {
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()) {
                String tanggal = hasil.getString("tanggal");
                String kode_part = hasil.getString("kode_part");
                String nama_part = hasil.getString("nama_part");
                String keterangan = hasil.getString("keterangan");
                String[] data = {"", tanggal, kode_part, nama_part, keterangan};
                tabmode.addRow(data);
                noTable();
            }
        } catch (Exception e) {

        }
    }

    public void pencarian2(String sql) {
        Object[] Baris = {"No", "Kode Kategori", "Nama Kategori"};
        tabmode2 = new DefaultTableModel(null, Baris);
        tabelKategori.setModel(tabmode2);
        int brs = tabelKategori.getRowCount();
        for (int i = 0; 1 < brs; i++) {
            tabmode2.removeRow(1);
        }
        try {
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()) {
                String kode_kategori = hasil.getString("kode_kategori");
                String nama_kategori = hasil.getString("nama_kategori");
                String[] data = {"", kode_kategori, nama_kategori};
                tabmode2.addRow(data);
                noTable2();
                lebarKolom2();
            }
        } catch (Exception e) {

        }
    }

    public void pencarian3(String sql) {
        Object[] Baris = {"No", "Kode", "Nama"};
        tabmode3 = new DefaultTableModel(null, Baris);
        tabelPemasok.setModel(tabmode3);
        int brs = tabelPemasok.getRowCount();
        for (int i = 0; 1 < brs; i++) {
            tabmode3.removeRow(1);
        }
        try {
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()) {
                String kodePemasok = hasil.getString("kode");
                String namaPemasok = hasil.getString("nama");
                String[] data = {"", kodePemasok, namaPemasok};
                tabmode3.addRow(data);
                noTable3();
                lebarKolom3();
            }
        } catch (Exception e) {

        }
    }

    public Data_Barang() {
        initComponents();
        dataTable();
        lebarKolom();
        aktif();
        tanggal();
        inpKodeBarang.requestFocus();
        comboBoxUpdate();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        UbahDataBarang = new javax.swing.JDialog();
        ContainerUbahKategori = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnUbahTanggal = new com.toedter.calendar.JDateChooser();
        inpUbahKodeBarang = new javax.swing.JTextField();
        inpUbahKetKat = new javax.swing.JTextField();
        btnUbahSimpan = new javax.swing.JButton();
        btnUbahBatal = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        inpUbahKategori = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        inpUbahNamaBarang = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        inpUbahNamaPemasok = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        inpUbahJumlah = new javax.swing.JTextField();
        listKategori = new javax.swing.JDialog();
        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        inpCariKategori = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelKategori = new javax.swing.JTable();
        listPemasok = new javax.swing.JDialog();
        jPanel5 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        inpCariPemasok = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelPemasok = new javax.swing.JTable();
        panelTitle = new javax.swing.JPanel();
        lbTitle = new javax.swing.JLabel();
        mainPanel = new javax.swing.JPanel();
        leftPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        inpKodeBarang = new javax.swing.JTextField();
        inpNamaBarang = new javax.swing.JTextField();
        inpKetKat = new javax.swing.JTextField();
        btnTambah = new javax.swing.JButton();
        btnUbah = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnBersih = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        btnTanggal = new com.toedter.calendar.JDateChooser();
        inpKodeKat = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        inpJumlah = new javax.swing.JTextField();
        btnCariKat = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        inpNamaPemasok = new javax.swing.JTextField();
        btnCariPemasok = new javax.swing.JButton();
        rightPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelBarang = new javax.swing.JTable();
        btnCari = new javax.swing.JButton();
        inpCari = new javax.swing.JTextField();

        UbahDataBarang.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        UbahDataBarang.setTitle("Ubah Data");
        UbahDataBarang.setResizable(false);
        UbahDataBarang.setSize(new java.awt.Dimension(400, 540));
        UbahDataBarang.setType(java.awt.Window.Type.POPUP);

        ContainerUbahKategori.setBackground(new java.awt.Color(38, 70, 83));
        ContainerUbahKategori.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        ContainerUbahKategori.setName(""); // NOI18N
        ContainerUbahKategori.setPreferredSize(new java.awt.Dimension(402, 540));
        ContainerUbahKategori.setRequestFocusEnabled(false);

        jPanel1.setBackground(new java.awt.Color(2, 48, 71));

        jLabel5.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 183, 3));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Ubah Data");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addContainerGap())
        );

        jLabel6.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Keterangan");

        jLabel8.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Kode Barang");

        jLabel9.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Tanggal");

        inpUbahKetKat.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        inpUbahKetKat.setToolTipText("");
        inpUbahKetKat.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        inpUbahKetKat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inpUbahKetKatActionPerformed(evt);
            }
        });

        btnUbahSimpan.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        btnUbahSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/writing.png"))); // NOI18N
        btnUbahSimpan.setText("Simpan");
        btnUbahSimpan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUbahSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUbahSimpanActionPerformed(evt);
            }
        });

        btnUbahBatal.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        btnUbahBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/x.png"))); // NOI18N
        btnUbahBatal.setText("Batal");
        btnUbahBatal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUbahBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUbahBatalActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Kategori");

        inpUbahKategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inpUbahKategoriActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Nama Barang");

        jLabel16.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Nama Pemasok");

        jLabel17.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Jumlah");

        inpUbahJumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inpUbahJumlahActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ContainerUbahKategoriLayout = new javax.swing.GroupLayout(ContainerUbahKategori);
        ContainerUbahKategori.setLayout(ContainerUbahKategoriLayout);
        ContainerUbahKategoriLayout.setHorizontalGroup(
            ContainerUbahKategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(ContainerUbahKategoriLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(ContainerUbahKategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(34, 34, 34)
                .addGroup(ContainerUbahKategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(inpUbahKodeBarang)
                    .addComponent(inpUbahKetKat)
                    .addComponent(btnUbahTanggal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ContainerUbahKategoriLayout.createSequentialGroup()
                        .addComponent(btnUbahSimpan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btnUbahBatal))
                    .addComponent(inpUbahKategori)
                    .addComponent(inpUbahNamaBarang)
                    .addComponent(inpUbahNamaPemasok)
                    .addComponent(inpUbahJumlah))
                .addGap(25, 25, 25))
        );
        ContainerUbahKategoriLayout.setVerticalGroup(
            ContainerUbahKategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ContainerUbahKategoriLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(ContainerUbahKategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(btnUbahTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(ContainerUbahKategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel8)
                    .addComponent(inpUbahKodeBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(ContainerUbahKategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel15)
                    .addComponent(inpUbahNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(ContainerUbahKategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel16)
                    .addComponent(inpUbahNamaPemasok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(ContainerUbahKategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel14)
                    .addComponent(inpUbahKategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(ContainerUbahKategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel17)
                    .addComponent(inpUbahJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(ContainerUbahKategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(inpUbahKetKat, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(ContainerUbahKategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUbahSimpan)
                    .addComponent(btnUbahBatal))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout UbahDataBarangLayout = new javax.swing.GroupLayout(UbahDataBarang.getContentPane());
        UbahDataBarang.getContentPane().setLayout(UbahDataBarangLayout);
        UbahDataBarangLayout.setHorizontalGroup(
            UbahDataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ContainerUbahKategori, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        UbahDataBarangLayout.setVerticalGroup(
            UbahDataBarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ContainerUbahKategori, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
        );

        listKategori.setTitle("List Kategori");
        listKategori.setAlwaysOnTop(true);
        listKategori.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        listKategori.setSize(new java.awt.Dimension(300, 325));

        jPanel4.setBackground(new java.awt.Color(2, 48, 71));
        jPanel4.setPreferredSize(new java.awt.Dimension(300, 325));

        jLabel12.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(238, 155, 0));
        jLabel12.setText("Cari Kategori");

        inpCariKategori.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                inpCariKategoriKeyTyped(evt);
            }
        });

        tabelKategori.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
        tabelKategori.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelKategoriMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tabelKategoriMouseEntered(evt);
            }
        });
        jScrollPane2.setViewportView(tabelKategori);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 26, Short.MAX_VALUE)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(inpCariKategori, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(inpCariKategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout listKategoriLayout = new javax.swing.GroupLayout(listKategori.getContentPane());
        listKategori.getContentPane().setLayout(listKategoriLayout);
        listKategoriLayout.setHorizontalGroup(
            listKategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        listKategoriLayout.setVerticalGroup(
            listKategoriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        listPemasok.setTitle("List Pemasok");
        listPemasok.setAlwaysOnTop(true);
        listPemasok.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        listPemasok.setSize(new java.awt.Dimension(300, 325));

        jPanel5.setBackground(new java.awt.Color(2, 48, 71));
        jPanel5.setPreferredSize(new java.awt.Dimension(300, 325));

        jLabel18.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(238, 155, 0));
        jLabel18.setText("Cari Pemasok");

        inpCariPemasok.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                inpCariPemasokKeyTyped(evt);
            }
        });

        tabelPemasok.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
        tabelPemasok.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelPemasokMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tabelPemasok);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(0, 21, Short.MAX_VALUE)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(inpCariPemasok, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(inpCariPemasok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout listPemasokLayout = new javax.swing.GroupLayout(listPemasok.getContentPane());
        listPemasok.getContentPane().setLayout(listPemasokLayout);
        listPemasokLayout.setHorizontalGroup(
            listPemasokLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        listPemasokLayout.setVerticalGroup(
            listPemasokLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setClosable(true);
        setForeground(java.awt.Color.darkGray);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Data Barang");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        setName("DataKategori"); // NOI18N
        setPreferredSize(new java.awt.Dimension(800, 800));

        panelTitle.setBackground(new java.awt.Color(20, 33, 61));

        lbTitle.setFont(new java.awt.Font("Poppins Black", 1, 24)); // NOI18N
        lbTitle.setForeground(new java.awt.Color(252, 163, 17));
        lbTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitle.setText("Pengolahan Data Barang");

        javax.swing.GroupLayout panelTitleLayout = new javax.swing.GroupLayout(panelTitle);
        panelTitle.setLayout(panelTitleLayout);
        panelTitleLayout.setHorizontalGroup(
            panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTitleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelTitleLayout.setVerticalGroup(
            panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTitleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        mainPanel.setBackground(new java.awt.Color(38, 70, 83));

        leftPanel.setBackground(new java.awt.Color(38, 70, 83));
        leftPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data Barang", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Poppins", 1, 14), new java.awt.Color(233, 196, 106))); // NOI18N
        leftPanel.setFont(new java.awt.Font("Poppins", 0, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Tanggal");

        jLabel2.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Kode Barang");

        jLabel3.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nama Barang");

        jLabel4.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Keterangan");

        inpKetKat.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        inpKetKat.setToolTipText("");
        inpKetKat.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        inpKetKat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inpKetKatActionPerformed(evt);
            }
        });

        btnTambah.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        btnTambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/plus.png"))); // NOI18N
        btnTambah.setText("Tambah");
        btnTambah.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });

        btnUbah.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        btnUbah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/editing.png"))); // NOI18N
        btnUbah.setText("Ubah");
        btnUbah.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUbahActionPerformed(evt);
            }
        });

        btnHapus.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        btnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/bin.png"))); // NOI18N
        btnHapus.setText("Hapus");
        btnHapus.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        btnBersih.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        btnBersih.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/eraser.png"))); // NOI18N
        btnBersih.setText("Bersih");
        btnBersih.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBersih.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBersihActionPerformed(evt);
            }
        });

        btnBatal.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        btnBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/x.png"))); // NOI18N
        btnBatal.setText("Batal");
        btnBatal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Kode Kategori");

        jLabel11.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Jumlah");

        btnCariKat.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        btnCariKat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/loupe.png"))); // NOI18N
        btnCariKat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCariKat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariKatActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Nama Pemasok");

        btnCariPemasok.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        btnCariPemasok.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/loupe.png"))); // NOI18N
        btnCariPemasok.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCariPemasok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariPemasokActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout leftPanelLayout = new javax.swing.GroupLayout(leftPanel);
        leftPanel.setLayout(leftPanelLayout);
        leftPanelLayout.setHorizontalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(leftPanelLayout.createSequentialGroup()
                        .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE))
                        .addGap(19, 19, 19)
                        .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(inpKodeBarang)
                            .addComponent(inpNamaBarang)
                            .addComponent(btnTanggal, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                            .addGroup(leftPanelLayout.createSequentialGroup()
                                .addComponent(inpNamaPemasok, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnCariPemasok))))
                    .addGroup(leftPanelLayout.createSequentialGroup()
                        .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(30, 30, 30)
                        .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(inpKetKat)
                            .addGroup(leftPanelLayout.createSequentialGroup()
                                .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(leftPanelLayout.createSequentialGroup()
                                        .addComponent(inpJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(inpKodeKat))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnCariKat))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, leftPanelLayout.createSequentialGroup()
                        .addGap(173, 173, 173)
                        .addComponent(btnTambah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnHapus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUbah, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, leftPanelLayout.createSequentialGroup()
                        .addGap(284, 284, 284)
                        .addComponent(btnBersih, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBatal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        leftPanelLayout.setVerticalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(btnTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel2)
                    .addComponent(inpKodeBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel3)
                    .addComponent(inpNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel13)
                    .addComponent(inpNamaPemasok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCariPemasok))
                .addGap(18, 18, 18)
                .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel10)
                    .addComponent(btnCariKat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(inpKodeKat, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(inpJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(leftPanelLayout.createSequentialGroup()
                        .addComponent(inpKetKat, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnTambah)
                            .addComponent(btnHapus)
                            .addComponent(btnUbah))
                        .addGap(18, 18, 18)
                        .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnBersih)
                            .addComponent(btnBatal)))
                    .addComponent(jLabel4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        rightPanel.setBackground(new java.awt.Color(38, 70, 83));
        rightPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tabel Data Barang", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Poppins", 1, 14), new java.awt.Color(233, 196, 106))); // NOI18N

        tabelBarang.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelBarang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tabelBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelBarangMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tabelBarangMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(tabelBarang);

        btnCari.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        btnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/loupe.png"))); // NOI18N
        btnCari.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariActionPerformed(evt);
            }
        });

        inpCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                inpCariKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout rightPanelLayout = new javax.swing.GroupLayout(rightPanel);
        rightPanel.setLayout(rightPanelLayout);
        rightPanelLayout.setHorizontalGroup(
            rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rightPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 644, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rightPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(inpCari, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCari)))
                .addContainerGap())
        );
        rightPanelLayout.setVerticalGroup(
            rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rightPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnCari, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(inpCari))
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(247, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(leftPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(rightPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rightPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(leftPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(1, 1, 1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setBounds(0, 0, 1226, 800);
    }// </editor-fold>//GEN-END:initComponents

    private void inpKetKatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inpKetKatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inpKetKatActionPerformed

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        if (inpKodeBarang.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Kode Barang tidak boleh kosong");
        } else if (inpNamaBarang.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Nama Barang tidak boleh kosong");
        } else if (inpKodeKat.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Kode Kategori tidak boleh kosong");
        } else if (inpNamaPemasok.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Nama Pemasok tidak boleh kosong");
        } else if (inpJumlah.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Jumlah tidak boleh kosong");
        } else {
            String sql = "INSERT INTO tb_barang VALUES (?,?,?,?,?,?,?)";
            String tampilan = "dd-MM-yyyy";
            SimpleDateFormat fm = new SimpleDateFormat(tampilan);
            String tanggal = String.valueOf(fm.format(btnTanggal.getDate()));
            try {
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.setString(1, inpKodeBarang.getText());
                stat.setString(2, inpNamaBarang.getText());
                stat.setString(3, inpNamaPemasok.getText());
                stat.setString(4, tanggal.toString());
                stat.setString(5, inpKodeKat.getText());
                stat.setString(6, inpJumlah.getText());
                stat.setString(7, inpKetKat.getText());
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
                kosong();
                dataTable();
                lebarKolom();
                inpKodeBarang.requestFocus();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data Gagal Disimpan" + e);
            }
        }
    }//GEN-LAST:event_btnTambahActionPerformed

    private void inpUbahKetKatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inpUbahKetKatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inpUbahKetKatActionPerformed

    private void btnUbahSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahSimpanActionPerformed
        String sql = "UPDATE tb_barang SET kode_barang=?,nama_barang=?,pemasok=?,tanggal=?,kategori=?,jumlah=?,keterangan=? where kode_barang='" + inpUbahKodeBarang.getText() + "'";
        String tampilan = "dd-MM-yyyy";
        SimpleDateFormat fm = new SimpleDateFormat(tampilan);
        String tanggal = String.valueOf(fm.format(btnUbahTanggal.getDate()));
        try {
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, inpUbahKodeBarang.getText());
            stat.setString(2, inpUbahNamaBarang.getText());
            stat.setString(3, inpUbahNamaPemasok.getText());
            stat.setString(4, tanggal.toString());
            stat.setString(5, inpUbahKategori.getText());
            stat.setString(6, inpUbahJumlah.getText());
            stat.setString(7, inpUbahKetKat.getText());
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
            kosong();
            dataTable();
            lebarKolom();
            inpKodeBarang.requestFocus();
            UbahDataBarang.dispose();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Diubah" + e);
        }
    }//GEN-LAST:event_btnUbahSimpanActionPerformed

    private void tabelBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelBarangMouseClicked
        int bar = tabelBarang.getSelectedRow();
        String nomor = tabmode.getValueAt(bar, 0).toString();
        String tanggal = tabmode.getValueAt(bar, 1).toString();
        String kodeBarang = tabmode.getValueAt(bar, 2).toString();
        String namaBarang = tabmode.getValueAt(bar, 3).toString();
        String namaPemasok = tabmode.getValueAt(bar, 4).toString();
        String kodeKategori = tabmode.getValueAt(bar, 5).toString();
        String jumlah = tabmode.getValueAt(bar, 6).toString();
        String keterangan = tabmode.getValueAt(bar, 7).toString();

        SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
        Date dateValue = null;
        try {
            dateValue = date.parse((String) tabelBarang.getValueAt(bar, 1));
        } catch (ParseException ex) {
            Logger.getLogger(Data_Barang.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println(tanggal + " " + kodeBarang);

        btnTanggal.setDate(dateValue);
        btnUbahTanggal.setDate(dateValue);
        inpKodeBarang.setText(kodeBarang);
        inpUbahKodeBarang.setText(kodeBarang);
        inpNamaBarang.setText(namaBarang);
        inpUbahNamaBarang.setText(namaBarang);
        inpNamaPemasok.setText(namaPemasok);
        inpUbahNamaPemasok.setText(namaPemasok);
        inpKodeKat.setText(kodeKategori);
        inpUbahKategori.setText(kodeKategori);
        inpJumlah.setText(jumlah);
        inpUbahJumlah.setText(jumlah);
        inpKetKat.setText(keterangan);
        inpUbahKetKat.setText(keterangan);
    }//GEN-LAST:event_tabelBarangMouseClicked

    private void btnUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahActionPerformed
        UbahDataBarang.setLocationRelativeTo(this);
        inpKodeBarang.setEditable(false);
        UbahDataBarang.setVisible(true);
    }//GEN-LAST:event_btnUbahActionPerformed

    private void tabelBarangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelBarangMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tabelBarangMouseEntered

    private void btnUbahBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahBatalActionPerformed
        UbahDataBarang.setVisible(false);
    }//GEN-LAST:event_btnUbahBatalActionPerformed

    private void btnBersihActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBersihActionPerformed
        tanggal();
        kosong();
    }//GEN-LAST:event_btnBersihActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        int ok = JOptionPane.showConfirmDialog(null, " Apakah Anda Yakin Ingin "
                + "Menghapus Data", "Konfirmasi Dialog", JOptionPane.YES_NO_OPTION);
        if (ok == 0) {
            String sql = "DELETE FROM tb_barang WHERE kode_barang='" + inpKodeBarang.getText() + "'";
            try {
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus");
                kosong();
                dataTable();
                lebarKolom();
                inpKodeBarang.requestFocus();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data Gagal Dihapus" + e);
            }
        }
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        dispose();
    }//GEN-LAST:event_btnBatalActionPerformed

    private void inpCariKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inpCariKeyTyped
        String sqlPencarian = "SELECT * from tb_kategori WHERE kode_kategori LIKE '%" + inpCari.getText() + "%' OR "
                + "nama_kategori LIKE '%" + inpCari.getText() + "%' OR "
                + "keterangan LIKE '%" + inpCari.getText() + "%'";
        pencarian(sqlPencarian);
        lebarKolom();
    }//GEN-LAST:event_inpCariKeyTyped

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
        String sqlPencarian = "SELECT * FROM tb_kategori WHERE kode_kategori LIKE '%" + inpCari.getText() + "%' OR "
                + "nama_kategori LIKE '%" + inpCari.getText() + "%' OR "
                + "keterangan LIKE '%" + inpCari.getText() + "%'";
        pencarian(sqlPencarian);
        lebarKolom();
    }//GEN-LAST:event_btnCariActionPerformed

    private void btnCariKatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariKatActionPerformed
        dataTable2();
        lebarKolom2();
        listKategori.setLocationRelativeTo(this);
        listKategori.setVisible(true);
    }//GEN-LAST:event_btnCariKatActionPerformed

    private void inpCariKategoriKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inpCariKategoriKeyTyped
        String sqlPencarian2 = "select * from tb_kategori where kode_kategori like '%" + inpCariKategori.getText() + "%' or "
                + "nama_kategori like '%" + inpCariKategori.getText() + "%'";
        pencarian2(sqlPencarian2);
        lebarKolom2();
    }//GEN-LAST:event_inpCariKategoriKeyTyped

    private void tabelKategoriMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelKategoriMouseClicked
        int bar = tabelKategori.getSelectedRow();
        String a = tabmode2.getValueAt(bar, 0).toString();
        String b = tabmode2.getValueAt(bar, 1).toString();
        String c = tabmode2.getValueAt(bar, 2).toString();

        inpKodeKat.setText(b);
        listKategori.dispose();
        inpJumlah.requestFocus();
    }//GEN-LAST:event_tabelKategoriMouseClicked

    private void inpUbahKategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inpUbahKategoriActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inpUbahKategoriActionPerformed

    private void inpUbahJumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inpUbahJumlahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inpUbahJumlahActionPerformed

    private void btnCariPemasokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariPemasokActionPerformed
        dataTable3();
        lebarKolom3();
        listPemasok.setLocationRelativeTo(this);
        listPemasok.setVisible(true);
    }//GEN-LAST:event_btnCariPemasokActionPerformed

    private void inpCariPemasokKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inpCariPemasokKeyTyped
        String sqlPencarian3 = "select * from tb_supplier where kode like '%" + inpCariPemasok.getText() + "%' or "
                + "nama like '%" + inpCariPemasok.getText() + "%'";
        pencarian3(sqlPencarian3);
        lebarKolom3();
    }//GEN-LAST:event_inpCariPemasokKeyTyped

    private void tabelPemasokMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelPemasokMouseClicked
        int bar = tabelPemasok.getSelectedRow();
        String no = tabmode3.getValueAt(bar, 0).toString();
        String kode = tabmode3.getValueAt(bar, 1).toString();
        String nama = tabmode3.getValueAt(bar, 2).toString();

        inpNamaPemasok.setText(nama);
        listPemasok.dispose();
        inpKodeKat.requestFocus();
    }//GEN-LAST:event_tabelPemasokMouseClicked

    private void tabelKategoriMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelKategoriMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tabelKategoriMouseEntered

    private void comboBoxUpdate() {
        JComboBox jc = new JComboBox();
        JPanel panel = new JPanel();
        Connection con;
        Statement st;
        ResultSet rs;

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/db_stock_barang", "root", "");
            st = con.createStatement();
            String s = "select * from tb_supplier";
            rs = st.executeQuery(s);
            while (rs.next()) {
                jc.addItem(rs.getString(1) + " === " + rs.getString(2));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR");
        }
        panel.add(jc);
        this.getContentPane().add(panel);
        this.setVisible(true);

    }

    void setExtendedState(int MAXIMIZED_BOTH) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ContainerUbahKategori;
    private javax.swing.JDialog UbahDataBarang;
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnBersih;
    private javax.swing.JButton btnCari;
    private javax.swing.JButton btnCariKat;
    private javax.swing.JButton btnCariPemasok;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnTambah;
    private com.toedter.calendar.JDateChooser btnTanggal;
    private javax.swing.JButton btnUbah;
    private javax.swing.JButton btnUbahBatal;
    private javax.swing.JButton btnUbahSimpan;
    private com.toedter.calendar.JDateChooser btnUbahTanggal;
    private javax.swing.JTextField inpCari;
    private javax.swing.JTextField inpCariKategori;
    private javax.swing.JTextField inpCariPemasok;
    private javax.swing.JTextField inpJumlah;
    private javax.swing.JTextField inpKetKat;
    private javax.swing.JTextField inpKodeBarang;
    private javax.swing.JTextField inpKodeKat;
    private javax.swing.JTextField inpNamaBarang;
    private javax.swing.JTextField inpNamaPemasok;
    private javax.swing.JTextField inpUbahJumlah;
    private javax.swing.JTextField inpUbahKategori;
    private javax.swing.JTextField inpUbahKetKat;
    private javax.swing.JTextField inpUbahKodeBarang;
    private javax.swing.JTextField inpUbahNamaBarang;
    private javax.swing.JTextField inpUbahNamaPemasok;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JPanel leftPanel;
    private javax.swing.JDialog listKategori;
    private javax.swing.JDialog listPemasok;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel panelTitle;
    private javax.swing.JPanel rightPanel;
    private javax.swing.JTable tabelBarang;
    private javax.swing.JTable tabelKategori;
    private javax.swing.JTable tabelPemasok;
    // End of variables declaration//GEN-END:variables
}
