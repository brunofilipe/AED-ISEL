package series.serie2.Problema;


public class ClientPrio {
    private int ns;
    private Service service;

    public ClientPrio(int ns, Service service) {
        this.ns = ns;
        this.service = service;
    }

    public int getNs() {
        return ns;
    }

    public Service getService() {
        return service;
    }
}
