package series.serie3.part2;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class StationGraph {
    private HashMap<String,Station> stationHashMap;
    private static HashMap<String, LineStation> linesMap;
    private BufferedReader bufferedReader;
    private String fileName;
    private int size;


    public StationGraph(String stationsFile) throws IOException {
        fileName = stationsFile;
        bufferedReader = new BufferedReader(new FileReader(fileName));
        size = Integer.parseInt(bufferedReader.readLine());
        stationHashMap = new HashMap();
        create();
    }

    private void create() throws IOException {
        for (int i = 0; i < size; i++) {
            String[] line = StringUtils.split(bufferedReader.readLine(),":\t");
            String key = line[0].trim();
            Station in = new Station(key); //create new station
            String[] belong = StringUtils.split(line[1], "\t ");
            in.setBelongTO(belong);
            stationHashMap.put(key, in);
        }
        String all;
        while ((all = bufferedReader.readLine())!=null){
            addToAdjList(stationHashMap, all);
        }
    }
    private static void addToAdjList(HashMap<String, Station> map, String all) {
        String[] aux = StringUtils.split(all,":",2);
        String firstStation = aux[0].trim();
        aux = StringUtils.split(aux[1],"-");
        String second = aux[0].trim();
        Station first = map.get(firstStation);
        Station sec = map.get(second);
        first.setNextStation(sec, aux[1]);
        sec.setNextStation(first, aux[1]);
    }
    public void createLines(String linesFile) throws IOException {
        bufferedReader = new BufferedReader(new FileReader(linesFile));
        int len = Integer.parseInt(bufferedReader.readLine());
        linesMap = new HashMap();
        for (int i = 0; i < len; i++) {
            String[] line = StringUtils.split(bufferedReader.readLine()," ");
            String key = line[0].trim();
            LineStation ls = new LineStation(key);
            ls.setMedianTime(line[1].trim());
            linesMap.put(key, ls);
        }
        String line;
        while ((line=bufferedReader.readLine())!=null){
            addLineToAdjList(linesMap, line);
        }
    }
    private static void addLineToAdjList(HashMap<String, LineStation> lineMap, String line) {
        String [] aux = line.split(" ");
        String first = aux[0].trim();
        LineStation f = lineMap.get(first);
        String sec = aux[1].trim();
        LineStation s = lineMap.get(sec);
        f.setNextLine(s, aux[2].trim());
        s.setNextLine(f, aux[2].trim());
    }



    public HashMap<String, Station> getStationHashMap() {
        return stationHashMap;
    }

    public LinkedList<Edge> getADJList(Station last) {
        LinkedList<Edge> adj = last.getNextStation();
        if(adj==null)
            return new LinkedList<>();
        return adj;
    }

    public HashMap<String, LineStation> getLinesMap() {
        return linesMap;
    }

}
