import Entities.CSV;
import Entities.Cancion;
import Exceptions.InformacionInvalida;
import TADS.Hash.MyHashTableImpl;
import TADS.LinkedList.LinkedListImpl;
import java.time.LocalDate;
import java.util.Scanner;

//C:\\Users\\grabo\\OneDrive\\Escritorio\\Dataset2.csv
public class Main {
    public static void main(String[] args) throws InformacionInvalida {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese la ruta del archivo CSV sin comillas:");
        String direcCSV = scanner.nextLine();
//        direcCSV = direcCSV.replace("\\", "\\\\");


        CSV csvProcessor = new CSV();
        MyHashTableImpl<LocalDate, MyHashTableImpl<String, LinkedListImpl<Cancion>>> hashMap = csvProcessor.hashMap(direcCSV);


        scanner.close();
    }
}
