/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//https://es.stackoverflow.com/questions/1487/guia-definitiva-de-conversi%C3%B3n-de-tipos-en-java
package proyectoconcurrencia;

import java.math.BigDecimal;

/**
 *
 * @author Osnayder
 */
public class Consumidor extends Thread {
    
    private Productor productor;
    private BigDecimal importeVentasTotales =BigDecimal.ONE;
    private int tipoOperacion = 0;
    private Registro registro;
    
    
    public Consumidor(Productor productor){
        this.productor = productor;
        this.registro = new Registro();
        
    }
    
    @Override
    public void run(){ 
        String registroString;
        String arrayDatos[];
        String restricion = "\"Sales\"";
        double doble;
        
        while((registroString=productor.getRegistro())!=null){
            arrayDatos = registroString.split(",");
            
            if(!arrayDatos[arrayDatos.length-1].equals(restricion)){
                
                doble = Double.parseDouble(arrayDatos[arrayDatos.length-1]);
                BigDecimal numero1 = new BigDecimal(""+doble); 
                importeVentasTotales = importeVentasTotales.multiply(numero1);
            }
            
            if(productor.getCon()==5000){
                System.out.println(registroString+"------"+importeVentasTotales);
            }
            
        }
        
        
        System.out.println("numero: "+productor.getCon());
        String dias = "Lunes,Martes,Miercoles,Jueves,Viernes,Sabado,Domingo";
        String diaArray[] = dias.split(",");

        System.out.println("--Ejemplo 1--");
        for(String dia : diaArray){
            System.out.println(dia);
        }
    }

}
