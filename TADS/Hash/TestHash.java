package TADS.Hash;

import Exceptions.InformacionInvalida;
import static org.junit.jupiter.api.Assertions.*;

import TADS.Hash.MyHashTable;
import TADS.Hash.MyHashTableImpl;
import org.junit.jupiter.api.Test;

public class TestHash {
    @Test
    public void testResizeContainsPut() throws InformacionInvalida {
        MyHashTable<String, Integer> prueba = new MyHashTableImpl<>(5);
        prueba.put("a",1);
        prueba.put("b",2);
        prueba.put("c",3);
        prueba.put("d",4);
        prueba.put("e",5);
        prueba.put("f",6);
        prueba.put("g",7);
        prueba.put("h",8);
        prueba.put("i",9);
        assertTrue(prueba.contains("g"));
        assertTrue(prueba.contains("a"));
        System.out.println(prueba.toString());
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