import java.util.Iterator;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


public class Labyrinth {
  //multi dimensjon array [rad] [kolonne]
  protected Rute [][] ruter;
  //multi 2 dmension array
  protected int rader;
  protected int kolonner;

  protected Koe<String> utveier;
  //FileNotFoundException??????? throw vs throws?
  public static Labyrinth lesFraFil(File fil) throws FileNotFoundException {
    /// leser inn hele greia
    String[] labInfo;
    Scanner filLeser = new Scanner(fil);
    String linje = filLeser.nextLine();
    labInfo = linje.split(" ");
    int rader = Integer.parseInt(labInfo[0]);
    int kolonner = Integer.parseInt(labInfo[1]);

    Rute[][] ruter = new Rute[rader][kolonner];

    int x = 0;
    int y = 0;
    while (filLeser.hasNextLine() ) {
      linje = filLeser.nextLine();
      for (y = 0; y < kolonner; y ++) {
          if ( linje.charAt(y) == '#' ) {
            ruter[x][y] = new SortRute(x + 1, y + 1);
          } else if (linje.charAt(y) == '.' && erAapning(x, y, rader, kolonner) ){
            ruter[x][y] = new Aapning(x + 1, y + 1);
          } else {
            ruter[x][y] = new HvitRute(x + 1, y + 1);
          }
        }
      x ++;
    }

    // konstruktør kall
    Labyrinth labyrinth = new Labyrinth (rader, kolonner, ruter);
    // jeg setter inn naa labyrinth referanse og naboere
    //for løkke
    for (x = 0; x < rader; x ++) {
      for (y = 0; y < kolonner; y ++) {
        labyrinth.ruter[x][y].labyrinth = labyrinth;

        if (x - 1 < 0) {
          labyrinth.ruter[x][y].nord = null;
        } else {
          labyrinth.ruter[x][y].nord = labyrinth.ruter[x - 1][y];
        }
        if (x + 1 == rader) {
          labyrinth.ruter[x][y].syd = null;
        } else {
          labyrinth.ruter[x][y].syd = labyrinth.ruter[x + 1][y];
        }
        labyrinth.ruter[x][y].labyrinth = labyrinth;
        if (y - 1 < 0) {
          labyrinth.ruter[x][y].vest = null;
        } else {
          labyrinth.ruter[x][y].vest = labyrinth.ruter[x][y - 1];
        }
        if (y + 1 == kolonner) {
          labyrinth.ruter[x][y].oest = null;
        } else {
          labyrinth.ruter[x][y].oest = labyrinth.ruter[x][y + 1];
        }
      }
    }
    return labyrinth;
  }

  static boolean erAapning( int x, int y, int rader, int kolonner) {
    if (x == 0 || y == 0 || x == rader - 1 || y == kolonner - 1  ) {
      return true;
    }
    return false;
  }

  private Labyrinth (int rader, int kolonner, Rute [][] ruter) {
    //kontruktor
    this.rader = rader;
    this.kolonner = kolonner;
    this.ruter = ruter;
  }

  public String toString() {
    //skriv ut hele labyrint

    String linje = rader + " " + kolonner + "\n";
    for (int x = 0; x < rader; x ++) {
      for (int y = 0; y < kolonner; y ++) {
        if(ruter[x][y].tilTegn()== '#') {
          linje = linje + "#";
        } else {
          linje = linje + ".";
        }
      }
      linje = linje + "\n";
    }

    return linje;
  }
  public Koe<String> finnUtveiFra(int kol, int rad) {

    utveier = new Koe<String>();
    ruter[rad-1][kol-1].finnUtvei();
    //gir bwskjed her om det ikke finnes utveier ....
    return utveier;

  }
}
