import java.awt.*;

public class ArrayPointList implements PointList {
    private Point[] arr;
    private int index;
    private int cursor;

    public ArrayPointList() {
        this.arr = new Point[MAX_SIZE];
        this.index = 0;
        this.cursor = -1;
    }

    public ArrayPointList(int size) {
        this.arr = new Point[size];
        this.index = 0;
        this.cursor = -1;
    }

    public void append(Point newPoint) {
        this.arr[index] = newPoint;
        this.cursor = this.index;
        this.index++;
    }

    public void clear() {
        for(int i=0; i < arr.length; i++) {
            this.arr[i] = null;
        }
        this.index = 0;
        this.cursor = -1;
    }

    public boolean isEmpty() {
        return this.index == 0;
    }

    public boolean isFull() {
        return index == arr.length;
    }

    public boolean goToBeginning() {
        if(!isEmpty()) {
            this.cursor = 0;
            return true;
        }
        return false;
    }

    public boolean goToEnd() {
        if(!isEmpty()) {
            this.cursor = this.index-1;
            return true;
        }
        return false;
    }

    public boolean goToNext() {
        if(this.cursor != index-1) {
            this.cursor++;
            return true;
        }
        return false;
    }

    public boolean goToPrior() {
        if(this.arr[cursor] != this.arr[0]) {
            this.cursor--;
            return true;
        }
        return false;
    }

    public Point getCursor() {
        if(!isEmpty()) {
            return new Point(this.arr[cursor]);
        }
        return null;
    }
}