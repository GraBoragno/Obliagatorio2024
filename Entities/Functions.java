package Entities;

import Exceptions.InformacionInvalida;
import Exceptions.PosicionInvalida;
import TADS.Hash.MyHashTableImpl;
import TADS.LinkedList.LinkedListImpl;
import TADS.Hash.DoubleNode;
import TADS.LinkedList.MyNode;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;

public class Functions {
    private MyHashTableImpl<LocalDate, MyHashTableImpl<String, LinkedListImpl<Cancion>[]>> hashMap;

    public Functions(MyHashTableImpl<LocalDate, MyHashTableImpl<String, LinkedListImpl<Cancion>[]>> hashMap) {
        this.hashMap = hashMap;

    }
    //abajo de esto hay que poner las funciones

    public void funcion1 (LocalDate fecha1, String pais1) throws InformacionInvalida {
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

        //continuar la funcion
    }

    public void funcion2(LocalDate fecha) throws InformacionInvalida {
        MyHashTableImpl<String, LinkedListImpl<Cancion>[]> hashPais = hashMap.get(fecha);
        if (hashPais == null) {
            System.out.println("No hay datos para esa fecha");
            return;
        }

        // cuenta las apariciones de canciones
        MyHashTableImpl<String, Integer> cancionesCount = new MyHashTableImpl<>(11);
        // las guarda por el url
        MyHashTableImpl<String, Cancion> cancionMap = new MyHashTableImpl<>(11);

        // Recorre hashPais y cuenta todas las apariciones de las canciones
        for (int i = 0; i < hashPais.size(); i++) {
            if (hashPais.getStashes() != null && hashPais.getStashes()[i] != null) {
                LinkedListImpl<Cancion>[] top50 = hashPais.getStashes()[i].getValue();
                if (top50 != null) {
                    for (int j = 0; j < top50.length; j++) {
                        if (top50[j] != null) {
                            MyNode<Cancion> currentNode = top50[j].getFirst();
                            while (currentNode != null) {
                                Cancion cancion = currentNode.getValue();
                                String cancionID = cancion.getUrl();

                                Integer count = cancionesCount.get(cancionID);
                                if (count == null) {
                                    count = 0;
                                }
                                cancionesCount.put(cancionID, count + 1);
                                cancionMap.put(cancionID, cancion);

                                currentNode = currentNode.getNext();
                            }
                        }
                    }
                }
            }
        }

        // va a ordenar las canciones por cantidad de veces que aparecen
        LinkedListImpl<DoubleNode<Integer, Cancion>> topFive = new LinkedListImpl<>();

        // agrega las canciones a la lista
        for (int i = 0; i < cancionesCount.size(); i++) {
            if (cancionesCount.getStashes() != null && cancionesCount.getStashes()[i] != null) {
                String cancionID = cancionesCount.getStashes()[i].getKey();
                Integer count = cancionesCount.get(cancionID);
                Cancion cancion = cancionMap.get(cancionID);

                if (count != null && cancion != null) {
                    DoubleNode<Integer, Cancion> nuevoStash = new DoubleNode<>(count, cancion);
                    topFive.add(nuevoStash);
                }
            }
        }

        // orden la lista por número de apariciones
        topFive.sort(new Comparator<DoubleNode<Integer, Cancion>>() {
            @Override
            public int compare(DoubleNode<Integer, Cancion> o1, DoubleNode<Integer, Cancion> o2) {
                return o2.getKey().compareTo(o1.getKey());
            }
        });

        // esta es para no printear las cosas dos veces
        LinkedListImpl<String> printedSongs = new LinkedListImpl<>();

        // printea las 5 que mas aparecen
        int pos = 1;
        int count = 0;
        MyNode<DoubleNode<Integer, Cancion>> currentNode = topFive.getFirst();
        while (currentNode != null && count < 5) {
            DoubleNode<Integer, Cancion> entry = currentNode.getValue();
            Cancion cancion = entry.getValue();

            // ve si ya se printeo
            boolean alreadyPrinted = printedSongs.contains(cancion.getUrl());
            if (!alreadyPrinted) {
                printedSongs.add(cancion.getUrl());
                String rankingInfo = " con " + entry.getKey() + " apariciones --- ";

                System.out.print(pos + "º" + rankingInfo);
                System.out.print("titulo: " + cancion.getTitulo() + " artista: " + cancion.getArtist());
                System.out.println();

                pos++;
                count++;
            }

            currentNode = currentNode.getNext();
        }
    }
}
