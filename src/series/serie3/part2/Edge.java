package series.serie3.part2;

import org.apache.commons.lang3.StringUtils;

public class Edge<E> {
    private int time;
    E value;

    public Edge(E value, String t) {
        String [] aux = StringUtils.split(t," :");
        time = Integer.parseInt(aux[0])*60;
        time+= Integer.parseInt(aux[1]);
        this.value = value;
    }

    public E getValue() {
        return value;
    }

    public int getTime() {
        return time;
    }
}
