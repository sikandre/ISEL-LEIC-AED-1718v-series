package series.serie1;

import java.io.*;
import java.util.Comparator;

public class toOneFile {

    private static final String FILE_NAME = "output.txt";

    static class MyFile{//classe interna para obter informacoes de um file em expecifico indicado no construtor
        private BufferedReader br;
        private String line;
        private int nWord;
        private String word[];

        public MyFile(String file, int nWord) throws IOException {//construtor
            this.nWord=nWord;
            br = new BufferedReader(new FileReader(file));
            line=getNewLine();
        }

        public String getNewLine() throws IOException {//saber string seguinte
            do{
                line=br.readLine();
                if(line!=null)
                    word=line.split(" ");
                else
                    return line;
            }while (nWord>word.length-1);
            return line;
        }

        public String getLine() {
            return line;
        }//retorna toda a linha

        public String getWord() {//retorna palavra
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
        int size = myFiles.length;
        for (int i = len-1; i >= 0; i--) {
            myFiles[i]=new MyFile(args[i], 0);
            if(myFiles[i].getWord()==null)
                size--;
        }
        if(size>0) {
            buildMinHeap(myFiles, size);
            fillFile(myFiles, size);
        }
    }

    private static void fillFile(MyFile[] myFiles, int size) throws IOException {//realiza a escrita no ficheiro de output verifica quando acaba de escrever todas as palavas de um dos txt de input
        //quando termina adapta o tamanho até que so resta 1 ficheiro e como as palavras estao dispostas ordenadamente como pretendemos apenas escreve o ultimo até ao final do txt
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

    private static void minHeapify(MyFile[] w, int p, int hSize, Comparator<String>cmp) {//garante que os descendentes de cada nó sejam maiores ou iguais que o nó
        int l, r, min;
        l = left(p);
        r = right(p);
        min=p;
        if(l < hSize && cmp.compare(w[l].getWord(),w[p].getWord())<0)
            min=l;
        if(r < hSize && cmp.compare(w[r].getWord(),w[p].getWord())<0)
            min=r;
        if (min == p ) return;
        exchange(w, p, min);
        minHeapify(w, min, hSize, cmp);
    }

    private static void exchange(MyFile[] w, int i, int j) {//realiza uma troca de posicoes(usado no miheapify para colocar o que pretendemos na posiçao da raiz da arvore binaria)
        MyFile tmp = w[i];
        w[i] = w[j];
        w[j] = tmp;
    }

    private static int right(int p) {
        return (p<<1)+2;
    }//descendente direito

    private static int left(int p) {
        return (p<<1)+1;
    }//descendente esquerdo

    private static void creatFile() throws IOException {//criaçao de ficheiro de output
        FileWriter fw = new FileWriter(FILE_NAME);
        bw = new BufferedWriter(fw);
    }

    private static void writeToFile(String line) throws IOException {//escrita no ficheiro de output
        bw.write(line);
        bw.newLine();
    }
}