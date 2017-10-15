public class HvitRute extends Rute {

  public HvitRute(int rad, int kolonne) {
    super(rad, kolonne);
  }

  public char tilTegn() {
    return '.';
  }

  public String toString() {
    return "Jeg er hvit."+ rad + " " + kolonne;
  }
}
