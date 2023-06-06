package il.ac.telhai.ds.trees;

public class BinaryTree<T> implements BinaryTreeI<T>{

    private T value;
    private BinaryTreeI<T> right;
    private BinaryTreeI<T> left;
    BinaryTree(T ele){
        this.value = ele;
    }

    BinaryTree(T ele , BinaryTreeI<T> left, BinaryTreeI<T> right){
        this.value = ele;
        this.left = left;
        this.right = right;
    }
    public BinaryTreeI<T> getLeft() {
        return this.left;
    }

    public BinaryTreeI<T> getRight() {
        return this.right;
    }

    public T getValue() {
        return this.value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public void setLeft(BinaryTreeI<T> left) {
        this.left = left;
    }

    public void setRight(BinaryTreeI<T> right) {
        this.right = right;
    }

    public boolean isLeaf() {
        return right == null && left == null;
    }

    public int height() {
        if (isLeaf()){
            return 0;
        }
        int left_height = 0;
        if( left != null)
            left_height = left.height();

        int right_height = 0;
        if(right != null)
            right_height = right.height();

        return Math.max(left_height,right_height) + 1;
    }

    public int size() {
        if (isLeaf()){
            return 1;
        }

        int left_size = 0 ;
        if( left != null)
            left_size = left.size();

        int right_size = 0;
        if (right != null)
               right_size = right.size();

        return (left_size + right_size + 1);
    }
    public void clear() {
        left = null;
        right = null;
    }

    public String preOrder() {
        return preOrder(" ", " ");
    }
    public String preOrder(String separationBeforeVal, String separationAfterVal) {

        StringBuilder str = new StringBuilder("");
        str.append(separationBeforeVal);
        str.append(value);
        str.append(separationAfterVal);
        if(left != null)
            str.append(left.preOrder(separationBeforeVal,separationAfterVal));

        if(right != null)
            str.append(right.preOrder(separationBeforeVal,separationAfterVal));

        return str.toString();
    }

    public String inOrder() {
        return inOrder(" ", " ");
    }
    public String inOrder(String separationBeforeVal, String separationAfterVal) {
        StringBuilder str = new StringBuilder();

        if (left != null)
            str.append(left.inOrder(separationBeforeVal, separationAfterVal));

        str.append(separationBeforeVal).append(value).append(separationAfterVal);

        if (right != null)
            str.append(right.inOrder(separationBeforeVal, separationAfterVal));

        return str.toString();
    }

    public String postOrder() {
        return postOrder(" "," ");
    }

    public String postOrder(String separationBeforeVal, String separationAfterVal) {
        StringBuilder str = new StringBuilder();

        if (left != null)
            str.append(left.postOrder(separationBeforeVal, separationAfterVal));
        if (right != null)
            str.append(right.postOrder(separationBeforeVal, separationAfterVal));

        str.append(separationBeforeVal).append(value).append(separationAfterVal);

        return str.toString();
    }
}
