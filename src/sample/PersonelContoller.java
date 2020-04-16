package sample;


import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import javax.swing.text.html.ImageView;
import java.util.List;

public class PersonelContoller {
    private Personel personel;
    private List<Personel> personelList;
    private DosyaYazma dosyaYazOku;
    @FXML
    private TextField tcKimlikNo;
    @FXML
    private TextField adi;
    @FXML
    private TextField soyadi;
    @FXML
    private DatePicker iseGirisTarihi;
    @FXML
    private TextField maas;
    @FXML
    private TextField departman;
    @FXML
    private ListView<String> iseGirisSaatleri;
    @FXML
    private ListView<String> istenCikisSaatleri;
    @FXML
    private ListView<String> personelListesi = new ListView<String>();
    @FXML
    private Button kisiyiGuncelle_yeniKisiEkle;
    @FXML
    private Button istenCikar;
    @FXML
    private Button formuTemizle_Detay;
    @FXML
    private ImageView fotograf;

    public void personelListele() {

    }

    public void personelBilgiGuncelle(ActionEvent e){
        tcKimlikNo.setText("1000000000");
        adi.setText("Gürkan");
        soyadi.setText("Gültekin");
        maas.setText("2000");
        departman.setText("Bilişim");
        this.personelListesi.setItems(FXCollections.observableArrayList("ADASDA","ADASDASD"));
        formuTemizle_Detay.setText("Formu Temizle");

    }

    public void personelOlustur() {

    }

    public void personelIstenCikar(ActionEvent e){
        tcKimlikNo.setText("1000000000");
        adi.setText("Gurkan");
        soyadi.setText("Gultekin");
        maas.setText("2000");
        departman.setText("Bilişim");
        this.personelListesi.setItems(FXCollections.observableArrayList("ADASDA","ADASDASD"));

    }



    public void formuTemizle_Detay(ActionEvent e){
        if(this.tcKimlikNo.equals("")){
            formuTemizle_Detay.setText("Formu Temizle");
        }else{
            tcKimlikNo.setText("");
            adi.setText("");
            soyadi.setText("");
            maas.setText("");
            departman.setText("");
            formuTemizle_Detay.setText("Detay");
        }
    }

    public Personel getPersonel() {
        return personel;
    }

    public void setPersonel(Personel personel) {
        this.personel = personel;
    }

    public List<Personel> getPersonelList() {
        return personelList;
    }

    public void setPersonelList(List<Personel> personelList) {
        this.personelList = personelList;
    }

    public DosyaYazma getDosyaYazOku() {
        return dosyaYazOku;
    }

    public void setDosyaYazOku(DosyaYazma dosyaYazOku) {
        this.dosyaYazOku = dosyaYazOku;
    }

    public TextField getTcKimlikNo() {
        return tcKimlikNo;
    }

    public void setTcKimlikNo(TextField tcKimlikNo) {
        this.tcKimlikNo = tcKimlikNo;
    }

    public TextField getAdi() {
        return adi;
    }

    public void setAdi(TextField adi) {
        this.adi = adi;
    }

    public TextField getSoyadi() {
        return soyadi;
    }

    public void setSoyadi(TextField soyadi) {
        this.soyadi = soyadi;
    }

    public DatePicker getIseGirisTarihi() {
        return iseGirisTarihi;
    }

    public void setIseGirisTarihi(DatePicker iseGirisTarihi) {
        this.iseGirisTarihi = iseGirisTarihi;
    }

    public TextField getMaas() {
        return maas;
    }

    public void setMaas(TextField maas) {
        this.maas = maas;
    }

    public TextField getDepartman() {
        return departman;
    }

    public void setDepartman(TextField departman) {
        this.departman = departman;
    }

    public ListView<String> getIseGirisSaatleri() {
        return iseGirisSaatleri;
    }

    public void setIseGirisSaatleri(ListView<String> iseGirisSaatleri) {
        this.iseGirisSaatleri = iseGirisSaatleri;
    }

    public ListView<String> getIstenCikisSaatleri() {
        return istenCikisSaatleri;
    }

    public void setIstenCikisSaatleri(ListView<String> istenCikisSaatleri) {
        this.istenCikisSaatleri = istenCikisSaatleri;
    }

    public ListView<String> getPersonelListesi() {
        return personelListesi;
    }

    public void setPersonelListesi(ListView<String> personelListesi) {
        this.personelListesi = personelListesi;
    }

    public Button getKisiyiGuncelle_yeniKisiEkle() {
        return kisiyiGuncelle_yeniKisiEkle;
    }

    public void setKisiyiGuncelle_yeniKisiEkle(Button kisiyiGuncelle_yeniKisiEkle) {
        this.kisiyiGuncelle_yeniKisiEkle = kisiyiGuncelle_yeniKisiEkle;
    }

    public Button getIstenCikar() {
        return istenCikar;
    }

    public void setIstenCikar(Button istenCikar) {
        this.istenCikar = istenCikar;
    }

    public Button getFormuTemizle_Detay() {
        return formuTemizle_Detay;
    }

    public void setFormuTemizle_Detay(Button formuTemizle_Detay) {
        this.formuTemizle_Detay = formuTemizle_Detay;
    }

    public ImageView getFotograf() {
        return fotograf;
    }

    public void setFotograf(ImageView fotograf) {
        this.fotograf = fotograf;
    }
}
