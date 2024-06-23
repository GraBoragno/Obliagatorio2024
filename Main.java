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

//NO FUNCIONA EL PAIS ZA
//LA FECHA 2024-05-13 TAMPOCO

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

            try {
                opcion = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Valor invalido. Ingrese un número del 1 al 6.");
                continue;
            }

            switch (opcion) {
                case 1:
                    LocalDate fecha1 = null;
                    while (fecha1 == null) {
                        System.out.println("Ingrese la fecha (yyyy-mm-dd): ");
                        String fecha1S = scanner.nextLine().trim();
                        try {
                            fecha1 = LocalDate.parse(fecha1S);
                        } catch (DateTimeParseException e) {
                            System.out.println("Formato de fecha incorrecto. Por favor, intente de nuevo.");
                        }
                    }
                    System.out.println("Ingrese el nombre del pais: ");
                    String pais1 = scanner.nextLine().trim();

                    functions.funcion1(fecha1, pais1);
                    break;

                case 2:
                    LocalDate fecha = null;
                    while (fecha == null) {
                        System.out.println("Ingrese la fecha (yyyy-mm-dd): ");
                        String fechaS = scanner.nextLine().trim();
                        try {
                            fecha = LocalDate.parse(fechaS);
                        } catch (DateTimeParseException e) {
                            System.out.println("Formato de fecha incorrecto. Por favor, intente de nuevo.");
                        }
                    }
                    functions.funcion2(fecha);
                    break;

                case 3:
                    LocalDate fecha31 = null;
                    LocalDate fecha32 = null;
                    while (fecha31 == null) {
                        System.out.println("Ingrese la fecha de inicio (yyyy-mm-dd): ");
                        String fechaS1 = scanner.nextLine().trim();
                        try {
                            fecha31 = LocalDate.parse(fechaS1);
                        } catch (DateTimeParseException e) {
                            System.out.println("Formato de fecha incorrecto. Por favor, intente de nuevo.");
                        }
                    }
                    while (fecha32 == null) {
                        System.out.println("Ingrese la fecha de fin (yyyy-mm-dd): ");
                        String fechaS2 = scanner.nextLine().trim();
                        try {
                            fecha32 = LocalDate.parse(fechaS2);
                        } catch (DateTimeParseException e) {
                            System.out.println("Formato de fecha incorrecto. Por favor, intente de nuevo.");
                        }
                    }
                    functions.funcion3(fecha31, fecha32);
                    break;

                case 4:
                    LocalDate fecha4 = null;
                    while (fecha4 == null) {
                        System.out.println("Ingrese la fecha (yyyy-mm-dd): ");
                        String fecha4S = scanner.nextLine().trim();
                        try {
                            fecha4 = LocalDate.parse(fecha4S);
                        } catch (DateTimeParseException e) {
                            System.out.println("Formato de fecha incorrecto. Por favor, intente de nuevo.");
                        }
                    }
                    System.out.println("Ingrese el nombre del pais: ");
                    String pais4 = scanner.nextLine().trim();
                    System.out.println("Ingrese el nombre del artista: ");
                    String artista = scanner.nextLine().trim();

                    functions.funcion4(fecha4, pais4, artista);
                    break;

                case 5:
                    LocalDate fechaInicio = null;
                    LocalDate fechaFin = null;
                    float tempoMin = 0;
                    float tempoMax = 0;

                    while (fechaInicio == null) {
                        System.out.println("Ingrese la fecha de inicio (yyyy-mm-dd): ");
                        String fechaInicioS = scanner.nextLine().trim();
                        try {
                            fechaInicio = LocalDate.parse(fechaInicioS);
                        } catch (DateTimeParseException e) {
                            System.out.println("Formato de fecha incorrecto. Por favor, intente de nuevo.");
                        }
                    }

                    while (fechaFin == null) {
                        System.out.println("Ingrese la fecha de fin (yyyy-mm-dd): ");
                        String fechaFinS = scanner.nextLine().trim();
                        try {
                            fechaFin = LocalDate.parse(fechaFinS);
                        } catch (DateTimeParseException e) {
                            System.out.println("Formato de fecha incorrecto. Por favor, intente de nuevo.");
                        }
                    }

                    while (tempoMin <= 0) {
                        System.out.println("Ingrese el tempo mínimo: ");
                        try {
                            tempoMin = Float.parseFloat(scanner.nextLine().trim());
                        } catch (NumberFormatException e) {
                            System.out.println("Valor invalido. Por favor, ingrese un número.");
                        }
                    }

                    while (tempoMax <= 0) {
                        System.out.println("Ingrese el tempo máximo: ");
                        try {
                            tempoMax = Float.parseFloat(scanner.nextLine().trim());
                        } catch (NumberFormatException e) {
                            System.out.println("Valor invalido. Por favor, ingrese un número.");
                        }
                    }

                    functions.funcion5(fechaInicio, fechaFin, tempoMin, tempoMax);
                    break;


                case 6:
                    System.out.println("programa finalizado.");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Se ingreso un valor invalido, intentar de nuevo: ");
            }
        }

        scanner.close();
    }
}
