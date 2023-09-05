/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.sihombing.jadwal;

/**
 *
 * @author Rizky David L
 */
public class Jadwal {
 String hari;
 String jam;
 String namamtk;
String ruang;
String dosen;

public Jadwal(String hari, String jam, String namamtk, String ruang, String dosen){
    this.hari=hari;
    this.jam=jam;
    this.namamtk=namamtk;
    this.ruang=ruang;
    this.dosen=dosen;
}
 public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Jadwal jadwal = (Jadwal) o;

    if (hari != null ? !hari.equals(jadwal.hari) : jadwal.hari != null) return false;
    if (jam != null ? !jam.equals(jadwal.jam) : jadwal.jam != null) return false;
    if (namamtk != null ? !namamtk.equals(jadwal.namamtk) : jadwal.namamtk != null) return false;
    if (ruang != null ? !ruang.equals(jadwal.ruang) : jadwal.ruang != null) return false;
    return dosen != null ? dosen.equals(jadwal.dosen) : jadwal.dosen == null;
}

    public String getHari(){
        return hari;
    }

    public String getJam(){
        return jam;
    }
    
    public String getNama(){
        return namamtk;
    }
    
    public String getRuang(){
        return ruang;
    }
    
    public String getDsn(){
        return dosen;
    }
}
