package series.serie2.Problema;

/**
 * Created by João Gameiro on 24/11/2016.
 */
public class Service {
    private String name;
    private int time;

    public Service(String name, int time) {
       this.name = name;
        this.time = time;
    }

    public int getTime(){
        return time;
    }

    public String getString(){
        return name;
    }
}
