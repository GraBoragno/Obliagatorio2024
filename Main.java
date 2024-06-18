import Entities.CSV;
import Entities.Cancion;
import Exceptions.InformacionInvalida;
import TADS.Hash.MyHashTableImpl;
import TADS.LinkedList.LinkedListImpl;
import java.time.LocalDate;
import java.util.Scanner;

//C:\\Users\\grabo\\OneDrive\\Escritorio\\Dataset2.csv
//C:\\Users\\marti\\OneDrive\\Desktop\\2024 S1\\prog2\\universal_top_spotify_songs (1)\\universal_top_spotify_songs.csv

public class Main {
    public static void main(String[] args) throws InformacionInvalida {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese la ruta del archivo CSV sin comillas:");
        String direcCSV = scanner.nextLine();
//        direcCSV = direcCSV.replace("\\", "\\\\");


        CSV csvProcessor = new CSV();
        MyHashTableImpl<LocalDate, MyHashTableImpl<String, LinkedListImpl<Cancion>>> hashMap = csvProcessor.hashMap(direcCSV);



        int opcion = 0;

        while (opcion != 6 ){
            System.out.println ("Elija una consulta para realizar:" );
            System.out.println ("1. Top 10 canciones en un dia dado");
            System.out.println ("2. Top 5 canciones con mas apariciones en los top 50 en un dia dado");
            System.out.println ("3. Top 7 artistas que mas aparecen en los top 50 para un rango de fechas");
            System.out.println ("4. Cantidad de veces que aparece un artista en un top 50");
            System.out.println ("5. Cantidad de canciones con un tempo en un rango especifico para un rango de fechas");
            System.out.println("6. Finalizar el programa.");

            opcion= scanner. nextInt();

            switch (opcion) {
                case 1:
                    //poner aca la funcion a la que quiero que vaya;
                    break;

                case 2:
                    //poner aca la funcion a la que quiero que vaya;
                    break;

                case 3:
                    //poner aca la funcion a la que quiero que vaya;
                    break;

                case 4:
                    //poner aca la funcion a la que quiero que vaya;
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
        scanner.close();
    }
}
