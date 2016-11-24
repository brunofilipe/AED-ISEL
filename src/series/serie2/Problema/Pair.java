package series.serie2.Problema;


public  class Pair<E, P> {

    private E elem;
    private P priority;

    public void setPriority(P priority) {
        this.priority = priority;
    }


    public E getElem() {
        return elem;
    }

    public P getPriority() {
        return priority;
    }

    public Pair(E elem,P priority){
        this.elem = elem;
        this.priority = priority;
    }
}