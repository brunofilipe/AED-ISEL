package series.serie2.Problema;

/**
 * Created by João Gameiro on 24/11/2016.
 */
public class Pair<E, P> {
    P priority;
    E elem;

    public Pair(E elem,P priority){
        this.elem = elem;
        this.priority = priority;
    }

    public E getElem() {
        return elem;
    }
    public P getPriority() {
        return priority;
    }

}
