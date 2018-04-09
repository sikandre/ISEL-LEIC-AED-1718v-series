package series.serie2;

import java.io.*;
import java.util.Comparator;

public class toOneFile {
    static class MyFile{
        private BufferedReader br;
        private String line;
        private int nWord;

        public MyFile(String file, int nWord) throws IOException {
            this.nWord=nWord;
            br = new BufferedReader(new FileReader(file));
            line=br.readLine();
        }

        public String getNewLine() throws IOException {
            do{
                line=br.readLine();
            }while (getWord()==null);
            return line;
        }

        public String getLine() {
            return line;
        }

        public String getWord() {
            String word[] = line.split(" ");
            if(nWord > word.length-1)
                return null;
            else
                return word[nWord];

        }
    }

    static PrintWriter pw;
    public static void main(String[] args) throws IOException {

        int len = args.length;
        MyFile[] myFiles=new MyFile[len];
        creatFile();

        for (int i = 0; i < len; i++) {
            myFiles[i]=new MyFile(args[i], 1);
        }
        int size = myFiles.length;
        buildMinHeap(myFiles, size);


    }

    private static void buildMinHeap(MyFile[] myFiles, int size) throws IOException {
        if(size==1){
            do{
                writeToFile(myFiles[0].getLine());
            }while (myFiles[0].getNewLine()!=null);
            pw.close();
        }
        else {
            int pos = (size >> 1) - 1;
            for (; pos >= 0; pos--) {
                minHeapify(myFiles, pos, size, String::compareTo);
                writeToFile(myFiles[0].getLine());
                if (myFiles[0].getNewLine() == null) {
                    exchange(myFiles, 0, size - 1);
                    --size;
                }
                if (size > 0)
                    buildMinHeap(myFiles, size);
            }
        }
    }

    private static void creatFile() throws FileNotFoundException {
        OutputStream out;
            out = new FileOutputStream("outPut.txt");
            pw = new PrintWriter(out);
    }

    private static void writeToFile(String line) {
            pw.println(line);
            pw.flush();

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
}
