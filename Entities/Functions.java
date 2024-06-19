package Entities;

import Exceptions.InformacionInvalida;
import TADS.Hash.MyHashTableImpl;
import TADS.LinkedList.LinkedListImpl;

import java.time.LocalDate;

public class Functions {
    private MyHashTableImpl<LocalDate, MyHashTableImpl<String, LinkedListImpl<Cancion>>> hashMap;
    MyHashTableImpl<LocalDate, LinkedListImpl<Cancion>> hashUrl;

    public Functions(MyHashTableImpl<LocalDate, MyHashTableImpl<String, LinkedListImpl<Cancion>>> hashMap, MyHashTableImpl<LocalDate, LinkedListImpl<Cancion>> hashUrl) {
        this.hashMap = hashMap;
        this.hashUrl = hashUrl;
    }
    //abajo de esto hay que poner las funciones

    public void funcion1 (LocalDate fecha1, String pais1) throws InformacionInvalida {
        //continuar la funcion
    }

    public void funcion2 (LocalDate fecha) throws InformacionInvalida {
        MyHashTableImpl<String, LinkedListImpl<Cancion>> hashPais = hashMap.get(fecha);
        if(hashPais == null){
            System.out.println("No hay datos para esa fecha");
            return;
        }
        MyHashTableImpl<String, Integer> songCount = new MyHashTableImpl<>(50); //almacena las apariciones de las canciones
        for (int i = 0; i < hashPais.buckets.length; i++) {

        }
    }
}
