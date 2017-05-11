package ru.spbstu.icc.kspt.tree;

public class RBNodeImpl<T> implements RBNode<T> {

    private T value;
    private boolean isRed;
    private RBNode<T> left = null;
    private RBNode<T> right = null;

    public RBNodeImpl(T value, boolean isRed) {
        this.value = value;
        this.isRed = isRed;
    }

    @Override
    public T getValue() {
        return value;
    }

    @Override
    public boolean isRed() {
        return isRed;
    }

    @Override
    public RBNode<T> getLeft() {
        return left;
    }

    @Override
    public RBNode<T> getRight() {
        return right;
    }

    @Override
    public void setLeft(RBNode<T> left) {
        this.left = left;
    }

    @Override
    public void setRight(RBNode<T> right) {
        this.right = right;
    }

    @Override
    public void setColor(boolean isRed) {
        this.isRed = isRed;
    }
}