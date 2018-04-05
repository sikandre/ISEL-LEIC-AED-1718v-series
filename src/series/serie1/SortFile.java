package series.serie1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;


public class SortFile {

    public class File{//class
        public BufferedReader[] br;

        public File(String arg) {
            br=BufferedReader()
        }
    }

    private static String loadFile(String arg, int line) {
        String aux="";
        BufferedReader in;
        try {
            in= new BufferedReader(new FileReader(arg));
            int i=0;
            while (i!=line)
                aux=in.readLine();
            aux=in.readLine();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return aux;
    }




    public static void main(String []args){
        int len=args.length;
        br = new BufferedReader[len];
        try {
            initBuffer(br,args,len);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        LinkedList<String> list=new LinkedList<>();
        String word="";
        for (int line = 0;word!=null ; line++) {
            for (int file = 0; file <len ; file++) {
                word=loadFile(args[file],line);
                list.add(word);
                if (list.size()>=3)
                    Heap(list,line);
            }
        }
}

    private static void initBuffer(BufferedReader[] br, String[] args, int len) throws FileNotFoundException {
        for (int i = 0; i < len; i++) {
            br[i]=new BufferedReader(new FileReader(args[i]));
        }
    }

    private static void Heap(LinkedList<String> list, int line) {
        int p= (line >> 1)-1;
        for ( ; p >=0 ; --p)
            min_heapify(list, p, line);
    }

    private static void min_heapify(LinkedList<String> list, int p, int line) {
    }

}
