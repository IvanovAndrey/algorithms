package ru.spbstu.icc.kspt.tree;

import org.junit.Test;

import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.Assert.assertEquals;

public class BinaryTreeTest {

    @Test
    public void testIterator() throws Exception {
        Random random = new Random();
        Set<Integer> tree = new BinaryTree<>();
        for (int i = 0; i < 1000; i++) {
            tree.add(random.nextInt(10000));
        }
        Set<Integer> treeSet = new TreeSet<>(tree);
        Iterator<Integer> it1 = tree.iterator();
        Iterator<Integer> it2 = treeSet.iterator();
        while (it1.hasNext()) {
            assertEquals(it1.next(), it2.next());
        }
    }
}