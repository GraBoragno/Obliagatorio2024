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

    public void funcion2 (LocalDate fecha) throws InformacionInvalida {
        //la funcion mas dificil que hice en mi vida
        MyHashTableImpl<String, LinkedListImpl<Cancion>[]> hashPais = hashMap.get(fecha);
        if (hashPais == null) {
            System.out.println("No hay datos para esa fecha");
            return;
        }

        MyHashTableImpl<String, LinkedListImpl<Cancion>> cancionesDelDia = new MyHashTableImpl<>(11);
        //es un asco esto
        for (int i = 0; i < hashPais.size(); i++) { //Recorre todas las fechas en hashpais
            if (hashPais.getStashes()[i] != null) {
                LinkedListImpl<Cancion>[] top50 = hashPais.getStashes()[i].getValue();
                for (int j = 0; j < top50.length; j++) { //es sobre los tops 50 de cada pais basicamente
                    if (top50[j] != null) {
                        MyNode<Cancion> currentNode = top50[j].getFirst();
                        while (currentNode != null) { //recore todos los nodos de las listas de naciones guardandose los valores del valor y el url
                            Cancion cancion = currentNode.getValue();
                            String cancionID = cancion.getUrl();
                            LinkedListImpl<Cancion> listaCancionX;
                            try {
                                listaCancionX = cancionesDelDia.get(cancionID);
                            } catch (InformacionInvalida e) {
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
}
