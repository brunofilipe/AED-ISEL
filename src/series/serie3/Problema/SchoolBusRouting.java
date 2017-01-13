package series.serie3.Problema;



import java.io.IOException;
import java.util.Scanner;

public class SchoolBusRouting {
    private static final Runnable[] COMMAND_LIST = new Runnable[]{SchoolBusRouting::schoolPath, SchoolBusRouting::e};
    private static final String[]COMMAND_KEY = {"schoolPath","e" };
    private static boolean isExit = false;
    private static Scanner scan;
    private static Crossing[]cross;
    private static int[]ids;
    private static int idxOdd = 0;



    public static void main(String[] args) {
        try {
            init(args[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        run();
    }

    private static void init(String filename) throws IOException {
        scan = new Scanner(System.in);
        cross = ReadGrFile.readFile(filename);
    }

    private static void run() {
        String command;
        do{
            System.out.println("Choose An Available Command");
            command= scan.next();
            checkCommand(command);
        }while (!isExit);
    }

    private static void checkCommand(String next) {
        for (int i = 0; i < COMMAND_KEY.length; i++) {
            if(next.equals(COMMAND_KEY[i])){
                COMMAND_LIST[i].run();
                break;
            }
        }
    }

    private static void schoolPath() {
        System.out.println("Choose a  .s file");
        String filename = scan.next();
        try {
            ids = ReadSFile.readFile(filename);
            Crossing[] graph = idCross(ids, cross);
            LinkedCollection<Crossing> list = dfsSearch(graph);
            printList(list);
        }catch (IOException e) {
            e.printStackTrace();
        }

    }


    private static void e(){
        System.out.println("Exiting App...");
        isExit = true;
    }

/*******utils***********/

    private static Crossing[] idCross(int[] ids, Crossing[] cross) {
        Crossing[] res = new Crossing[ids.length];

        for (int i = 0; i <res.length ; i++) {
            res[i] = cross[ids[i]];
        }

        for (Crossing re : res) {
            Street iter = re.list;
            Street previous = re.list;
            while (iter != null) {
                if (!contains(ids, iter.dest.id)) {
                    Street toRemove = iter;
                    if (previous.equals(toRemove)) {
                        re.list = re.list.next;
                    } else {
                        previous.next = toRemove.next;
                    }

                }
                else {
                    previous = iter;
                }
                iter = iter.next;

            }
        }
        return res;
    }

    private static LinkedCollection<Crossing> dfsSearch(Crossing[]graph){
        if(!checkIfISEuler(graph)){ return null;}
        Stack<Crossing> stack = new LinkedStack<>();
        LinkedCollection<Crossing>path = new LinkedCollection<>();
        stack.push(graph[idxOdd]);
        while(!stack.isEmpty()) {
            Crossing vertex = stack.pop();
            if(!thereAreAdjacent(vertex)){
                path.add(vertex);
            }
            else {
                stack.push(vertex);
                Street iter = vertex.list;
                while (iter!=null){
                    if(!iter.isVisited){
                        stack.push(iter.dest);
                        iter.isVisited = true;
                        removeEdge(vertex,iter.dest);
                        break;
                    }
                    iter = iter.next;
                }

            }
        }
        return path;
    }

    private static void printList(LinkedCollection<Crossing> list) {
        for (Crossing aList : list) {
            System.out.println(aList.id );
        }
    }

    private static void removeEdge(Crossing vertex, Crossing dest) {
        Street iter = dest.list;
        while (iter != null) {
            if(iter.dest.id == vertex.id ){
                iter.isVisited = true;
                break;
            }
            iter = iter.next;
        }
    }

    private static boolean thereAreAdjacent(Crossing vertex) {
        Street iter = vertex.list;
        while (iter!=null){
            if(!iter.isVisited){
                return true;
            }
            iter = iter.next;
        }
        return false;
    }
    private static boolean checkIfISEuler(Crossing[] graph) {
        int count = 0;
        int odd = 0;
        for (int i = 0; i < graph.length; i++) {
            Crossing g = graph[i];
            Street n = g.list;
            while (n != null) {
                ++count;
                n = n.next;
            }
            if (count % 2 != 0) {
                idxOdd = i;
                ++odd;
            }
            count = 0;
        }
        return odd == 2 || odd==0;
    }

    private static boolean contains(int[] ids, int id) {
        for (int i = 0; i < ids.length; i++) {
            if(ids[i] == id)
                return true;
        }
        return false;
    }
}
