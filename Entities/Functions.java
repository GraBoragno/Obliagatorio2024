package Entities;

import TADS.Hash.MyHashTableImpl;
import TADS.LinkedList.LinkedListImpl;

import java.time.LocalDate;

public class Functions {
    private MyHashTableImpl<LocalDate, MyHashTableImpl<String, LinkedListImpl<Cancion>>> hashMap;

    public Functions(MyHashTableImpl<LocalDate, MyHashTableImpl<String, LinkedListImpl<Cancion>>> hashMap) {
        this.hashMap = hashMap;
    }
    public LinkedListImpl<Cancion> funcion2 (LocalDate fecha){
        MyHashTableImpl<String, LinkedListImpl<Cancion>> hashPais = hashMap.get(fecha);
        return null;
    }
}
