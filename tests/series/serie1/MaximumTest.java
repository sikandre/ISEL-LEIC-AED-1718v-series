package series.serie1;


import org.junit.jupiter.api.Test;

import static series.serie1.Arrays.maximum;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MaximumTest {
	
	@Test
	public void test_getMaximum_with_one_shifted_positions(){
		int[] array = { 55, 7, 6, 4, 3, 1 };
		assertEquals(55,maximum(array, 0, 5));
		
	}
	
	@Test
	public void test_getMaximum_with_also_one_shifted_positions(){
		int[] array = { 55, 40, 38, 36, 34, 30, 22, 20, 18, 16, 14, 10, 5};
		assertEquals(55,maximum(array, 0, 12));
		
	}
	
	@Test
	public void test_getMaximum_with_two_shifted_positions(){
		int[] array = { 35, 42, 40, 30, 27, 25 };
		assertEquals(42,maximum(array, 0, 5));
		
	}
	
	@Test
	public void test_getMaximum_with_four_shifted_positions(){
		int[] array = { 27, 29, 35, 42, 5, 1 };
		assertEquals(42,maximum(array, 0, 5));
		
	}
	
	@Test
	public void test_getMaximum_with_also_four_shifted_positions(){
		int[] array = { 55, 57, 59, 60, 53, 50, 48, 47, 42, 30, 22, 20, 18};
		assertEquals(60,maximum(array, 0, 12));
		
	}

	@Test
	public void test_getMaximum_with_also_four_shifted_positions2(){
		int[] array = {59, 60};
		assertEquals(60,maximum(array, 0, 1));

	}

	

	
	


}
