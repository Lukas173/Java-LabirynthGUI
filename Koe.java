public class Koe<T> extends Lenkeliste<T> {

  public void settInn(T element) {
    //skal sette node til slutt av lista
    if (erTom() ) {
      foran = new Node(element);
      bak = foran;
      antallNoder ++;
    } else {
      Node temp = new Node(element);
      bak.neste = temp;
      bak = temp;
      antallNoder ++;
    }
  }
  public T fjern() {
    if (erTom() ) {
      return null;
    } else if (foran.neste == null) {
      T returVerdi = foran.verdi;
      foran = null;
      bak = null;
      antallNoder --;
      return returVerdi;
    }
    T returVerdi = foran.verdi;
    foran = foran.neste;
    antallNoder --;
    return returVerdi;
  }


}
