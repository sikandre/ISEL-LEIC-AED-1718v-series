package series.serie2;

import series.serie2.Node;
import org.junit.jupiter.api.Test;
import static series.serie2.ListUtils.removeAfterIntersectionPoint;
import static series.serie2.ListUtilTest.*;
import static series.serie2.ListUtilTest.assertListEquals;


public class RemoveAfterIntersectionPointTest {
    private static final Node<String> EMPTY_LIST = emptyListWithSentinel();
    private static final Node<String> SINGLETON_LIST_WITH_STR_A = makeList("A");
    private static final Node<String> SINGLETON_LIST_WITH_STR_b = makeList("b");
    private static final Node<String> SINGLETON_LIST_WITH_STR_c = makeList("c");


    @Test
    public void remove_after_intersectionPoint_empty_lists(){
        removeAfterIntersectionPoint( EMPTY_LIST , EMPTY_LIST, String::compareTo);
        assertListEquals(EMPTY_LIST,emptyListWithSentinel(),String::compareTo);
        removeAfterIntersectionPoint( EMPTY_LIST, SINGLETON_LIST_WITH_STR_A, String::compareTo );
        assertListEquals(EMPTY_LIST,emptyListWithSentinel(),String::compareTo);
        removeAfterIntersectionPoint( SINGLETON_LIST_WITH_STR_A,EMPTY_LIST, String::compareTo);
       assertListEquals(SINGLETON_LIST_WITH_STR_A, makeList("A") ,String::compareTo);
    }

    @Test
    public void  remove_after_intersectionPoint_one_element_lists(){
        Node<String> l1 =  makeList("A");
        removeAfterIntersectionPoint(  l1 , makeList( "a" ),String::compareToIgnoreCase );
        assertListEquals(EMPTY_LIST,l1,String::compareToIgnoreCase);
        Node<String> list=makeList( "a" );
       removeAfterIntersectionPoint(list,SINGLETON_LIST_WITH_STR_A, String::compareTo );
       assertListEquals(SINGLETON_LIST_WITH_STR_A,list,String::compareToIgnoreCase);
    }

  @Test
    public void  remove_after_intersectionPoint_one_element_match(){
        Node<String> l1 = makeList( "b", "a" );
        Node<String> l2 = SINGLETON_LIST_WITH_STR_A;
        removeAfterIntersectionPoint(l1, l2, String::compareToIgnoreCase);
        assertListEquals(SINGLETON_LIST_WITH_STR_b,l1,String::compareTo);
    }

    @Test
    public void  remove_after_intersectionPoint_two_element_match() {
        Node<String> l1 = makeList("c", "b", "a");
        Node<String> l2 = makeList("f", "b", "a");
        removeAfterIntersectionPoint(l1, l2, String::compareToIgnoreCase);
        assertListEquals(SINGLETON_LIST_WITH_STR_c, l1, String::compareToIgnoreCase);
    }

   @Test
    public void  remove_after_intersectionPoint_not_match(){
        Node<String> l = makeList( "b", "a" );
       Node<String> equalToL = makeList( "b", "a" );
        Node<String> reverseL = makeList( "a", "b" );
        Node<String> greaterL = makeList("b", "a", "c" );
        removeAfterIntersectionPoint(l,reverseL,String::compareTo);
        assertListEquals(equalToL,l,String::compareTo);
       removeAfterIntersectionPoint(l,greaterL,String::compareTo);
       assertListEquals(equalToL,l,String::compareTo);
       removeAfterIntersectionPoint(l,SINGLETON_LIST_WITH_STR_A,String::compareTo);
       assertListEquals(equalToL,l,String::compareTo);
    }

    @Test
    public void remove_after_intersectionPoint_all_match() {
      Node<String> fiveElements = makeList("a", "b", "c", "d", "e");
        removeAfterIntersectionPoint(fiveElements, makeList("a", "b", "c", "d", "e"), String::compareToIgnoreCase);
        assertListEquals(EMPTY_LIST, fiveElements, String::compareToIgnoreCase);
    }

    @Test
    public void remove_after_intersectionPoint_some_match(){
       Node<Integer> n1=makeList(3,5,2,7,4);
       Node<Integer> n2=makeList(9,3,10,8,2,7,4);
       Node<Integer> expected=makeList(3,5);
       removeAfterIntersectionPoint(n1,n2,Integer::compareTo);
       assertListEquals(expected,n1,Integer::compareTo);

    }

}
