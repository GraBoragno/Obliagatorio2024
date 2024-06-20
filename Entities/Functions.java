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

        MyHashTableImpl<String, LinkedListImpl<Cancion>> cancionesDelDia = new MyHashTableImpl<>(11);

        // Recorrer hashPais y agregar todas las canciones a cancionesDelDia
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

                                LinkedListImpl<Cancion> listaCancionX;
                                try {
                                    listaCancionX = cancionesDelDia.get(cancionID);
                                } catch (InformacionInvalida e) {
                                    listaCancionX = new LinkedListImpl<>();
                                    cancionesDelDia.put(cancionID, listaCancionX);
                                }

                                if (listaCancionX == null) {
                                    listaCancionX = new LinkedListImpl<>();
                                    cancionesDelDia.put(cancionID, listaCancionX);
                                }

                                listaCancionX.add(cancion);
                                currentNode = currentNode.getNext();
                            }
                        }
                    }
                }
            }
        }

        // Calcular las canciones más populares y agregarlas a topFive
        LinkedListImpl<DoubleNode<Integer, LinkedListImpl<Cancion>>> topFive = new LinkedListImpl<>();

        for (int i = 0; i < cancionesDelDia.size(); i++) {
            if (cancionesDelDia.getStashes()[i] != null) {
                LinkedListImpl<Cancion> canciones = cancionesDelDia.getStashes()[i].getValue();
                if (canciones != null) { // Verificar que canciones no sea null
                    int count = canciones.size();
                    DoubleNode<Integer, LinkedListImpl<Cancion>> nuevoStash = new DoubleNode<>(count, canciones);
                    topFive.add(nuevoStash);
                }
            }
        }

        // Ordenar topFive por número de apariciones en orden descendente
        topFive.sort(new Comparator<DoubleNode<Integer, LinkedListImpl<Cancion>>>() {
            @Override
            public int compare(DoubleNode<Integer, LinkedListImpl<Cancion>> o1, DoubleNode<Integer, LinkedListImpl<Cancion>> o2) {
                return o2.getKey().compareTo(o1.getKey()); // Orden descendente por número de apariciones
            }
        });

        // Mostrar los resultados de los top 5 más populares me printea los menossss y no puedo arreglarlo
        int pos = 1;
        MyNode<DoubleNode<Integer, LinkedListImpl<Cancion>>> currentNode = topFive.getFirst();
        while (currentNode != null && pos <= 5) {
            DoubleNode<Integer, LinkedListImpl<Cancion>> entry = currentNode.getValue();
            LinkedListImpl<Cancion> canciones = entry.getValue();

            String rankingInfo = canciones.size() > 1 ? " (Empate) " : " ";
            rankingInfo += "con " + entry.getKey() + " apariciones --- ";

            System.out.print(pos + "º" + rankingInfo);

            MyNode<Cancion> currentSongNode = canciones.getFirst();
            while (currentSongNode != null) {
                Cancion cancion = currentSongNode.getValue();
                System.out.print("titulo: " + cancion.getTitulo() + " artista: " + cancion.getArtist());
                currentSongNode = currentSongNode.getNext();
                if (currentSongNode != null) {
                    System.out.print("  ---  ");
                }
            }
            System.out.println();
            pos++;

            currentNode = currentNode.getNext();
        }
    }

}
