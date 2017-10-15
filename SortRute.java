public class SortRute extends Rute {

public SortRute(int rad, int kolonne) {
  super(rad, kolonne);
}

  public char tilTegn() {
    return '#';
  }
  public String toString() {
    return "Jeg er sort. "+ rad + " " + kolonne;
  }
}
