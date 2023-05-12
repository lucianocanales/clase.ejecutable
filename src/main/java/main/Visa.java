package main;

import java.util.Calendar;
import java.util.Date;

public class Visa implements Taza  {

    private int year;
    private int month;
    
    public Visa( int year, int month) {
        this.year = year;
        this.month = month;
    }

    public Visa() {
        Date date = new Date(); // Fecha actual
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        
        this.month = calendar.get(Calendar.MONTH) + 1; // Los meses empiezan en 0, por eso se le suma 1
        this.year = calendar.get(Calendar.YEAR % 100);
    }
    
    public double getTaza(double importe){
        double resultado = importe + ((year/month) * importe);
        return resultado;
    } 
}
