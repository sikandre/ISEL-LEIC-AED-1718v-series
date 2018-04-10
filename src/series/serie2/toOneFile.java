package series.serie2;

import java.io.*;
import java.util.Comparator;

public class toOneFile {

    private static final String FILE_NAME = "outPut.txt";

    static class MyFile{
        private BufferedReader br;
        private String line;
        private int nWord;
        private String word[];

        public MyFile(String file, int nWord) throws IOException {
            this.nWord=nWord;
            br = new BufferedReader(new FileReader(file));
            line=br.readLine();
        }

        public String getNewLine() throws IOException {
            do{
                line=br.readLine();
            }while (line!= null && getWord()==null);
            return line;
        }

        public String getLine() {
            return line;
        }

        public String getWord() {
            if(line==null)
                return null;
            word = line.split(" ");
            if(nWord > word.length-1)
                return null;
            else
                return word[nWord];
        }
    }

    private static BufferedWriter bw;

    public static void main(String[] args) throws IOException {

        int len = args.length;
        MyFile[] myFiles=new MyFile[len];
        creatFile();

        for (int i = 0; i < len; i++) {
            myFiles[i]=new MyFile(args[i], 0);
        }
        int size = myFiles.length;
        buildMinHeap(myFiles, size);
        fillFile(myFiles, size);
    }

    private static void fillFile(MyFile[] myFiles, int size) throws IOException {
        while (size>1) {
            writeToFile(myFiles[0].getLine());
            if (myFiles[0].getNewLine() == null) {
                exchange(myFiles, 0, size - 1);
                --size;
            }
            if(size==1)
                break;
            minHeapify(myFiles, ((size >> 1) - 1), size, String::compareTo);
        }
        do{
            writeToFile(myFiles[0].getLine());
        }while (myFiles[0].getNewLine()!=null);
        bw.close();
        }

    private static void buildMinHeap(MyFile[] myFiles, int size) throws IOException {
        int pos = (size >> 1) - 1;
        for (; pos >= 0; pos--) {
            minHeapify(myFiles, pos, size, String::compareTo);
        }
    }

    private static void minHeapify(MyFile[] w, int p, int hSize, Comparator<String>cmp) {
        int l, r, min;
        l = left(p);
        r = right(p);
        min=p;

        if(l < hSize && cmp.compare(w[l].getWord(),w[p].getWord())<0)
            min=l;

        if(r < hSize && cmp.compare(w[r].getWord(),w[p].getWord())<0)
            min=r;

        if ( min == p ) return;
        exchange(w, p, min);
        minHeapify(w, min, hSize, cmp);
    }

    private static void exchange(MyFile[] w, int i, int j) {
        MyFile tmp = w[i];
        w[i] = w[j];
        w[j] = tmp;
    }

    private static int right(int p) {
        return (p<<1)+2;
    }

    private static int left(int p) {
        return (p<<1)+1;
    }

    private static void creatFile() throws IOException {
        FileWriter fw = new FileWriter(FILE_NAME);
        bw = new BufferedWriter(fw);
    }

    private static void writeToFile(String line) throws IOException {
        bw.write(line);
        bw.newLine();
        bw.flush();
    }
}
