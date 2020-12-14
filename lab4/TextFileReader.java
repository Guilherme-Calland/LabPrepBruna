import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class TextFileReader {
    public static void main(String[]args) throws FileNotFoundException {
        File file = new File("Lotr.txt");
        Scanner s = new Scanner(file);

        ArrayList<String> fellowshipOfTheRing = new ArrayList<String>();

        while(s.hasNext()){
            fellowshipOfTheRing.add(s.next());
        }
        s.close();

    
    }
}
