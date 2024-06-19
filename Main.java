import Entities.CSV;
import Entities.Cancion;
import Entities.Functions;
import Exceptions.InformacionInvalida;
import Exceptions.PosicionInvalida;
import TADS.Hash.MyHashTableImpl;
import TADS.LinkedList.LinkedListImpl;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

//C:\\Users\\grabo\\OneDrive\\Escritorio\\Dataset2.csv
//C:\\Users\\marti\\OneDrive\\Desktop\\2024 S1\\prog2\\universal_top_spotify_songs (1)\\universal_top_spotify_songs.csv

public class Main {
    public static void main(String[] args) throws InformacionInvalida, PosicionInvalida {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese la ruta del archivo CSV sin comillas:");
        String direcCSV = scanner.nextLine();


        CSV csvProcessor = new CSV();
        MyHashTableImpl<LocalDate, MyHashTableImpl<String, LinkedListImpl<Cancion>[]>> result = csvProcessor.hashMap(direcCSV);

        MyHashTableImpl<LocalDate, MyHashTableImpl<String, LinkedListImpl<Cancion>[]>> hashMap = result;
        Functions functions = new Functions(hashMap);

        int opcion = 0;

        while (opcion != 6) {
            System.out.println("Menu Consultas");
            System.out.println("1. Top 10 canciones en un pais y dia dado");
            System.out.println("2. Top 5 canciones con mas apariciones en los top 50 en un dia dado");
            System.out.println("3. Top 7 artistas que mas aparecen en los top 50 para un rango de fechas");
            System.out.println("4. Cantidad de veces que aparece un artista en un top 50");
            System.out.println("5. Cantidad de canciones con un tempo en un rango especifico para un rango de fechas");
            System.out.println("6. Finalizar el programa.");
            System.out.println("Elija una consulta para realizar:");

            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    LocalDate fecha1 = null;
                    while (fecha1 == null) {
                        System.out.println("Ingrese la fecha (yyyy-mm-dd): ");
                        String fecha1S = scanner.next();
                        try {
                            fecha1 = LocalDate.parse(fecha1S);
                        } catch (DateTimeParseException e) {
                            System.out.println("Formato de fecha incorrecto. Por favor, intente de nuevo.");
                        }
                    }
                    System.out.println("Ingrese el nombre del pais: ");
                    String pais1 = scanner.next();

                    functions.funcion1(fecha1, pais1);
                    break;


                case 2:
                    LocalDate fecha = null;
                    while (fecha == null) {
                        System.out.println("Ingrese la fecha (yyyy-mm-dd): ");
                        String fechaS = scanner.next();
                        try {
                            fecha = LocalDate.parse(fechaS);
                        } catch (DateTimeParseException e) {
                            System.out.println("Formato de fecha incorrecto. Por favor, intente de nuevo.");
                        }
                    }
                    functions.funcion2(fecha);
                    break;

                case 3:
                    //poner aca la funcion a la que quiero que vaya;
                    break;

                case 4:
                    LocalDate fecha4 = null;
                    while (fecha4 == null) {
                        System.out.println("Ingrese la fecha (yyyy-mm-dd): ");
                        String fecha4S = scanner.next();
                        try {
                            fecha4 = LocalDate.parse(fecha4S);
                        } catch (DateTimeParseException e) {
                            System.out.println("Formato de fecha incorrecto. Por favor, intente de nuevo.");
                        }
                    }
                    break;

                case 5:
                    //poner aca la funcion a la que quiero que vaya;
                    break;

                case 6:
                    System.out.println("programa finalizado.");
                    break;

                default:
                    System.out.println("Se ingreso un valor invalido, intentar de nuevo: ");
            }
        }

        //no se si funciona!!
        System.out.println("Le gustaria hacer otra consulta? (si/no): ");
        String respuesta = scanner.next();

        if (!respuesta.equals("si")) {
            System.out.println("Finalizando el programa.");
            scanner.close();
            return;

        }
    }
}
