package series.serie3;

public class BST<E extends Comparable<E>> {
    Node<E> root;
    public static <E extends Comparable<E>> boolean less(E f, E g){
        return (f.compareTo(g)<0)? true:false;
    }
    public static <E extends Comparable<E>> boolean equals(E f, E g){
        return (f.compareTo(g)==0)? true:false;
    }
}

