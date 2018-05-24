package series.serie3;

import java.util.function.Predicate;

public class TreeUtils {

    public static Integer sumIf(Node<Integer> root, Predicate<Integer> predicate){
        Integer res=0;
        if(root!=null){
            Integer aux = root.value;
            if(predicate.test(aux))
                res+=aux;
            res+=sumIf(root.left, predicate);
            res+=sumIf(root.right,predicate);
        }
        return res;
    }
}
