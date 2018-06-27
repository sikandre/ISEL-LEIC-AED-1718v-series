package series.serie3.part1;

public class DNACustomCollection {
    private static final int DNA_FRAGMENTS = 4;
    private static String[] VALID_DNA = {"a","c","t","g"};

    static DNANode root;

    static class DNANode {
        DNANode[] children = new DNANode[DNA_FRAGMENTS];
        int count;

        // isFragmented is true if the node represents
        // end of a word
        boolean isFragmented;

        DNANode(){
            isFragmented = false;
            for (int i = 0; i < DNA_FRAGMENTS; i++){
                children[i] = null;
            }
        }
    }
    static void add(String fragment) {
        int level;
        int length = fragment.length();
        int idx;

        DNANode curr = root;

        //check before
        if(!isValid(fragment)) {
            System.out.println(fragment + "  Is not a valid DNA fragment");
            return;
        }

        for (level = 0; level < length; level++) {
            idx = getIDX(fragment.charAt(level));
            if (curr.children[idx] == null)
                curr.children[idx] = new DNANode(); //create new node
            curr.count++; //count total words with prefix
            curr = curr.children[idx];
        }
        // mark last node as leaf
        curr.isFragmented = true;
    }

    private static boolean isValid(String fragment) {
        for (int i = 0; i < DNA_FRAGMENTS; i++) {
            if(!fragment.toLowerCase().contains(VALID_DNA[i]))
                return false;
        }
        return true;
    }

    private static int getIDX(char c) {
        c = Character.toLowerCase(c);
        int idx=0;
        switch (c){
            case 'a': idx = 0;
                break;
            case 'c': idx = 1;
                break;
            case 't': idx = 2;
                break;
            case 'g': idx = 3;
                break;
        }
        return idx;
    }

    public static int prefixCount(String p){
        int len = p.length();
        int idx;
        DNANode aux = root;
        for (int i = 0; i < len; i++) {
            idx = getIDX(p.charAt(i));
            aux=aux.children[idx];
        }
        return aux==null?0:aux.count;
    }
}
