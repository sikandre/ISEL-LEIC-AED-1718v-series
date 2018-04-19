package series.serie2;

import Aed.series.serie2.Node;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class ListUtilTest {

	/*
	 * For circular lists with sentinel
	 */

	public static <E> Node<E> emptyListWithSentinel() {
		Node<E> empty = new Node<E>();
		empty.next = empty.previous = empty;
		return empty;
	}

	public static <E> boolean isEmptyListWithSentinel(Node<E> list) {
		return list.next == list && list.previous == list;
	}



	private static void addWordsToList(Node<String> list, String sentence) {
		if (sentence.length() > 0) {
			String[] words = sentence.split("\\s+");
			for (int j = 0; j < words.length; j++) {
				if (words[j].length() > 0) {
					list.previous.next = newNode(words[j], list.previous, list);
					list.previous = list.previous.next;
				}
			}
		}
	}


	/*
	 * For non_circular lists with no sentinel
	 */



	public static <E> Node<E> emptyListWithoutSentinel() {
		return null;
	}

	public static Node<Integer> getListWithoutSentinel(int begin, int end,
			int step) {
		if (end < begin)
			return null;
		Node<Integer> list = new Node<Integer>();
		Node<Integer> cur = list;
		cur.value = begin;
		begin += step;
		for (; begin < end; begin += step) {
			Node<Integer> next = new Node<Integer>();
			cur.next = next;
			next.previous = cur;
			next.value = begin;
			cur = cur.next;
		}
		return list;
	}

	public static Node<Integer> getRandomList(int dimension) {
		Random r = new Random();
		Node<Integer> list = ListUtilTest.<Integer> emptyListWithoutSentinel();
		for (int i = 0; i < dimension; i++) {
			Node<Integer> novo = newNode(r.nextInt() % 40);
			novo.next = list;
			if (list != null)
				list.previous = novo;
			list = novo;
		}
		return list;
	}

	public static Node<Integer> getListWithoutSentinel(ArrayList<Integer> array) {
		if (array.size() == 0)
			return null;
		Node<Integer> list = new Node<Integer>();
		Node<Integer> cur = list;
		cur.value = array.get(0);
		for (int i = 1; i < array.size(); i++) {
			Node<Integer> next = new Node<Integer>();
			cur.next = next;
			next.previous = cur;
			next.value = array.get(i);
			cur = cur.next;
		}
		return list;

	}

	public static <E> Node<E> getNNode(int N, Node<E> list) {
		for (int i = 0; i < N; i++) {
			if (list == null)
				return null;
			list = list.next;
		}
		return list;

	}

	public static <E> boolean isSorted(Node<E> list, Node<E> last,
			Comparator<E> cmp) {
		Node<E> curr = list;
		if (curr == null || curr == last)
			return true;
		while (curr.next != last) {
			if (cmp.compare(curr.value, curr.next.value) > 0)
				return false;
			curr = curr.next;
		}
		return true;
	}

	/*
	 * For circular lists with sentinel
	 *
	 */




    static <E>  Node<E> makeList( E ... array) {
        Node<E> list = emptyListWithSentinel();
        for ( E v : array ) {
            list.previous = newNode( v, list.previous, list);
            list.previous.previous.next= list.previous;
        }
        return list;
    }

	/*
	 * For non_circular lists with no sentinel
	 *
	 */


    static  <E> boolean isSorted(Node<E> list,Comparator<E> cmp){
        Node<E> curr=list.next;
        if(curr==list || curr==list.previous) return true;
        while( curr.next!=list){
            if(cmp.compare(curr.value, curr.next.value)>0) return false;
            curr=curr.next;
        }
        return true;
    }

	/*
	 * 
	 * Generic Methods
	 */

	public static <E> Node<E> newNode(E v) {
		Node<E> result = new Node<E>();
		result.value = v;
		return result;
	}

	public static <E> Node<E> newNode(E v, Node<E> p, Node<E> n) {
		Node<E> result = newNode(v);
		result.previous = p;
		result.next = n;
		return result;
	}
}
