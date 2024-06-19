package Entities;

import Exceptions.InformacionInvalida;
import TADS.Hash.MyHashTableImpl;
import TADS.LinkedList.LinkedListImpl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

public class CSV {
    public MyHashTableImpl<LocalDate, MyHashTableImpl<String, LinkedListImpl<Cancion>[]>> hashMap(String direcCSV) throws InformacionInvalida {

        String filePath = direcCSV;
        MyHashTableImpl<LocalDate, MyHashTableImpl<String, LinkedListImpl<Cancion>[]>> hashMap = new MyHashTableImpl<>(50);
        //Es un hash con clave fechas que como value tiene otro hash con clave pais y con value una lista con las canciones

//        int contador = 0;

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

                String dateString = values[6];
                LocalDate date = LocalDate.parse(dateString);


                nuevaC.snapshot_date = date;
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
//                System.out.println(nuevaC.toString());
//                contador ++;

                MyHashTableImpl<String, LinkedListImpl<Cancion>[]> hashPais = hashMap.get(date);
                boolean algo = hashMap.contains(date);

                if (!algo){
                    // caso en el que el hash no tiene la fecha, entonces agrego la fecha, pero para eso agrego el hash con el pais y la linked list con la cancion
                    MyHashTableImpl<String, LinkedListImpl<Cancion>[]> hashNuevo = new MyHashTableImpl<>(50);
                    LinkedListImpl<Cancion>[] listaNueva = new LinkedListImpl[50];
                    listaNueva[0].add(nuevaC);
                    hashNuevo.put(nuevaC.getCountry(), listaNueva);
                    hashMap.put(date, hashNuevo);
                }else {
                    if (!hashPais.contains(nuevaC.getCountry())) {
                        // Caso en el que está la fecha pero no está el país, entonces creo el país pero para eso creo la linked list con la canción
                        LinkedListImpl<Cancion>[] listaNueva = new LinkedListImpl[50];
                        listaNueva[0].add(nuevaC);
                        hashPais.put(nuevaC.getCountry(), listaNueva);
                    } else {
                        // Caso en el que está la fecha y el país, entonces agrego la canción a la lista existente
                        LinkedListImpl<Cancion>[] listaExistente = hashPais.get(nuevaC.getCountry());
                        listaExistente[0].add(nuevaC);
                    }
                }
            }
        } catch (IOException  e) {
            e.printStackTrace();
        } catch (InformacionInvalida e) {
            throw new RuntimeException(e);
        }
//        System.out.println(hashMap.toString());
        return hashMap;
    }

}