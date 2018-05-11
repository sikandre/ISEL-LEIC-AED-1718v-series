package series.serie2;


import java.io.*;
import java.util.*;
import java.util.function.BiFunction;

public class LinkedHashMap<K,V> extends AbstractMap<K, V> {
    private static final int DEFAULT_CAPACITY = 11;
    private static final float DEFAULT_LOAD_FACTOR = 0.75F;
    private Node<K, V> first;
    private Node<K, V> current;

    private static class Node<K, V> extends AbstractMap.SimpleEntry<K, V> {
        private final int hc;
        private Node<K, V> next;//next hasmap
        private Node<K, V> nextIter;

        private Node(K key, V value, int hc, Node<K, V> next) {
            super(key, value);
            this.hc = hc;
            this.next = next;
        }
    }

    private int nElements;
    private final float loadFactor;
    private Node<K, V>[] table;
    private int dim;
    private Node<K, V> firstIter;
    private Node<K, V> lastIter;


    //<< Construtores >>
    public LinkedHashMap(int initialCapacity, float lf) {
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal initial capacity: " + initialCapacity);
        if (initialCapacity > DEFAULT_CAPACITY)
            initialCapacity = DEFAULT_CAPACITY;

        loadFactor = lf;
        table = (Node<K, V>[]) new Node[initialCapacity];
        dim = initialCapacity;
    }

    public LinkedHashMap() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public LinkedHashMap(Map<? extends K, ? extends V> m) {
        this(m.size(), DEFAULT_LOAD_FACTOR);
        putAll(m);
    }

    public V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        V oldValue = get(key);
        V newValue = remappingFunction.apply(key, oldValue);
        if (oldValue != null) {
            if (newValue != null)
                putI(key, newValue);
        } else {
            if (newValue != null) {
                putI(key, newValue);
            } else
                return null;
        }
        return newValue;
    }

    private void putI(K key, V newValue) {
        int hash = index(key);
        Node<K, V> novo = new Node<>(key, newValue, hash, null);
        if (nElements / dim > loadFactor) {
            resizing();
            hash = index(key);
        }
        if (table[hash] == null)
            table[hash] = novo;
        else {
            Node<K, V> current = table[hash];
            while (current != null) {
                if (current.getKey().equals(key)) {
                    current.setValue(newValue);
                    return;
                }
                current = current.next;
            }
            novo.next = table[hash];
            table[hash] = novo;
        }
        nElements++;
        LinkedNode(novo);
    }

    private void resizing() {
        Node<K, V>[] newTable = (Node<K, V>[]) new Node[dim * 2];
        for (int i = 0; i < dim; i++) {
            Node<K, V> current = table[i];
            while (current != null) {
                K key = current.getKey();
                int idx = index(key);
                Node<K, V> aux = current;
                current = current.next;
                aux.next = newTable[idx];
                newTable[idx] = aux;
            }
        }
        table = newTable;
        dim *= 2;
    }

    private void LinkedNode(Node<K, V> novo) {
        if (firstIter == null) {
            firstIter = novo;
            lastIter = novo;
        } else {
            lastIter.nextIter = novo;
            lastIter = novo;
        }
    }

    private int index(K key) {
        int i = key.hashCode() % dim;
        return (i < 0) ? i + dim : i;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return entrySet;
    }

    private Set<Entry<K, V>> entrySet = new AbstractSet<Entry<K, V>>() {
        @Override
        public Iterator<Entry<K, V>> iterator() {
            return new Iterator<Entry<K, V>>() {

                private Node<K, V> iter = first;
                private Node<K, V> curr = null;

                @Override
                public boolean hasNext() {
                    if (curr != null)
                        return true;

                    if (iter != null) {
                        curr = iter;
                        iter = iter.nextIter;
                        return true;
                    }
                    return false;

                }

                @Override
                public Entry<K, V> next() {
                    if (!hasNext()) throw new NoSuchElementException();
                    Node<K, V> auc = curr;
                    curr = null;
                    return auc;
                }
            };
        }

        @Override
        public int size() {
            return nElements;
        }

        /* Métodos que têm que ser redefinidos OBRIGATORIAMENTE no SET
         * embora tenham herdado uma implementação de AbstractSet
         */
        @Override
        public boolean contains(Object o) {
            Node<K, V> n = search(o);
            while (n != null) {
                if (o.equals(n)) return true;
                n = n.next;
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
    public V get(Object o) {
        Node<K, V> e = search(o);

        return (e == null) ? null : e.getValue();
    }

    @Override
    public boolean containsKey(Object o) {
        Node<K, V> e = search(o);
        while (e != null) {
            if (e.getKey() == o) return true;
            e = e.next;
        }
        return false;
    }

    @Override
    public void clear() {//precorrer todos e substituir por um null
        // Substituir para fazer uma implementação mais eficiente
        Node<K, V> aux = table[0];
        while (aux != null) {
            while (aux != null) {
                aux = null;
                aux = aux.nextIter;
            }
            aux = aux.next;
        }
    }

    private Node<K, V> search(Object o) {
        int idx = index((K) o);
        Node<K, V> current = table[idx];
        while (current != null) {
            K key = current.getKey();
            if (key.equals(o))
                return current;
            current = current.next;
        }
        return null;
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

    public V remove(Object k) {
        throw new UnsupportedOperationException();
    }


    static class MyFile {//classe interna para obter informacoes de um file em expecifico indicado no construtor
        private BufferedReader br;
        private String line;
        private String word[];

        public MyFile(String file) throws IOException {//construtor
            br = new BufferedReader(new FileReader(file));
            line = getLine();
        }

        public String getNewLine() throws IOException {//saber string seguinte
            return line = br.readLine();
        }

        public String getLine() {
            return line;
        }//retorna toda a linha
    }


    public static void main(String[] args) throws IOException {

                /*//using Java.Util
                MyFile myFile = new MyFile(args[0]);
                java.util.LinkedHashMap linkedHashMap = new java.util.LinkedHashMap();
                while (myFile.getNewLine() != null) {
                    String nWord[] = myFile.line.split(" ");
                    for (int i = 0; i < nWord.length; i++) {
                        linkedHashMap.compute(nWord [i],(k,v) -> (v == null) ? 1 : (int)v+1);
                    }
                }
                System.out.println("Contents of LinkedHashMap : " + linkedHashMap);
                System.out.println("\nThe size of the LinkedHashMap is : " + linkedHashMap.size());*/


        MyFile myFile = new MyFile(args[0]);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        while (myFile.getNewLine() != null) {
            String nWord[] = myFile.line.split(" ");
            for (int i = 0; i < nWord.length; i++) {
                linkedHashMap.compute(nWord[i], (k, v) -> (v == null) ? 1 : (int) v + 1);
            }
        }

        System.out.println(" size of the map is: " + linkedHashMap.size());
    }

    private static <K, V> void writeFile(LinkedHashMap<K, V> lhm) throws IOException {
        FileWriter fileWriter = new FileWriter("res.txt");
        BufferedWriter bw = new BufferedWriter(fileWriter);
        Node<K, V> n = lhm.firstIter;
        while (n != null) {
            V val = n.getValue();
            if (val.equals(1)) {
                bw.write((String) n.getKey());
                bw.newLine();
            }
            n = n.nextIter;
        }
        bw.close();

    }

}





