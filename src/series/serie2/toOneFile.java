package series.serie2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

public class toOneFile {
    static class MyFile{
        private BufferedReader br;
        private String line;
        private String word;

        public MyFile(String file) throws IOException {
            br = new BufferedReader(new FileReader(file));
            line=br.readLine();
        }

        public String getNewLine() throws IOException {
            return line=br.readLine();
        }

        public String getLine() {
            return line;
        }

        public String getWord() {
            int nword=0;
            String word[] = new String[0];
            if(nword>line.length())
                return null;

            for (int i = 0; i <= nword; i++) {
                if(i==nword)
                    word = line.split(" ");
            }
            return word[0];
        }
    }

    public static void main(String[] args) throws IOException {
        int len = args.length;
        MyFile[] myFiles=new MyFile[len];

        for (int i = 0; i < len; i++) {
            myFiles[i]=new MyFile(args[i]);
        }
        buildMinHeap(myFiles);


    }

    private static void buildMinHeap(MyFile[] myFiles) {
        int size = myFiles.length;
        for (int i = 0; i < size; i++) {
            minHeapify(myFiles, i, size, String::compareTo);
        }
    }

    private static void minHeapify(MyFile[] w, int p, int hSize, Comparator<String>cmp) {
        int l, r, min;
        l = left(p);
        r = right(p);
        min=p;
        //if(l < hSize && v[l] > v[p]) min=l;
        if(l <= hSize && cmp.compare(w[l].getWord(),w[p].getWord())<0) min=l;

/*        if ( r < hSize && v[r] > v[min]) min = r;
        if ( min == p ) return;
        exchange(v, p, min);*/
        minHeapify(w, min, hSize, cmp);

    }

    private static int right(int p) {
        return 2 * p + 2;
    }

    private static int left(int p) {
        return 2 * p + 1;
    }
}
