import java.util.Iterator;

public abstract class Lenkeliste<T> implements Liste<T> {

  protected Node foran;
  protected Node bak;
  protected int antallNoder;

  protected class Node {
    protected T verdi;
    protected Node neste;
//konstruktoren er public, private??? no modifier..
  Node (T verdi) {
      this.verdi = verdi;
    }
  }
  // Liste grensesnitt
    public int storrelse() {
      return antallNoder;
    }
    public boolean erTom() {
      return foran == null;
    }
    public abstract void settInn(T element);

    public abstract T fjern();


    public Iterator<T> iterator() {
      return new LenkelisteIterator();
    }
    protected class LenkelisteIterator implements Iterator<T> {

      private Node posisjon;

      public LenkelisteIterator() {
        this.posisjon = foran;
      }

      public boolean hasNext() {
        if (posisjon == null) {
          return false;
        } else {
          return true;
        }
      }
      public T next() {
        T returVerdi = posisjon.verdi;
        posisjon = posisjon.neste;
        return returVerdi;
      }
      public void remove() {
        //.............
      }

    }

}
