import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.application.Platform;
import java.io.File;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.layout.Background;
import java.io.FileNotFoundException;
import java.io.IOException;


//
public class LabyrinthGUI extends Application  {
  TextField filFelt;
  TextField utveier;
  Labyrinth labyraus;
  Koe<String> ut;
  boolean[][] granFinale;
  GridPane rutenett;

  @Override
  public void start(Stage stage) {


    BorderPane root = new BorderPane();


    Label info = new Label("Velg en labyrinth og trykk paa en hvit rute!");
    info.setPadding(new Insets(10, 10, 10, 10));

    filFelt = new TextField();
    filFelt.setPadding(new Insets(10, 10, 10, 10));




    Button velgFil = new Button("Velg fil..");
    velgFil.setPadding(new Insets(10, 10, 10, 10));
    velgFil.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent e) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Velg labyrinthfil");
        File selectedFile = fileChooser.showOpenDialog(stage);
        if(selectedFile != null) {
                filFelt.setText(selectedFile.getPath());
            }

            System.out.println("vi laster inn labyrinten da");
            //System.out.println(labi.toString());


      }
    });


    Button lastInn = new Button("Last inn");
    lastInn.setPadding(new Insets(10, 10, 10, 10));
    lastInn.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent e) {
        rutenett = new GridPane();
        rutenett.setAlignment(Pos.CENTER);
        File fil = new File(filFelt.getText() );
        try {
        labyraus = Labyrinth.lesFraFil(fil);
        System.out.println(labyraus.toString() );
        for(int x = 0; x < labyraus.rader; x ++) {
          for (int y = 0; y < labyraus.kolonner; y ++) {
            if (labyraus.ruter[x][y].tilTegn() == '#') {
              rutenett.add(new GUISortRute(x + 1, y + 1), y, x);
            } else {
              rutenett.add(new GUIHvitRute(x + 1, y + 1), y, x);
            }

          }
          }
        }
        catch (FileNotFoundException f){}
          root.setCenter(rutenett);
          stage.sizeToScene();



        /*File fil = new File();
        try {
          Labyrinth labi = Labyrinth.lesFraFil(fil);
        }
        catch(FileNotFoundException f)  {}
        System.out.println("vi laster inn labyrinten da");
        System.out.println(labi.toString() );*/
      }
    });
    HBox topp = new HBox(25, info, velgFil, filFelt, lastInn);
    topp.setPadding(new Insets(10, 10, 10, 10));

    Label kommentUtveier = new Label("Antall utveier");
    kommentUtveier.setPadding(new Insets(10, 10, 10, 10));

    utveier  = new TextField();
    utveier.setMinWidth(300);
    utveier.setPadding(new Insets(10, 10, 10, 10));

    Button andre = new Button("Andre utveier");
    andre.setPadding(new Insets(10, 10, 10, 10));

    Button avslutt = new Button("Avslutt");
    avslutt.setPadding(new Insets(10, 10, 10, 10));

    andre.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent e) {
        if (ut == null || ut.storrelse() == 0) {
          utveier.setText("Det finnes ikke andre utveier");
        } else {
          for(int x = 0; x < labyraus.rader; x ++) {
            for (int y = 0; y < labyraus.kolonner; y ++) {
              if (labyraus.ruter[x][y].tilTegn() == '#') {
                rutenett.add(new GUISortRute(x + 1, y + 1), y, x);
              } else {
                rutenett.add(new GUIHvitRute(x + 1, y + 1), y, x);
              }

            }
            }
            granFinale = losningStringTilTabell(ut.fjern(), labyraus.kolonner, labyraus.rader);
            for (int x = 0; x < labyraus.rader; x ++ ) {
              for (int y = 0; y < labyraus.kolonner; y ++) {
                if (granFinale[x][y] == true) {
                  rutenett.add(new losningRute(x + 1, y + 1), y, x);
                }
              }
            }
        }
      }
    });

    avslutt.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent e) {
        Platform.exit();
      }
    });



    HBox bunn = new HBox(25, kommentUtveier, utveier, andre, avslutt);
    bunn.setPadding(new Insets(10, 10, 10, 10));
    root.setTop(topp);

    root.setBottom(bunn);

    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.sizeToScene();
    stage.show();
  }
  public class GUIRute extends Rectangle {

    int storrelse;
    int rad;
    int kolonne;
    Labyrinth labi;


    public GUIRute (int rad, int kolonne) {
      super(8, 8);
      this.rad = rad;
      this.kolonne = kolonne;
      labi = labyraus;

      setStroke(Color.BLACK);
    }
    public int resizeRute() {
      if (labi.rader + labi. kolonner <= 50) {
        return 25;
      } else if (labi.rader + labi. kolonner <= 75) {
        return 15;
      } else {
        return 7;
      }
    }
  }
  public class GUISortRute extends GUIRute {

    public GUISortRute (int rad, int kolonne) {
      super(rad, kolonne);
    }
  }
  public class GUIHvitRute extends GUIRute {
    //bytt background color

    public GUIHvitRute (int rad, int kolonne) {
      super(rad, kolonne);

      setFill(Color.WHITE);
      addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                  public void handle(MouseEvent event) {
                    System.out.println("Bra, det er hvit!");
                    for(int x = 0; x < labyraus.rader; x ++) {
                      for (int y = 0; y < labyraus.kolonner; y ++) {
                        if (labyraus.ruter[x][y].tilTegn() == '#') {
                          rutenett.add(new GUISortRute(x + 1, y + 1), y, x);
                        } else {
                          rutenett.add(new GUIHvitRute(x + 1, y + 1), y, x);
                        }

                      }
                      }
                  /*  if (granFinale != null) {
                      for (int x = 0; x < labyraus.rader; x ++ ) {
                        for (int y = 0; y < labyraus.kolonner; y ++) {
                          if (granFinale[x][y] == true) {
                            rutenett.add(new GUIHvitRute(x + 1, y + 1), y, x);
                          }
                        }
                      }
                    }*/
                    ut = labyraus.finnUtveiFra(kolonne, rad);
                    if (labyraus.ruter[rad - 1][kolonne -1] instanceof Aapning) {
                      utveier.setText("dette er en aapning");
                    } else if (ut.storrelse() == 0) {
                      utveier.setText("Ingen utveier");
                    } else {
                      utveier.setText(ut.storrelse() + "" );
                      granFinale = losningStringTilTabell(ut.fjern(), labyraus.kolonner, labyraus.rader);
                      for (int x = 0; x < labyraus.rader; x ++ ) {
                        for (int y = 0; y < labyraus.kolonner; y ++) {
                          if (granFinale[x][y] == true) {
                            rutenett.add(new losningRute(x + 1, y + 1), y, x);
                          }
                        }
                      }

                    }

                  }
              });
    }
    public void oppdaterFarge() {
      setFill(Color.GREEN);
    }

  }
  public class losningRute extends GUIRute {

    public losningRute (int rad, int kolonne) {
      super(rad, kolonne);
      setFill(Color.GREEN);

    }
  }

  /**
 * Konverterer losning-String fra oblig 5 til en boolean[][]-representasjon
 * av losningstien.
 * @param losningString String-representasjon av utveien
 * @param bredde        bredde til labyrinten
 * @param hoyde         hoyde til labyrinten
 * @return              2D-representasjon av rutene der true indikerer at
 *                      ruten er en del av utveien.
 */
static boolean[][] losningStringTilTabell(String losningString, int bredde, int hoyde) {
    boolean[][] losning = new boolean[hoyde][bredde];
    java.util.regex.Pattern p = java.util.regex.Pattern.compile("\\(([0-9]+),([0-9]+)\\)");
    java.util.regex.Matcher m = p.matcher(losningString.replaceAll("\\s",""));
    while(m.find()) {
        int x = Integer.parseInt(m.group(1))-1;
        int y = Integer.parseInt(m.group(2))-1;
        losning[y][x] = true;
    }
    return losning;
}



  public static void main(String[] args) {
    launch();
  }
}
