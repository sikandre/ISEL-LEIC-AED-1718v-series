package series.serie3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;


public class IterablesTest {

    @Test
    public void testIter(){
        ArrayList<Integer> arrayList=new  ArrayList<Integer>(Arrays.asList(1,3));
        ArrayList<Integer> arrayList1=new  ArrayList<Integer>(Arrays.asList(1));
        Assertions.assertIterableEquals(arrayList1,Iterables.alternateEvenOdd(arrayList));

    }
}
