package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class MuhasebeciController extends PersonelContoller{
    @FXML
    private TextField zimmetEdilenMiktar;
    @FXML
    private TextField odeyecegiMiktar;
    @FXML
    private TextField geriGetirilenMiktar;

    @Override
    public void personel() {
        this.getPersonelList().add(new Muhasebeci());
        this.getPersonelList().add(new Muhasebeci());
        this.getPersonelList().add(new Muhasebeci());
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
            this.getPersonelListesi().getItems().add(this.getPersonelList().get(i).getAdi() + " " + this.getPersonelList().get(i).getSoyadi());
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
            if(this.getPersonelListesi().getSelectionModel().getSelectedItem().equals(this.getPersonelList().get(i).getAdi() + " " + this.getPersonelList().get(i).getSoyadi())){
                Muhasebeci muhasebeci = (Muhasebeci) this.getPersonelList().get(i);
                this.getTcKimlikNo().setText(muhasebeci.getTCKimlik().toString());
                this.getAdi().setText(muhasebeci.getAdi());
                this.getSoyadi().setText(muhasebeci.getSoyadi());
                this.getDepartman().setText(muhasebeci.getDepartman());
                this.getMaas().setText(String.valueOf(muhasebeci.getMaas()));
                this.getGeriGetirilenMiktar().setText(String.valueOf(muhasebeci.getGeriGetirilenTutar()));
                this.getOdeyecegiMiktar().setText(String.valueOf(muhasebeci.getOdeyecegiMiktar()));
                this.getZimmetEdilenMiktar().setText(String.valueOf(muhasebeci.getZimmetEdilenMiktar()));
                this.getFotograf().setImage(new Image(muhasebeci.getFotografUrl()));
            }
        }
        this.getFormuTemizle().setVisible(true);
        this.getIstenCikar().setVisible(true);
        this.getKisiyiGuncelle_yeniKisiEkle().setText("Personel Güncelle");
    }

    @Override
    public void personelBilgiGuncelleEkle(ActionEvent e){
        if(this.getKisiyiGuncelle_yeniKisiEkle().getText().equals("Yeni Personel Ekle")){
            Muhasebeci p = new Muhasebeci();
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
    public void personelOlustur(Muhasebeci i) {
        this.getPersonelList().add(i);
        this.getPersonelListesi().getItems().add(this.getPersonelList().get((this.getPersonelList().size()-1)).getAdi() + " " + this.getPersonelList().get((this.getPersonelList().size()-1)).getSoyadi());
        this.formTemizle();
    }

    public TextField getZimmetEdilenMiktar() {
        return zimmetEdilenMiktar;
    }

    public void setZimmetEdilenMiktar(TextField zimmetEdilenMiktar) {
        this.zimmetEdilenMiktar = zimmetEdilenMiktar;
    }

    public TextField getOdeyecegiMiktar() {
        return odeyecegiMiktar;
    }

    public void setOdeyecegiMiktar(TextField odeyecegiMiktar) {
        this.odeyecegiMiktar = odeyecegiMiktar;
    }

    public TextField getGeriGetirilenMiktar() {
        return geriGetirilenMiktar;
    }

    public void setGeriGetirilenMiktar(TextField geriGetirilenMiktar) {
        this.geriGetirilenMiktar = geriGetirilenMiktar;
    }
}
