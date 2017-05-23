package ru.spbstu.icc.kspt.multimap;

import org.junit.Test;

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

}