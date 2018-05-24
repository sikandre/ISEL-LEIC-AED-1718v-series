package series.serie3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static series.serie3.Iterables.*;


public class IterablesTest {

    @Test
    public void testIter(){
        ArrayList<Integer> arrayList=new  ArrayList<Integer>(Arrays.asList(1,3));
        ArrayList<Integer> arrayList1=new  ArrayList<Integer>(Arrays.asList(1));
        Assertions.assertIterableEquals(arrayList1,Iterables.alternateEvenOdd(arrayList));

    }

    @Test
    public void getTheIncreasingSequence1(){
        ArrayList<Integer> arrayList=new  ArrayList<Integer>(Arrays.asList(2,1,2,5,6,6,0,0,4,4,2,2,6,6,6,6,8,8));
        ArrayList<Integer> arrayList1=new  ArrayList<Integer>(Arrays.asList(2,5,6,8));
        Assertions.assertIterableEquals(arrayList1,getTheIncreasingSequence(arrayList, Integer::compareTo));
    }


    @Test
    public void getWordsThatContains1(){
        Iterable<Iterable<String>> src = new ArrayList<>();
        ((ArrayList<Iterable<String>>) src).add(Arrays.asList("O","rato","roeu","a","rolha","da","garrafa","do","rei","da","Russia"));
        ((ArrayList<Iterable<String>>) src).add(Arrays.asList("O","original","nunca","se","desoriginou","nem","nunca","se","desoriginalizara"));
        ((ArrayList<Iterable<String>>) src).add(Arrays.asList("Tres","pratos","de","trigo","para","tres","tristes","tigres"));

        ArrayList<String> res = new ArrayList<>(Arrays.asList("rato","roeu","rolha","garrafa","rei","Russia","original","desoriginou","desoriginalizara",
                "Tres","pratos","trigo","para","tres","tristes","tigres"));

        Assertions.assertIterableEquals(res,getWordsThatContains(src,"r"));
    }
    @Test
    public void getWordsThatContains2(){
        Iterable<Iterable<String>> src = new ArrayList<>();
        ((ArrayList<Iterable<String>>) src).add(Arrays.asList());
        ((ArrayList<Iterable<String>>) src).add(Arrays.asList());
        ((ArrayList<Iterable<String>>) src).add(Arrays.asList());


        ArrayList<String> res = new ArrayList<>(Arrays.asList());

        Assertions.assertIterableEquals(res,getWordsThatContains(src," "));
    }


}
