import java.awt.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class PointListCommandLine {

    public PointListCommandLine() {
    }

    public static void main(String[] args) throws IOException {
        ArrayPointList array = new ArrayPointList();

        InputStreamReader reader = new InputStreamReader(System.in);
        StreamTokenizer tokens = new StreamTokenizer(reader);

        while(tokens.nextToken() != StreamTokenizer.TT_NUMBER && !tokens.sval.equals("quit")) {
            Point p;

            if(tokens.sval.equals("add")) {
                p = new Point();
                tokens.nextToken();
                p.x = (int) tokens.nval;
                tokens.nextToken();
                p.y = (int) tokens.nval;
                array.append(p);
            }
            else if(tokens.sval.equals("start")) {
                System.out.println(array.goToBeginning());
            }
            else if(tokens.sval.equals("curr")) {
                p = array.getCursor();
                System.out.println("(" + p.x + ", " + p.y + ")");
            }
            else if(tokens.sval.equals("next")) {
                System.out.println(array.goToNext());
            }
            else if(tokens.sval.equals("prev")) {
                System.out.println(array.goToPrior());
            }
            else if(tokens.sval.equals("end")) {
                System.out.println(array.goToEnd());
            }
            else if(tokens.sval.equals("empty")) {
                System.out.println(array.isEmpty());
            }
            else if(tokens.sval.equals("full")) {
                System.out.println(array.isFull());
            }
            else if(tokens.sval.equals("clear")) {
                array.clear();
            }
        }
    }
}
