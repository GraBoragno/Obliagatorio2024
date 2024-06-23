package Entities;

import Exceptions.InformacionInvalida;
import Exceptions.PosicionInvalida;
import TADS.Hash.MyHashTableImpl;
import TADS.LinkedList.LinkedListImpl;
import TADS.Hash.DoubleNode;
import TADS.LinkedList.MyNode;

import java.time.LocalDate;

public class Functions {
    private MyHashTableImpl<LocalDate, MyHashTableImpl<String, LinkedListImpl<Cancion>[]>> hashMap;

    public Functions(MyHashTableImpl<LocalDate, MyHashTableImpl<String, LinkedListImpl<Cancion>[]>> hashMap) {
        this.hashMap = hashMap;

    }
    //abajo de esto hay que poner las funciones

    public void funcion1 (LocalDate fecha1, String pais1) throws InformacionInvalida, PosicionInvalida {
        MyHashTableImpl<String, LinkedListImpl<Cancion>[]> hashPais = hashMap.get(fecha1);
        if (hashPais == null) {
            System.out.println("No hay datos para esa fecha");
            return;
        }
        LinkedListImpl<Cancion>[] listaCanciones = hashPais.get(pais1);
        if (listaCanciones == null) {
            System.out.println("No hay datos para esa fecha en ese pais");
            return;
        }
        LinkedListImpl<Cancion>[] top50 = hashPais.get(pais1);
        for (int i = 0; i < 10; i++) {
            if(top50[i] != null){
                for (int j = 0; j < top50[i].size(); j++) {
                    System.out.println(top50[i].get(j));
                }
            }
        }
    }

    public void funcion2(LocalDate fecha) throws InformacionInvalida, PosicionInvalida {
        MyHashTableImpl<String, LinkedListImpl<Cancion>[]> hashPais = hashMap.get(fecha);
        if (hashPais == null) {
            System.out.println("No hay datos para esa fecha");
            return;
        }
        System.out.println("En ejecucion...");

        MyHashTableImpl<String, Integer> cancionesCount = new MyHashTableImpl<>(100);
        MyHashTableImpl<String, Cancion> cancionMap = new MyHashTableImpl<>(100);

        for (int i = 0; i < hashPais.size(); i++) {
            if (hashPais.getStashes()[i] != null) {
                LinkedListImpl<Cancion>[] top50 = hashPais.getStashes()[i].getValue();
                if (top50 != null) {
                    for (int j = 0; j < top50.length; j++) {
                        if (top50[j] != null) {
                            MyNode<Cancion> currentNode = top50[j].getFirst();
                            while (currentNode != null) {
                                Cancion cancion = currentNode.getValue();
                                String cancionTitulo = cancion.getTitulo();

                                Integer count = cancionesCount.get(cancionTitulo);
                                if (count == null) {
                                    cancionesCount.put(cancionTitulo, 1);
                                    cancionMap.put(cancionTitulo, cancion);
                                } else {
                                    cancionesCount.put(cancionTitulo, count + 1);
                                }

                                currentNode = currentNode.getNext();
                            }
                        }
                    }
                }
            }
        }

        Integer[] topCounts = new Integer[5];
        Cancion[] topCanciones = new Cancion[5];
        for (int i = 0; i < cancionesCount.size(); i++) {
            if (cancionesCount.getStashes()[i] != null) {
                String titulo = cancionesCount.getStashes()[i].getKey();
                int count = cancionesCount.getStashes()[i].getValue();
                Cancion cancion = cancionMap.get(titulo);

                for (int j = 0; j < 5; j++) {
                    if (topCounts[j] == null || count > topCounts[j]) {
                        for (int k = 4; k > j; k--) {
                            topCounts[k] = topCounts[k - 1];
                            topCanciones[k] = topCanciones[k - 1];
                        }
                        topCounts[j] = count;
                        topCanciones[j] = cancion;
                        break;
                    }
                }
            }
        }

        for (int i = 0; i < 5; i++) {
            if (topCanciones[i] != null) {
                System.out.println(topCounts[i] + " " + topCanciones[i].getTitulo() + " " + topCanciones[i].getArtist().toString());
            }
        }
    }



    public void funcion3(LocalDate fecha1, LocalDate fecha2) throws InformacionInvalida {
        MyHashTableImpl<String, LinkedListImpl<Cancion>[]> hashPais1 = hashMap.get(fecha1);
        if (hashPais1 == null) {
            System.out.println("No hay datos para esa fecha");
            return;
        }
        MyHashTableImpl<String, LinkedListImpl<Cancion>[]> hashPais2 = hashMap.get(fecha2);
        if (hashPais2 == null) {
            System.out.println("No hay datos para esa fecha");
            return;
        }
        DoubleNode<LocalDate, MyHashTableImpl<String, LinkedListImpl<Cancion>[]>>[] buckets = hashMap.getStashes();
        Integer posicion1 = 0;
        Integer posicion2 = 0;
        for (int i = 0; i < buckets.length; i++) {
            if(buckets[i] != null){
                if(buckets[i].getKey().equals(fecha1)){
                    posicion1 = i;
                }
                if(buckets[i].getKey().equals(fecha2)){
                    posicion2 = i;
                }
            }
        }
//        System.out.println(posicion1 + " " + posicion2);
    }
    public void funcion4(LocalDate fecha, String pais, String artista) throws InformacionInvalida, PosicionInvalida {
        int apariciones = 0;

        MyHashTableImpl<String, LinkedListImpl<Cancion>[]> hashPais = hashMap.get(fecha);
        if (hashPais == null) {
            System.out.println("No hay datos para esa fecha");
            return;
        }

        LinkedListImpl<Cancion>[] cancionesDelTop = hashPais.get(pais);
        if (cancionesDelTop == null) {
            System.out.println("No hay datos para esa fecha en ese país");
            return;
        }

        String artistaMinusculas = artista.toLowerCase();

        for (int i = 0; i < cancionesDelTop.length; i++) {
            if (cancionesDelTop[i] != null) {
                for (int j = 0; j < cancionesDelTop[i].size(); j++) {
                    LinkedListImpl<String> artistas = cancionesDelTop[i].get(j).getArtist();
                    if (artistas != null) {
                        MyNode<String> artistaNode = artistas.getFirst();
                        while (artistaNode != null) {
                            if (artistaNode.getValue().toLowerCase().equals(artistaMinusculas)) {
                                apariciones++;
                            }
                            artistaNode = artistaNode.getNext();
                        }
                    }
                }
            }
        }

        System.out.println("El artista " + artista + " aparece " + apariciones + " veces en el top 50 de " + pais + " en la fecha " + fecha);
    }

    public void funcion5(LocalDate fechaInicio, LocalDate fechaFin, float tempoMin, float tempoMax) throws InformacionInvalida, PosicionInvalida {
        int contadorCanciones = 0;


        for (LocalDate fecha = fechaInicio; !fecha.isAfter(fechaFin); fecha = fecha.plusDays(1)) {
            MyHashTableImpl<String, LinkedListImpl<Cancion>[]> hashPais = hashMap.get(fecha);
            if (hashPais == null) {
                continue;
            }

            for (int i = 0; i < hashPais.size(); i++) {
                if (hashPais.getStashes() != null && hashPais.getStashes()[i] != null) {
                    LinkedListImpl<Cancion>[] listaCanciones = hashPais.getStashes()[i].getValue();
                    if (listaCanciones != null) {
                        for (LinkedListImpl<Cancion> lista : listaCanciones) {
                            if (lista != null) {
                                MyNode<Cancion> currentNode = lista.getFirst();
                                while (currentNode != null) {
                                    Cancion cancion = currentNode.getValue();
                                    if (cancion.getTempo() >= tempoMin && cancion.getTempo() <= tempoMax) {
                                        contadorCanciones++;
                                    }
                                    currentNode = currentNode.getNext();
                                }
                            }
                        }
                    }
                }
            }
        }

        System.out.println("Cantidad de canciones con tempo entre " + tempoMin + " y " + tempoMax + " en el rango de fechas " + fechaInicio + " a " + fechaFin + ": " + contadorCanciones);
    }


}
