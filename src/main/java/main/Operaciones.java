package main;

public class Operaciones {
    
    private int montoMaximo;

    public Operaciones() {
        this.montoMaximo = 1000;
    }

    public int getMontoMaximo() {
        return montoMaximo;
    }


    public void setMontoMaximo(int montoMaximo) {
        this.montoMaximo = montoMaximo;
    }


    public boolean isValid(int monto) {
        boolean validate = false;

        if (monto < this.montoMaximo) {
            validate = true;
        } 

        return validate;
    }

    public double getTaza(String marca, int importe) {
        double tazaReturned = TazaFactory.getTaza(marca).getTaza(importe);
        return tazaReturned;
    }
}
