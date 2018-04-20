package series.serie2;

import java.util.Comparator;
import java.util.Iterator;

public class ListUtils<E> {


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
        if(last!=null && first!=last){
            Node <E> curr = partition(first, last, cmp);
            quicksort(first, curr.previous, cmp);
            quicksort(curr, last, cmp);
        }

    }
    private static <E> Node<E> partition(Node<E> first, Node<E> last, Comparator<E> cmp) {
        Node <E> pivot = last;
        Node <E> i = first.previous;
        Node <E> j = first;
        for (; j != last ; j=j.next) {
            if(cmp.compare(j.value, pivot.value)<=0){
                if(i==null)
                    i=first;
                else
                    i=i.next;
                swap(i,j);
            }
        }
        i = (i==null) ? first : i.next;
        swap(i,last);
        return i;
    }
    private static <E> void swap(Node<E> i, Node<E> j) {
        E val = i.value;
        i.value =j.value;
        j.value = val;
    }

    public static <E>  Node<E> merge(Node<E>[] lists,Comparator<E> cmp){
        int i =0;   //run array
        int j=1;    //run subarray
        int size= lists.length;
        Node <E> head=new Node<>();
        initSentinel(head);
        Node <E> current;
        while (i<size){
            current=lists[i++];
            if (current==null)
                continue;
            Node<E> sublist = current.next;
            addNode(head, current);
            while (hasNext(sublist) && j<size) {
                if(cmp.compare(sublist.value, lists[j].value)>0) {
                    addNode(head, sublist);
                    sublist = sublist.next;
                }
                else {
                    addNode(head, lists[j]);
                    j++;
                }
            }
        }

        return head;
    }

    private static <E> boolean hasNext(Node<E> current) {
        return  current!= null;
    }


    private static <E> void addNode(Node<E> head, Node<E> current) {
        current.next=head;
        current.previous=head.previous;
        head.previous.next=current;
        head.previous=current;

    }

    private static <E> void initSentinel(Node<E> head) {
        head.next=head;
        head.previous=head;
    }
}
