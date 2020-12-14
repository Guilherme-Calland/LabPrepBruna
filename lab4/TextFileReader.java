import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class TextFileReader {
    public static void main(String[]args) throws FileNotFoundException {
        File lotr1 = new File("Lotr.txt");
        File lotr2 = new File("LotrCht2.txt");

        Scanner s = new Scanner(lotr1);
        Scanner s2 = new Scanner(lotr2);

        

        WordCounter wc1 = new WordCounter();

        while(s.hasNext()){
            wc1.addWord(s.next());
        }

        

        wc1.getAllTreeInfo();


        s.close();

    
    }
}
