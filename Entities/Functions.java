package Entities;

import Exceptions.InformacionInvalida;
import TADS.Hash.MyHashTableImpl;
import TADS.LinkedList.LinkedListImpl;

import java.time.LocalDate;

public class Functions {
    private MyHashTableImpl<LocalDate, MyHashTableImpl<String, LinkedListImpl<Cancion>>> hashMap;

    public Functions(MyHashTableImpl<LocalDate, MyHashTableImpl<String, LinkedListImpl<Cancion>>> hashMap) {
        this.hashMap = hashMap;
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
        else{
            System.out.println("el if anda");
        }

    }
}
