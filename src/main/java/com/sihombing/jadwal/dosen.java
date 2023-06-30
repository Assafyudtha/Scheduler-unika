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
public class dosen implements Serializable {
    String nama;
    String id;
    
    public dosen(String nama, String id){
        this.nama=nama;
        this.id=id;
    }
    
    public String dosenString(){
        return "Dosen [nama: "+nama+" id:"+id+"]";
    }
    
      public String getName() {
            return nama;
        }

        public String getId() {
            return id;
        }
}
