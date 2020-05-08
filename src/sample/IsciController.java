package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class IsciController extends PersonelContoller {
    @FXML
    private TextField gunlukUretim;
    @FXML
    private TextField aylikHedef;
    @FXML
    private TextField toplamUretim;

    @Override
    public void personel() {
        this.getPersonelList().add(new Isci(100,200));
        this.getPersonelList().add(new Isci(50,150));
        this.getPersonelList().add(new Isci(10,20));
        this.getPersonelList().get(0).setAdi("Gürkan");
        this.getPersonelList().get(0).setSoyadi("Gültekin");
        this.getPersonelList().get(0).setTCKimlik(487124L);
            this.getPersonelList().get(0).setDepartman("Yazılım");
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
            //this.getPersonelListesi().getItems().add(this.getPersonelList().get(i).getAdi() + " " + this.getPersonelList().get(i).getSoyadi());
        }
        this.getIseGirisTarihi().setValue(LocalDate.now());
        this.getFotograf().setImage(new javafx.scene.image.Image("/sample/unnamed.jpg"));
        this.getFormuTemizle().setVisible(false);
        this.getIstenCikar().setVisible(false);
    }

    @Override
    public void personelDetay(MouseEvent e) {
        int size = this.getPersonelList().size();
        for(int i = 0 ; i < size ; i++){
            /*if(this.getPersonelListesi().getSelectionModel().getSelectedItem().equals(this.getPersonelList().get(i).getAdi() + " " + this.getPersonelList().get(i).getSoyadi())){
                Isci pazarlamaci = (Isci) this.getPersonelList().get(i);
                this.getTcKimlikNo().setText(pazarlamaci.getTCKimlik().toString());
                this.getAdi().setText(pazarlamaci.getAdi());
                this.getSoyadi().setText(pazarlamaci.getSoyadi());
                this.getDepartman().setText(pazarlamaci.getDepartman());
                this.getMaas().setText(String.valueOf(pazarlamaci.getMaas()));
                this.getAylikHedef().setText(String.valueOf(pazarlamaci.getAylikHedef()));
                this.getGunlukUretim().setText("0");
                this.getToplamUretim().setText(String.valueOf(pazarlamaci.getToplamUretim()));
                this.getFotograf().setImage(new Image(pazarlamaci.getFotografUrl()));
            }*/
        }
        this.getFormuTemizle().setVisible(true);
        this.getIstenCikar().setVisible(true);
        this.getKisiyiGuncelle_yeniKisiEkle().setText("Personel Güncelle");
    }

    @Override
    public void personelBilgiGuncelleEkle(ActionEvent e){
        if(this.getKisiyiGuncelle_yeniKisiEkle().getText().equals("Yeni Personel Ekle")){
            Pazarlamaci p = new Pazarlamaci(Integer.parseInt(this.getAylikHedef().getText()), Integer.parseInt(this.getToplamUretim().getText()));
            p.setAdi(this.getAdi().getText());
            p.setSoyadi(this.getAdi().getText());
            p.setTCKimlik(Long.valueOf(this.getTcKimlikNo().getText()));
            p.setMaas(Integer.parseInt(this.getMaas().getText()));
            p.setDepartman(this.getDepartman().getText());
            p.setFotografUrl("/sample/unnamed.jpg");
            //this.personelOlustur(p);
        }else{
            //persoenl bilgi guncelleme islemleri
        }
    }

    /*@Override
    public void personelOlustur(Isci i) {
        this.getPersonelList().add(i);
        this.getPersonelListesi().getItems().add(this.getPersonelList().get((this.getPersonelList().size()-1)).getAdi() + " " + this.getPersonelList().get((this.getPersonelList().size()-1)).getSoyadi());
        this.formTemizle();
    }*/

    public TextField getGunlukUretim() {
        return gunlukUretim;
    }

    public void setGunlukUretim(TextField gunlukUretim) {
        this.gunlukUretim = gunlukUretim;
    }

    public TextField getAylikHedef() {
        return aylikHedef;
    }

    public void setAylikHedef(TextField aylikHedef) {
        this.aylikHedef = aylikHedef;
    }

    public TextField getToplamUretim() {
        return toplamUretim;
    }

    public void setToplamUretim(TextField toplamUretim) {
        this.toplamUretim = toplamUretim;
    }
}
