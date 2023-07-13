/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.sihombing.jadwal;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
/**
 *
 * @author Rizky David L
 */
public class kdjam{

    
    public enum jam {
    A,
    B,
    C,
    D,
    E,
    F,
    G,
    H,
    I,
    J,
    K,
    }

public static class RandomizeHasil{
    private List<jam> sequence;
    private String hasil;
    
    public RandomizeHasil(List<jam> sequence, String hasil){
    this.sequence = sequence;
    this.hasil = hasil;
}

public List<jam> getSequence(){
    return sequence;
}

public String getHasil(){
    return hasil;
}
}
//why i should make array like this ? jam[] timeCodes = jam.values();
public static RandomizeHasil randomJam(int jatah){
    StringBuilder hasil = new StringBuilder();
    Random random = new Random();
    jam[] kodewaktu = jam.values();
    List<jam> sequence = new ArrayList<>();
    jam kode= kodewaktu[random.nextInt(kodewaktu.length)];
    
        if (kode == jam.A || kode== jam.B){
            if(jatah==2){
                    sequence.add(jam.A);
                    sequence.add(jam.B);

                }else{
                    sequence.add(jam.A);
                    sequence.add(jam.B);
                    sequence.add(jam.C);

                }
        }else if(kode == jam.C){
            if(jatah==2){
                    sequence.add(jam.C);
                    sequence.add(jam.D);

                }else{
                    sequence.add(jam.C);
                    sequence.add(jam.D);
                    sequence.add(jam.E);

                }
        }else if(kode == jam.D){
            if(jatah==2){
                    sequence.add(jam.D);
                    sequence.add(jam.E);

                }else{
                    sequence.add(jam.D);
                    sequence.add(jam.E);
                    sequence.add(jam.F);

                }
        }else if(kode == jam.E ||kode == jam.F){
            sequence.add(jam.E);
            sequence.add(jam.F);
        }else if(kode == jam.G||kode == jam.H){
            if(jatah==2){
                    sequence.add(jam.G);
                    sequence.add(jam.H);

                }else{
                    sequence.add(jam.G);
                    sequence.add(jam.H);
                    sequence.add(jam.I);
                }
        }else if(kode == jam.I||kode == jam.J){
            if(jatah==2){
                    sequence.add(jam.I);
                    sequence.add(jam.J);

                }else{
                    sequence.add(jam.I);
                    sequence.add(jam.J);
                    sequence.add(jam.K);

                }
        }else if(kode==jam.K){
                    sequence.add(jam.I);
                    sequence.add(jam.J);
                    sequence.add(jam.K);
        }
   
    for(jam kd: sequence){
        hasil.append(kd);
    }
    
    
    return new RandomizeHasil(sequence, hasil.toString().trim());
}

}