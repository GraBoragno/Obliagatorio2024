package TADS.LinkedList;

import Exceptions.EmptyListException;
import Exceptions.InformacionInvalida;
import Exceptions.PosicionInvalida;

import java.util.Comparator;

public interface MyList<T> {

    void add(T value) throws InformacionInvalida;

    T get(int position) throws PosicionInvalida;

    boolean contains(T value);

    void remove(T value) throws InformacionInvalida, EmptyListException;

    int size();

    public void sort(Comparator<T> comparator);

}
