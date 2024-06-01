package TADS.Hash;

import Exceptions.InformacionInvalida;
import static org.junit.jupiter.api.Assertions.*;
import TADS.Hash.MyHashTable;
import TADS.Hash.MyHashTableImpl;
import org.junit.jupiter.api.Test;

public class TestHash {
    @Test
    public void testResizeContainsPut() throws InformacionInvalida {
        MyHashTable<Integer, Integer> prueba = new MyHashTableImpl<>(5);
        prueba.put(142,1);
        prueba.put(25,2);
        prueba.put(33,3);
        prueba.put(4,4);
        prueba.put(57,5);
        prueba.put(623,6);
        prueba.put(78,7);
        prueba.put(867,8);
        prueba.put(97,9);
        assertTrue(prueba.contains(142));
        assertTrue(prueba.contains(867));
    }
    @Test
    public void testPut1() {
        MyHashTable<Integer, Integer> prueba = new MyHashTableImpl<>(30);
        assertThrows(InformacionInvalida.class, () -> {
            prueba.put(null, 34);
        });
    }
    @Test
    public void testPutyContains2() throws InformacionInvalida {
        MyHashTable<Integer, Integer> prueba = new MyHashTableImpl<>(30);
        prueba.put(1, 146);
        prueba.put(2, 234);
        assertTrue(prueba.contains(1));
        assertTrue(prueba.contains(2));
    }
    @Test
    public void testPut3() {
        MyHashTable<Integer, Integer> prueba = new MyHashTableImpl<>(30);
        assertThrows(InformacionInvalida.class, () -> {
            prueba.put(3, null);
        });
    }
    @Test
    public void testFind1() throws InformacionInvalida {
        MyHashTable<Integer, Integer> prueba = new MyHashTableImpl<>(30);
        prueba.put(1,5);
        prueba.put(12,67);
        assertEquals(5,prueba.find(1));
        assertEquals(67,prueba.find(12));
    }
    @Test
    public void testFind2() {
        MyHashTable<Integer, Integer> prueba = new MyHashTableImpl<>(30);
        assertThrows(InformacionInvalida.class, () -> {
            prueba.find(23);
        });
    }
    @Test
    public void testFind3() {
        MyHashTable<Integer, Integer> prueba = new MyHashTableImpl<>(30);
        assertThrows(InformacionInvalida.class, () -> {
            prueba.find(null);
        });
    }
    @Test
    public void testRemove1() throws InformacionInvalida {
        MyHashTable<Integer, Integer> prueba = new MyHashTableImpl<>(30);
        prueba.put(3,98);
        prueba.put(13,12);
        prueba.remove(3);
        assertTrue(prueba.contains(13));
    }
    @Test
    public void testRemove2() throws InformacionInvalida {
        MyHashTable<Integer, Integer> prueba = new MyHashTableImpl<>(30);
        prueba.put(3,98);
        prueba.put(13,12);
        prueba.remove(3);
        assertFalse(prueba.contains(3));
    }
}