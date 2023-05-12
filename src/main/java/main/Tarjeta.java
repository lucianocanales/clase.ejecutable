package main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Tarjeta {
    private String marca;
    private Taza taza;
    private long numero;
    private String cardHolder;
    private Date vencimiento;
    private boolean valid;
    
    public Tarjeta() {
    }


    /**
     * @param marca
     * @param taza
     * @param numero
     * @param cardHolder
     * @param vencimiento
     * @param valid
     */
    public Tarjeta(String marca, long numero, String cardHolder, Date vencimiento) {
        this.marca = marca;
        this.taza = TazaFactory.getTaza(marca);
        this.numero = numero;
        this.cardHolder = cardHolder;
        this.vencimiento = vencimiento;
        this.setValid();
    }



    public String getMarca() {
        return marca;
    }



    public void setMarca(String marca) {
        this.marca = marca;
    }



    public Taza getTaza() {
        return taza;
    }



    public void setTaza(Taza taza) {
        this.taza = taza;
    }



    /**
     * @return
     */
    public long getNumero() {
        return numero;
    }


    /**
     * @param numero
     */
    public void setNumero(long numero) {
        this.numero = numero;
    }


    /**
     * @return
     */
    public String getCardHolder() {
        return cardHolder;
    }


    /**
     * @param cardHolder
     */
    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }


    /**
     * @return
     */
    public Date getVencimiento() {
        return vencimiento;
    }


    /**
     * @param vencimiento
     */
    public void setVencimiento(Date vencimiento) {
        this.vencimiento = vencimiento;
    }


    /**
     * @return
     */
    public boolean isValid() {
        return valid;
    }


    /**
     * @param valid
     */
    public void setValid() {
        Date fechaActual = new Date();
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(fechaActual);
        this.valid = false;
        if (this.vencimiento.compareTo(calendario.getTime()) >= 0){
            this.valid = true;
        } 
    }

    public void cargarPorUsuario() {

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yyyy");
        
        boolean fechaValida = false;
        boolean marcaValida = false;
        boolean numeroValida = false;
        boolean cardHolderValida = false;
        boolean finaliza = false;
        boolean hayError = false;

        String marca = "";
        long numero = 0;

        Scanner scanner = new Scanner(System.in);
        while (!finaliza) {
            try {
                if (!hayError) {
                    System.out.println("Agregando nueva tarjeta");
                    hayError = true;
                }
                if (!marcaValida) {
                    System.out.println("Ingrese la marca de la tarjeta (VISA,NARA,AMEX)");
                    marca = scanner.nextLine().toUpperCase();
                    this.taza = TazaFactory.getTaza(marca);
                    this.marca = marca;
                    marcaValida = true;
                  
                    
                }

                if (!numeroValida) {
                    System.out.println("ingrese el numero de la tarjeta");
                    numero = scanner.nextLong();
                    this.numero = numero;
                    numeroValida = true;
                    scanner.nextLine();
                }

                if (!cardHolderValida) {
                    System.out.println("ingrese el nombre y apellido del titular de la tarjeta tal como aparece");
                    this.cardHolder = scanner.nextLine().toUpperCase();
                    if (this.cardHolder.equals("")) {
                        throw new NoSuchElementException();
                    }
                    cardHolderValida = true;
                } 

                if (!fechaValida) {
                    System.out.print("Ingrese la fecha de vencimiento de la tarjeta (formato MM/yyyy): ");
                    String fechaStr = scanner.nextLine();
                    this.vencimiento = dateFormat.parse(fechaStr);
                    fechaValida = true;
                 
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Marca de tarjeta no soportada: " + marca);
               
            }catch (InputMismatchException e) {
                System.out.println("Error: debe ingresar un valor numerico entero. Intente nuevamente.");
                scanner.nextLine();
            }catch (NoSuchElementException e) {
                System.out.println("Error: no se ingreso ningún valor");
            } catch (ParseException e) {
                System.out.println("Formato de fecha invalido. Intente nuevamente.");
            }
            if (marcaValida 
                && numeroValida 
                && cardHolderValida 
                && fechaValida
                ) {
                    this.setValid();
                    System.out.println("Se agrego la tarjeta con exito.");
                    finaliza = true;    
            }
            }
          
        
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yyyy");

        return "Tarjeta [marca = " + marca + ", numero = " + numero + ", cardHolder = " + cardHolder
                + ", vencimiento = " + dateFormat.format(vencimiento) + "]";
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((marca == null) ? 0 : marca.hashCode());
        result = prime * result + (int) (numero ^ (numero >>> 32));
        result = prime * result + ((cardHolder == null) ? 0 : cardHolder.hashCode());
        result = prime * result + ((vencimiento == null) ? 0 : vencimiento.hashCode());
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Tarjeta other = (Tarjeta) obj;
        if (marca == null) {
            if (other.marca != null)
                return false;
        } else if (!marca.equals(other.marca))
            return false;
        if (numero != other.numero)
            return false;
        if (cardHolder == null) {
            if (other.cardHolder != null)
                return false;
        } else if (!cardHolder.equals(other.cardHolder))
            return false;
        if (vencimiento == null) {
            if (other.vencimiento != null)
                return false;
        } else if (!vencimiento.equals(other.vencimiento))
            return false;
        return true;
    }


   
 
    

}
