/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoconcurrencia;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Osnayder
 */
public class Productor {
    
    private  BufferedReader archivo[] = new BufferedReader[13];
    private  int contador_refenciaArchivo = 0;
    int con = 0;
    public Productor() {
        try {
            for(int i=1; i<=13; i++){
                archivo[i-1] = new BufferedReader(new FileReader("D:\\p\\Copy of Copy of Sales_Data_"+i+".csv"));
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Productor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public synchronized String getRegistro(){
        String fila_registro;
        try {
            fila_registro = archivo[contador_refenciaArchivo].readLine();
            if(fila_registro==null){
                if(contador_refenciaArchivo<12){
                    contador_refenciaArchivo++;
                    fila_registro = archivo[contador_refenciaArchivo].readLine();
                }else{
                    fila_registro = null;
                }
            }
        } catch (IOException ex) {            
            Logger.getLogger(Productor.class.getName()).log(Level.SEVERE, null, ex);
            fila_registro = null;
        }
        con++;
        return fila_registro;
    }
    
    public static void main(String[] args){
        Productor productor = new Productor();
        Consumidor consumidor1 = new Consumidor(productor);
                   consumidor1.start();
        Consumidor consumidor2 = new Consumidor(productor);
                   consumidor2.start();
    }

    public int getCon() {
        return con;
    }
    
    
}
