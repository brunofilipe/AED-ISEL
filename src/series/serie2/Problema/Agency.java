package series.serie2.Problema;

import java.util.Comparator;
import java.util.Scanner;

public class Agency implements KeyExtractor {

    private static int capacity;
    private static PriorityQueue<Client,Service>queue;
    private static final Runnable[] COMMAND_LIST = new Runnable[]{Agency::newCostumer, Agency::removeCostumer,
                                Agency::removeNext, Agency::getNextCostumer,Agency::changeService,Agency::waitingTime};
    private static final String[]COMMAND_KEY = {"newCostumer","removeCostumer", "removeNextCostumer","getNextCostumer",
                                                "changeService","waitingTime"  };
    private static Comparator<Service>cmp;
    private static Scanner scan;

    @Override
    public int getKey(Object o) {
        return 0;
    }
    public static void main(String[] args) {
        if(args.length == 0){showOptions();return;}
        capacity = Integer.parseInt(args[0]);
        init(capacity);
        run();
    }

    private static void init(int capacity) {
        cmp = (cmp1, cmp2) -> Math.abs(cmp1.getTime()-cmp2.getTime());
        queue = new PriorityQueue(capacity,cmp,new Agency());
        scan = new Scanner(System.in);
    }


    private static void run() {
        do{
            System.out.println("Choose An Available Command");
            checkCommand(scan.next());
        }while (!scan.next().equals("exit"));
    }

    private static void checkCommand(String next) {
        for (int i = 0; i < COMMAND_KEY.length; i++) {
            if(next.equals(COMMAND_KEY[i])){
                COMMAND_LIST[i].run();
            }
        }
    }
    private static void newCostumer() {

    }
    private static void removeCostumer() {

    }
    private static void removeNext() {

    }
    private static void getNextCostumer() {

    }
    private static void changeService() {

    }
    private static void waitingTime() {

    }












    private static void showOptions() {
        System.out.println("1.newCostumer <c> <t>   - " + "Adicionar novos clientes à fila de espera.\n" +
                           "2.removeCostumer <k>    - " + "Remover clientes da fila de espera, dado o seu identificador de senha.\n" +
                           "3.removeNextCostumer    - " + "Remover o próximo cliente mais prioritário da fila de espera.\n" +
                           "4.getNextCostumer       - " + "Obter o próximo cliente mais prioritário da fila de espera.\n" +
                           "5.changeService <k> <t> - " + "Alterar o serviço que um determinado cliente pretende obter da agência, dado o seu identificador de senha.\n" +
                           "6.waitingTime <k> <n>   - " + "Obter a informação do tempo estimado de espera para um dado cliente, indicando o seu identificador de senha.\n" +
                                                              " Para esta funcionalidade é necessário identificar o número de funcionários que estão a realizar o atendimento ao cliente. .\n" );

    }


}
