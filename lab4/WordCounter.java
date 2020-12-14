import java.util.*;

/**
 * Keep a record of how many times each word was
 * entered by users.
 * 
 * @author     Michael Kölling and David J. Barnes
 * @version    1.0 (2016.02.29)
 */
public class WordCounter
{
    // Associate each word with a count.
    private HashMap<String, Integer> counts;
    private BST bstHistogram;
    private HashMap<Double, Double> depthHistogram;
    // will contain the histogramm with the interger over the words

    /**
     * Create a WordCounter
     */
    public WordCounter()
    {
        counts = new HashMap<>();
        bstHistogram = new BST();
        depthHistogram = new HashMap<Double, Double>();
        //updateBSTHistogram(); //instance variable
    }

    /**
     * Update the usage count of all words in input.
     * @param input A set of words entered by the user.
     */
    public void addWords(HashSet<String> input)
    {
        for(String word : input) {
            addWord(word);
        }
    }

    /**
     * Method addWord and update the counts of all words
     *
     * @param word A word to be added
     */
    public void addWord(String word)
    {
        int counter = counts.getOrDefault(word, 0);
        // get(word) would return null if word is not in the counts HashMap
        // but we want to get returned 0 it word is not ... " ....
        // getOrDefault(word, 999) will return 999 if word is not ... " ....
        counts.put(word, counter + 1);
        updateBSTHistogram(word);
    }

    /**
     * get all words
     *
     * @return the set of all words
     */
    public HashSet<String> getWords()
    {
        HashSet<String> set= new HashSet<String>(counts.keySet()); // uses HashSet's copy contructor
        return set;
    }

    private void updateBSTHistogram(String word) // allways to be called after changing counts!!
    // in order to keep inverted consistent!!!
    {
            int counter= counts.get(word); // returns the number of occurencies of the word
            bstHistogram.put(word, counter);
    }

    /**
     * Method calculateInverted 
     * to demonstrate the implementation of problems 8,9 in Lab 3
     * contains redundant code taken from method private updateInverted
     * Think the HashMap in terms of Histogram and inverted Histogram (sketch in the lecture)
     *
     * @return The return value
     */
    public HashMap<Integer, HashSet<String> > calculateInverted()
    {
        HashMap<Integer, HashSet<String> > inverted 
        = new HashMap<Integer, HashSet<String> >();
        // counts.keySet() returns all the words in the HashMap keys
        for (String word: counts.keySet()){
            int counter= counts.get(word); // returns the number of occurencies of the word
            // i.e. the y-values in the histogram !
            HashSet<String> tmp= inverted.get(counts.get(word));// returns the hashSet of words 
            // in inverted for the y-value 
            // or null if that integer is not in the keys of inverted
            if(tmp == null) tmp= new HashSet<String>();
            // inverted.getOrDefault(counter, new HashSet<String>()); // replaces the last 2 lines
            tmp.add(word);
            inverted.put(counter, tmp);
        }
        return inverted;
    }


    public double findNumberOFDepths(ArrayList<Double> depthsList, double depth){
        int counter = 0;
        for(double d : depthsList){
            if(d == depth){
                counter ++;
            }
        }

        return counter;
    }

    public void getAllTreeInfo()
    {   
        BST histogram = this.bstHistogram;
        Iterator iterator = histogram.iterator();
        System.out.print("Tree: ");
        ArrayList<Double> depths = new ArrayList<Double>();
        while(iterator.hasNext()){
            System.out.print(iterator.next() + ", ");
            double nextDepth = histogram.tempDepth;
            depths.add(nextDepth);
        }
        
        System.out.print("\n");
        double meanDepth = (histogram.depthSum/ histogram.numOfNodes);

        double maxDepth = 0;

        for(double d : depths){
            if(d > maxDepth){
                maxDepth = d;
            }
        }

        for(double d = 0; d <= maxDepth; d++){
            depthHistogram.put(d, findNumberOFDepths(depths, d));
        }
        System.out.println("maxDepth: " + maxDepth);
        System.out.println("\n############################\n");
        System.out.println("meanDepth: " + meanDepth);
        System.out.println("\n############################\n");
        System.out.println("Depth Histogram:");
        for(Map.Entry<Double, Double> d : depthHistogram.entrySet()){
            System.out.println(d.getKey() + ": " + d.getValue());
        }
    }   

    public static void main(String[]args){
        WordCounter wc = new WordCounter();
        wc.addWord("c");
        wc.addWord("b");
        wc.addWord("d");
        wc.addWord("a");
        wc.addWord("t");
        wc.addWord("e");
        wc.addWord("z");

        wc.getAllTreeInfo();

        WordCounter wc2 = new WordCounter();
        wc2.addWord("one");
        wc2.addWord("more");
        wc2.addWord("nice");
        wc2.addWord("simple");
        wc2.addWord("example");
        wc2.addWord("of");
        wc2.addWord("a");
        wc2.addWord("binary");
        wc2.addWord("search");
        wc2.addWord("tree");

        wc2.getAllTreeInfo();

    }
}
