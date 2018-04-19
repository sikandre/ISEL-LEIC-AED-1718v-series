package series.serie2;



import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static series.serie2.ListUtils.merge;

public class MergeTest {
	static final Comparator<Integer> CMP_REVERSE_ORDER= new Comparator<Integer>() {
		public int compare(Integer i1, Integer i2) {
			return i2.compareTo(i1);
		}
	};	

	static final Comparator<Integer> CMP_NATURAL_ORDER= new Comparator<Integer>() {
		public int compare(Integer i1, Integer i2) {
			return i1.compareTo(i2);
		}
	};	
	@Test
	public void  merge_empty_array(){
		Node<Integer>[] array=(Node<Integer>[])new Node[10];
		assertTrue(ListUtilTest.isEmptyListWithSentinel(merge(array, CMP_NATURAL_ORDER)) );
	}
	
	@Test
	public void merge_singleton_lists(){
		ArrayList<Integer> elements=new ArrayList<Integer>();
		Node<Integer>[] array=(Node<Integer>[])new Node[10];
		for(int i=0;i<array.length;i++){
			elements.add(i);
			array[i]=new Node<Integer>(i);
		}
		Node<Integer> merge=merge(array,CMP_NATURAL_ORDER);
		assertTrue(ListUtilTest.isSorted(merge, CMP_NATURAL_ORDER));
		Node<Integer> aux=merge.next;
        for(int i=0; i<elements.size();i++){
        	
        	assertTrue(elements.get(i).equals(aux.value));
        	aux=aux.next;
        }
		}
	
	@Test
	public void merge_lists(){
		ArrayList<ArrayList<Integer>> elements=new ArrayList<ArrayList<Integer>>();
		Node<Integer>[] array=(Node<Integer>[])new Node[10];
		for(int i=0;i<10;i++){
			elements.add(new ArrayList<Integer>());
			for(int j=0;j<10;j++){
				elements.get(i).add(j%5 + i%2);	
			}
			Collections.sort(elements.get(i),CMP_REVERSE_ORDER);
		}
		for(int i=0;i<10;i++){
			ArrayList<Integer> elem=elements.get(i);
			for(int j=0;j<10;j++){
				Node<Integer> novo=new Node<Integer>(elem.get(j));
				novo.next=array[i];
				array[i]=novo;
			}
		}
		Node<Integer> merge=merge(array,CMP_NATURAL_ORDER);
		assertTrue(ListUtilTest.isSorted(merge, CMP_NATURAL_ORDER));
		ArrayList<Integer> flattern=elements.get(0);
		for(int i=1;i<10;i++){
			flattern.addAll(elements.get(i));
		}
		Collections.sort(flattern,CMP_NATURAL_ORDER);
		Node<Integer> aux=merge.next;
        for(int i=0; i<flattern.size();i++){
        	assertTrue(flattern.get(i).equals(aux.value));
        	aux=aux.next;
        }
	}
		
	}


