import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


public class Oblig5 {
  public static void main(String[] args) throws FileNotFoundException{
    File file = new File("3.in");
    Labyrinth lab = Labyrinth.lesFraFil(file);
    System.out.println(lab.toString() );
    lab.finnUtveiFra(2,2);
    //lab.finnUtveiFra(6,2);

    System.out.println(lab.ruter[1][1].nord.rad );
    System.out.println(lab.ruter[0][0].syd.kolonne );
    System.out.println(lab.ruter[0][1].toString() );
    System.out.println(lab.ruter[0][1].toString() );
    if (lab.ruter[1][0].vest != null) {
      System.out.println(lab.ruter[1][0].vest.toString() );
    } else {
      System.out.println("Hey, du er ut!" );
    }

    System.out.println(lab.ruter[1][1].nord.toString() );
    System.out.println(lab.ruter[1][1].syd.toString() );
    System.out.println(lab.ruter[1][1].oest.oest.toString() );
    System.out.println(lab.utveier.storrelse() );
    System.out.println("uhh" );












  }
}
