package main;

import java.util.Calendar;
import java.util.Date;

public class Nara implements Taza{
  
    private int day;
    
    public Nara(int day) {
        this.day=day;
    }
    
    public Nara() {

        Date date = new Date(); // Fecha actual
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        this.day = day;
       
    }
    
    public double getTaza(double importe){
        double resultado = importe + ((day * 0.5f) * importe);
        return resultado;
    } 
}
