/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.sihombing.jadwal;

/**
 *
 * @author Rizky David L
 */
public enum jenismatkul {
    Teori(1),
    Praktek(2);
    
    private int value;
    
    jenismatkul(int value){
    this.value = value;
}
    public int getValue(){
        return value;
    }
}
