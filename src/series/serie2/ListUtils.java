package series.serie2;

import java.util.Comparator;

public class ListUtils<E> {


    public static <E> void removeAfterIntersectionPoint(Node<E> list1,Node<E> list2,Comparator<E> cmp){
        Node<E> currentList1=list1.previous,currentList2=list2.previous; //before sentinel
        while (currentList1.value!=null && currentList2.value!=null) {
            currentList1 = currentList1.previous;   //decrement node
            currentList2 = currentList2.previous;
            if (cmp.compare(currentList1.next.value, currentList2.next.value) == 0) {
                list1.previous = currentList1;
                currentList1.next = list1;
            }
        }
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



    public static <E>  Node<E> merge(Node<E>[] lists,Comparator<E> cmp) {

        Node <E> resultList = new Node<>();
        initSentinel(resultList);
        int size = lists.length;
        SingleList<E>[] singleList= (SingleList<E>[])new SingleList[size];
        for(int i=0; i < lists.length; i++){
            if(lists[i]!=null)
                singleList[i] = new SingleList(lists[i]);
            else
                size--;
        }
        if(size>0){
            buildMinHeap(singleList, size, cmp);
            fillList(resultList, singleList, size, cmp);
        }
        
        return resultList;
    }

    private static <E> void fillList(Node<E> resultList, SingleList<E>[] singleList, int size, Comparator<E>cmp) {
        Node<E>last=null;
        while (size>0){

            last=singleList[0].getList();
            addNode(resultList, last);

            if(singleList[0].getNextNode()==null)
                singleList[0]=singleList[--size];

            //minHeapify(singleList, ((size >> 1) - 1), size, cmp);
            buildMinHeap(singleList,size,cmp);
        }
        last.next=resultList;

    }


    private static <E> void buildMinHeap(SingleList<E>[] singleList, int size, Comparator<E> cmp) {
        int pos = (size >> 1) - 1;
        for (; pos >= 0; pos--) {
            minHeapify(singleList, pos, size, cmp);
        }
    }

    private static <E> void minHeapify(SingleList<E>[] list, int pos, int hSize, Comparator<E> cmp) {
        int l, r, min;
        l = left(pos);
        r = right(pos);
        min=pos;
        if(l < hSize && cmp.compare(list[l].getValue(),list[pos].getValue())<0)
            min=l;
        if(r < hSize && cmp.compare(list[r].getValue(),list[min].getValue())<0)
            min=r;
        if (min == pos ) return;
        exchange(list, pos, min);
        minHeapify(list, min, hSize, cmp);
    }

    private static void exchange(SingleList[] list, int pos, int min) {
        SingleList tmp = list[pos];
        list[pos] = list[min];
        list[min] = tmp;
    }

    private static int right(int p) {
        return (p<<1)+2;
    }//descendente direito

    private static int left(int p) {
        return (p<<1)+1;
    }//descendente esquerdo


    private static <E> void addNode(Node<E> res, Node<E> src) {
        Node<E>tmp=src;
        res.previous.next=tmp;
        tmp.previous=res.previous;
        res.previous=tmp;
        //tmp.next=res;
    }

    private static <E> void initSentinel(Node<E> head) {
        head.next=head;
        head.previous=head;
    }

    static class SingleList<E> {
        Node<E> list;
        SingleList(Node<E> list) {
            if(list!=null) {
                this.list = list;
            }
        }
        public Node<E> getList() {
            return list;
        }
        E getValue() {
            return list.value;
        }

        public Node<E> getNextNode() {
            return list = list.next;
        }
    }
}

