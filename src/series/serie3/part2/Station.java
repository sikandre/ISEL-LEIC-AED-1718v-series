package series.serie3.part2;

import java.util.LinkedList;

public class Station {
    private String stationName;
    private LineStation[] belongTO;
    private LinkedList<Edge> nextStation = new LinkedList<>();

    Station(String name){
        stationName = name;
    }

    public void setNextStation(Station station, String time) {
        nextStation.add(new Edge(station, time));
    }


    public LineStation [] getBelongTO() {
        return belongTO;
    }

    public void setBelongTO(LineStation[] belongTO) {
        this.belongTO = belongTO;
    }
}
