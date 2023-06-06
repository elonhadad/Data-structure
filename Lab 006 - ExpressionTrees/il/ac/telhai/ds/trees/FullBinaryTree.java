package il.ac.telhai.ds.trees;

public class FullBinaryTree<T> extends BinaryTree<T> {

    FullBinaryTree(T ele) {
        super(ele);
    }

    FullBinaryTree(T ele, BinaryTreeI<T> left, BinaryTreeI<T> right) {
        super(ele, left, right);
        if ((left != right) && (left == null || right == null)){
            throw new RuntimeException();
        }
    }

    @Override
    public void setLeft(BinaryTreeI<T> left) {

        if (left instanceof FullBinaryTree<T>) {
            if (left.isLeaf() && this.getLeft().height() == this.getRight().height())
                super.setLeft(left);
        }
        else if (this.getLeft() == null && this.getRight() ==null){
            super.setLeft(left);
        }
        else
            throw new RuntimeException();

    }

    @Override
    public void setRight(BinaryTreeI<T> right) {
        if (right instanceof FullBinaryTree<T>) {
            if (right.isLeaf() && this.getLeft().height() == this.getRight().height())
                super.setRight(right);
        }
        else if (this.getLeft() == null && this.getRight() == null){
            super.setLeft(right);
        }
        else
            throw new RuntimeException();
    }
}
