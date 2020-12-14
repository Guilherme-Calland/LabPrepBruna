import java.io.*;

public class FileReaderWriter{
    public static void main(String[] args){
        File file = new File("Lotr.txt");
        try{
            FileReader fr = new FileReader(file);
            char[] in = new char[500];
            int size = fr.read(in);
            System.out.println("size: " + size);
            for(char c: in){
                System.out.println(c);
            }
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}