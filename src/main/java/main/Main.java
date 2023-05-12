package main;


import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int input = 0;
        Scanner mainScanner = new Scanner(System.in);
        boolean finaliza = true;
        boolean finValida = false;
        boolean fin = false;
        List<Tarjeta> tarjetas = new ArrayList<>();

        while (finaliza) {
            Tarjeta tempTarjeta = new Tarjeta();
            System.out.println("----------------------------------------------------");
            tempTarjeta.cargarPorUsuario();
            
            tarjetas.add(tempTarjeta);
            while (!finValida) {
                try {
                    System.out.println("Desea agregar una nueva tarjeta. (si/no)");
                    String continuar = mainScanner.nextLine().toUpperCase();
                    if (continuar.equals("")){
                        throw new NoSuchElementException();
                    }
                    if (continuar.equals("NO")) {
                        finaliza = false;
                    }
                    finValida = true;
                } catch (NoSuchElementException e) {
                    System.out.println("Error: no se ingreso ningun valor");
                    mainScanner.nextLine();
                } 
            }
            System.out.print("\033[H\033[2J");  
            System.out.flush();  
            finValida=false;
        }
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
        System.out.println("Le damos la bienvenida al sistema de cobro");
        while (!fin) {
            try {
                
                System.out.println("----------------------------------------------------");
                System.out.println("ingrese un numero para acceder al menu");
                System.out.println("1 - Pedir informacion de mis tarjetas");
                System.out.println("2 - Chequear operacion");
                System.out.println("3 - Checar tarjeta");
                System.out.println("4 - Diferenciar tarjetas");
                System.out.println("5 - Obtener taza de una operacion");
                System.out.println("6 - Salir");
                System.out.println("----------------------------------------------------");
                input = mainScanner.nextInt();
                System.out.print("\033[H\033[2J");  
                System.out.flush();  
                System.out.println("----------------------------------------------------");
                switch (input) {
                    case 1:
                        verTarjetas(tarjetas);

                        break;
                    case 2:
                        chequearOperacion();
                        break;
                    case 3:
                        checkTarjeta(tarjetas);
                        break;
                    case 4:
                        diferenciarTarjetas(tarjetas);
                        break;
                    case 5:
                        TazaOperacion();
                        break;
                    case 6:
                        fin = true;
                        break;

                    default:
                        System.out.println("Opcion no válida, intente nuevamente.");
                        break;
                }
            }catch (InputMismatchException e) {
                System.out.println("Error: debe ingresar un valor numerico entero. Intente nuevamente.");
                mainScanner.nextLine();
            }
        }
        mainScanner.close();
       
    }

    private static void verTarjetas(List<Tarjeta> tarjetas){
        for (int i = 0; i < tarjetas.size(); i++) {
            System.out.println( i + " - " + tarjetas.get(i).toString());
        }
    }

    private static void checkTarjeta(List<Tarjeta> tarjetas){
        
        Tarjeta tempTarjeta = seleccionarTarjeta(tarjetas);
        if ( tempTarjeta.isValid()) {
            System.out.println("Tarjeta valida para operar");
        } else {
            System.out.println("Tarjeta no valida para operar");
        }
        
    }

    private static void diferenciarTarjetas(List<Tarjeta> tarjetas) {
        Tarjeta tempTarjeta1 = seleccionarTarjeta(tarjetas);
        Tarjeta tempTarjeta2 = seleccionarTarjeta(tarjetas);
        if (tempTarjeta1.equals(tempTarjeta2)) {
            System.out.println("Las tarjetas seleccionadas son iguales");
        }else{
            System.out.println("Las tarjetas seleccionadas son distintas");
        }
    }

    private static Tarjeta seleccionarTarjeta(List<Tarjeta> tarjetas) {
       
        Scanner mainScanner = new Scanner(System.in);
        int input = 0;
        boolean inputValida = false;

        System.out.println("Elija una tarjeta");
        System.out.println("----------------------------------------------------");
        verTarjetas(tarjetas);
        System.out.println("----------------------------------------------------");
        while (!inputValida) {
            try {
                input = mainScanner.nextInt();
                inputValida = true;
                if (input > tarjetas.size() || input<0) {
                    throw new IllegalArgumentException();
                }
            }catch (InputMismatchException e) {
                System.out.println("Error: debe ingresar un valor numerico entero. Intente nuevamente.");
                mainScanner.nextLine();
            } catch(IllegalArgumentException e){
                System.out.println("Error: Elija un numero dentreo de la lista");
                mainScanner.nextLine();
            }
        }
        System.out.println("----------------------------------------------------");
        return tarjetas.get(input);
    }

    private static void chequearOperacion() {
        Scanner mainScanner = new Scanner(System.in);
        int input = 0;
        boolean inputValida = false;

        System.out.println("Creando nueva operacion");
        while (!inputValida) {
            try {
                System.out.println("ingrese el importe de la nueva operacion");
                input = mainScanner.nextInt();
                inputValida = true;
            }catch (InputMismatchException e) {
                System.out.println("Error: debe ingresar un valor numerico entero. Intente nuevamente.");
                mainScanner.nextLine();
            }
        }

        Operaciones tempOperaciones = new Operaciones();
        
        if (tempOperaciones.isValid(input)) {
            System.out.println("operacion valida");
        } else {
            System.out.println("operacion invalida");
        }
        
    }

    private static void TazaOperacion() {
        boolean inputValida = false;
        boolean marcaValida = false;
        boolean Valida = false;
        Scanner mainScanner = new Scanner(System.in);
        int input = 0;
        String marca = "";
        Operaciones temOperaciones = new Operaciones();

        System.out.println("Creando nueva operacion");
        while (!Valida) {
            try {
                if (!inputValida) {
                    System.out.println("ingrese el importe de la nueva operacion");
                    input = mainScanner.nextInt();
                    if (!temOperaciones.isValid(input)) {
                        System.err.println(
                            "La operacion es invalida, ingrese un monto menor a " 
                            + temOperaciones.getMontoMaximo()
                          );
                        continue;
                    }
                    inputValida = true;
                    mainScanner.nextLine();
                }
                if (!marcaValida) {
                    System.out.println("Ingrese la marca de la tarjeta (VISA,NARA,AMEX)");
                    marca = mainScanner.nextLine().toUpperCase();
                    System.out.println("la taza de la operacione es " + temOperaciones.getTaza(marca, input));
                    marcaValida = true;   
                }
            }catch (InputMismatchException e) {
                System.out.println("Error: debe ingresar un valor numerico entero. Intente nuevamente.");
                mainScanner.nextLine();
            }catch (IllegalArgumentException e) {
                System.out.println("Marca de tarjeta no soportada: " + marca);
               
            }
            if (marcaValida && inputValida) {
                Valida = true;
            }
        }
        
        

    }
}

