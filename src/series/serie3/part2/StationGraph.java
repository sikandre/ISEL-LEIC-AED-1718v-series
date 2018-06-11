package series.serie3.part2;

import java.util.*;

public class StationGraph {
    private HashMap<String,Station> stationHashMap;

    public StationGraph(HashMap<String, Station> stationHashMap) {
        this.stationHashMap = stationHashMap;
    }

    public HashMap<String, Station> getStationHashMap() {
        return stationHashMap;
    }

    public List<Station> getAllStations() {
        return new ArrayList<Station>(stationHashMap.values());
    }

    public LinkedList<Edge> getStation(Station last) {
        LinkedList<Edge> adj = last.getNextStation();
        if(adj==null)
            return new LinkedList<>();
        return new LinkedList<Edge>(adj);
    }
}
