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
        // cuenta las apariciones de canciones
        LinkedListImpl<DoubleNode<Integer, Cancion>> cancionesCount = new LinkedListImpl<>();
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
                                String cancionNombre = cancion.getTitulo();

                                boolean Contiene = false;
                                for (int k = 0; k < cancionesCount.size(); k++) {
                                    if(cancionesCount.get(k).getValue().getTitulo().equals(cancion.getTitulo())){
                                        cancionesCount.get(k).setKey(cancionesCount.get(k).getKey() + 1);
                                        Contiene = true;
                                    }
                                }
                                if(!Contiene) {
                                    cancionesCount.add(new DoubleNode<>(1, cancion));
                                }
                                currentNode = currentNode.getNext();
                            }
                        }
                    }
                }
            }
        }
        Integer numero1 = 0;
        Integer numero2 = 0;
        Integer numero3 = 0;
        Integer numero4 = 0;
        Integer numero5 = 0;
        Cancion cancion1 = null;
        Cancion cancion2 = null;
        Cancion cancion3 = null;
        Cancion cancion4 = null;
        Cancion cancion5 = null;
        for (int i = 0; i < cancionesCount.size(); i++) {
            if (cancionesCount.get(i).getKey() > numero1){
                numero1 = cancionesCount.get(i).getKey();
                cancion1 = cancionesCount.get(i).getValue();
            }
            if (cancionesCount.get(i).getKey() > numero2 && cancionesCount.get(i).getKey() < numero1){
                numero2 = cancionesCount.get(i).getKey();
                cancion2 = cancionesCount.get(i).getValue();
            }
            if (cancionesCount.get(i).getKey() > numero3 && cancionesCount.get(i).getKey() < numero2){
                numero3 = cancionesCount.get(i).getKey();
                cancion3 = cancionesCount.get(i).getValue();
            }
            if (cancionesCount.get(i).getKey() > numero4 && cancionesCount.get(i).getKey() < numero3){
                numero4 = cancionesCount.get(i).getKey();
                cancion4 = cancionesCount.get(i).getValue();
            }
            if (cancionesCount.get(i).getKey() > numero5 && cancionesCount.get(i).getKey() < numero4){
                numero5 = cancionesCount.get(i).getKey();
                cancion5 = cancionesCount.get(i).getValue();
            }
        }

        System.out.println(numero1 + " " + cancion1);
        System.out.println(numero2 + " " + cancion2);
        System.out.println(numero3 + " " + cancion3);
        System.out.println(numero4 + " " + cancion4);
        System.out.println(numero5 + " " + cancion5);
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
        System.out.println(posicion1 + " " + posicion2);
    }

}
