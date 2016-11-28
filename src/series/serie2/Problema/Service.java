package series.serie2.Problema;

/**
 * Created by João Gameiro on 28/11/2016.
 */
public enum Service {
        DL(2,"Depositos/Levantamentos"), CA(5,"Cartoes"),
        CR(10, "Creditos"), OF(20, "Operacoes Financeiras");

        private String name;
        private int time;

        Service(int time,String name) {
            this.name = name;
            this.time = time;
        }

        public String getName() {
            return name;
        }

        public int getTime() {
            return time;
        }
    }