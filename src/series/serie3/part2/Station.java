package series.serie3.part2;

import java.util.LinkedList;

public class Station {
    private String stationName;
    private String[] belongTO;
    private LinkedList<Edge> nextStation;
    private boolean isVisited;
    private int distance = Integer.MAX_VALUE;
    private Station predecessur;


    Station(String name){
        stationName = name;
        nextStation = new LinkedList<>();
    }

    public void setNextStation(Station station, String time) {
        nextStation.add(new Edge(station, time));
    }


    public String [] getBelongTO() {
        return belongTO;
    }

    public void setBelongTO(String[] belongTO) {
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
        return "{stationName = '" + stationName + '\'' + '}';
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getDistance() {
        return distance;
    }

    public void setPredecessur(Station predecessur) {
        this.predecessur = predecessur;
    }

    public Station getPredecessur() {
        return predecessur;
    }

    public String getStationName() {
        return stationName;


    }
}
