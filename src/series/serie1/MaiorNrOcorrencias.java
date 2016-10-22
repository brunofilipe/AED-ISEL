package series.serie1;

import java.io.*;
import java.util.Comparator;


public class MaiorNrOcorrencias {

    private static Comparator<File> comparator;
    private static File[] files;
    private static BufferedWriter writer;

    public static void main(String[] args) throws IOException {
        init(args);

    }

    public static void createFiles(int size, int numberOfFiles) throws IOException {
        for (int i = 1, aux = size; i <= numberOfFiles; i++, aux = size) {
            BufferedWriter writer = new BufferedWriter(new FileWriter("test"+i+".txt"));
            BufferedReader reader = new BufferedReader(new FileReader("f"+i+".txt"));
            while (aux-- > 0) {
                writer.write(reader.readLine());
                writer.newLine();
            } writer.close(); reader.close();
        }
    }

    public static void init(String[] args) throws IOException {

    }

    public static void orderFiles() throws IOException {


    }

}
