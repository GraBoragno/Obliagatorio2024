package Entities;

import Exceptions.InformacionInvalida;
import Exceptions.PosicionInvalida;
import TADS.Hash.MyHashTableImpl;
import TADS.LinkedList.LinkedListImpl;
import TADS.Hash.DoubleNode;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;

public class Functions {
    private MyHashTableImpl<LocalDate, MyHashTableImpl<String, LinkedListImpl<Cancion>>> hashMap;
    private MyHashTableImpl<LocalDate, LinkedListImpl<Cancion>> hashUrl;

    public Functions(MyHashTableImpl<LocalDate, MyHashTableImpl<String, LinkedListImpl<Cancion>>> hashMap,
                     MyHashTableImpl<LocalDate, LinkedListImpl<Cancion>> hashUrl) {
        this.hashMap = hashMap;
        this.hashUrl = hashUrl;
    }
    //abajo de esto hay que poner las funciones

    public void funcion1 (LocalDate fecha1, String pais1) throws InformacionInvalida {
        //continuar la funcion
    }

    private DoubleNode<String, Integer> findNextNode(DoubleNode<String, Integer>[] buckets, int idxAct, String key) {
        //metodo auxiliar para la funcion 2, espero que funcione, sirve para encontrar el siguiente
        int index = (idxAct + 1) % buckets.length;
        while (index != idxAct){
            if (buckets[index] != null && buckets[index].getKey().equals(key)) {
                return buckets[index];
            }
            index = (index + 1) % buckets.length;
        }
        return null;
    }
    private void actualizarTop5(String url, int cantidad, String[] topCanciones, int[] topCant) {
        //metodo auxiliar funcion 2
        for (int i = 0; i < 5; i++) {
            if (topCanciones[i] == null || cantidad > topCant[i]) {
                // saca la cancion si hay una con mas apariciones
                for (int j = 4; j > i; j--) {
                    topCanciones[j] = topCanciones[j - 1];
                    topCant[j] = topCant[j - 1];
                }
                // Actualizar la nueva canción y frecuencia en la posición i
                topCanciones[i] = url;
                topCant[i] = cantidad;
                break;
            }
        }
    }

    public void funcion2 (LocalDate fecha) throws InformacionInvalida, PosicionInvalida {
        //la funcion mas dificil que hice en mi vida
        MyHashTableImpl<String, LinkedListImpl<Cancion>> hashPais = hashMap.get(fecha);
        if(hashPais == null){
            System.out.println("No hay datos para esa fecha");
            return;
        }
        //esto no deberia pasar pero por las dudas
        LinkedListImpl<Cancion> listaCancionesFecha = hashUrl.get(fecha);
        if (listaCancionesFecha == null) {
            System.out.println("No hay datos para esa fecha");
            return;
        }
        //hash para ver la cantidad de veces que aparece una cancion
        MyHashTableImpl<String, Integer> hashAuxCan = new MyHashTableImpl<>(50);
        for (int i = 0; i < listaCancionesFecha.size(); i++) {
            Cancion cancion = listaCancionesFecha.get(i);
            String url = cancion.getUrl();
            if (hashAuxCan.contains(url)) {
                int count = hashAuxCan.get(url);
                hashAuxCan.put(url, count + 1);
            } else {
                hashAuxCan.put(url, 1);
            }
        }
        // Encuentra las 5 canciones
        String[] topCanciones = new String[5];
        int[] topFrecuencias = new int[5];

        for (int i = 0; i < hashAuxCan.buckets.length; i++) {
            DoubleNode<String, Integer> node = hashAuxCan.buckets[i];
            while (node != null) {
                actualizarTop5(node.getKey(), node.getValue(), topCanciones, topFrecuencias);
                node = findNextNode(hashAuxCan.buckets, i, node.getKey());
            }
        }
        // porfa que funcione
        System.out.println("Top 5 canciones con más apariciones en los top 50 el " + fecha + ":");
        for (int i = 0; i < 5; i++) {
            if (topCanciones[i] != null) {
                System.out.println(topCanciones[i]);
            }
        }

    }
}
