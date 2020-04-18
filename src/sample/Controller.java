package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class Controller {
    @FXML
    private Button btn1;
    @FXML
    private Button btn2;
    @FXML
    private Button btn3;
    @FXML
    private Button btn4;
    @FXML
    private Button btn5;
    @FXML
    private Button btn6;
    @FXML
    private Button btn7;
    @FXML
    private Button btn8;
    @FXML
    private Button btn9;
    @FXML
    private Button btn10;
    @FXML
    private Button btn11;
    @FXML
    private AnchorPane anchorPane;

    public void girisCikis(ActionEvent w) throws IOException {
        AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("girisCikisTakip.fxml"));
        anchorPane.getChildren().setAll(pane);
    }
    public void personelListesi(ActionEvent w) throws IOException {
        AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("personel.fxml"));
        anchorPane.getChildren().setAll(pane);
    }
    public void isciler(ActionEvent w) throws IOException {
        AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("isci.fxml"));
        anchorPane.getChildren().setAll(pane);
    }
    public void pazarlamacilar(ActionEvent w) throws IOException {
        AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("pazarlamaci.fxml"));
        anchorPane.getChildren().setAll(pane);
    }
    public void itElemani(ActionEvent w) throws IOException {
        AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("ITElemani.fxml"));
        anchorPane.getChildren().setAll(pane);
    }
    public void muhasebeci(ActionEvent w) throws IOException {
        AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("muhasebeci.fxml"));
        anchorPane.getChildren().setAll(pane);
    }
    public void muhendis(ActionEvent w) throws IOException {
        AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("muhendis.fxml"));
        anchorPane.getChildren().setAll(pane);
    }
    public void satislar(ActionEvent w) throws IOException {
        AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("satislar.fxml"));
        anchorPane.getChildren().setAll(pane);
    }
    public void uretim(ActionEvent w) throws IOException {
        AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("uretilenler.fxml"));
        anchorPane.getChildren().setAll(pane);
    }
    public void teknisyen(ActionEvent w) throws IOException {
        AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("teknisyen.fxml"));
        anchorPane.getChildren().setAll(pane);
    }
    public void teknikEleman(ActionEvent w) throws IOException {
        AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("teknikEleman.fxml"));
        anchorPane.getChildren().setAll(pane);
    }

}
