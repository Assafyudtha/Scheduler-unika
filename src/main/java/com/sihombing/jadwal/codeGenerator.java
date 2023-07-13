/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sihombing.jadwal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JTable;
/**
 *
 * @author assaf
 */
public class codeGenerator {
    private Set<Integer> existingIds;

    public codeGenerator(){
        existingIds=new HashSet<>();
    }
    public void addExistingID(JTable table){
        int idColumnIndex=0;
        int rowcount = table.getRowCount();
        
        for (int i=0; i<rowcount; i++) {
            int existingId = Integer.parseInt(table.getValueAt(i,idColumnIndex).toString());
            existingIds.add(existingId);   
        }
    }
    
    public int generateID(JTable table){
        addExistingID(table);
        int id =0;
        
        while(existingIds.contains(id)){
            id++;
        }
    
        existingIds.add(id);
        return id;
    
    }
  public void deleteID(int id){
      existingIds.remove(id);
  
    }
}
