package series.serie3.part2;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class SubwayTrip {


    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new FileReader(args[1]));
        int size = Integer.parseInt(bufferedReader.readLine());
        HashMap<String, Station> stationsMap = new HashMap();

        setStations(stationsMap, bufferedReader, size);

        bufferedReader = new BufferedReader(new FileReader(args[0]));
        size = Integer.parseInt(bufferedReader.readLine());
        HashMap<String, LineStation> linesMap = new HashMap();

        setLines(linesMap, bufferedReader, size);






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

