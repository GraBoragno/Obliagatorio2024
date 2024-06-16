package TADS.Hash;

import Exceptions.InformacionInvalida;
import TADS.Hash.DoubleNode;
import TADS.Hash.MyHashTable;

public class MyHashTableImpl<K,T> implements MyHashTable<K, T> {
    public DoubleNode<K,T>[] buckets; // array de nodos dobles
    private int numBuckets;
    private int ocupados;

    public MyHashTableImpl(int numBuckets) {
        this.numBuckets = numBuckets;
        this.ocupados = 0;
        this.buckets = (DoubleNode<K, T>[]) new DoubleNode[numBuckets];
    }
    private int getBucketIndex(K key) {
        return Math.abs(key.hashCode()) % numBuckets;
    }

    private void resize(int size2){
        int size = size2;
        DoubleNode<K, T>[] newBuckets = (DoubleNode<K, T>[]) new DoubleNode[size]; //creo un array nuevo con el doble de lugares
        for (int i = 0; i < numBuckets; i++) {
            DoubleNode<K, T> temp = buckets[i];
            if (temp != null) {
                int indexNuevo = Math.abs(temp.getKey().hashCode()) % size; // lo ubico en el nuevo array
                while (newBuckets[indexNuevo] != null) {
                    indexNuevo = (indexNuevo + 1) % size; //aca es igual al put pero no preciso actualizar el valor
                }
                newBuckets[indexNuevo] = temp;
//                System.out.println(indexNuevo + " " + temp.getKey().toString());

            }
        }
        buckets = newBuckets;
        numBuckets = size;
    }
    @Override
    public void put(K key, T value) throws InformacionInvalida{
        if (key == null || value == null){
            throw new InformacionInvalida();
        }
        int index = getBucketIndex(key);
        DoubleNode <K,T> temp = buckets [index];
        if (ocupados == numBuckets){
            resize(numBuckets*2);
        }
        while (temp != null) {
            if (temp.getKey().equals(key)) {
//                temp.setValue(value);
                return;
            }

            index = (index + 1) % numBuckets;
            temp = buckets[index];

        }
        DoubleNode<K, T> newNode = new DoubleNode<>(key, value);
        buckets[index] = newNode;
        ocupados++;
//        System.out.println(index);
    }

    @Override
    public boolean contains(K key) throws InformacionInvalida {
        if (key == null){
            throw new InformacionInvalida();
        }
        int index = getBucketIndex(key);
        DoubleNode <K,T> temp = buckets [index];
        int start = index;
        while (buckets[index] != null) {
            if (buckets[index].getKey().equals(key)) {
                return true;
            }
            index = (index + 1) % numBuckets;
            if (index == start) break;
        }
        return false;
    }

    @Override
    public void remove(K key) throws InformacionInvalida{
        if (key == null){
            throw new InformacionInvalida();
        }
        int index = getBucketIndex(key);
        DoubleNode <K,T> temp = buckets [index];
        if (temp == null){
            throw new InformacionInvalida();
        }
        while(!temp.getKey().equals(key) && temp != null){
            index = (index + 1) % buckets.length;
            temp = buckets[index];
        }
        buckets[index] = null;
        ocupados--;
        resize(numBuckets);
    }

    @Override
    public T get(K key) throws InformacionInvalida {
        if (key == null){
            throw new InformacionInvalida();
        }
        int index = getBucketIndex(key);
        DoubleNode <K,T> temp = buckets [index];
        while(temp != null){
            if (!temp.getKey().equals(key)){
                index++;
                temp = buckets[index];
            } else if (temp.getKey().equals(key)){
                return temp.getValue();
            }
        }
        throw new InformacionInvalida();
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < numBuckets; i++) {
            if(buckets[i] != null){
                s += i + ". k= " + buckets[i].getKey().toString() + " - v= " + buckets[i].getValue().toString() + "\n";
            }
            else {
                s += i + ". vacio\n";
            }
        }

        return s;
    }
}
