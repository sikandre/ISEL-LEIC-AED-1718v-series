package series.serie3.part2;

import org.apache.commons.lang3.StringUtils;

import java.util.LinkedList;
import java.util.List;

public class LineStation {
    private String lineName;
    private LinkedList<Edge> nextLine = new LinkedList<>();

    private int medianTime;

    LineStation(String name){
        lineName = name;
    }

    public void setMedianTime(String time) {
        String [] aux = StringUtils.split(time,":");
        medianTime = Integer.parseInt(aux[0]);
        medianTime+= Integer.parseInt(aux[1]);
    }

    public void setNextLine(LineStation lineStation, String time) {
        nextLine.add(new Edge(lineStation, time));
    }
}
