package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.*;
import java.util.ResourceBundle;

public class GirisCikisTakipController implements Initializable {

    private DosyaYazma dosyaYazOku;

    @FXML
    private Label hata;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button geri;
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

    private ObservableList<Personel> list = FXCollections.observableArrayList();

    private Personel selectedPersonel;


    private Main main;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.personelTabosuGuncelle();
        /*
         * form bos oldugu zaman form temizle ve isten cikar butonları gereksiz olacagindan bu butonlari default olarak
         * gorunmez yapiyoruz...
         * */
    }

    public void geri(ActionEvent e) throws Exception {
        this.getMain().goToPage("anasayfa.fxml");
        this.getMain().setTitle("Anasayfa");
    }

    public void personelDetay(MouseEvent e) {
        // tablomuzda secilen verinin alinmiyor
        TableView.TableViewSelectionModel<Personel> selectionModel = this.getPersonelTable().getSelectionModel();

        // tablodam secilecek maks item sayisinin 1 olmasi gerektigini belirliyoruz
        selectionModel.setSelectionMode(SelectionMode.SINGLE);

        /*
         * tablodan secilen itemin bir personel nesnesi oldugu biliniyor ve personel nesnesine cast islemi gercekleserek
         * diger metodlardan da ulasilabilrmasi adina global selectedPersonel nesnesine aktariliyor.
         * */
        this.setSelectedPersonel((Personel) selectionModel.getSelectedItem());
    }

    public void personelGirdi(ActionEvent e) {
        if (this.getSelectedPersonel() == null) {
            this.getHata().setText("Personel Seçmediniz!");
        } else {
            String string;
            string = "!" + this.getSelectedPersonel().getTCKimlik() + "@" + LocalDate.now() + "&" + LocalTime.now() + "%";

            this.getDosyaYazOku().setYazilacakDeger(string);
            try {
                this.getDosyaYazOku().dosyayaYaz("personelGiris");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            this.getHata().setText("");
            this.getPersonelTable().getSelectionModel().clearSelection();
        }
    }

    public void personelCikti(ActionEvent e) {
        if (this.getSelectedPersonel() == null) {
            this.getHata().setText("Personel Seçmediniz!");
        } else {
            String string;
            string = "!" + this.getSelectedPersonel().getTCKimlik() + "@" + LocalDate.now() + "&" + LocalTime.now() + "%";

            this.getDosyaYazOku().setYazilacakDeger(string);
            try {
                this.getDosyaYazOku().dosyayaYaz("personelCikis");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            this.getHata().setText("");
            this.getPersonelTable().getSelectionModel().clearSelection();
        }
    }

    public void personelTabosuGuncelle() {
        //ilk once mevcut tablomuzda ve listemizde herhangi bir veri varsa onlari sifirliyoruz.
        this.getPersonelTable().getItems().clear();
        this.getList().clear();
        try {
            //dosyadaki tum satirlari okuyarak bir arraylist'e atiyoruz.
            List<String> lines = this.getDosyaYazOku().dosyadanOku("personel");
            int size = lines.size();//satir sayimizi aliyoruz.
            /*
             * Bu for dongusunde her bir satir icin islem gerceklesecektir. i degiskeni txt dosyamizdaki 1 satiri ifade ediyor.
             * */
            for (int i = 0; i < size; i++) {
                //satirdaki yaziyi arraylist degiskeninden string degiskenine aktariyoruz
                String str = lines.get(i);
                //satirin uzunlugunu aliyoruz.
                int strsize = str.length();
                /*
                 * Buradak degiskenler her bir satirdaki ad, soyad vb. bilgileri bir degiskene karakter karakter
                 * yazabilmemiz icin gereklidir
                 * */
                String ad = "";
                String soyad = "";
                String tckimlik = "";
                String maass = "";
                String depart = "";
                String isegiris = "";
                String fotoName = "";
                /*
                 * Bu for dongusunde bir satirdaki her bir karakter icin islem gerceklesecektir. her j degiskeni
                 * satirdaki 1 karakteri ifade ediyor.
                 * */
                for (int j = 0; j < strsize; j++) {
                    /*
                     * her bir sart bir ozelligi belirliyor
                     * satirimizda isim alani '!' simgesi ile baslayip '@' simgesi ile sona eriyor
                     * satirimizda soyisim alani '@' simgesi ile baslayip '%' simgesi ile sona eriyor
                     * satirimizda tckimlik alani '%' simgesi ile baslayip '&' simgesi ile sona eriyor
                     * satirimizda maas alani '&' simgesi ile baslayip '?' simgesi ile sona eriyor
                     * satirimizda departman alani '?' simgesi ile baslayip '*' simgesi ile sona eriyor
                     * satirimizda isegiristarihi alani '*' simgesi ile baslayip '_' simgesi ile sona eriyor
                     * satirimizda fotorgraf adi alani '_' simgesi ile baslayip '<' simgesi ile sona eriyor
                     * bunlara gore de gerekli atama islemleri yapilarak personel nesnesinin olusmasi saglaniyor...
                     * */
                    if (str.charAt(j) == '!') {
                        j++;
                        while (str.charAt(j) != '@') {
                            ad += str.charAt(j++);
                        }
                    }
                    if (str.charAt(j) == '@') {
                        j++;
                        while (str.charAt(j) != '%') {
                            soyad += str.charAt(j++);
                        }
                    }
                    if (str.charAt(j) == '%') {
                        j++;
                        while (str.charAt(j) != '&') {
                            tckimlik += str.charAt(j++);
                        }
                    }
                    if (str.charAt(j) == '&') {
                        j++;
                        while (str.charAt(j) != '?') {
                            maass += str.charAt(j++);
                        }
                    }
                    if (str.charAt(j) == '?') {
                        j++;
                        while (str.charAt(j) != '*') {
                            depart += str.charAt(j++);
                        }
                    }
                    if (str.charAt(j) == '*') {
                        j++;
                        while (str.charAt(j) != '_') {
                            isegiris += str.charAt(j++);
                        }
                    }
                    if (str.charAt(j) == '_') {
                        j++;
                        while (str.charAt(j) != '<') {
                            fotoName += str.charAt(j++);
                        }
                    }
                }
                /*
                 * Bir satirin okunma islemi bitti ve personel nesnesi lusturularak tableview listesine
                 * ekleme islemi gerceklestirilecek
                 * */
                Personel p = new Personel();
                p.setAdi(ad);
                p.setSoyadi(soyad);
                p.setDepartman(depart);
                p.setMaas(Integer.parseInt(maass));
                p.setTCKimlik(Long.valueOf(tckimlik));
                p.setIseGiris(LocalDate.parse(isegiris));
                p.setFotoName(fotoName);
                this.getList().add(p);
            }
        } catch (Exception e) {
            //olasi bir hata durumunda karsilasilan hatayi konsol ekranina yazma islemi gerceklesecek
            e.printStackTrace();
        }
        /*
         * dosyadan okunarak nesneye donusturulen personel bilgilerinin tablomuza aktarma islemlerini gerceklestirelim
         * tablomuza liste degiskenini ekleyelim
         *  */
        this.getPersonelTable().setItems(this.getList());
        //Tablomuzun tableTC sutununa personel nesnemizdeki TCKimlik ozelligini aktariyoruz
        this.getTableTC().setCellValueFactory(new PropertyValueFactory<Personel, Long>("TCKimlik"));
        //Tablomuzun tableAdi sutununa personel nesnemizdeki adi ozelligini aktariyoruz
        this.getTableAdi().setCellValueFactory(new PropertyValueFactory<Personel, String>("adi"));
        //Tablomuzun tableSoyadi sutununa personel nesnemizdeki soyadi ozelligini aktariyoruz
        this.getTableSoyadi().setCellValueFactory(new PropertyValueFactory<Personel, String>("soyadi"));
        //Tablomuzun tableDepartmani sutununa personel nesnemizdeki departman ozelligini aktariyoruz
        this.getTableDepartmani().setCellValueFactory(new PropertyValueFactory<Personel, String>("departman"));
        //Tablomuzun tableIsegiris sutununa personel nesnemizdeki iseGiris ozelligini aktariyoruz
        this.getTableIsegiris().setCellValueFactory(new PropertyValueFactory<Personel, Date>("iseGiris"));
    }

    public AnchorPane getAnchorPane() {
        return anchorPane;
    }

    public void setAnchorPane(AnchorPane anchorPane) {
        this.anchorPane = anchorPane;
    }

    public Main getMain() {
        if (this.main == null)
            this.main = new Main();
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Button getGeri() {
        return geri;
    }

    public void setGeri(Button geri) {
        this.geri = geri;
    }

    public DosyaYazma getDosyaYazOku() {
        if (this.dosyaYazOku == null)
            this.dosyaYazOku = new DosyaYazma();
        return dosyaYazOku;
    }

    public void setDosyaYazOku(DosyaYazma dosyaYazOku) {
        this.dosyaYazOku = dosyaYazOku;
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

    public Personel getSelectedPersonel() {
        return selectedPersonel;
    }

    public void setSelectedPersonel(Personel sselectedPersonel) {
        this.selectedPersonel = sselectedPersonel;
    }

    public Label getHata() {
        return hata;
    }

    public void setHata(Label hata) {
        this.hata = hata;
    }
}
