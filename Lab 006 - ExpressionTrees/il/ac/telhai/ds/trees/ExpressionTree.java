package il.ac.telhai.ds.trees;
import java.io.IOException;
import java.io.StreamTokenizer;
public class ExpressionTree extends FullBinaryTree<String> {
    ExpressionTree(String ele) {
        super(ele);
    }

    ExpressionTree(String ele, BinaryTreeI<String> left, BinaryTreeI<String> right) {
        super(ele, left, right);
    }

    public static ExpressionTree createTree(StreamTokenizer tokenizer) throws IOException {

        tokenizer.nextToken();
        ExpressionTree left;
        ExpressionTree right;
        char op;

        if (tokenizer.ttype == StreamTokenizer.TT_NUMBER)
            return new ExpressionTree(String.valueOf((int)tokenizer.nval), null, null);

        else {
            op = (char) tokenizer.ttype;
            left = createTree(tokenizer);
            right = createTree(tokenizer);
        }
        return new ExpressionTree(String.valueOf(op), left, right);
    }

    public String infix() {
        return this.inOrder("(", ")");
    }

    public String prefix() {
        return super.preOrder(" ", " ");
    }

    @Override
    public String inOrder(String separationBeforeVal, String separationAfterVal) {

        StringBuilder str = new StringBuilder();

        if (this.isLeaf()) {
            str.append(getValue());
            return str.toString();
        }
        str.append(separationBeforeVal).append(getLeft().inOrder(separationBeforeVal, separationAfterVal));

        str.append(" ").append(getValue()).append(" ");

        str.append(getRight().inOrder(separationBeforeVal, (separationAfterVal))).append(separationAfterVal);

        return str.toString();
    }

    public double evaluate() {
        ExpressionTree left = (ExpressionTree) getLeft();
        ExpressionTree right = (ExpressionTree) getRight();

        double sum;

        switch (getValue()){
            case "+" -> sum = left.evaluate() + right.evaluate();
            case "-" -> sum = left.evaluate() - right.evaluate();
            case "*" -> sum = left.evaluate() * right.evaluate();
            case "/" -> sum = left.evaluate() / right.evaluate();
            default -> sum = Double.parseDouble(getValue());
        }

        return sum;
    }
}