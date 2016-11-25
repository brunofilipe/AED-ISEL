package series.serie2.Problema;

public class Client {
    private int id;
    private int ns;
    private Service service;

    public Client(int id, int ns, Service service) {
        this.id = id;
        this.ns=ns;
        this.service=service;
    }

    public int getNs() { return ns; }
    public int getId() {
        return id;
    }
    public Service getService(){ return service; }

}
