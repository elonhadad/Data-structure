public class Main {
    public static void main(String[] args) {
        DiagonalMatrix mat = new DiagonalMatrix(3);
        mat.set(1, 1, 7);
        mat.set(2, 3, 11);
        mat.transpose();

        System.out.println(mat.get(3, 2));
//        System.out.println(mat.get(1, 3));
//        mat.transpose();
//        System.out.println(mat.get(3, 2));
//        System.out.println(mat.get(3, 1));
    }
}
