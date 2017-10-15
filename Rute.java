abstract public class Rute {

  protected Labyrinth labyrinth;
  protected int rad;
  protected int kolonne;

  protected String hvor;

  protected Rute nord;
  protected Rute syd;
  protected Rute vest;
  protected Rute oest;

  public Rute (int rad, int kolonne) {
    this.rad = rad;
    this.kolonne = kolonne;
    this.hvor = "(" + Integer.toString(kolonne) + ", " + Integer.toString(rad) + ")";
  }
  abstract public char tilTegn();

  public void finnUtvei() {
    if(this instanceof SortRute) {
      System.out.println("det gaar ikke " + kolonne + ", "+ rad);
      return;
    }

    if (this.nord instanceof HvitRute) {
      //System.out.println("Vi skal nord. " + nord.kolonne + " " + nord.rad);
      nord.gaa(kolonne, rad, hvor);
    }
    if (this.syd instanceof HvitRute) {
      //System.out.println("Vi skal syd. " + syd.kolonne + " " + syd.rad);
      syd.gaa(kolonne, rad, hvor);
    }
    if (this.vest instanceof HvitRute) {
      //System.out.println("Vi skal vest. " + vest.kolonne + " " + vest.rad );
      vest.gaa(kolonne, rad, hvor);
    }
    if (this.oest instanceof HvitRute) {
      //System.out.println("Vi skal oest. " + oest.kolonne + " " + oest.rad );
      oest.gaa(kolonne, rad, hvor);
    }

  }



  public void gaa (int fraKolonne, int fraRad, String hvor ) {

    hvor = hvor + " --> " + this.hvor;

    if(this instanceof Aapning) {
      System.out.println(hvor);
      labyrinth.utveier.settInn(hvor);

      return;
    }


        if (this.nord instanceof HvitRute && nord.rad != fraRad) {
      //System.out.println("Vi skal nord. " + nord.kolonne + " " + nord.rad);
      nord.gaa(kolonne, rad, hvor);
    }
    if (this.syd instanceof HvitRute && syd.rad != fraRad) {
      //System.out.println("Vi skal syd. " + syd.kolonne + " " + syd.rad);
      syd.gaa(kolonne, rad, hvor);
    }
    if (this.vest instanceof HvitRute && vest.kolonne != fraKolonne ) {
      //System.out.println("Vi skal vest. " + vest.kolonne + " " + vest.rad );
      vest.gaa(kolonne, rad, hvor);
    }
    if (this.oest instanceof HvitRute && oest.kolonne != fraKolonne ) {
      //System.out.println("Vi skal oest. " + oest.kolonne + " " + oest.rad );
      oest.gaa(kolonne, rad, hvor);
    }
  }


}
