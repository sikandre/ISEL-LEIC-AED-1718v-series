package series.serie1;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static series.serie1.Arrays.countEquals;

public class CountEqualsTest	{

    @Test
    public	void countEquals_OnBothEmptyArrays(){
        int[] v1=new int[0];
        int[] v2=new int[0];
        int	count=countEquals(v1,0,-1,v2,0,-1);
        assertEquals(0,count);
    }

    @Test
    public	void countEquals_OnAnArraysWithOneEqualElementInTheMiddle(){
        int[] v1=new int[]{1,3,4,5,7,10,12};
        int[] v2=new int[]{0,2,6,7,11,13};
        int	count=countEquals(v1,0,6,v2,	0,5);
        assertEquals(1,count);
    }
}