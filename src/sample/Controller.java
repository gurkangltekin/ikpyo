package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class Controller {
    @FXML
    private AnchorPane anchorPane;

    private Main main = new Main();


    public void girisCikis(ActionEvent e) throws IOException {
        this.getMain().setTitle("Giriş Çıkış Takip");
        AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("girisCikisTakip.fxml"));
        anchorPane.getChildren().setAll(pane);
    }
    public void personelListesi(ActionEvent e) throws IOException {
        this.getMain().setTitle("Personel");
        AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("personel.fxml"));
        anchorPane.getChildren().setAll(pane);
    }
    public void isciler(ActionEvent e) throws IOException {
        this.getMain().setTitle("İşçi");
        AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("Isci.fxml"));
        anchorPane.getChildren().setAll(pane);
    }
    public void pazarlamacilar(ActionEvent e) throws IOException {
        this.getMain().setTitle("Pazarlamacı");
        AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("Pazarlamaci.fxml"));
        anchorPane.getChildren().setAll(pane);
    }
    public void itElemani(ActionEvent e) throws IOException {
        this.getMain().setTitle("IT Elemanı");
        AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("ITElemani.fxml"));
        anchorPane.getChildren().setAll(pane);
    }
    public void muhasebeci(ActionEvent e) throws IOException {
        this.getMain().setTitle("Muhasebeci");
        AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("Muhasebeci.fxml"));
        anchorPane.getChildren().setAll(pane);
    }
    public void muhendis(ActionEvent e) throws IOException {
        this.getMain().setTitle("Mühendis");
        AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("Muhendis.fxml"));
        anchorPane.getChildren().setAll(pane);
    }
    public void satislar(ActionEvent e) throws IOException {
        this.getMain().setTitle("Satışlar");
        AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("satislar.fxml"));
        anchorPane.getChildren().setAll(pane);
    }
    public void uretim(ActionEvent e) throws IOException {
        this.getMain().setTitle("Üretilenler");
        AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("uretilenler.fxml"));
        anchorPane.getChildren().setAll(pane);
    }
    public void teknisyen(ActionEvent e) throws IOException {
        this.getMain().setTitle("Teknisyen");
        AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("teknisyen.fxml"));
        anchorPane.getChildren().setAll(pane);
    }
    public void teknikEleman(ActionEvent e) throws IOException {
        this.getMain().setTitle("Teknik Eleman");
        AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("TeknikEleman.fxml"));
        anchorPane.getChildren().setAll(pane);
    }

    public Main getMain() {
        if(this.main == null)
            this.main = new Main();
        return main;
    }
}
