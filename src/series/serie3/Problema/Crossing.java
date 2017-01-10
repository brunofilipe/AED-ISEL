package series.serie3.Problema;


public class Crossing {
    public int id;
    public Street list;
    public Enum color = Color.WHITE;
    public  Crossing pre;


    public Crossing(int id){

        this.id=id;
    }

    public Crossing(int id, Street list){
        this.id = id;
        this.list = list;
    }

}
