package series.serie3.part2;

import org.apache.commons.lang3.StringUtils;

public class Edge<E> {
    private int time;
    E next;

    public Edge(E next, String t) {
        String [] aux = StringUtils.split(t," :");
        int res = Integer.parseInt(aux[0]);
        res+= Integer.parseInt(aux[1]);
        this.next = next;
    }
}
