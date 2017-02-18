package series.serie2;


import java.util.Iterator;
import java.util.NoSuchElementException;

public class Iterables {

    public static  Iterable<Integer> alternateEvenOdd(final Iterable<Integer> src){
        return () -> new Iterator<Integer>() {
            Iterator<Integer> iterAux = src.iterator();
            Integer curr ;
            boolean pair = false;
            @Override
            public boolean hasNext() {
                if (curr!= null)return true;
                while (iterAux.hasNext()){
                    Integer aux = iterAux.next();
                    if(!pair && aux%2 != 0){
                        pair = true;
                        curr = aux;
                        return true;
                    }
                    if(pair && aux%2 == 0){
                        pair = false;
                        curr = aux;
                        return true;
                    }
                }
                return false;
            }

            @Override
            public Integer next() {
              Integer temp;
              if(hasNext()){
                  temp = curr;
                  curr = null;
                  return temp;
              }
              throw new NoSuchElementException("alternateEvenoOdd: no more elements");
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("alternateEvenoOdd: remove not supported");
            }
        };
	}

	public static <T> Iterable<T> flatten( final Iterable< Iterable<T> > src) {
        return () -> new Iterator<T>() {
            Iterator<Iterable<T>> iterAux = src.iterator();
            Iterator<T> it;
            T curr;
            {
                if( iterAux.hasNext() ){
                   it= iterAux.next().iterator();
                }
            }

            @Override
            public boolean hasNext() {
                if (it == null)return false;
                if(curr!= null)return true;
                while (!it.hasNext() && iterAux.hasNext() ){
                    it = iterAux.next().iterator();
            }
                boolean flag = it.hasNext();
                if(flag) curr = it.next();
                return flag;
            }
            @Override
            public T next() {
                T temp;
                if (hasNext()){
                    temp = curr;
                    curr = null;
                    return temp;
                }
                throw new NoSuchElementException("flatten: no more elements");

            }
            @Override
            public void remove() {
                throw new UnsupportedOperationException("flatten: remove not supported");
            }
        };
	}
}
