package il.ac.telhai.ds.stack;

public class DLinkedList <T> implements List<T>{

    private DNode head;
    private DNode cursor;
    private DNode tail;

    private class DNode {
        private T element;
        private DNode next;
        private DNode prev;

        public DNode(T element){
            this.element = element;
        }

        public T getElement() {
            return element;
        }

        public DNode getNext() {
            return next;
        }

        public void setNext(DNode next) {
            this.next = next;
        }

        public DNode getPrev() {
            return prev;
        }

        public void setPrev(DNode prev) {
            this.prev = prev;
        }
    }

    public DLinkedList() {
        this.head = new DNode(null);
        this.tail = new DNode(null);
        this.head.setNext(tail);
        this.tail.setPrev(head);
        this.cursor = head;
    }

    public void insert(T newElement) {
        if (newElement == null){
            throw new NullPointerException();
        }
        DNode node = new DNode(newElement);
        node.setPrev(cursor);
        node.setNext(cursor.getNext());
        cursor.getNext().setPrev(node);
        cursor.setNext(node);
        cursor = node;
    }

    public T remove() {
        if (isEmpty()){
            return null;
        }

        T element = cursor.getElement();

        cursor.getPrev().setNext(cursor.getNext());
        cursor.getNext().setPrev(cursor.getPrev());
        if(cursor.getNext() == tail) {
            if (isEmpty()) {
                cursor = head;
            }
            else {
                cursor = head.getNext();
            }
        }
        else {
            cursor = cursor.getNext();
        }

        return element;
    }

    public T remove(T element) {
        if (isEmpty()){
            return null;
        }

        DNode temp = cursor;
        goToBeginning();

        while(cursor.getElement() != element){
            if (cursor == tail){
                cursor = temp;
                return null;
            }
            cursor = cursor.getNext();
        }
        return remove();
    }

    public void clear() {
        head.setNext(tail);
        tail.setPrev(head);
        cursor = head;
    }

    public void replace(T newElement) {
        if (newElement == null || isEmpty()){
            throw new NullPointerException();
        }
        insert(newElement);
        cursor = cursor.getPrev();
        remove();
    }

    public boolean isEmpty() {
        if(head.getNext() == tail && tail.getPrev()== head)
            return true;
        return false;
    }

    public boolean goToBeginning() {
        if (!isEmpty()){
            cursor = head.getNext();
            return true;
        }
        cursor = head;
        return false;
    }

    public boolean goToEnd() {
        if (!isEmpty()){
            cursor = tail.getPrev();
            return true;
        }
        return false;
    }

    public T getNext() {
        if (!(cursor.getNext() == tail)){
           cursor = cursor.getNext();
           return cursor.getElement();
        }
        return null;
    }

    public T getPrev() {
        if (cursor.getPrev() == head || isEmpty()){
            return null;
        }

        cursor = cursor.getPrev();
        return cursor.getElement();
    }

    public T getCursor() {
        if (isEmpty()){
            return null;
        }
        T element = cursor.getElement();
        return element;
    }

    public boolean hasNext() {
        if (isEmpty()){
            return false;
        }
        return !(cursor.getNext() == tail);
    }

    public boolean hasPrev() {
        if (isEmpty()){
            return false;
        }
        return (!(cursor.getPrev() == head));
    }
    @Override
    public String toString() {
        return "DLinkedList{" +
                "head=" + head +
                ", cursor=" + cursor +
                ", tail=" + tail +
                '}';
    }
}
