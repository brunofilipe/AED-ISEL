package series.serie3.Problema;



import series.serie3.Ex3.Edge;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


import static series.serie3.Problema.Color.*;

public class SchoolBusRouting {
    private static final Runnable[] COMMAND_LIST = new Runnable[]{SchoolBusRouting::schoolPath, SchoolBusRouting::e};
    private static final String[]COMMAND_KEY = {"schoolPath","e"  };
    private static boolean isExit = false;
    private static Scanner scan;
    private static Crossing[]cross;
    private static int[]ids;



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
            Crossing [] graph =dfs(ids,cross);
            printPath(graph);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private static Crossing[] dfs(int[] ids, Crossing[] cross) {
        Crossing[] graph = idCross(ids, cross);
        if(checkIfISEuler(graph)) {
            for (Crossing u : graph) {
                u.color = WHITE;
                u.pre = null;
            }
            for (Crossing u : graph) {
                if (u.color == WHITE) {
                    dfsVisit(graph, u);
                }
            }
        }
        return graph;
    }

    private static void dfsVisit(Crossing[] G, Crossing u) {
        u.color = GRAY;
        Street iter = u.list;
        while (iter!=null){
            Crossing v = iter.dest;
            if(v.color == WHITE){
                v.pre = u;
                dfsVisit(G,v);
            }
            iter = iter.next;
        }
        u.color = BLACK;
    }

    private static ArrayList<Crossing> dfsSearch(Crossing[]graph){
        if(!checkIfISEuler(graph)){ return null;}
        Stack<Crossing> stack = new LinkedStack<>();
        java.util.ArrayList<Crossing> caminho = new ArrayList<>();

        ///**********/
        while (!stack.isEmpty()){

        }
        return caminho;

    }


    private static void e(){
        isExit = true;
    }
/*******utils***********/

    private static void printPath(Crossing[] graph) {
        for (Crossing aGraph : graph) {
            if (aGraph.color == BLACK) {
                Street iter = aGraph.list;
                while (iter != null) {
                    if (iter.dest.color == BLACK) {
                        System.out.println(aGraph.id + " " + iter.dest.id + " " + iter.weight);
                        iter = iter.next;
                    }
                }
            }
        }
    }
    private static boolean checkIfISEuler(Crossing[] graph) {
        int count = 0;
        int odd = 0;
        for (Crossing g : graph){
            Street n = g.list;
            while (n!=null){
                ++count;
                n = n.next;
            }
            if(count%2 != 0){
                ++odd;
            }
            count = 0;
        }
        return odd == 2 || odd==0;
    }
    private static Crossing[] idCross(int[] ids, Crossing[] cross) {
        Crossing[] res = new Crossing[ids.length];
        int idx = 0;
        for (Crossing cros : cross) {
            for (int id : ids) {
                if (cros.id == id) {
                    res[idx++] = cros;
                }
            }
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

                } else {
                    previous = iter;
                }
                iter = iter.next;

            }
        }
        return res;
    }

    private static boolean contains(int[] ids, int id) {
        for (int i = 0; i < ids.length; i++) {
            if(ids[i] == id)
                return true;
        }
        return false;
    }
}
