package series.serie2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;

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

        public String getWord(int nword) {
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

        String word=myFiles[0].getWord(0);
        myFiles[0].getNewLine();
        word=myFiles[0].getWord(0);


    }
}
