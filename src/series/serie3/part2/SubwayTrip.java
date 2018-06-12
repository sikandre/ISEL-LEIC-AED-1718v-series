package series.serie3.part2;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class SubwayTrip {
    static StationGraph stationGraph;
    private static LinkedList<Station> visited;
    private static LinkedList<Station> minStations;
    private static HashMap<String, Station> stationsMap;
    private static HashMap<String, LineStation> linesMap;


    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new FileReader(args[1]));
        int size = Integer.parseInt(bufferedReader.readLine());
        stationsMap = new HashMap();

        setStations(stationsMap, bufferedReader, size);

        bufferedReader = new BufferedReader(new FileReader(args[0]));
        size = Integer.parseInt(bufferedReader.readLine());
        linesMap = new HashMap();

        setLines(linesMap, bufferedReader, size);

        stationGraph = new StationGraph(stationsMap);


        /*get shortest path*/
        Station a = stationGraph.getStationHashMap().get("Reboleira");
        Station b = stationGraph.getStationHashMap().get("Telheiras");


        List<Station> paths = allPaths(a,b);
        for (Station s:paths) {
            System.out.println(s);
        }
        LinkedList <Station> path2 = new LinkedList<>();
        visited = new LinkedList<>();

        depthFirst(a,b);

        minStations = new LinkedList<>();
        LinkedList<Station> fast = fastestPath(a,b);
        for (int i = fast.size()-1; i >= 0 ; i--) {
            System.out.println(fast.get(i));

        }
    }

    private static LinkedList<Station> fastestPath(Station a, Station b) {
        int time=0;
        initSource(a);
        Queue<Station> queue = new LinkedList<>();
        LinkedList<Edge> closeTo = a.getNextStation();
        for (Edge e : closeTo) {
            Station s = (Station) e.value;
            s.setPredecessur(a);
            if(a.getBelongTO().length == s.getBelongTO().length) {   //same line
                LineStation ls = linesMap.get(a.getBelongTO()[0].getLineName());
                s.setDistance(ls.getMedianTime()+e.getTime());
            }

            else{
                LineStation ls = (a.getBelongTO()[0]!=s.getBelongTO()[0]) ? a.getBelongTO()[0] : a.getBelongTO()[1];
                ls = linesMap.get(ls.getLineName());
                s.setDistance(ls.getMedianTime()+e.getTime());
            }
            s.setPredecessur(a);
            ((LinkedList<Station>) queue).add(s);
        }
        while (queue!=null){
            Station min = deQueue(queue);
            if(min.getStationName().equals(b.getStationName())){
                LinkedList<Station> res = new LinkedList<>();
                while (min.getPredecessur()!=null){
                    res.add(min);
                    min=min.getPredecessur();
                }
                return res;
            }
            for (Edge v : min.getNextStation() ) {
                Station prox = (Station) v.value;
                if(prox.getDistance()==Integer.MAX_VALUE){

                    time=min.getDistance();
                    boolean sameLine=false;
                    for (int i = 0; i < min.getPredecessur().getBelongTO().length; i++) {
                        for (int j = 0; j < prox.getBelongTO().length; j++) {
                            if(min.getPredecessur().getBelongTO()[i].getLineName().equals(prox.getBelongTO()[j].getLineName())) {
                                sameLine = true;
                                time+=v.getTime();
                                prox.setDistance(time);
                                prox.setPredecessur(min);
                                ((LinkedList<Station>) queue).add(prox);
                                break;
                            }
                        }
                    }
                    String commonLine;
                    if(!sameLine) {
                        for (int i = 0; i < prox.getBelongTO().length; i++) {
                            for (int j = 0; j < min.getBelongTO().length; j++) {
                                if (prox.getBelongTO()[i].getLineName().equals(min.getBelongTO()[j].getLineName())) {
                                    commonLine = min.getBelongTO()[j].getLineName();
                                    LineStation ls = linesMap.get(commonLine);
                                    LinkedList<Edge> lsEgde = ls.getNextLine();
                                    String currentLine = getCurrentLine(min.getPredecessur(), min);
                                    for (Edge l : lsEgde) {
                                        LineStation tmp = (LineStation) l.value;
                                        if(tmp.getLineName().equals(currentLine)) {
                                            time += ls.getMedianTime();
                                            time += l.getTime();
                                            time += v.getTime();
                                            prox.setDistance(time);
                                            prox.setPredecessur(min);
                                            ((LinkedList<Station>) queue).add(prox);
                                        }

                                    }

                                }
                            }
                        }
                    }
                }
                //relax(min, prox, v);
            }

        }
        return null;
    }

    private static void relax(Station min, Station prox, Edge v) {
        if(prox.getDistance()>min.getDistance()+v.getTime()) {
            prox.setDistance(min.getDistance() + v.getTime());
            prox.setPredecessur(min);
        }
    }


    private static String getCurrentLine(Station predecessur, Station curr) {
        for (int i = 0; i < predecessur.getBelongTO().length; i++) {
            for (int j = 0; j < curr.getBelongTO().length; j++) {
                if(predecessur.getBelongTO()[i].getLineName().equals(curr.getBelongTO()[j].getLineName()))
                    return predecessur.getBelongTO()[i].getLineName();
            }
        }
        return null;
    }

    private static Station deQueue(Queue<Station> queue) {
        Station min = null;
        for (int i = 0; i < queue.size(); i++) {
            Station last = ((LinkedList<Station>) queue).get(i);
            if (min == null)
                min = last;
            if (min.getDistance() >= last.getDistance())
                min = last;
        }
        queue.remove(min);
        return min;
    }

    private static void initSource(Station a) {
        a.setDistance(0);
    }

    private static void depthFirst(Station a, Station b) {
        if(visited.size()==0)
            visited.add(a);
        LinkedList<Edge> edges = stationGraph.getStation(visited.getLast());
        for (Edge edge : edges) {
            if(visited.contains(edge.value))
                continue;
            if(edge.value.equals(b)){
                visited.add((Station) edge.value);
                printPath(visited);
                visited.removeLast();
                break;
            }
        }
        for (Edge edge : edges) {
            if(visited.contains(edge.value)||edge.value.equals(b))
                continue;
            visited.addLast((Station) edge.value);
            depthFirst((Station) edge.value, b);
            visited.removeLast();
        }
    }

    private static void printPath(LinkedList<Station> visited) {
        for (Station station : visited)
            System.out.println(station+" ");
        System.out.println();
    }

    /*teste 1*/
    private static List<Station> allPaths(Station a, Station b) {
        List <Station> res = new ArrayList<>();
        Queue<Station> queue = new LinkedList<>();
        ((LinkedList<Station>) queue).add(a);
        res.add(a);
        a.setVisited(true);
        while (queue!=null){
            a = queue.poll();
            Iterator<Edge> edgeIterator = a.getNextStation().listIterator();
            while (edgeIterator.hasNext()){
                Edge tmp = edgeIterator.next();
                Station s = (Station) tmp.getValue();
                if(s.equals(b))
                    return res;
                if(!s.isVisited()){
                    s.setVisited(true);
                    ((LinkedList<Station>) queue).add(s);
                    res.add(s);
                }
            }
        }
        return null;
    }




    private static void setLines(HashMap<String, LineStation> lineMap, BufferedReader bufferedReader, int size) throws IOException {
        for (int i = 0; i < size; i++) {
            String[] line = StringUtils.split(bufferedReader.readLine()," ");
            String key = line[0].trim();
            LineStation ls = new LineStation(key);
            ls.setMedianTime(line[1].trim());
            lineMap.put(key, ls);
        }
        String line;
        while ((line=bufferedReader.readLine())!=null){
            addLineToAdjList(lineMap, line);
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

    private static void setStations(HashMap<String, Station> map, BufferedReader bufferedReader, int size) throws IOException {
        for (int i = 0; i < size; i++) {
            String[] line = StringUtils.split(bufferedReader.readLine(),":\t");
            String key = line[0].trim();
            Station in = new Station(key); //create new station
            String[] belong = StringUtils.split(line[1], "\t ");
            LineStation[] arr = new LineStation[belong.length];
            for (int j = 0; j < belong.length; j++) {
                LineStation lineStation = new LineStation(belong[j]);
                arr[j] = lineStation;
            }
            in.setBelongTO(arr);
            map.put(key, in);
        }
        String all;
        while ((all = bufferedReader.readLine())!=null){
            addToAdjList(map, all);
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
}

