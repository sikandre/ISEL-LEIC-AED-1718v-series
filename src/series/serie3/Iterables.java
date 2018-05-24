package series.serie3;

import java.util.*;
import java.util.function.Predicate;


public class Iterables {
    public static Iterable<Integer> alternateEvenOdd(Iterable<Integer> src){
        return new Iterable<Integer>() {
            @Override
            public Iterator<Integer> iterator() {
                return new Iterator<Integer>() {
                    Iterator<Integer> it=src.iterator();
                    Integer current =null;
                    boolean odd = true;
                    @Override
                    public boolean hasNext() {
                        if(current !=null)return true;
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

    private static final Comparator<Integer> cmp = new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1-o2;
        }
    } ;
    static <E> Iterable<E> getTheIncreasingSequence(Iterable<E> sequence, Comparator<E> cmp){
        return new Iterable<E>() {
            @Override
            public Iterator<E> iterator() {
                return new Iterator<E>() {
                    private E first;
                    private E last;
                    Iterator<E> it=sequence.iterator();
                    E current = null;
                    @Override
                    public boolean hasNext() {
                        if(current !=null)return true;
                        while (it.hasNext()){
                            E aux = it.next();
                            if(first==null) {
                                first = aux;
                                last = first;
                                current = last;
                                return true;
                            }
                            if(cmp.compare(aux,last)>0){
                               last = aux;
                               current = last;
                               return true;
                            }
                        }
                        return false;
                    }

                    @Override
                    public E next() {
                        if(!hasNext())throw new NoSuchElementException();
                        E aux=current;
                        current=null;
                        return aux;
                    }
                };
            }
        };
    }

    public static Iterable<String> getWordsThatContains(Iterable<Iterable<String>> src, String subStr){
        return new Iterable<String>() {
            @Override
            public Iterator<String> iterator() {
                return new Iterator<String>() {
                    Iterator<Iterable<String>> it = src.iterator();
                    Iterator<String> subIt;
                    String current = null;

                    Predicate<String> predicate = new Predicate<String>() {
                        @Override
                        public boolean test(String s) {
                            return s.toLowerCase().contains(subStr.toLowerCase());
                        }
                    };

                    @Override
                    public boolean hasNext() {
                        /*if(current!=null) return true;

                        while (it.hasNext() || subIt.hasNext()) {
                            String aux = subIt.next();
                            if(predicate.test(aux.toLowerCase())) {
                                System.out.println(aux);
                                current = aux;
                                return true;
                            }
                        }
                        return false;
                        */
                        while(current==null){
                            if(subIt!=null && subIt.hasNext()) {
                                String aux = subIt.next();
                                if(predicate.test(aux))
                                    current = aux;
                            }
                            else{
                                if(it.hasNext()){
                                    subIt=it.next().iterator();
                                }
                                else
                                    return false;
                            }
                        }
                        return true;
                    }


                    @Override
                    public String next() {
                        if(!hasNext())throw new NoSuchElementException();
                        String  aux=current;
                        current=null;
                        return aux;
                    }
                };
            }
        };
    }


}
