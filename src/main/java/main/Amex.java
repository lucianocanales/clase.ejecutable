package main;

import java.util.Calendar;
import java.util.Date;

public class Amex  implements Taza{
  
    private int month;
    
    public Amex( int month) {
       this.month = month;
       
    }

    public Amex() {
        Date date = new Date(); // Fecha actual
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        this.month = calendar.get(Calendar.MONTH) + 1; // Los meses empiezan en 0, por eso se le suma 1
    }
    
    public double getTaza(double importe){
        double resultado = importe + ((month * 0.1f) * importe);
        return resultado;
    } 
}
