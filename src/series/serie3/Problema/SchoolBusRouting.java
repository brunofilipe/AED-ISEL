package series.serie3.Problema;



import java.io.IOException;
import java.util.Scanner;

public class SchoolBusRouting {
    private static final Runnable[] COMMAND_LIST = new Runnable[]{SchoolBusRouting::schoolPath, SchoolBusRouting::e};
    private static final String[]COMMAND_KEY = {"schoolPath","e"  };
    private static boolean isExit = false;
    private static Scanner scan;
    private static Crossing[]cross;



    public static void main(String[] args) {
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        run();
    }

    private static void init() throws IOException {
        scan = new Scanner(System.in);
        cross = ReadGrFile.readFile("grafo.gr");
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

    }

    private static void e(){
        isExit = true;
    }
}
