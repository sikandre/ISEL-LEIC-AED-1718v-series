package series.serie2;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class ListUtils<E> {
    static class Node<E>{
        E value;
        Node<E> next;
        Node<E> previous;
    }
    private Node<E> head;
    public ListUtils() {
        head=new Node<E>();
        head.next=head;
        head.previous=head;
    }
//k
    public static <E> void removeAfterIntersectionPoint(Node<E> list1,Node<E> list2,Comparator<E> cmp){
        List<Integer> List= new LinkedList<>();
        Node<E> current= list1.next;
        int aux=0;
        while (current!=head){
            if(current.value==cmp)List.add(aux);
            ++aux;
            current=current.next;
        }
    }
}
