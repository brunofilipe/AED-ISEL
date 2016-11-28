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
        run();
    }

    private static void init() {
        cmp= (o1, o2) -> {
           if(o1.getService().getTime()==o2.getService().getTime())
               return (o2.getNs()-o1.getNs());
            return (o2.getService().getTime() - o1.getService().getTime());
        };
        queue = new PriorityQueue(10,cmp,new Agency());
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
        System.out.println("Insert the service number you want: ");
        System.out.println("1. Depósitos/Levantamentos (DL) – 2 mn;\n" +
                           "2. Cartões (CA) – 5 mn;\n" +
                           "3. Créditos (CR) – 10 mn;\n" +
                           "4. Operações Financeiras (OF) – 20 mn.\n");
        int i = scan.nextInt();
        Service [] s = Service.values();
        Client client = new Client(id,key);
        queue.add(client,new ClientPrio(client.getNs(),s[i-1]));
        System.out.println("Your key is: " + key);
        ++key;
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
            System.out.println("Client with the key: " + key + " removed!");
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
            System.out.println("The Id of the removed client is " + id);
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
                System.out.println("1. Depósitos/Levantamentos (DL) – 2 mn;\n" +
                        "2. Cartões (CA) – 5 mn;\n" +
                        "3. Créditos (CR) – 10 mn;\n" +
                        "4. Operações Financeiras (OF) – 20 mn.\n");
                int i = scan.nextInt();
                Service [] s = Service.values();
                queue.update(key,new ClientPrio(key,s[i-1]));
            }
    }
    private static void waitingTime() {
        int[] bancadas = new int[numberOfEmployees];
        int iHeap = 0;
        ClientPrio c = queue.getPriority(iHeap);
        while ( c.getNs() != key){
            int currTime = c.getService().getTime();
            bancadas[0] += currTime;
            minHeapify(bancadas, numberOfEmployees, 0);
            c = queue.getPriority(++iHeap);
        }
    }

    public static void minHeapify(int[] heap, int heapSize, int i) {
        int l = left(i);
        int r = right( i );
        int largest = ( l >= heapSize || heap[ l ] > heap[i] ) ? i : l;
        if ( r < heapSize && heap[ largest ] > heap[ r ] ) largest = r;
        if ( largest != i ) {
            swap( heap, i, largest );
            minHeapify( heap, heapSize, largest);
        }
    }

    private static void swap(int[] a, int i1, int i2) {
        int aux = a[i1];
        a[i1] = a[i2];
        a[i2] = aux;
    }

    public static int left(int i) { return (i << 1) + 1; }
    public static int right(int i) { return (i << 1) + 2; }

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
