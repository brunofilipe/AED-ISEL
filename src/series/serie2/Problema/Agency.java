package series.serie2.Problema;

import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

public class Agency implements KeyExtractor<Client> {

    private static int key;
    private static PriorityQueue<Client,ClientPrio>queue;
    private static final Runnable[] COMMAND_LIST = new Runnable[]{Agency::newCostumer, Agency::removeCostumer,
                                Agency::removeNextCostumer, Agency::getNextCostumer,Agency::changeService,Agency::waitingTime};
    private static final String[]COMMAND_KEY = {"newCostumer","removeCostumer", "removeNextCostumer","getNextCostumer",
                                                "changeService","waitingTime"  };
    private static Comparator<ClientPrio>cmp;
    private static Scanner scan;
    private static int numberOfEmployees;
    private static Random rmd = new Random();


    public static void main(String[] args) {
        if(args.length == 0){showOptions();return;}
        numberOfEmployees =Integer.parseInt(args[0]);
        init();
        Client c1 = new Client(123,key);
        queue.add(c1,new ClientPrio(key,new Service("cartoes",5)));
        ++key;
        Client c2 = new Client(321,key);
        queue.add(c2,new ClientPrio(key,new Service("depositos",2)));
        ++key;
        Client c3 = new Client(231,key);
        queue.add(c3,new ClientPrio(key,new Service("levantamentos",1)));
        ++key;
       /* Client c4 = new Client(999,key);
        queue.add(c4,new ClientPrio(key,new Service("levantamentos",1)));
        ++key;
        Client c5 = new Client(1000,key);
        queue.add(c5,new ClientPrio(key,new Service("levantamentos",1)));
        ++key;
        Client c6 = new Client(1001,key);
        queue.add(c6,new ClientPrio(key,new Service("depositos",2)));
        ++key;*/
        run();
    }

    private static void init() {
        cmp= (o1, o2) -> {
           if(o1.getService().getTime()==o2.getService().getTime())
               return (o1.getNs()-o2.getNs());
            return (o1.getService().getTime() - o2.getService().getTime());
        };
        queue = new PriorityQueue(cmp,new Agency());
        key = rmd.nextInt(20);
        scan = new Scanner(System.in);
    }

    private static void run() {
        String command;
        do{
            System.out.println("Choose An Available Command");
             command= scan.next();
            checkCommand(command);
        }while (!command.equals("exit"));
    }

    @Override
    public int getKey(Client c) {
        return c.getNs();
    }

    private static void checkCommand(String next) {
        for (int i = 0; i < COMMAND_KEY.length; i++) {
            if(next.equals(COMMAND_KEY[i])){
                COMMAND_LIST[i].run();
                break;
            }
        }
    }

    private static void newCostumer() {
        System.out.println("Insert client ID ");
        int id = scan.nextInt();
        System.out.println("Insert which service you want ");
        String name = scan.next();
        System.out.println("Insert how long the service takes ");
        int time = scan.nextInt();
        Service service = new Service(name,time);
        Client client = new Client(id,rmd.nextInt(20));
        queue.add(client,new ClientPrio(client.getNs(),service));
    }

    private static void removeCostumer() {
        String answ;
        do{
            System.out.println("Are you sure you want to remove the Client? Y/N");
            answ = scan.next();
        }while(!answ.equals("Y") && !answ.equals("N"));
        if(answ.equals("Y")) {
            System.out.println("Insert key to Remove");
            int key = scan.nextInt();
            queue.remove(key);
        }
    }

    private static void removeNextCostumer() {
        String answ;
        do{
            System.out.println("Are you sure you want to remove the Client? Y/N");
            answ = scan.next();
        }while(!answ.equals("Y") && !answ.equals("N"));
        if(answ.equals("Y")) {
           int id = queue.poll().getId();
            System.out.println("Id of the removed client is" + id);
        }
    }

    private static void getNextCostumer() {
        System.out.println("This is your next Client!");
        int id = queue.pick().getId();
        System.out.println("ID: " + id);
    }

    private static void changeService() {
        String answ;
            do{
                System.out.println("Are you sure you want to change the Service? Y/N");
                answ = scan.next();
            }while(!answ.equals("Y") && !answ.equals("N"));
            if(answ.equals("Y")) {
                System.out.println("Insert the Client key...");
                int key = scan.nextInt();
                System.out.println("Now insert the Service you want to Change To...");
                System.out.println("Option 1 - \n"+
                                   "Option 2 - \n"+
                                   "Option 3 - \n"+
                                   "Option 4 - \n");

                String name = scan.next();
                System.out.println("How long does it take");
                int time = scan.nextInt();
                Service serv = new Service(name,time);
                queue.update(key,new ClientPrio(key,serv));
            }
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
