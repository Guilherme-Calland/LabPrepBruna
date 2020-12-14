import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class TextFileReader {
    public static void main(String[]args) throws FileNotFoundException {
        File lotr1 = new File("Lotr.txt");
        File lotr2 = new File("LotrCht2.txt");

        Scanner s = new Scanner(lotr1);
        Scanner s2 = new Scanner(lotr2);

        BST bst1 = new BST();
        RedBlackBST rbt1 = new RedBlackBST();

        while(s.hasNext()){
            String nextWord = s.next();
            bst1.put(nextWord, 0);
            rbt1.put(nextWord, 0);
        }


        System.out.println("First Text BST: ");
        WordCounter.printTreeDepthHistogramBST(bst1);
        System.out.println("\n");
        System.out.println("First Text RBT");
        WordCounter.printTreeDepthHistogramRbt(rbt1);
        System.out.println("\n\n");

        BST bst2 = new BST();
        RedBlackBST rbt2 = new RedBlackBST();

        while(s2.hasNext()){
            String nextWord = s2.next();
            bst2.put(nextWord, 0);
            rbt2.put(nextWord, 0);
        }

        System.out.println("Second Text BST: ");
        WordCounter.printTreeDepthHistogramBST(bst2);
        System.out.println("\n");
        System.out.println("Second Text RBT");
        WordCounter.printTreeDepthHistogramRbt(rbt2);
        System.out.println("\n");


        s.close();
        s2.close();

    
    }
}
