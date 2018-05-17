package series.serie3;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Iterables {
    public static Iterable<Integer> alternateEvenOdd(Iterable<Integer> src){
        return new Iterable<Integer>() {
            @Override
            public Iterator<Integer> iterator() {
                return new Iterator<Integer>() {
                    Iterator<Integer> it=src.iterator();
                    Integer current =null;
                    @Override
                    public boolean hasNext() {
                        if(current !=null)return true;
                        boolean odd=true;
                        while (it.hasNext()){
                            Integer aux =it.next();
                            if(aux%2==1 && odd){
                                current =aux;
                                odd=false;
                                return true;
                            }
                            if(aux%2==0 && !odd) {
                                current = aux;
                                odd = true;
                                return true;
                            }
                        }
                        return false;
                    }

                    @Override
                    public Integer next() {
                        if(!hasNext())throw new NoSuchElementException();
                        Integer aux=current;
                        current=null;
                        return aux;
                    }
                };
            }
        };
    }

}
