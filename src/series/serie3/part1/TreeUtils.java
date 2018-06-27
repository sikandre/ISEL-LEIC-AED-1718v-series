package series.serie3.part1;

import java.util.*;
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

    public static Iterable<Integer> getByDecreasingOrder(Node<Integer> root){
        return new Iterable<Integer>() {
            @Override
            public Iterator<Integer> iterator() {
                return new Iterator<Integer>() {
                    Node<Integer> current = getMax(root);
                    Node<Integer> next;

                    @Override
                    public boolean hasNext() {
                        if(current!=null)
                            return true;
                        current = treePredecessor(next);
                        return current!=null;

                    }

                    @Override
                    public Integer next() {
                        if (!hasNext()) throw new NoSuchElementException();
                        next = current;
                        current = null;
                        return next.value;

                    }
                };
            }

            private Node<Integer> treePredecessor(Node<Integer> x) {
                if(x.left!= null)
                    return getMax(x.left);
                Node<Integer> y = x.parent;
                while (y != null && x==y.left){
                    x=y;
                    y=y.parent;
                }
                return y;
            }

            private Node<Integer> getMax(Node<Integer> root) {
                Node<Integer> aux=root;
                while (aux.right!=null)
                    aux=aux.right;
                return aux;
            }
        };
    }

    public static <E> boolean isBalanced(Node<E> root){
        int l =  depth(root.left);
        int r = depth(root.right);
        return r==l;
    }

    private static <E> int depth(Node<E> root) {
        if(root==null) return -1;
        int l = depth(root.left);
        int r = depth(root.right);
        return Math.max(l,r)+1;
    }

}
