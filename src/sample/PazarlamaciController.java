package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PazarlamaciController extends PersonelContoller {
    @FXML
    private TextField gunlukSatis;
    @FXML
    private TextField aylikHedef;
    @FXML
    private TextField toplamSatis;

    public TextField getGunlukSatis() {
        return gunlukSatis;
    }



    @Override
    public void personel() {
        this.getPersonelList().add(new Pazarlamaci(100,200));
        this.getPersonelList().add(new Pazarlamaci(50,150));
        this.getPersonelList().add(new Pazarlamaci(10,20));
        this.getPersonelList().get(0).setAdi("Gürkan");
        this.getPersonelList().get(0).setSoyadi("Gültekin");
        this.getPersonelList().get(0).setTCKimlik(487124L);
        this.getPersonelList().get(0).setDepartman("Pazarlama");
        this.getPersonelList().get(0).setMaas(2500);
        this.getPersonelList().get(0).setFotografUrl("/sample/agent47.png");
        this.getPersonelList().get(1).setAdi("Suzan Nur");
        this.getPersonelList().get(1).setSoyadi("Bülbül");
        this.getPersonelList().get(1).setTCKimlik(487126L);
        this.getPersonelList().get(1).setDepartman("Pazarlama");
        this.getPersonelList().get(1).setMaas(3000);
        this.getPersonelList().get(1).setFotografUrl("/sample/unnamed.jpg");
        this.getPersonelList().get(2).setAdi("Ahmet");
        this.getPersonelList().get(2).setSoyadi("Uysal");
        this.getPersonelList().get(2).setTCKimlik(487128L);
        this.getPersonelList().get(2).setDepartman("Pazarlama");
        this.getPersonelList().get(2).setMaas(3500);
        this.getPersonelList().get(2).setFotografUrl("/sample/unnamed.jpg");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.personel();
        int size = this.getPersonelList().size();
        for(int i = 0 ; i < size ; i++){
            this.getPersonelListesi().getItems().add(this.getPersonelList().get(i).getAdi() + " " + this.getPersonelList().get(i).getSoyadi());
        }
        this.getIseGirisTarihi().setValue(LocalDate.now());
        this.getFotograf().setImage(new Image("/sample/unnamed.jpg"));
        this.getFormuTemizle().setVisible(false);
        this.getIstenCikar().setVisible(false);
    }

    @Override
    public void personelDetay(MouseEvent e) {
        int size = this.getPersonelList().size();
        for(int i = 0 ; i < size ; i++){
            if(this.getPersonelListesi().getSelectionModel().getSelectedItem().equals(this.getPersonelList().get(i).getAdi() + " " + this.getPersonelList().get(i).getSoyadi())){
                Pazarlamaci pazarlamaci = (Pazarlamaci) this.getPersonelList().get(i);
                this.getTcKimlikNo().setText(pazarlamaci.getTCKimlik().toString());
                this.getAdi().setText(pazarlamaci.getAdi());
                this.getSoyadi().setText(pazarlamaci.getSoyadi());
                this.getDepartman().setText(pazarlamaci.getDepartman());
                this.getMaas().setText(String.valueOf(pazarlamaci.getMaas()));
                this.getAylikHedef().setText(String.valueOf(pazarlamaci.getAylikHedef()));
                this.getGunlukSatis().setText("0");
                this.getToplamSatis().setText(String.valueOf(pazarlamaci.getToplamSatis()));
                this.getFotograf().setImage(new Image(pazarlamaci.getFotografUrl()));
            }
        }
        this.getFormuTemizle().setVisible(true);
        this.getIstenCikar().setVisible(true);
        this.getKisiyiGuncelle_yeniKisiEkle().setText("Personel Güncelle");
    }

    @Override
    public void personelBilgiGuncelleEkle(ActionEvent e){
        if(this.getKisiyiGuncelle_yeniKisiEkle().getText().equals("Yeni Personel Ekle")){
            Pazarlamaci p = new Pazarlamaci(Integer.parseInt(this.getAylikHedef().getText()), Integer.parseInt(this.getToplamSatis().getText()));
            p.setAdi(this.getAdi().getText());
            p.setSoyadi(this.getAdi().getText());
            p.setTCKimlik(Long.valueOf(this.getTcKimlikNo().getText()));
            p.setMaas(Integer.parseInt(this.getMaas().getText()));
            p.setDepartman(this.getDepartman().getText());
            p.setFotografUrl("/sample/unnamed.jpg");
            this.personelOlustur(p);
        }else{
            //persoenl bilgi guncelleme islemleri
        }
    }

    @Override
    public void personelOlustur(Pazarlamaci p) {
        this.getPersonelList().add(p);
        this.getPersonelListesi().getItems().add(this.getPersonelList().get((this.getPersonelList().size()-1)).getAdi() + " " + this.getPersonelList().get((this.getPersonelList().size()-1)).getSoyadi());
        this.formTemizle();
    }

    public void setGunlukSatis(TextField gunlukSatis) {
        this.gunlukSatis = gunlukSatis;
    }

    public TextField getAylikHedef() {
        return aylikHedef;
    }

    public void setAylikHedef(TextField aylikHedef) {
        this.aylikHedef = aylikHedef;
    }

    public TextField getToplamSatis() {
        return toplamSatis;
    }

    public void setToplamSatis(TextField toplamSatis) {
        this.toplamSatis = toplamSatis;
    }
}
