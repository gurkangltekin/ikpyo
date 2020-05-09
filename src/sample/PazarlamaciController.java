package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.time.LocalDate;
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
    public void personelBilgiGuncelleEkle(ActionEvent e){
        if(this.getKisiyiGuncelle_yeniKisiEkle().getText().equals("Yeni Personel Ekle")){
            Pazarlamaci p = new Pazarlamaci(Integer.parseInt(this.getAylikHedef().getText()), Integer.parseInt(this.getToplamSatis().getText()));
            p.setAdi(this.getAdi().getText());
            p.setSoyadi(this.getAdi().getText());
            p.setTCKimlik(Long.valueOf(this.getTcKimlikNo().getText()));
            p.setMaas(Integer.parseInt(this.getMaas().getText()));
            p.setDepartman(this.getDepartman().getText());
            p.setFotoName("/images/unnamed.jpg");
            //this.personelOlustur(p);
        }else{
            //persoenl bilgi guncelleme islemleri
        }
    }

    /*@Override
    public void personelOlustur(Pazarlamaci p) {
        this.getPersonelList().add(p);
        //this.getPersonelListesi().getItems().add(this.getPersonelList().get((this.getPersonelList().size()-1)).getAdi() + " " + this.getPersonelList().get((this.getPersonelList().size()-1)).getSoyadi());
        this.formTemizle();
    }*/

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
