import java.security.InvalidParameterException;

public class DiagonalMatrix implements Matrix {
    private final double[] arr;
    private final int size;
    private boolean isTranspose;


    public DiagonalMatrix() {
        this(MAX_SIZE);
    }

    public DiagonalMatrix(int size) {
        this.isTranspose = false;
        this.arr = new double[(size * 2) - 1];
        this.size = size;
    }

    public double get(int i, int j) throws InvalidParameterException {
        if((i < 1 || i > size) || (j < 1 || j > size)) {
            throw new InvalidParameterException("Invalid index (i or j)");
        }
        if(!isTranspose) {
            return this.arr[(i - j) + (size - 1)];
        }
        return this.arr[(j - i) + (size - 1)];
    }

    public void set(int i, int j, double x) throws InvalidParameterException {
        if((i < 1 || i > size) || (j < 1 || j > size)) {
            throw new InvalidParameterException("Invalid index (i or j)");
        }
        if(isTranspose) {
            this.arr[(j - i) + (size - 1)] = x;
        }
        this.arr[(i - j) + (size - 1)] = x;
    }

    public void transpose() {
        this.isTranspose = !this.isTranspose;
    }

    public Matrix getTranspose() {
        DiagonalMatrix mat = new DiagonalMatrix(size);
        int index = (size * 2) - 1;
        for(int i = 0; i < (size * 2) - 1; i++) {
            mat.arr[i] = this.arr[index - 1 - i];
        }
        return mat;
    }

    @Override
    public String toString() {
        String output = "";
        for(int i = 1; i < size+1; i++) {
            for(int j = 1; j < size+1; j++) {
                output += get(i, j);
                if(j != size) {
                    output += "\t";
                }
            }
            output += "\n";
        }
        return output;
    }
}
