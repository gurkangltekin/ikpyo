package sample;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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
    @FXML
    private TableView personelTable;
    @FXML
    private TableColumn<Personel, Long> tableTC;
    @FXML
    private TableColumn<Personel, String> tableAdi;
    @FXML
    private TableColumn<Personel, String> tableSoyadi;
    @FXML
    private TableColumn<Personel, String> tableDepartmani;
    @FXML
    private TableColumn<Personel, Date> tableIsegiris;

    private Main main = new Main();

    private Personel isci;
    private Personel ITElemani;
    private Personel muhasebeci;
    private Personel muhendis;
    private Personel pazarlamaci;
    private Personel teknikEleman;
    private Personel teknisyen;

    private ObservableList<Personel> list = FXCollections.observableArrayList();

    public void personel() {
        this.getPersonelTable().getItems().clear();
        this.getList().clear();
        try {
            List<String> lines = this.getDosyaYazOku().dosyadanOku("personel");
            int size = lines.size();
            for (int i = 0; i < size; i++) {
                String str = lines.get(i);
                int strsize = str.length();
                for (int j = 0; j < strsize; j++) {
                    String ad = "";
                    String soyad = "";
                    String tckimlik = "";
                    String maass = "";
                    String depart = "";
                    String isegiris = "";
                    if (str.charAt(j++) == '!') {
                        while (str.charAt(j) != '@') {
                            ad += str.charAt(j++);
                        }
                    }
                    if (str.charAt(j++) == '@') {
                        while (str.charAt(j) != '%') {
                            soyad += str.charAt(j++);
                        }
                    }
                    if (str.charAt(j++) == '%') {
                        while (str.charAt(j) != '&') {
                            tckimlik += str.charAt(j++);
                        }
                    }
                    if (str.charAt(j++) == '&') {
                        while (str.charAt(j) != '?') {
                            maass += str.charAt(j++);
                        }
                    }
                    if (str.charAt(j++) == '?') {
                        while (str.charAt(j) != '*') {
                            depart += str.charAt(j++);
                        }
                    }
                    if (str.charAt(j++) == '*') {
                        while (str.charAt(j) != '_') {
                            isegiris += str.charAt(j++);
                        }
                    }
                    Personel p = new Personel();
                    p.setAdi(ad);
                    p.setSoyadi(soyad);
                    p.setDepartman(depart);
                    p.setMaas(Integer.parseInt(maass));
                    p.setTCKimlik(Long.valueOf(tckimlik));
                    p.setIseGiris(LocalDate.parse(isegiris));
                    this.getPersonelList().add(p);
                    list.add(p);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.getPersonelTable().setItems(this.getList());
        this.getTableTC().setCellValueFactory(new PropertyValueFactory<Personel, Long>("TCKimlik"));
        this.getTableAdi().setCellValueFactory(new PropertyValueFactory<Personel, String>("adi"));
        this.getTableSoyadi().setCellValueFactory(new PropertyValueFactory<Personel, String>("soyadi"));
        this.getTableDepartmani().setCellValueFactory(new PropertyValueFactory<Personel, String>("departman"));
        this.getTableIsegiris().setCellValueFactory(new PropertyValueFactory<Personel, Date>("iseGiris"));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.personel();
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
        int size = this.getPersonelList().size();
        TableView.TableViewSelectionModel<Personel> selectionModel = personelTable.getSelectionModel();
        selectionModel.setSelectionMode(SelectionMode.SINGLE);
        Personel pe = (Personel) selectionModel.getSelectedItem();
        for (int i = 0; i < size; i++) {
            if (pe.getTCKimlik().equals(this.getPersonelList().get(i).getTCKimlik())) {
                Personel p = this.getPersonelList().get(i);
                this.getTcKimlikNo().setText(p.getTCKimlik().toString());
                this.getAdi().setText(p.getAdi());
                this.getSoyadi().setText(p.getSoyadi());
                this.getDepartman().setText(p.getDepartman());
                this.getMaas().setText(String.valueOf(p.getMaas()));
                this.getIseGirisTarihi().setValue(p.getIseGiris());
                //this.getFotograf().setImage(new Image(pazarlamaci.getFotografUrl()));
            }
        }
        this.getFormuTemizle().setVisible(true);
        this.getIstenCikar().setVisible(true);
        this.getKisiyiGuncelle_yeniKisiEkle().setText("Personel Güncelle");

    }

    public void personelBilgiGuncelleEkle(ActionEvent e) {
        if (this.getKisiyiGuncelle_yeniKisiEkle().getText().equals("Yeni Personel Ekle")) {
            this.personelOlustur();
        } else {
            //persoenl bilgi guncelleme islemleri
            String string = "!" + this.getAdi().getText() + "@" + this.getSoyadi().getText() + "%" + this.getTcKimlikNo().getText() + "&" + this.getMaas().getText() + "?" + this.getDepartman().getText() + "*" + this.getIseGirisTarihi().getValue() + "_";
            boolean check = false;
            try {
                List<String> lines = this.getDosyaYazOku().dosyadanOku("personel");
                int size = lines.size();
                for (int i = 0; i < size; i++) {
                    String str = lines.get(i);
                    int strsize = str.length();
                    for (int j = 0; j < strsize; j++) {
                        String tckimlik = "";
                        if (str.charAt(j) == '%') {
                            j++;
                            while (str.charAt(j) != '&') {
                                tckimlik += str.charAt(j++);
                            }
                        }
                        if (tckimlik.equals(this.getTcKimlikNo().getText())) {
                            lines.remove(i);
                            check = true;
                            lines.add(string);
                            this.getDosyaYazOku().dosyaGuncelle("personel", lines);
                        }
                    }

                }
                if (!check) {
                    this.personelOlustur();
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            this.personel();
            this.formTemizle();
        }
    }

    public void personelOlustur(Pazarlamaci p) {
        this.getPersonelList().add(p);
        this.getPersonelListesi().getItems().add(this.getPersonelList().get((this.getPersonelList().size() - 1)).getAdi() + " " + this.getPersonelList().get((this.getPersonelList().size() - 1)).getSoyadi());
        this.formTemizle();
    }

    public void personelOlustur(Isci i) {
        this.getPersonelList().add(i);
        this.getPersonelListesi().getItems().add(this.getPersonelList().get((this.getPersonelList().size() - 1)).getAdi() + " " + this.getPersonelList().get((this.getPersonelList().size() - 1)).getSoyadi());
        this.formTemizle();
    }

    public void personelOlustur(Muhasebeci i) {
        this.getPersonelList().add(i);
        this.getPersonelListesi().getItems().add(this.getPersonelList().get((this.getPersonelList().size() - 1)).getAdi() + " " + this.getPersonelList().get((this.getPersonelList().size() - 1)).getSoyadi());
        this.formTemizle();
    }

    public void personelOlustur() {
        String str = "!" + this.getAdi().getText() + "@" + this.getSoyadi().getText() + "%" + this.getTcKimlikNo().getText() + "&" + this.getMaas().getText() + "?" + this.getDepartman().getText() + "*" + this.getIseGirisTarihi().getValue() + "_";
        this.getDosyaYazOku().setYazilacakDeger(str);
        try {
            this.getDosyaYazOku().dosyayaYaz("personel");
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.personel();
        this.formTemizle();
    }

    public void personelIstenCikar(ActionEvent e) {
        try {
            List<String> lines = this.getDosyaYazOku().dosyadanOku("personel");
            int size = lines.size();
            boolean check = false;
            for (int i = 0; i < size; i++) {
                String str = lines.get(i);
                int strsize = str.length();
                for (int j = 0; j < strsize; j++) {
                    String tckimlik = "";
                    if (str.charAt(j) == '%') {
                        j++;
                        while (str.charAt(j) != '&') {
                            tckimlik += str.charAt(j++);
                        }
                    }
                    if (tckimlik.equals(this.getTcKimlikNo().getText())) {
                        lines.remove(i);
                        this.getDosyaYazOku().dosyaGuncelle("personel", lines);
                        i = size;
                    }
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        this.personel();
        this.formTemizle();
    }

    public void formuTemizle(ActionEvent e) {
        this.formTemizle();
    }

    public void formTemizle() {
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
        this.personelTable.getSelectionModel().clearSelection();
    }

    public Personel getPersonel() {
        return personel;
    }

    public void setPersonel(Personel personel) {
        this.personel = personel;
    }

    public List<Personel> getPersonelList() {
        if (this.personelList == null)
            this.personelList = new ArrayList<>();
        return personelList;
    }

    public void setPersonelList(List<Personel> personelList) {
        this.personelList = personelList;
    }

    public DosyaYazma getDosyaYazOku() {
        if (this.dosyaYazOku == null)
            this.dosyaYazOku = new DosyaYazma();
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

    public Personel getIsci() {
        if (this.isci == null)
            this.isci = new Isci(150, 100);
        return isci;
    }

    public void setIsci(Personel isci) {
        this.isci = isci;
    }

    public Personel getITElemani() {
        if (this.ITElemani == null)
            this.ITElemani = new ITElemani();
        return ITElemani;
    }

    public void setITElemani(Personel ITElemani) {
        this.ITElemani = ITElemani;
    }

    public Personel getMuhasebeci() {
        if (this.muhasebeci == null)
            this.muhasebeci = new Muhasebeci();
        return muhasebeci;
    }

    public void setMuhasebeci(Personel muhasebeci) {
        this.muhasebeci = muhasebeci;
    }

    public Personel getMuhendis() {
        if (this.muhendis == null)
            this.muhendis = new Muhendis();
        return muhendis;
    }

    public void setMuhendis(Personel muhendis) {
        this.muhendis = muhendis;
    }

    public Personel getPazarlamaci() {
        if (this.pazarlamaci == null)
            this.pazarlamaci = new Pazarlamaci(0, 0);
        return pazarlamaci;
    }

    public void setPazarlamaci(Personel pazarlamaci) {
        this.pazarlamaci = pazarlamaci;
    }

    public Personel getTeknikEleman() {
        if (this.teknikEleman == null)
            this.teknikEleman = new TeknikEleman();
        return teknikEleman;
    }

    public void setTeknikEleman(Personel teknikEleman) {
        this.teknikEleman = teknikEleman;
    }

    public Personel getTeknisyen() {
        if (this.teknisyen == null)
            this.teknisyen = new Teknisyen();
        return teknisyen;
    }

    public void setTeknisyen(Personel teknisyen) {
        this.teknisyen = teknisyen;
    }

    public TableView getPersonelTable() {
        return personelTable;
    }

    public void setPersonelTable(TableView personelTable) {
        this.personelTable = personelTable;
    }

    public TableColumn<Personel, Long> getTableTC() {
        return tableTC;
    }

    public void setTableTC(TableColumn<Personel, Long> tableTC) {
        this.tableTC = tableTC;
    }

    public TableColumn<Personel, String> getTableAdi() {
        return tableAdi;
    }

    public void setTableAdi(TableColumn<Personel, String> tableAdi) {
        this.tableAdi = tableAdi;
    }

    public TableColumn<Personel, String> getTableSoyadi() {
        return tableSoyadi;
    }

    public void setTableSoyadi(TableColumn<Personel, String> tableSoyadi) {
        this.tableSoyadi = tableSoyadi;
    }

    public TableColumn<Personel, String> getTableDepartmani() {
        return tableDepartmani;
    }

    public void setTableDepartmani(TableColumn<Personel, String> tableDepartmani) {
        this.tableDepartmani = tableDepartmani;
    }

    public TableColumn<Personel, Date> getTableIsegiris() {
        return tableIsegiris;
    }

    public void setTableIsegiris(TableColumn<Personel, Date> tableIsegiris) {
        this.tableIsegiris = tableIsegiris;
    }

    public ObservableList<Personel> getList() {
        return list;
    }

    public void setList(ObservableList<Personel> list) {
        this.list = list;
    }
}