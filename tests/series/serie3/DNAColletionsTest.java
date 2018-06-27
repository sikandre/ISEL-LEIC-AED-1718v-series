package series.serie3;

import org.junit.jupiter.api.Test;
import static series.serie3.part1.DNACustomCollection.*;


public class DNAColletionsTest {

    @Test
    public void  DNACollection1() {
        String keys[] = {"x","AGGGATGC", "GATCTCGAATTCGGTA", "ATTGTTGAATGC", "TTATGCATGC", "ATGTTTGGGAAATTTGGATGC"};

        root = new DNANode();

        // Construct trie
        int i;
        for (i = 0; i < keys.length; i++)
            add(keys[i]);

        System.out.println(prefixCount("c")); // res=0

    }
}
