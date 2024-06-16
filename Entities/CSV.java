package Entities;

import Exceptions.InformacionInvalida;
import TADS.Hash.MyHashTable;
import TADS.Hash.MyHashTableImpl;
import TADS.LinkedList.LinkedListImpl;
import TADS.LinkedList.MyList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class CSV {
    public MyHashTableImpl<LocalDate,MyHashTableImpl<String, LinkedListImpl<Cancion>>> hashDeDatos(String direcCSV)  {

        String filePath = direcCSV;
        MyHashTableImpl<LocalDate, MyHashTableImpl<String, LinkedListImpl<Cancion>>> hashMap = new MyHashTableImpl<>(50);
        //Es un hash con clave fechas que como value tiene otro hash con clave pais y con value una lista con las canciones

        int contador = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                Cancion nuevaC = new Cancion();

                // Divide la línea por puntos y coma
                String[] values = line.split("\",\"");

                for (int i = 0; i < values.length; i++) {
                    values[i] = values[i].replace("\"", "");
//                    values[i] = values[i].replace("", "null");
                }
                values[23] = values[23].replaceAll(";","");

//                System.out.println(values);
                String[] aux = values[0].split(",", 2);


                nuevaC.setUrl(aux[0]);
                nuevaC.setTitulo(aux[1]);
                nuevaC.setArtist(values [1]);
                nuevaC.setDaily_rank(Integer.parseInt(values[2]));
                nuevaC.setDaily_movement(Integer.parseInt(values[3]));
                nuevaC.setWeekly_movement(Integer.parseInt(values[4]));

                if (values[5].isEmpty()){
                    nuevaC.setCountry("World");
                }else{
                    nuevaC.setCountry(values[5]);
                }

                nuevaC.snapshot_date = values[6];
                nuevaC.popularity = Integer.parseInt(values[7]);
                nuevaC.is_explicit = Boolean.parseBoolean(values[8]);
                nuevaC.duration_ms= Integer.parseInt(values[9]);
                nuevaC.album_name = values[10];
                nuevaC.album_release_date = values[11];
                nuevaC.danceability = Float.parseFloat(values[12]);
                nuevaC.energy = Float.parseFloat(values[13]);
                nuevaC.key= Integer.parseInt(values[14]);
                nuevaC.loudness = Float.parseFloat(values[15]);
                nuevaC.mode = Integer.parseInt(values[16]);
                nuevaC.speechiness = Float.parseFloat(values[17]);
                nuevaC.acusticness = Float.parseFloat(values[18]);
                nuevaC.instrumentalness = Float.parseFloat(values[19]);
                nuevaC.liveness = Float.parseFloat(values[20]);
                nuevaC.valence = Float.parseFloat(values[21]);
                nuevaC.tempo = Float.parseFloat(values[22]);
                nuevaC.time_signature = Integer.parseInt(values[23]);

                contador ++;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date = LocalDate.parse(nuevaC.getSnapshot_date(), formatter);

                if (!hashMap.contains(date)){
                    MyHashTableImpl<String, LinkedListImpl<Cancion>> hashNuevo = new MyHashTableImpl<>(50);
                    LinkedListImpl<Cancion> listaNueva = new LinkedListImpl<>();
                    listaNueva.add(nuevaC);
                    hashNuevo.put(nuevaC.getCountry(), listaNueva);
                    hashMap.put(date, hashNuevo);
                }else if (hashMap.getClass())

            }
        } catch (IOException  e) {
            e.printStackTrace();
        } catch (InformacionInvalida e) {
            throw new RuntimeException(e);
        }
        return hashMap;
    }
}