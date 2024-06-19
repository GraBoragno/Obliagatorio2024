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

    public Functions(MyHashTableImpl<LocalDate, MyHashTableImpl<String, LinkedListImpl<Cancion>>> hashMap) {
        this.hashMap = hashMap;

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


    }
}
