package series.serie1;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.function.Predicate;

import static series.serie1.Arrays.removeIf;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RemoveIfTest {

    private static final Integer[] evens={2, 4, 6, 8, 10, 12 };
    private static final Integer[] odds={1, 3, 5, 7, 9, 11 };
	private static final Integer[] emptySequence={};
    private static final Integer[] singletonEven = {2};
    private static final Integer[] singletonOdd = {3};
    private static final Integer[] interleavedbyone={ 2, 3, 4, 5, 6, 7, 8, 9 };
	private static Predicate<Integer> even = new Predicate<Integer>() {
		@Override
		public boolean test(Integer integer) {
			return integer%2==0;
		}
	};
            
	@Test
	public void test_removeIf_in_empty_subsequence(){
		assertEquals(0, removeIf(emptySequence, 0, emptySequence.length - 1, even));
	}
	
	@Test
	public void test_removeIf_with_oneElement(){
		assertEquals(1, removeIf(singletonEven,0,0, even));
        assertEquals(0, removeIf(singletonOdd,0,0, even));
		}
	
	@Test
	public void test_removeIf_allOrNone(){
        assertEquals(evens.length, removeIf(evens,0,evens.length-1, even));
        assertEquals(0, removeIf(odds,0,odds.length-1, even));
		}

	@Test
    public void test_removeIf_interleaved() {
      	for (int i = 0; i < interleavedbyone.length; i++) {
            Integer[] array=Arrays.copyOf(interleavedbyone,interleavedbyone.length);
            assertEquals((interleavedbyone.length - i) / 2,
					removeIf(array, i, interleavedbyone.length - 1, even));
        }
    }
   }

	
