package ru.spbstu.icc.kspt.multimap;

import org.junit.Test;

import java.util.Collection;
import java.util.Iterator;

import static org.junit.Assert.*;

public class ListMultimapTest {

    private ListMultimap<Character, Integer> multimap = new ListMultimapImpl<>();

    private void init() {
        multimap.clear();

        int i = 1;
        for (char c = 'a'; c <= 'z'; c++) {
            for (int j = 1; j <= i; j++) {
                multimap.put(c, j);
            }
            i++;
        }
    }

    @Test
    public void testPut() throws Exception {
        init();

        assertFalse(multimap.isEmpty());
        assertEquals(26, multimap.size());
        assertEquals(1, multimap.get('a').size());
        assertEquals(26, multimap.get('z').size());
    }

    @Test
    public void testRemove() throws Exception {
        init();

        assertEquals(26, multimap.size());
        assertEquals(26, multimap.get('z').size());

        assertTrue(multimap.remove('z', 1));
        assertEquals(25, multimap.get('z').size());

        assertEquals(25, multimap.removeAll('z').size());
        assertEquals(25, multimap.size());
    }

    @Test
    public void testContains() throws Exception {
        init();

        assertTrue(multimap.containsKey('z'));
        assertFalse(multimap.containsKey('='));

        assertTrue(multimap.containsValue(26));
        assertFalse(multimap.containsValue(27));

        assertFalse(multimap.containsEntry('a', 20));
        assertTrue(multimap.containsEntry('z', 20));
    }

    @Test
    public void testValues() throws Exception {
        init();

        Collection<Integer> values = multimap.values();
        for (int i = 1; i < 26; i++) {
            assertTrue(values.contains(i));
        }
    }

    @Test
    public void testValuesSize() throws Exception {
        init();

        int expected = 0;
        for (Character c : multimap.keySet()) {
            expected += multimap.get(c).size();
        }

        int actual = 0;
        for (int i : multimap.values()) {
            actual++;
        }

        assertEquals(expected, actual);
    }

    @Test
    public void testValuesIteratorAndRemove() throws Exception {
        init();

        int before = multimap.values().size();
        Iterator<Integer> it = multimap.values().iterator();
        while (it.hasNext()) {
            if (it.next() == 26) { // only once
                it.remove();
            }
        }
        assertEquals(before - 1, multimap.values().size());
    }

    @Test
    public void testValuesIsEmpty() throws Exception {
        assertTrue(multimap.values().isEmpty());
        assertFalse(multimap.values().iterator().hasNext());
    }
}