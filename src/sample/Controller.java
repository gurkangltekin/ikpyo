package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class Controller {
    @FXML
    private AnchorPane anchorPane;

    private Main main = new Main();


    public void girisCikis(ActionEvent e) throws Exception {
        this.getMain().goToPage("girisCikisTakip.fxml");
        this.getMain().setTitle("Giriş Çıkış Takip");
    }
    public void personelListesi(ActionEvent e) throws Exception {
        this.getMain().goToPage("personel.fxml");
        this.getMain().setTitle("Personel");
    }
    public void isciler(ActionEvent e) throws Exception {
        this.getMain().goToPage("isci.fxml");
        this.getMain().setTitle("İşçi");
    }
    public void pazarlamacilar(ActionEvent e) throws Exception {
        this.getMain().goToPage("pazarlamaci.fxml");
        this.getMain().setTitle("Pazarlamacı");
    }
    public void itElemani(ActionEvent e) throws Exception {
        this.getMain().goToPage("ITElemani.fxml");
        this.getMain().setTitle("IT Elemanı");
    }
    public void muhasebeci(ActionEvent e) throws Exception {
        this.getMain().goToPage("Muhasebeci.fxml");
        this.getMain().setTitle("Muhasebeci");
    }
    public void muhendis(ActionEvent e) throws Exception {
        this.getMain().goToPage("Muhendis.fxml");
        this.getMain().setTitle("Mühendis");
    }
    public void satislar(ActionEvent e) throws Exception {
        this.getMain().goToPage("satislar.fxml");
        this.getMain().setTitle("Satışlar");
    }
    public void uretim(ActionEvent e) throws Exception {
        this.getMain().goToPage("uretilenler.fxml");
        this.getMain().setTitle("Üretilenler");
    }
    public void teknisyen(ActionEvent e) throws Exception {
        this.getMain().goToPage("teknisyen.fxml");
        this.getMain().setTitle("Teknisyen");
    }
    public void teknikEleman(ActionEvent e) throws Exception {
        this.getMain().goToPage("TeknikEleman.fxml");
        this.getMain().setTitle("Teknik Eleman");
    }

    public Main getMain() {
        if(this.main == null)
            this.main = new Main();
        return main;
    }
}
