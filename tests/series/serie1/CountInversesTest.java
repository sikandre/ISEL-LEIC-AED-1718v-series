package series.serie1;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static series.serie1.Arrays.countInverses;
import static series.serie1.Arrays.countLessOrEqual;
import static series.serie1.Arrays.maximum;


public class CountInversesTest {
	
	@Test
	public void countInverses_OnAnEmptyArray(){
		String[] v=new String[0];
		int count= countInverses(v, 0, -1);
		assertEquals(0,count);
	}

	@Test
	public void countInverses_OnAnArrayWith1Element(){
		String[]v=new String[]{"aed"};
		int count= countInverses(v, 0, 0);
		assertEquals(0,count);
	}
	
	@Test
	public void countInverses_OnAnArrayWithSomeElements(){
		String[]v=new String[]{"aed","cad","dae","git","otpx","ptx","xpto","z"};
		int count= countInverses(v, 0, v.length - 1);
		assertEquals(2,count);
	}

	@Test
	public void countInverses_OnAnArrayWithSomeUnOrderedElements(){
		String[]v=new String[]{"otpx","cad","dae","z","git","ptx","aed","xpto"};
		int count= countInverses(v, 0, v.length - 1);
		assertEquals(2,count);
	}

	@Test
	public void countInverses_OnAnArrayWithSomeOrderedElements1(){
		String[]v=new String[]{"abc","cad","cba","ccba","git","ptx","ztq"};
		int count= countInverses(v, 0, v.length - 1);
		assertEquals(1,count);
	}

	@Test
	public void countInverses_OnAnArrayWithSomeOrderedElements2(){
		String[]v=new String[]{"abc","acc","caa","caabbb","cba","git"};
		int count= countInverses(v, 0, v.length - 1);
		assertEquals(1,count);
	}

}
