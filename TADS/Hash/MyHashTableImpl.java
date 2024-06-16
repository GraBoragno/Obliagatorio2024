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

    private void resize() {
        int newSize = numBuckets * 2;
        DoubleNode<K, T>[] newBuckets = (DoubleNode<K, T>[]) new DoubleNode[newSize];

        for (int i = 0; i < numBuckets; i++) {
            DoubleNode<K, T> node = buckets[i];
            if (node != null) {
                int newIndex = Math.abs(node.getKey().hashCode()) % newSize;
                while (newBuckets[newIndex] != null) {
                    newIndex = (newIndex + 1) % newSize;
                }
                newBuckets[newIndex] = node;
            }
        }

        buckets = newBuckets;
        numBuckets = newSize;
    }
    @Override
    public void put(K key, T value) throws InformacionInvalida {
        if (key == null || value == null) {
            throw new InformacionInvalida();
        }

        if (ocupados == numBuckets) {
            resize();
        }

        int index = getBucketIndex(key);
        while (buckets[index] != null) {
            if (buckets[index].getKey().equals(key)) {
                buckets[index].setValue(value);
                return;
            }
            index = (index + 1) % numBuckets;
        }

        buckets[index] = new DoubleNode<>(key, value);
        ocupados++;
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
        resize();
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
                index = (index + 1) % numBuckets;
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
