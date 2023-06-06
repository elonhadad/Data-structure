public class SparseMatrix<T> implements Matrix<T> {
    private T defaultValue;
    private int size;
    private List<SparseMatrixEntry> matrix;
    private boolean isTranspose;

    public SparseMatrix(T defaultValue) {
        this(MAX_SIZE, defaultValue);
    }

    public SparseMatrix(int size, T defaultValue) {
        this.defaultValue = defaultValue;
        this.size = size;
        this.matrix = new DLinkedList<>();
        this.isTranspose = false;
    }

    private class SparseMatrixEntry {
        private T value;
        private int row;
        private int col;

        public SparseMatrixEntry(T value, int row, int col) {
            this.value = value;
            this.row = row;
            this.col = col;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public int getRow() {
            return row;
        }

        public void setRow(int row) {
            this.row = row;
        }

        public int getCol() {
            return col;
        }

        public void setCol(int col) {
            this.col = col;
        }
    }

    public T get(int row, int col) {
        throwEx(row, col);
        if(this.matrix.isEmpty()) {
            return this.defaultValue;
        }
        this.matrix.goToBeginning();
        while(this.matrix.getCursor() != null) {
            SparseMatrixEntry s = this.matrix.getCursor();
            if(!isTranspose) {
                if(s.getRow() == row && s.getCol() == col) {
                    return s.getValue();
                }
            }
            else {
                if(s.getRow() == col && s.getCol() == row) {
                    return s.getValue();
                }
            }
            if(this.matrix.getNext() == null) {
                break;
            }
        }
        return this.defaultValue;
    }

    public void set(int row, int col, T element) {
        throwEx(row, col);
        SparseMatrixEntry s = new SparseMatrixEntry(element, row, col);
        this.matrix.goToBeginning();
        while(this.matrix.getCursor() != null) {
            SparseMatrixEntry ele = this.matrix.getCursor();
            if(ele.getRow() == row && ele.getCol() == col) {
                this.matrix.replace(s);
                return;
            }
            if(this.matrix.getNext() == null) {
                break;
            }
        }
        this.matrix.insert(s);
    }

    public void transpose() {
        this.isTranspose = !this.isTranspose;
    }

    private void throwEx(int row, int col) {
        if(row < 1 || col < 1 || row > size || col > size) {
            throw new IllegalArgumentException();
        }
    }
}
