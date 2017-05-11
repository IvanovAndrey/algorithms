package ru.spbstu.icc.kspt.tree;

public class TreeChecker {

    /**
     * Проверить, что дети красных узлов - только черные узлы
     */
    public static <T> boolean checkTree(RBNode<T> node) {
        if (node == null) {
            return true;
        }
        if (node.isRed() && !checkNode(node)) {
            return false;
        }
        return checkTree(node.getLeft()) && checkTree(node.getRight());
    }

    private static <T> boolean checkNode(RBNode<T> node) {
        return (node.getLeft() == null || !node.getLeft().isRed()) &&
                (node.getRight() == null || !node.getRight().isRed());
    }
}