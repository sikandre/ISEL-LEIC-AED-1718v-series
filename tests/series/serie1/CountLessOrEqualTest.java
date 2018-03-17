package series.serie1;

import org.junit.jupiter.api.Test;

import static series.serie1.Arrays.countLessOrEqual;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;


public class CountLessOrEqualTest {

    @Test
    public void countLessOrEqual_OnAnEmptyArrays(){
        int[] a=new int[0];
        int[] b=new int[0];
        assertArrayEquals(a, countLessOrEqual(a,b));
    }

    @Test
    public void countLessOrEqual_OnAnEmptyArrayA(){
        int[] a=new int[0];
        int[] b=new int[]{1,2,3,4,5};
        assertArrayEquals(a, countLessOrEqual(a,b));
    }
    @Test
    public void countLessOrEqual_OnAnEmptyArrayB(){
        int[] a=new int[]{1,2,3,4,5};
        int[] b=new int[0];
        int[] c=new int[]{0,0,0,0,0};
        assertArrayEquals(c, countLessOrEqual(a,b));
    }

    @Test
    public void countLessOrEqual_OnExample(){
        int[] a= new int[]{1, 2, 3, 4, 7, 9};
        int[] b= new int[]{0, 1, 1, 1, 2, 4};
        int[] c= new int[]{4, 5, 5, 6, 6, 6};
        assertArrayEquals(c, countLessOrEqual(a,b));
    }

    @Test
    public void countLessOrEqual_Equals(){
        int[] a=new int[10], b=new int[10];
        for(int i=0;i<10;i++)
            a[i]=b[i]=i+1;
        assertArrayEquals(a, countLessOrEqual(a,b));
    }

    @Test
    public void countLessOrEqual_Bigger(){
        int[] a= new int[]{1, 2, 3, 4, 7, 9};
        int[] b= new int[]{12, 13, 14, 15, 20, 24};
        int[] c= new int[6];
        assertArrayEquals(c, countLessOrEqual(a,b));
    }

    @Test
    public void countLessOrEqual_Smaller(){
        int[] b= new int[]{1, 2, 3, 4, 7, 9};
        int[] a= new int[]{12, 13, 14, 15, 20, 24};
        int[] c= new int[]{6, 6, 6, 6, 6, 6};
        assertArrayEquals(c, countLessOrEqual(a,b));
    }
}
