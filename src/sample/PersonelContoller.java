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

public class PersonelContoller implements Initializable {
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
    private ListView<String> personelListesi;
    @FXML
    private Button kisiyiGuncelle_yeniKisiEkle;
    @FXML
    private Button istenCikar;
    @FXML
    private Button formuTemizle;
    @FXML
    private Button geri;
    @FXML
    private ImageView fotograf;
    @FXML
    private AnchorPane anchorPane;

    private Main main = new Main();




    public void personel(){
        this.getPersonelList().add(new Personel());
        this.getPersonelList().add(new Personel());
        this.getPersonelList().add(new Personel());
        this.getPersonelList().get(0).setAdi("Gürkan");
        this.getPersonelList().get(0).setSoyadi("Gültekin");
        this.getPersonelList().get(1).setAdi("Suzan Nur");
        this.getPersonelList().get(1).setSoyadi("Bülbül");
        this.getPersonelList().get(2).setAdi("Ahmet");
        this.getPersonelList().get(2).setSoyadi("Uysal");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.personel();
        int size = this.getPersonelList().size();
        for(int i = 0 ; i < size ; i++){
            this.personelListesi.getItems().add(this.getPersonelList().get(i).getAdi() + " " + this.getPersonelList().get(i).getSoyadi());
        }
        this.iseGirisTarihi.setValue(LocalDate.now());
        this.fotograf.setImage(new Image("/sample/unnamed.jpg"));
        this.formuTemizle.setVisible(false);
        this.istenCikar.setVisible(false);
    }

    public void geri(ActionEvent e) throws IOException {
        this.main.setTitle("Anasayfa");
        AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("anasayfa.fxml"));
        this.anchorPane.getChildren().setAll(pane);
    }

    public void personelDetay(MouseEvent e) {
        if(personelListesi.getSelectionModel().getSelectedItem().equals("Gürkan Gültekin")){
            this.getTcKimlikNo().setText("10000000000");
            this.getAdi().setText("Gürkan");
            this.getSoyadi().setText("Gültekin");
            this.getDepartman().setText("Yazılımcı");
            this.getMaas().setText("2000");
            this.getFotograf().setImage(new Image("/sample/agent47.png"));
        } else if(personelListesi.getSelectionModel().getSelectedItem().equals("Suzan Nur Bülbül")){
            this.getTcKimlikNo().setText("10000000002");
            this.getAdi().setText("Suzan Nur");
            this.getSoyadi().setText("Bülbül");
            LocalDate ld = LocalDate.parse("2015-05-11");
            this.getIseGirisTarihi().setValue(ld);
            this.getDepartman().setText("Bilişim");
            this.getMaas().setText("3000");
            this.getFotograf().setImage(new Image("/sample/usmanaga.png"));
        } else if (personelListesi.getSelectionModel().getSelectedItem().equals("Ahmet Uysal")){
            this.getTcKimlikNo().setText("10000000004");
            this.getAdi().setText("Ahmet");
            this.getSoyadi().setText("Uysal");
            this.getDepartman().setText("Müdür");
            this.getMaas().setText("4000");
            this.getFotograf().setImage(new Image("/sample/unnamed.jpg"));
        }else{
            this.getTcKimlikNo().setText("Bilgi Yok!");
            this.getAdi().setText("Bilgi Yok!");
            this.getSoyadi().setText("Bilgi Yok!");
            this.getDepartman().setText("Bilgi Yok!");
            this.getMaas().setText("Bilgi Yok!");
        }
        this.formuTemizle.setVisible(true);
        this.istenCikar.setVisible(true);
        this.getKisiyiGuncelle_yeniKisiEkle().setText("Personel Güncelle");

    }

    public void personelBilgiGuncelleEkle(ActionEvent e){
        if(this.getKisiyiGuncelle_yeniKisiEkle().getText().equals("Yeni Personel Ekle")){
            this.personelOlustur();
        }else{
            //persoenl bilgi guncelleme islemleri
        }
    }

    public void personelOlustur() {
        this.getPersonelList().add(new Personel());
        this.getPersonelList().get((this.getPersonelList().size()-1)).setAdi(this.getAdi().getText());
        this.getPersonelList().get((this.getPersonelList().size()-1)).setSoyadi(this.getSoyadi().getText());
        this.personelListesi.getItems().add(this.getPersonelList().get((this.getPersonelList().size()-1)).getAdi() + " " + this.getPersonelList().get((this.getPersonelList().size()-1)).getSoyadi());
        this.formTemizle();
    }

    public void personelIstenCikar(ActionEvent e){
        this.personelListesi.getItems().removeAll(this.getAdi().getText() + " " + this.getSoyadi().getText());
        this.formTemizle();
    }

    public void formuTemizle(ActionEvent e){
        this.formTemizle();
    }

    public void formTemizle(){
        tcKimlikNo.setText("");
        adi.setText("");
        soyadi.setText("");
        maas.setText("");
        departman.setText("");
        iseGirisTarihi.setValue(LocalDate.now());
        this.fotograf.setImage(new Image("/sample/unnamed.jpg"));
        this.getKisiyiGuncelle_yeniKisiEkle().setText("Yeni Personel Ekle");
        this.formuTemizle.setVisible(false);
        this.istenCikar.setVisible(false);
        this.personelListesi.getSelectionModel().clearSelection();
    }

    public Personel getPersonel() {
        return personel;
    }

    public void setPersonel(Personel personel) {
        this.personel = personel;
    }

    public List<Personel> getPersonelList() {
        if(this.personelList == null)
            this.personelList = new ArrayList<>();
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

    public Button getFormuTemizle() {
        return formuTemizle;
    }

    public void setFormuTemizle(Button formuTemizle) {
        this.formuTemizle = formuTemizle;
    }

    public ImageView getFotograf() {
        return fotograf;
    }

    public void setFotograf(ImageView fotograf) {
        this.fotograf = fotograf;
    }

    public Button getGeri() {
        return geri;
    }

    public void setGeri(Button geri) {
        this.geri = geri;
    }

    public AnchorPane getAnchorPane() {
        return anchorPane;
    }

    public void setAnchorPane(AnchorPane anchorPane) {
        this.anchorPane = anchorPane;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }
}