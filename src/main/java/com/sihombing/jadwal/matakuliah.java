/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sihombing.jadwal;
import java.io.Serializable;
/**
 *
 * @author Rizky David L
 */
public class matakuliah implements Serializable {
    String id;
    String nama;
    jenismatkul tipe;
    hari hari;
    kdjam jam;
    dosen dosen;
    String dosenString;
    int jatah;
    int jumlahmhs;
    public matakuliah(String id, String nama, jenismatkul tipe, hari hari, kdjam jam, dosen dosen){
        this.id = id;
        this.nama=nama;
        this.tipe=tipe;
        this.hari=hari;
        this.jam =jam;
        this.dosen = dosen;
    }
    public matakuliah(String id, String nama, jenismatkul tipe, int jatah, String dosen, int jumlahmhs){
        this.id = id;
        this.nama=nama;
        this.tipe=tipe;
        this.jatah=jatah;
        this.dosenString=dosen;
        this.jumlahmhs=jumlahmhs;
    }
    
    public String getNamamtk(){
        return nama;
    }
    
    public String getDosen(){
        return dosen.toString();
    }
    public String getDosenString(){
        return dosenString;
    }
    
    public jenismatkul getType(){
        return tipe;
    }
}
