/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.sihombing.jadwal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rizky David L
 */
public class SusunJadwal extends javax.swing.JFrame {

    private DefaultTableModel tblmtk;
    private DefaultTableModel tbljad;
    private DefaultTableModel tblruang;

    Main awalan;
    koneksi dbconnect = new koneksi();
    Connection koneksi = dbconnect.getConnection();
    codeGenerator code = new codeGenerator();
    /**
     * Creates new form MatkulUI
     */
    public SusunJadwal() {
        this.awalan = new Main();
        initComponents();
        tblruang= (DefaultTableModel) jTable3.getModel();
        tblmtk = (DefaultTableModel) jTable2.getModel();
        tbljad = (DefaultTableModel) jTable1.getModel();
        showMtk();
        ambilRuangan();
    }
    
    private void showMtk(){
        try {
        tblmtk.setRowCount(0);
        Connection connection = dbconnect.getConnection();
        String query= "select * from matakuliah";
        
            Statement statement = connection.createStatement();
            Statement dosenstate = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {                
                String id = result.getString("kdmtk");
                String nama = result.getString("Matkul");
                String jlh = result.getString("Jumlah_mhs");
                int jenis = result.getInt("jenismtk");
                String dosen = result.getString("dosen");
                int jatah = result.getInt("jlh_waktu");
                String sdosen="select namadsn from dosen where id="+ dosen;
                String dosnam = "";
                try (ResultSet result2 = dosenstate.executeQuery(sdosen);){
                    if(result2.next()){
                        dosnam = result2.getString("namadsn");
                    }
                }
                
                jenismatkul jenismtk = null;
                if (jenis == 1){
                    jenismtk = jenismatkul.Teori;
                    }else{
                    jenismtk = jenismatkul.Praktek;

                }
                tblmtk.addRow(new  Object[]{id,nama,dosnam,jenismtk,jatah,jlh});
            }
            
            jTable2.setModel(tblmtk);
            result.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
        e.printStackTrace();
        }
    }

    
    
    private void Susunacak2(){
    List<ruangan> listruang = new ArrayList<>();
    List<matakuliah> listMatkul = new ArrayList<>();
    List<matakuliah> listmtkPrak = new ArrayList<>();
    int dayidx = 0;
    int timeidx= 0;
    matakuliah mtkpilih = null;
    Random random= new Random();
    for (int row=0; row<jTable2.getRowCount();row++){
        String namamtk = jTable2.getValueAt(row, 1).toString();
        String dosen =  jTable2.getValueAt(row, 2).toString();
        jenismatkul jenis = (jenismatkul) jTable2.getValueAt(row, 3);
        int jumlah = Integer.parseInt(jTable2.getValueAt(row, 5).toString());
        int jatahwaktu = Integer.parseInt(jTable2.getValueAt(row, 4).toString());
        matakuliah matkul = new matakuliah(null,namamtk,jenis,jatahwaktu,dosen,jumlah);
        if(jenis == jenismatkul.Praktek){
            listmtkPrak.add(matkul);
        }else{
            listMatkul.add(matkul);    
        }
        
    }
    for (int row=0;row<jTable3.getRowCount(); row++){
        String namaRuang = jTable3.getValueAt(row, 1).toString();
        String id = jTable3.getValueAt(row,0).toString();
        int kapasitas = Integer.parseInt(jTable3.getValueAt(row, 2).toString());
        jenismatkul jenis = (jenismatkul) jTable3.getValueAt(row, 3);
        ruangan ruang = new ruangan (id,namaRuang,kapasitas, jenis); 
        listruang.add(ruang);
    }
    
    List<ruangan> fullruangList=new ArrayList<>(listruang);
    
    while (!fullruangList.isEmpty()){
        int randomidx = random.nextInt(fullruangList.size());
        ruangan pilihan = fullruangList.get(randomidx);
        fullruangList.remove(randomidx);
        String namarng = pilihan.getName();
        jenismatkul tipe = pilihan.getType();
        if(tipe==jenismatkul.Praktek){
            if(!listmtkPrak.isEmpty()){
            mtkpilih = listmtkPrak.remove(0);
            }
        }else{
            if(!listMatkul.isEmpty()){
            mtkpilih = listMatkul.remove(0);
            }
        }
        String mtk = mtkpilih.getNamamtk();
        
        String dsn = mtkpilih.getDosenString();
        tbljad.addRow(new Object[]{null,null,mtk,namarng,dsn});
        
        if (fullruangList.isEmpty()){
            fullruangList.addAll(listruang);
        }
    }
    
    
    
    }
    
    private void Susunacak3(){
    List<ruangan> listruang = new ArrayList<>();
    List<ruangan> listruangP = new ArrayList<>();

    List<matakuliah> listMatkul = new ArrayList<>();
    List<matakuliah> listmtkPrak = new ArrayList<>();
    List<hari> listhari = new  ArrayList<hari>();
    List<kdjam> listjam2= new ArrayList<kdjam>();
    List<kdjam> listjam3= new ArrayList<kdjam>();
    listhari.add(hari.Senin);
    listhari.add(hari.Selasa);
    listhari.add(hari.Rabu);
    listhari.add(hari.Kamis);
    listhari.add(hari.Jumat);
    listhari.add(hari.Sabtu);
    listjam2.add(kdjam.AB);
    listjam2.add(kdjam.CD);
    listjam2.add(kdjam.EF);
    listjam2.add(kdjam.GH);
    listjam2.add(kdjam.IJ);
    listjam3.add(kdjam.ABC);
    listjam3.add(kdjam.DEF);
    listjam3.add(kdjam.GHI);
    listjam3.add(kdjam.IJK);
    int dayidx = 0;
    int timeidx= 0;
    matakuliah mtkpilih = null;
    Random random= new Random();
    for (int row=0; row<jTable2.getRowCount();row++){
        String namamtk = jTable2.getValueAt(row, 1).toString();
        String dosen =  jTable2.getValueAt(row, 2).toString();
        jenismatkul jenis = (jenismatkul) jTable2.getValueAt(row, 3);
        int jumlah = Integer.parseInt(jTable2.getValueAt(row, 5).toString());
        int jatahwaktu = Integer.parseInt(jTable2.getValueAt(row, 4).toString());
        matakuliah matkul = new matakuliah(null,namamtk,jenis,jatahwaktu,dosen,jumlah);
        if(jenis == jenismatkul.Praktek){
            listmtkPrak.add(matkul);
        }else{
            listMatkul.add(matkul);    
        }
        
    }
    for (int row=0;row<jTable3.getRowCount(); row++){
        String namaRuang = jTable3.getValueAt(row, 1).toString();
        String id = jTable3.getValueAt(row,0).toString();
        int kapasitas = Integer.parseInt(jTable3.getValueAt(row, 2).toString());
        jenismatkul jenis = (jenismatkul) jTable3.getValueAt(row, 3);
        ruangan ruang = new ruangan (id,namaRuang,kapasitas, jenis); 
        if(jenis == jenismatkul.Teori){
        listruang.add(ruang);
        }else{
            listruangP.add(ruang);
        }
    }
    List<ruangan> fullruangTList=new ArrayList<>(listruang);
    List<ruangan> fullruangPList=new ArrayList<>(listruangP);
    List<Jadwal> jadwalfin =new ArrayList<Jadwal>();
    int index=0;
    int indexh=0;
    
    for (hari day:listhari){
        for(kdjam jam: listjam2){
            for(ruangan ruang: listruang){
                if(!listMatkul.isEmpty()){
                ruangan pilihan = ruang;
                String namarng = pilihan.getName();
                mtkpilih = listMatkul.remove(0);
                
                String mtk = mtkpilih.getNamamtk();

                String dsn = mtkpilih.getDosenString();
                Jadwal jadwal = new Jadwal(day.toString(), jam.toString(), mtk, namarng, dsn);
                for(Jadwal jadu:jadwalfin){
                    if(jadwal.getHari().equals(jadu.getHari())){
                        if(checkJam(jadwal.getJam(), jadu.getJam())){
                            if(jadwal.getDsn().equals(jadu.getDsn())){
                                System.out.print(jadwal.getJam());
                                if(index>=4){
                                    if(indexh<6){
                                        jadwal = new Jadwal(listhari.get(indexh+1).toString(), listjam2.get(0).toString(), mtk, namarng, dsn);
                                    }else{
                                    jadwal = new Jadwal(day.toString(), listjam2.get(index).toString(), mtk, namarng, dsn);    
                                    }
                                }else{
                                jadwal = new Jadwal(day.toString(), listjam2.get(index+1).toString(), mtk, namarng, dsn);
                                }
                                
                                System.out.println(jadwal.getJam());
                            }
                        }
                    }
                }
                jadwalfin.add(jadwal);
                tbljad.addRow(new Object[]{jadwal.getHari(),jadwal.getJam(),jadwal.getNama(),jadwal.getRuang(),jadwal.getDsn()});
                }
            }
            for(ruangan ruangP : listruangP){
                if(!listmtkPrak.isEmpty()){
                ruangan pilihan = ruangP;
                String namarng = pilihan.getName();
                mtkpilih = listmtkPrak.remove(0);
                kdjam jamP;
                if(index>=3){
                    jamP= listjam3.get(3);
                }else{
                    jamP= listjam3.get(index);
                }
                String mtk = mtkpilih.getNamamtk();

                String dsn = mtkpilih.getDosenString();
                Jadwal jadwal = new Jadwal(day.toString(), jamP.toString(), mtk, namarng, dsn);
                for(Jadwal jadu:jadwalfin){
                    if(jadwal.getHari()==jadu.getHari()){
                        if(checkJam(jadwal.getJam(), jadu.getJam())){
                            if(jadwal.getDsn().equals(jadu.getDsn())){
                                System.out.print(jadwal.getNama());

                                System.out.print(jadwal.getNama());
                                System.out.print(jadwal.getDsn().equals(jadu.getDsn()));
                                System.out.print(jadwal.getJam());
                                if(index>=3){
                                    if(indexh<6){
                                        jadwal = new Jadwal(listhari.get(indexh+1).toString(), listjam3.get(1).toString(), mtk, namarng, dsn);
                                    }else
                                    {
                                     jadwal = new Jadwal(day.toString(), listjam3.get(index).toString(), mtk, namarng, dsn);
                                    }
                                }else{
                                jadwal = new Jadwal(day.toString(), listjam3.get(index+1).toString(), mtk, namarng, dsn);
                                }
                                
                                System.out.println(jadwal.getJam().toString());
                            }
                        }
                    }
                }
                jadwalfin.add(jadwal);
                tbljad.addRow(new Object[]{jadwal.getHari(),jadwal.getJam(),jadwal.getNama(),jadwal.getRuang(),jadwal.getDsn()});
                
            }
            }
            index++;
        }
        indexh++;
    }
    index = 0;
    indexh=0;
    ArrayList<Jadwal> jadwalnew = new ArrayList<>(jadwalfin);
    for(Jadwal jadwal:jadwalnew){
        for(Jadwal jadu:jadwalnew){
            if(jadwal!=jadu){
                if(jadwal.getHari()==jadu.getHari()){
                if(checkJam(jadwal.getJam(), jadu.getJam())){
                    if(jadwal.getDsn().equals(jadu.getDsn())){
                                    System.out.print(jadwal.getNama());
                                    System.out.print(jadwal.getNama());
                                    System.out.print(jadwal.getDsn().equals(jadu.getDsn()));
                                    System.out.print(jadwal.getJam());
                                    
                                    if(jadwal.getJam().length()==3){
                                        if(index>=3){
                                            if(indexh<6){
                                                jadwal = new Jadwal(listhari.get(indexh+1).toString(), listjam3.get(0).toString(), jadwal.getNama(), jadwal.getRuang(), jadwal.getDsn());
                                            }else
                                            {
                                             jadwal = new Jadwal(jadwal.getHari(), listjam3.get(index).toString(), jadwal.getNama(), jadwal.getRuang(), jadwal.getDsn());
                                            }
                                        }else{
                                        jadwal = new Jadwal(jadwal.getHari(), listjam3.get(index+1).toString(), jadwal.getNama(), jadwal.getRuang(), jadwal.getDsn());
                                        }
                                    }else{
                                        if(index>=4){
                                            if(indexh<6){
                                                 jadwal = new Jadwal(listhari.get(indexh+1).toString(), listjam2.get(0).toString(), jadwal.getNama(), jadwal.getRuang(), jadwal.getDsn());
                                            }else{
                                             jadwal = new Jadwal(jadwal.getHari(), listjam2.get(index).toString(), jadwal.getNama(), jadwal.getRuang(), jadwal.getDsn());    
                                            }
                                        }else{
                                         jadwal = new Jadwal(jadwal.getHari(), listjam2.get(index+1).toString(), jadwal.getNama(), jadwal.getRuang(), jadwal.getDsn());
                                        } 
                                    }

                                    System.out.println(jadwal.getJam());
                                    jadwalfin.remove(jadu);
                                    jadwalfin.add(jadwal);
                                    
                    }
                    if(jadwal.getRuang().equals(jadu.getRuang())){
                        if(jadwal.getJam().length()==3){
                                        if(index>=3){
                                            if(indexh<6){
                                                jadwal = new Jadwal(listhari.get(indexh+1).toString(), listjam3.get(0).toString(), jadwal.getNama(), jadwal.getRuang(), jadwal.getDsn());
                                            }else
                                            {
                                             jadwal = new Jadwal(jadwal.getHari(), listjam3.get(index).toString(), jadwal.getNama(), jadwal.getRuang(), jadwal.getDsn());
                                            }
                                        }else{
                                        jadwal = new Jadwal(jadwal.getHari(), listjam3.get(index+1).toString(), jadwal.getNama(), jadwal.getRuang(), jadwal.getDsn());
                                        }
                                    }else{
                                        if(index>=4){
                                            if(indexh<6){
                                                 jadwal = new Jadwal(listhari.get(indexh+1).toString(), listjam2.get(0).toString(), jadwal.getNama(), jadwal.getRuang(), jadwal.getDsn());
                                            }else{
                                             jadwal = new Jadwal(jadwal.getHari(), listjam2.get(index).toString(), jadwal.getNama(), jadwal.getRuang(), jadwal.getDsn());    
                                            }
                                        }else{
                                         jadwal = new Jadwal(jadwal.getHari(), listjam2.get(index+1).toString(), jadwal.getNama(), jadwal.getRuang(), jadwal.getDsn());
                                        } 
                                    }
                                    System.out.println(jadwal.getJam());
                                    jadwalfin.remove(jadu);
                                    jadwalfin.add(jadwal);
                    }
                    index++;
                }
            indexh++;
            }
            }
        }
    }
   /* for(Jadwal jadu:jadwalnew){
        Jadwal jadwal = jadwalnew.get(index-1);
        if(jadwal!=null){
            if(jadwal.getHari()==jadu.getHari()){
                if(checkJam(jadwal.getJam(), jadu.getJam())){
                    if(jadwal.getDsn().equals(jadu.getDsn())){
                                    System.out.print(jadwal.getNama());
                                    System.out.print(jadwal.getNama());
                                    System.out.print(jadwal.getDsn().equals(jadu.getDsn()));
                                    System.out.print(jadwal.getJam());
                                    
                                    if(jadwal.getJam().length()==3){
                                        if(index>=3){
                                            if(indexh<6){
                                                jadwal = new Jadwal(listhari.get(indexh+1).toString(), listjam3.get(0).toString(), jadwal.getNama(), jadwal.getRuang(), jadwal.getDsn());
                                            }else
                                            {
                                             jadwal = new Jadwal(jadwal.getHari(), listjam3.get(index).toString(), jadwal.getNama(), jadwal.getRuang(), jadwal.getDsn());
                                            }
                                        }else{
                                        jadwal = new Jadwal(jadwal.getHari(), listjam3.get(index+1).toString(), jadwal.getNama(), jadwal.getRuang(), jadwal.getDsn());
                                        }
                                    }else{
                                        if(index>=4){
                                            if(indexh<6){
                                                 jadwal = new Jadwal(listhari.get(indexh+1).toString(), listjam2.get(0).toString(), jadwal.getNama(), jadwal.getRuang(), jadwal.getDsn());
                                            }else{
                                             jadwal = new Jadwal(jadwal.getHari(), listjam2.get(index).toString(), jadwal.getNama(), jadwal.getRuang(), jadwal.getDsn());    
                                            }
                                        }else{
                                         jadwal = new Jadwal(jadwal.getHari(), listjam2.get(index+1).toString(), jadwal.getNama(), jadwal.getRuang(), jadwal.getDsn());
                                        } 
                                    }

                                    System.out.println(jadwal.getJam());
                                    jadwalfin.remove(jadu);
                                    jadwalfin.add(jadwal);
                                    
                    }
                    if(jadwal.getRuang().equals(jadu.getRuang())){
                        if(jadwal.getJam().length()==3){
                                        if(index>=3){
                                            if(indexh<6){
                                                jadwal = new Jadwal(listhari.get(indexh+1).toString(), listjam3.get(0).toString(), jadwal.getNama(), jadwal.getRuang(), jadwal.getDsn());
                                            }else
                                            {
                                             jadwal = new Jadwal(jadwal.getHari(), listjam3.get(index).toString(), jadwal.getNama(), jadwal.getRuang(), jadwal.getDsn());
                                            }
                                        }else{
                                        jadwal = new Jadwal(jadwal.getHari(), listjam3.get(index+1).toString(), jadwal.getNama(), jadwal.getRuang(), jadwal.getDsn());
                                        }
                                    }else{
                                        if(index>=4){
                                            if(indexh<6){
                                                 jadwal = new Jadwal(listhari.get(indexh+1).toString(), listjam2.get(0).toString(), jadwal.getNama(), jadwal.getRuang(), jadwal.getDsn());
                                            }else{
                                             jadwal = new Jadwal(jadwal.getHari(), listjam2.get(index).toString(), jadwal.getNama(), jadwal.getRuang(), jadwal.getDsn());    
                                            }
                                        }else{
                                         jadwal = new Jadwal(jadwal.getHari(), listjam2.get(index+1).toString(), jadwal.getNama(), jadwal.getRuang(), jadwal.getDsn());
                                        } 
                                    }
                    }
                    index++;
                }
            indexh++;
            }
        }
    }*/
    tbljad.setRowCount(0);
    for(Jadwal jadfix: jadwalfin){
        tbljad.addRow(new Object[]{jadfix.getHari(),jadfix.getJam(),jadfix.getNama(),jadfix.getRuang(),jadfix.getDsn()});
    }
    
    }
            
    
    private boolean checkJam(String jam1, String jam2) {
    boolean adaSama = false;

    for (int i = 0; i < jam1.length(); i++) {
        char char1 = jam1.charAt(i);

        for (int k = 0; k < jam2.length(); k++) {
            char char2 = jam2.charAt(k);

            if (char1 == char2) {
                adaSama = true;
                break;
            }
        }
    }

    return adaSama;
}

            
    
    private void ambilRuangan(){
      try {
            Connection konektik = dbconnect.getConnection();
            tblruang.setRowCount(0);
            String kueri = "select * from ruangan";
            Statement state = konektik.createStatement();
            ResultSet hasil= state.executeQuery(kueri);
            while(hasil.next()){
                String id = hasil.getString("id");
                String nama = hasil.getString("namarng");
                String kapasitas = hasil.getString("kapasitas");
                int jenis = hasil.getInt("jenis");
                jenismatkul jenisrng = null;
                if (jenis==1){
                    jenisrng = jenismatkul.Teori;
                }else{
                    jenisrng = jenismatkul.Praktek;

                }
                tblruang.addRow(new Object[]{id,nama,kapasitas,jenisrng});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //jika jatah waktu = 3 maka 3 kali matakuliahnya dibuat, karena satu variable jam hanya bisa 
    //satu enumerator
    /*
    Senin = 20 matkul
    selasa = 25 matkul
    rabu = 
    kamis =
    jumat =
    jam roster diisi berdasarkan jumlah ruangan, contoh, jika seluruh ruangan dan lab pada jam ABC sudah habis, lanjutkan ke jam CD CDE dan setetursnya
    cara penjadwalannya, 1 cari teori lalu masukkan ke semua ruangan hingga penuh pada jam AB, jika sudah penuh jam AB, cari matkul yang Praktek lalu isi hingga semua Lab penuh pada ABC
    setelah itu lanjut ke jam berikutnya
    cara nya sama dengan metode random pada jam kuliah tapi ini hanya masukkan ke dalam list dan tidak dirandom atau hanya merandom matakuliah
    RALAT, penjadwalan dilakukan sesuai ruangan, jadi ruangan akan di tentukan matkulnya menurut jenis ruangannya sehingga ruangan sesuai pada saat jam kelompokknya
    Update : Penjdawalan dilakukan dengan membuat 2 kelompok kode waktu, yaitu kelompok jatah waktu 2 dan juga jatah waktu 3
    */
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 255, 255));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Hari", "Jam", "Matakuliah", "Ruang", "Dosen"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton2.setText("HAPUS TERPILIH");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Matakuliah", "Dosen", "Jenis", "Jatah Waktu", "Jumlah Mahasiswa"
            }
        ));
        jScrollPane3.setViewportView(jTable2);

        jButton1.setText("SUSUN");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setText("Menu Utama");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nama Ruangan", "Kapasitas", "Jenis Ruangan"
            }
        ));
        jScrollPane2.setViewportView(jTable3);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 562, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 499, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 499, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1)
                    .addComponent(jButton3))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
Susunacak3();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
awalan.setVisible(true);
        setVisible(false);         // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    // End of variables declaration//GEN-END:variables
}
