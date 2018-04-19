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

    public static <E> void removeAfterIntersectionPoint(Node<E> list1,Node<E> list2,Comparator<E> cmp){
        Node<E> currentList1=list1.previous,currentList2=list2.previous;
        while (cmp.compare(currentList1.value,currentList2.value)==0){
            currentList1=currentList1.previous;
            currentList2=currentList2.previous;
        }
        list1.previous=currentList1;
        currentList1.next=list1;
    }
    public static <E> void quicksort(Node<E> first, Node<E> last, Comparator<E> cmp){
        
    }
}
