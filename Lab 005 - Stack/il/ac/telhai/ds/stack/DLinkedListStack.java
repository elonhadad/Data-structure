package il.ac.telhai.ds.stack;

public class DLinkedListStack<T> implements Stack<T> {
    DLinkedList<T> stack;

    public DLinkedListStack() {
        this.stack = new DLinkedList<>();
    }

    public void push(T t) {
        this.stack.goToEnd();
        this.stack.insert(t);
    }

    public T pop() {
        if(this.stack.isEmpty()) {
            return null;
        }
        this.stack.goToEnd();
        return this.stack.remove(this.stack.getCursor());
    }

    public T top() {
        if(this.stack.isEmpty()) {
            return null;
        }
        this.stack.goToEnd();
        return this.stack.getCursor();
    }

    public boolean isEmpty() {
        return this.stack.isEmpty();
    }

    @Override
    public String toString() {
        if(isEmpty()) {
            return "[]";
        }
        StringBuilder str = new StringBuilder();
        str.append("[");
        this.stack.goToEnd();
        while(this.stack.getCursor() != null) {
            str.append(this.stack.getCursor());

            if(this.stack.getPrev() == null) {
                break;
            }
            str.append(", ");
        }
        str.append("]");
        return str.toString();
    }
}
