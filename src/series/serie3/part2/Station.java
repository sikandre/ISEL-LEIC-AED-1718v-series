package series.serie3.part2;

import java.util.LinkedList;

public class Station {
    private String stationName;
    private LineStation[] belongTO;
    private LinkedList<Edge> nextStation;
    private boolean isVisited;
    private int d = Integer.MAX_VALUE;


    Station(String name){
        stationName = name;
        nextStation = new LinkedList<>();
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

    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    public LinkedList<Edge> getNextStation() {
        return nextStation;
    }

    public boolean isVisited() {
        return isVisited;
    }

    @Override
    public String toString() {
        return "Station{" +
                "stationName='" + stationName + '\'' +
                '}';
    }

    public String getStationName() {
        return stationName;
    }
}
