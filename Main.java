import Exceptions.InformacionInvalida;
import TADS.Hash.MyHashTable;
import TADS.Hash.MyHashTableImpl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
//lo que esta comentado son cosas que tuve que probar
public class Main {
    public static void main(String[] args) throws InformacionInvalida {
        String filePath = "C:\\Users\\grabo\\OneDrive\\Escritorio\\Dataset2.csv";
        Cancion nuevaC = new Cancion();
        MyHashTable<String, Cancion> CancionesAgregadas = new MyHashTableImpl<>(1000);

//        int contador = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {

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
//                System.out.println("url" + " " + nuevaC.url);
//                contadorurl++;

                nuevaC.setTitulo(aux[1]);
//                System.out.println("titulo" + " " + nuevaC.titulo);
//                contadortitulo++;

                nuevaC.setArtist(values [1]);
//                System.out.println("artist" + " " + nuevaC.artist);
//                contadorartista++;

                nuevaC.setDaily_rank(Integer.parseInt(values[2]));
//                System.out.println("daily_rank" + " " + nuevaC.daily_rank);
//                contadordailyrank++;

                nuevaC.setDaily_movement(Integer.parseInt(values[3]));
//                System.out.println("daily_movement" + " " + nuevaC.daily_movement);
//                contadordailymovement++;

                nuevaC.setWeekly_movement(Integer.parseInt(values[4]));
//                System.out.println("weekly_movement" + " " + nuevaC.weekly_movement);
//                contadorweeklymovement++;

                if (values[5].isEmpty()){
                    nuevaC.setCountry(null);
                }else{
                    nuevaC.setCountry(values[5]);
                }
//                System.out.println(values[5]);
//                System.out.println("country" + " " + nuevaC.country);

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

//                System.out.println(nuevaC.toString());
//                contador++;

//                if(CancionesAgregadas.contains(nuevaC.getUrl())){
//                    return;
//                }
                if (CancionesAgregadas.find(nuevaC.getUrl()) != nuevaC) {
                    CancionesAgregadas.put(nuevaC.getUrl(), nuevaC);
                } else {
                    System.out.println("La canción con URL " + nuevaC.getUrl() + " ya está en la tabla.");
                }

                CancionesAgregadas.put(nuevaC.getUrl(), nuevaC);
                System.out.println(CancionesAgregadas.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(CancionesAgregadas.toString());

//        System.out.println(contador);

    }



}
