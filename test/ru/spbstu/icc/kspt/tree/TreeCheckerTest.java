package ru.spbstu.icc.kspt.tree;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TreeCheckerTest {

    @Test
    public void testEmptyTree() throws Exception {
        RBNode<Integer> root = new RBNodeImpl<>(5, false);
        assertEquals(true, TreeChecker.checkTree(root));
    }

    @Test
    public void testCheckTree() throws Exception {
        RBNode<Integer> root = new RBNodeImpl<>(5, false); // black root
        RBNode<Integer> node1 = new RBNodeImpl<>(3, true); // red node
        RBNode<Integer> node2 = new RBNodeImpl<>(1, true); // another red node

        root.setLeft(node1);
        node1.setLeft(node2); // red node with red child

        assertEquals(false, TreeChecker.checkTree(root));

        node2.setColor(false); // red node with black child

        assertEquals(true, TreeChecker.checkTree(root));
    }
}