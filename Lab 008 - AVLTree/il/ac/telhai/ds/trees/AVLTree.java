package il.ac.telhai.ds.trees;

public class AVLTree<T extends Comparable<T>> {
    private AVLTree<T> left;
    private AVLTree<T> right;
    private T value;
    private int height;

    public AVLTree(T val) {
        this.left = null;
        this.right = null;
        this.value = val;
        this.height = 0;
    }

    //add the value to the tree, and return the updated root of the tree.
    public AVLTree<T> add(T value) {
        if(value == null) {
            return this;
        }
        if(value.compareTo(getValue()) < 0) {
            if(left == null) {
                left = new AVLTree<>(value);
            }
            else {
                left.add(value);
            }
        }
        else {
            if(right == null) {
                right = new AVLTree<>(value);
            }
            else {
                right.add(value);
            }
        }

        setHeight();

        int bf = balanceFactor();

        if(bf == 2 || bf == -2) {
            if(bf == 2) {
                if(left.balanceFactor() >= 0) {
                    rotateLL();
                }
                else if(left.balanceFactor() == -1) {
                    rotateLR();
                }
            }
            else {
                if(right.balanceFactor() <= 0) {
                    rotateRR();
                }
                else if(right.balanceFactor() == 1) {
                    rotateRL();
                }
            }
        }
        return this;
    }

    //return the value in this node
    public T getValue() {
        return this.value;
    }

    //return the left subTree of this node
    public AVLTree<T> getLeft() {
        return this.left;
    }

    //return the right subTree of this node
    public AVLTree<T> getRight() {
        return this.right;
    }

    private int getRightHeight() {
        if(right == null) {
            return -1;
        }
        return right.height;
    }

    private int getLeftHeight() {
        if(left == null) {
            return -1;
        }
        return left.height;
    }

    private void setHeight() {
        if(left != null) {
            left.height = Math.max(left.getLeftHeight(), left.getRightHeight()) + 1;
        }
        if(right != null) {
            right.height = Math.max(right.getRightHeight(), right.getLeftHeight()) + 1;
        }
        height = Math.max(getRightHeight(), getLeftHeight()) + 1;
    }

    private int balanceFactor() {
        return getLeftHeight() - getRightHeight();
    }

    private void rotateLR() {
        AVLTree<T> leftChild = getLeft();
        left = getLeft().getRight();
        leftChild.right = getLeft().getRight();
        left.left = leftChild;
        rotateLL();
    }

    private void rotateLL() {
        AVLTree<T> current = new AVLTree<>(getValue());
        current.left = getLeft().getRight();
        current.right = getRight();
        value = left.getValue();
        right = current;
        left = getLeft().getLeft();
        setHeight();
    }

    private void rotateRL() {
        AVLTree<T> rightChild = getRight();
        right = getRight().getLeft();
        rightChild.left = getRight().getRight();
        right.right = rightChild;
        rotateRR();
    }

    private void rotateRR() {
        AVLTree<T> current = new AVLTree<>(getValue());
        current.right = getRight().getLeft();
        current.left = getLeft();
        value = getRight().getValue();
        left = current;
        right = getRight().getRight();
        setHeight();
    }
}
