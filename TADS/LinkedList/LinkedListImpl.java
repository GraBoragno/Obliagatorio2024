package TADS.LinkedList;

import Exceptions.EmptyListException;
import Exceptions.InformacionInvalida;
import Exceptions.PosicionInvalida;

import java.util.Comparator;

public class LinkedListImpl<T> implements MyList<T>{

    private MyNode<T> first;

    private MyNode<T> last;

    public LinkedListImpl() {
        this.first = null;
        this.last = null;
    }

    public void addEnd(T value) throws InformacionInvalida {
        MyNode<T> temp = new MyNode<>(value);
        if (value != null){
            if (this.first == null){
                this.first = temp;
            }else {
                this.last.setNext(temp);
            }
            this.last = temp;
        }else {
            throw new InformacionInvalida();
        }
    }

    @Override
    public void add(T value) throws InformacionInvalida{
        addEnd(value);
    }

    @Override
    public int size() {
        int Resultado = 0;
        MyNode<T> temp = this.first;
        while(temp != null){
            temp = temp.getNext();
            Resultado ++;
        }
        return Resultado;
    }

    @Override
    public T get(int position) throws PosicionInvalida {
        if (position < 0 || position >= size()){
            throw new PosicionInvalida();
        }

        MyNode<T> temp = this.first;
        int temp2 = 0;
        while (temp.getNext() != null && position != temp2){
            temp = temp.getNext();
            temp2 ++;
        }
        return temp.getValue();
    }


    @Override
    public boolean contains(T value) {
        MyNode<T> temp = this.first;
        while (temp != null) {
            if (temp.getValue().equals(value)) {
                return true;
            }
            temp = temp.getNext();
        }
        return false;
    }

    @Override
    public void remove(T value) throws InformacionInvalida, EmptyListException {
        if (first == null){
            throw new EmptyListException();
        }else{
            if (!contains(value)){
                throw new InformacionInvalida();
            }
            if (first.getValue().equals(value)){
                first = first.getNext();
                if (first == null){
                    last = null;
                }
            }else{
                MyNode<T> anteriorTemp = null;
                MyNode<T> temp = first;
                while (temp != null && !temp.getValue().equals(value)){
                    anteriorTemp = temp;
                    temp = temp.getNext();
                }
                anteriorTemp.setNext(temp.getNext());
                temp.setNext(null);
            }
        }

    }
    //esto es para la funcion 2
    public void sort(Comparator<T> comparator) {
        if (first == null || first.getNext() == null) {
            return; // La lista está vacía o tiene un solo elemento, ya está ordenada ponele
        }

        boolean swapped;
        do {
            swapped = false;
            MyNode<T> current = first;
            MyNode<T> previous = null;

            while (current != null && current.getNext() != null) {
                if (comparator.compare(current.getValue(), current.getNext().getValue()) > 0) {
                    // Si el nodo actual es mayor que el siguiente los intercambia
                    MyNode<T> next = current.getNext();
                    MyNode<T> nextNext = next.getNext();

                    if (previous == null) {
                        first = next;
                    } else {
                        previous.setNext(next);
                    }
                    next.setNext(current);
                    current.setNext(nextNext);

                    previous = next;
                    swapped = true;
                } else {
                    previous = current;
                    current = current.getNext();
                }
            }
        } while (swapped);
    }


    public MyNode<T> getFirst() {
        return first;
    }

    public MyNode<T> getLast() {
        return last;
    }

    @Override
    public String toString() {
        String s = "";
        try{
            s+=get(0).toString();
            for (int i = 1; i < size(); i++) {
                s += ", " + get(i).toString();
            }
        }catch (PosicionInvalida e) {
            throw new RuntimeException(e);
        }
        s += "\n              ";
        return s;
    }
}