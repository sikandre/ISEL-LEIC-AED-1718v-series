package series.serie2;

import java.util.*;
import java.util.function.BiFunction;

    public class LinkedHashMap<K,V> extends AbstractMap<K, V> {
        private static final int DEFAULT_CAPACITY = 11;
        private static final float DEFAULT_LOAD_FACTOR = 0.75F;
        private Node<K,V> first;
        private Node<K,V> current;
        private static class Node<K,V> extends AbstractMap.SimpleEntry<K,V> {
            private final int hc;
            private Node<K,V> next;//next hasmap
            private Node<K,V> nextIter;

            private Node(K key, V value, int hc, Node<K,V> next) {
                super( key, value );
                this.hc = hc;
                this.next = next;
            }
        }
        // << Variaveis de instância >>
        //TODO
        private int nElements;
        private final float loadFactor;
        private Node<K,V>[] table;
        private int dim;

        //<< Construtores >>
        public LinkedHashMap( int initialCapacity, float lf ) {
            if (initialCapacity < 0)
                throw new IllegalArgumentException("Illegal initial capacity: " + initialCapacity);
            if (initialCapacity > DEFAULT_CAPACITY)
                initialCapacity = DEFAULT_CAPACITY;
            if (lf <= 0 || Float.isNaN(lf))
                throw new IllegalArgumentException("Illegal load factor: " + lf);
            loadFactor = lf;
            table=(Node<K, V>[])new Node[initialCapacity];
            dim=initialCapacity;

        }

        public LinkedHashMap(  ) {
            this( DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR );
        }
        public LinkedHashMap(Map<? extends K, ? extends V> m) {
            this(m.size(), DEFAULT_LOAD_FACTOR);
            putAll(m);
        }
        public V compute(K key, BiFunction<? super K,? super V,? extends V> remappingFunction) {
            V oldValue = get(key);
            V newValue = remappingFunction.apply(key, oldValue);
            if (oldValue != null) {
                if (newValue != null)
                    put(key, newValue);
            } else {
                if (newValue != null)
                    put(key, newValue);
                else
                    return null;
            }
            return newValue;
        }

        @Override
        public Set<Entry<K,V>> entrySet() { return entrySet; }
        private Set<Entry<K,V>> entrySet = new AbstractSet<Entry<K, V>>() {
            @Override
            public Iterator<Entry<K, V>> iterator() {
                //TODO
                return null;
            }
            @Override
            public int size() {
                return 0;
            }
            /* Métodos que têm que ser redefinidos OBRIGATORIAMENTE no SET
             * embora tenham herdado uma implementação de AbstractSet
             */
            @Override
            public boolean contains(Object o) {
                Node<K,V> n =search(o);
                while (n!=null) {
                    if(o.equals(n))return true;
                    n=n.next;
                }
                return false;
                // Substituir para fazer O(1)
                //return super.contains( o ); // O(n)
            }
            @Override
            public void clear() {
                LinkedHashMap.this.clear();
            }
        };

        /* Métodos que devem ser redefinidos OBRIGATORIAMENTE no SET
         * embora tenham herdado uma implementação de AbstractCollection
         */

        @Override
        public V get( Object k ) {
            Node<K,V> e=search(k);
            return (e== null) ? null : e.getValue();
        }

        @Override
        public boolean containsKey(Object key) {//TODO
            // Substituir para fazer O(1)
            return super.containsKey( key);
        }

        @Override
        public void clear() {//precorrer todos e substituir por um null
            // Substituir para fazer uma implementação mais eficiente
            Node<K,V> aux =table[0];
            while (aux!=null){
                while (aux!=null){
                    aux=null;
                    aux=aux.nextIter;
                }
                aux=aux.next;
            }
        }
        private Node<K,V> search(Object o){
            return table[o.hashCode()%nElements];
        }

        //Analisar os métodos
        @Override
        public Set<K> keySet() {
            // Só implementar se conseguirem diminuir a complexidade dos métodos
            // caso contrário, remover este Override
            //nao conseguimos porque ja esta a ser utilizado o iterador de entrySet
            return super.keySet();
        }
        @Override
        public Collection<V> values() {
            // Só implementar se conseguirem diminuir a complexidade dos métodos
            // caso contrário, remover este Override
            return super.values();
        }

        public V remove( Object k ) {
            throw new UnsupportedOperationException();
        }
    }


