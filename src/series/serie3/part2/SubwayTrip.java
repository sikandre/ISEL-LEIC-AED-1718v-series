package series.serie3.part2;

import java.io.IOException;
import java.util.*;

public class SubwayTrip {
    private static StationGraph stationGraph;
    private static LinkedList<Station> visited;
    private static int count;
    private static LinkedList<Station> lessPath;
    private static boolean printAll = true;
    private static final int EXIT = 0;
    private static final String[] OPTIONS = {"allPaths", "fastestPath", "pathWithLessChanges"};
    private static Station a,b;

    public static void main(String[] args) throws IOException {

        stationGraph = new StationGraph(args[1]);
        stationGraph.createLines(args[0]);
        Scanner in = new Scanner(System.in);

        String init;
        String dest;
        boolean valid;

        int keyPressed = -1;

        while (keyPressed != EXIT) {
            do {
                System.out.println("Choose initial station.");
                init = in.nextLine();
                if (!stationGraph.getStationHashMap().containsKey(init)) {
                    System.out.println("Invalid Name.");
                    valid = true;
                }
                else {
                    a = stationGraph.getStationHashMap().get(init);
                    valid = false;
                }

            } while (valid);

            do {
                System.out.println("Choose destination station");
                dest = in.nextLine();
                if (!stationGraph.getStationHashMap().containsKey(dest)) {
                    System.out.println("Invalid Name.");
                    valid = true;
                }
                else {
                    b = stationGraph.getStationHashMap().get(dest);
                    valid = false;
                }

            } while (valid);

            System.out.println("Choose Option or 0(zero) to exit");
            for (int i = 0; i < OPTIONS.length; i++) {
                System.out.println((i + 1) + " -> " + OPTIONS[i]);
            }
            keyPressed = in.nextInt();
            in.nextLine();

            switch (keyPressed){
                case 1: allPaths(a, b);
                break;
                case 2: fastPath(a, b);
                break;
                case 3: pathWithLessChanges(a,b);
                break;
                case 0: continue;
                default:
                    System.out.println("Not a valid option");
                    break;
            }
            System.out.println("Would you like to continue? 0 to exit");
            keyPressed = in.nextLine().charAt(0)-'0';
        }
    }

    private static void pathWithLessChanges(Station a, Station b) {
        lessPath = new LinkedList<>();
        visited = new LinkedList<>();
        printAll=false;
        count = 0;
        depthFirst(a,b);
        System.out.println("Path with less changes");
        printPath(lessPath);
    }

    private static void fastPath(Station a, Station b) {
        LinkedList<Station> fast = getFastestPath(a,b);
        System.out.println("The fastest path");
        System.out.println(a);
        for (int i = fast.size()-1; i >= 0 ; i--) {
            System.out.println(fast.get(i));
        }
        System.out.println();
    }

    private static void allPaths(Station a, Station b) {
        visited = new LinkedList<>();
        System.out.println("Os Caminhos possiveis são:");
        depthFirst(a,b);
    }

    private static LinkedList<Station> getFastestPath(Station src, Station b) {
        int time=0;
        initSource(src);
        Queue<Station> queue = new LinkedList<>();
        LinkedList<Edge> closeTo = src.getNextStation();
        for (Edge e : closeTo) {
            Station nextStation = (Station) e.value;
            nextStation.setPredecessur(src);
            if(src.getBelongTO().length == nextStation.getBelongTO().length) {   //same line
                LineStation ls = stationGraph.getLinesMap().get(src.getBelongTO()[0]);
                nextStation.setDistance(ls.getMedianTime()+e.getTime());
            }

            else{
                String colorLine = (!src.getBelongTO()[0].equals(nextStation.getBelongTO()[0])) ? src.getBelongTO()[0] : src.getBelongTO()[1];
                LineStation ls = stationGraph.getLinesMap().get(colorLine);
                nextStation.setDistance(ls.getMedianTime()+e.getTime());
            }
            nextStation.setPredecessur(src);
            queue.add(nextStation);
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
                            if(min.getPredecessur().getBelongTO()[i].equals(prox.getBelongTO()[j])) {
                                sameLine = true;
                                time+=v.getTime();
                                prox.setDistance(time);
                                prox.setPredecessur(min);
                                queue.add(prox);
                                break;
                            }
                        }
                    }
                    String commonLine;
                    if(!sameLine) {
                        for (int i = 0; i < prox.getBelongTO().length; i++) {
                            for (int j = 0; j < min.getBelongTO().length; j++) {
                                if (prox.getBelongTO()[i].equals(min.getBelongTO()[j])) {
                                    commonLine = min.getBelongTO()[j];
                                    LineStation ls = stationGraph.getLinesMap().get(commonLine);
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
                                            queue.add(prox);
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
                if(predecessur.getBelongTO()[i].equals(curr.getBelongTO()[j]))
                    return predecessur.getBelongTO()[i];
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
        if(visited.size()==0) {
            visited.add(a);
            a.setPredecessur(null);
        }
        LinkedList<Edge> edges = stationGraph.getADJList(visited.getLast());
        for (Edge edge : edges) {
            if(visited.contains(edge.value))
                continue;
            if(edge.value.equals(b)){
                visited.add((Station) edge.value);
                if(printAll)
                    printPath(visited);
                else
                    getLessSwitch(visited);
                visited.removeLast();
                break;
            }
        }
        for (Edge edge : edges) {
            if(visited.contains(edge.value)||edge.value.equals(b))
                continue;
            visited.addLast((Station) edge.value);
            ((Station) edge.value).setPredecessur(a);
            depthFirst((Station) edge.value, b);
            visited.removeLast();
        }
    }

    private static void getLessSwitch(LinkedList<Station> visited) {
        int res=0;
        if(lessPath.size()==0)
            lessPath= (LinkedList<Station>) visited.clone();
        String lastStation = null;
        String currentStation;
        for (Station s : visited) {
            if(s.getPredecessur()==null)
                continue;
            currentStation=getCurrentLine(s.getPredecessur(),s);
            if(lastStation==null)
                lastStation = currentStation;
            if(!lastStation.equals(currentStation))
                res++;
            lastStation=currentStation;
        }
        if(count==0)
            count=res;
        if(res<=count) {
            lessPath = (LinkedList<Station>) visited.clone();
            count = res;
        }
    }

    private static void printPath(LinkedList<Station> visited) {
        for (Station station : visited)
            System.out.println(station+" ");
        System.out.println();
    }
}

