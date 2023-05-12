package main;

public class TazaFactory {
    public static Taza getTaza(String marca) {
        switch (marca) {
            case "VISA":
                return new Visa();
            case "AMEX":
                return new Amex();
            case "NARA":
                return new Nara();
            default:
                throw new IllegalArgumentException();
        }
    }
}