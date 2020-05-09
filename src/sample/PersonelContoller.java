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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.*;

public class PersonelContoller implements Initializable {
    private DosyaYazma dosyaYazOku;//dosya yazma/okuma nesnemiz.
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
    private Button kisiyiGuncelle_yeniKisiEkle;
    @FXML
    private Button istenCikar;
    @FXML
    private Button formuTemizle;
    @FXML
    private Button geri;
    @FXML
    private ImageView fotograf;
    @FXML// containerimizi ifade eder.
    private AnchorPane anchorPane;
    /*tableview attribute*/
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
    /*end tableview attribute*/
    @FXML
    private Button fotoSec;
    @FXML
    /*
      secilen fotografin adini kullaniciya arayuzde kullaniciya gosterecek label. Ayrica dosyanin secilip secilmedigini
      kontrol edebilmemiz icin bize yardimci olacaktir.
    */
    private Label FotoName;

    // kullanicinin personel icin sectigi fotografin yolunu barindiracak string degiskeni
    private String fotoUrl;
    // tableview uzerinde sectigimiz personeli temsil eder.
    private Personel selectedPersonel;

    // bu main nesnemiz, pencereler arasi gecislerimizde pencere title'ını degistirmemiz icin gereklidir.
    private Main main = new Main();

    // tableview icin bilgilerin tutuldugu liste
    private ObservableList<Personel> list = FXCollections.observableArrayList();

    private String name;

    // polymorphism
    private Personel isci;
    private Personel ITElemani;
    private Personel muhasebeci;
    private Personel muhendis;
    private Personel pazarlamaci;
    private Personel teknikEleman;
    private Personel teknisyen;



    /*Metodlar*/


    // Personel Listesi sayfasina ilk girdigimizde arayüzümüzdeki birimlere ilk deger atamasini gerceklestirecek kod blogu
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.personelTabosuGuncelle();
        this.getIseGirisTarihi().setValue(LocalDate.now());
        this.getFotograf().setImage(new Image("/images/unnamed.jpg"));
        /*
         * form bos oldugu zaman form temizle ve isten cikar butonları gereksiz olacagindan bu butonlari default olarak
         * gorunmez yapiyoruz...
         * */
        this.getFormuTemizle().setVisible(false);
        this.getIstenCikar().setVisible(false);
    }

    // personelin fotografinin secilmesi icin fotograf sec butonuna basilince gerceklesecek islemlerin oldugu blok
    public void fotografSec(ActionEvent e) {
        FileChooser fileChooser = new FileChooser();
        Stage secondaryStage = new Stage();
        //Secilecek dosyanın turunu belirleme islemi saglayan kod parcalari.
        FileChooser.ExtensionFilter fileExtensions = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png", "*.jpeg");
        fileChooser.getExtensionFilters().add(fileExtensions);
        //dosya secim ekraninin acilip secilen dosyanin file nesnesine atanmasi islemi gerceklesiyor
        File selectedFile = fileChooser.showOpenDialog(secondaryStage);
        //secilen dosyanın adini diger metodlarda ulasabilmek icin global label degiskenine ve dosya yolunu da string degiskenine atiyoruz.
        this.getFotoName().setText(selectedFile.getName());
        this.setFotoUrl(selectedFile.getPath());
    }

    // personel bilgilerinin yer aldigi tableview'e bilgilerin yuklenmesini gerceklestirecek kod blogu
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

    // Geri butonunun tıklandığında bir önceki sayfaya geçmesini sağlayacak kod parçası.
    public void geri(ActionEvent e) throws IOException {
        /*
         * pencerenin basliginin guncellenmesi gerek. static olarak olusturdugumuz stage penceremizin basligini kendi
         * olusturdugumuz setTitle metodu ile gunvelliyoruz
         * */
        this.getMain().setTitle("Anasayfa");
        //anasayfa arayuzunu yeni bir anchorpane degiskenine aktarıyoruz
        AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("anasayfa.fxml"));
        //olusturulan anchorpane mevcut anchor pane'imizin üstüne yaziliyor
        this.getAnchorPane().getChildren().setAll(pane);
    }

    // tableview'da bir personele tiklandiginda personel bigilerinin form uzerine aktarilmasini saglayacak kod blogu
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

        // form ozelliklerine nesne ozellikleri aktarilarak formlar dolduruluyor...
        this.getTcKimlikNo().setText(this.getSelectedPersonel().getTCKimlik().toString());
        this.getAdi().setText(this.getSelectedPersonel().getAdi());
        this.getSoyadi().setText(this.getSelectedPersonel().getSoyadi());
        this.getDepartman().setText(this.getSelectedPersonel().getDepartman());
        this.getMaas().setText(String.valueOf(this.getSelectedPersonel().getMaas()));
        this.getIseGirisTarihi().setValue(this.getSelectedPersonel().getIseGiris());
        this.getFotograf().setImage(new Image("/images/" + this.getSelectedPersonel().getFotoName()));

        /*
         * default olarak gorunmez yaptigimiz butonlarımizi gorunur hale getiriyoruz ve daha onceform bos oldugu icin
         * yeni eleman ekle yazan butonumuz personel guncelle olarak guncelleniyor.
         * */
        this.getFormuTemizle().setVisible(true);
        this.getIstenCikar().setVisible(true);
        this.getKisiyiGuncelle_yeniKisiEkle().setText("Personel Güncelle");
    }

    /*Yeni bir personel eklemek istedigimizde Yeni personel ekle butonuna bastigimizda veya var olan personelin
      bilgilerini guncellemek istedigimizde persoel guncelle butonuna basıldiginda gerceklesecek islemlerin
      bulundugu kod blogu
    */
    public void personelBilgiGuncelleEkle(ActionEvent e) {
        if (this.getKisiyiGuncelle_yeniKisiEkle().getText().equals("Yeni Personel Ekle")) {
            /*
            * her personel kategorisine ozel personel olusturucu metodu olusturulacaktır. ve her calisan kendi personel
            * olusturucusuyla dosyaya yazilacaktir.
            * */
            if (this.getDepartman().getText().equals("İşçi")) {
                this.setIsci(new Isci(100, 0));
                this.getIsci().setAdi(this.getAdi().getText());
                this.getIsci().setSoyadi(this.getSoyadi().getText());
                this.getIsci().setTCKimlik(Long.valueOf(this.getTcKimlikNo().getText()));
                this.getIsci().setMaas(Integer.parseInt(this.getMaas().getText()));
                this.getIsci().setDepartman(this.getDepartman().getText());
                this.getIsci().setFotoName(this.getFotoName().getText());
                this.getIsci().setIseGiris(this.getIseGirisTarihi().getValue());
                this.personelOlustur((Isci) this.getIsci());
            } else if (this.getDepartman().getText().equals("ITElemanı")) {

            } else if (this.getDepartman().getText().equals("Muhasebeci")) {

            } else if (this.getDepartman().getText().equals("Muhendis")) {

            } else if (this.getDepartman().getText().equals("Pazarlamacı")) {

            } else if (this.getDepartman().getText().equals("Teknisyen")) {

            } else if (this.getDepartman().getText().equals("Teknik Eleman")) {

            } else {
                this.personelOlustur();//personel sinifi belirlenemeyen bir sinifsa default personel dosyasina kayit edilecek
            }
        } else {
            /*
             * her personel kategorisine ozel personel guncelleme metodu olusturulacaktır. ve her calisan kendi personel
             * guncelleme metoduyla dosyada guncellenecektir
             * */
            if (this.getDepartman().getText().equals("İşçi")) {
                this.setIsci(new Isci());
                this.getIsci().setAdi(this.getAdi().getText());
                this.getIsci().setSoyadi(this.getSoyadi().getText());
                this.getIsci().setTCKimlik(Long.valueOf(this.getTcKimlikNo().getText()));
                this.getIsci().setMaas(Integer.parseInt(this.getMaas().getText()));
                this.getIsci().setDepartman(this.getDepartman().getText());
                this.getIsci().setFotoName(this.getFotoName().getText());
                this.getIsci().setIseGiris(this.getIseGirisTarihi().getValue());
                this.personelGuncelle((Isci) this.getIsci());
            } else if (this.getDepartman().getText().equals("ITElemanı")) {

            } else if (this.getDepartman().getText().equals("Muhasebeci")) {

            } else if (this.getDepartman().getText().equals("Muhendis")) {

            } else if (this.getDepartman().getText().equals("Pazarlamacı")) {

            } else if (this.getDepartman().getText().equals("Teknisyen")) {

            } else if (this.getDepartman().getText().equals("Teknik Eleman")) {

            } else {
                this.personelGuncelle();//personel sinifi belirlenemeyen bir sinifsa default personel dosyasindan guncellenecek
            }
        }
    }

    public boolean personelGuncelle() {
        /*
         *  string degiskeni, dosyaya yazilacak yaziyi, name dagiskenini ise personelin fotograf isminin benzersiz
         *  olmasi icin random bir dosya ismi belirlememizde kullancagiz.
         * */
        String string;
        this.setName(UUID.randomUUID().toString());
        /*
         * aaa boolean degiskeni, bir personelin bilgisi guncellenirken fotograf olarak mevcut fotografi degistirdiysek
         * yeni dosya adiyla yeni fotografin kullanilmasi icin true degerini alacak ve buna bagli olarak kisinin kendine ozel
         * olarak tanimlanan name fotograf adiyla dosyaya yeniden kaydedilmesini saglayacaktir.
         * */
        boolean aaa = false;
        /*
         * fotograf olarak herhangi bir dosya secilmediyse mevcuttaki fotograf adininin degistirilmeden kullanilmasini
         * saglamak icin dosyaya bu fotograf adiyla kaydediyoruz
         * */
        if (this.getFotoName().getText().equals("")) {
            string = "!" + this.getAdi().getText() + "@" + this.getSoyadi().getText() + "%" + this.getTcKimlikNo().getText()
                    + "&" + this.getMaas().getText() + "?" + this.getDepartman().getText() + "*" + this.getIseGirisTarihi().getValue() + "_" + this.getSelectedPersonel().getFotoName() + "<";
        } else {
            string = "!" + this.getAdi().getText() + "@" + this.getSoyadi().getText() + "%" + this.getTcKimlikNo().getText()
                    + "&" + this.getMaas().getText() + "?" + this.getDepartman().getText() + "*" + this.getIseGirisTarihi().getValue() + "_" + this.getName() + ".jpg<";
            aaa = true;
        }
        /*
         * check degiskeni, dosyada degistirilmesi gereken kisinin satiri bulunmadiysa veya kisinin kimlik numarasi
         * degistiyse yeni personel olusturulmasi icin gerekli islemlerin saglanmasini gercekletirilmesi adina
         * disarda kontrol edilecek ve yeni personel olusturulacak
         *
         * */
        boolean check = false;
        try {
            /*
             * baslangicta dosyadaki satirlar okunuyor
             * */
            List<String> lines = this.getDosyaYazOku().dosyadanOku("personel");
            int size = lines.size();
            for (int i = 0; i < size; i++) {
                String str = lines.get(i);
                int strsize = str.length();
                for (int j = 0; j < strsize; j++) {
                    String tckimlik = "";
                    /*
                     * formdaki kimlik numarasinin dosyada var olup olmadigini kontrol etmek amaciyla her satirdaki
                     * kimlik numaralari okunuyor
                     * */
                    if (str.charAt(j) == '%') {
                        j++;
                        while (str.charAt(j) != '&') {
                            tckimlik += str.charAt(j++);
                        }
                    }
                    /*
                     * formdaki kimlik numarasi bu satirdaki okunan kimlik numarasi ile eslesiyorsa bu sart icindeki
                     * islemler gerceklesecek
                     * */
                    if (tckimlik.equals(this.getTcKimlikNo().getText())) {
                        /*
                         * eger aaa degiskeni true ise bu personelin kisisel fotografi var demektir ve secilen
                         * fotograf bizim images klasorumuze kaydedilecek.
                         * */
                        if (aaa) {
                            /*
                             * a degiskeni, mevcut fotograf dosyada varsa eger onu silip yenisini yuklemek amaciyla
                             * kontrol etmemize yariyor
                             * */
                            boolean a = this.getDosyaYazOku().dosyaAra(this.getSelectedPersonel().getFotoName());
                            // dosya adi unnamed degilse silme islemi gerceklesecek.
                            if (a && !this.getSelectedPersonel().getFotoName().equals("unnamed.jpg")) {
                                Files.delete(Paths.get("C:\\Users\\gurkangltekin\\OneDrive - Istanbul Universitesi\\Belgeler\\IdeaProjects\\ikpyo\\src\\images\\" + this.getSelectedPersonel().getFotoName()));
                            }
                            // kaynak fotograf yolu
                            File source = new File(this.getFotoUrl());
                            // kopyalanacak yol
                            File dest = new File("C:\\Users\\gurkangltekin\\OneDrive - Istanbul Universitesi\\Belgeler\\IdeaProjects\\ikpyo\\src\\images\\" + this.getName() + ".jpg");
                            try {
                                //kopyalama islemi
                                Files.copy(source.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }
                        //guncellenecek satir siliniyoe
                        lines.remove(i);
                        //guncelleme islemi yapildigi isaretleniyor
                        check = true;
                        //yeni bilgi listeye ekleniyor
                        lines.add(string);
                        //yeni bilgilerle dosyaya yazma islemi gerceklestiriliyor.
                        this.getDosyaYazOku().dosyaGuncelle("personel", lines);
                        //daha fazla islem yapilip zaman harcanmamasi adina i size'a esitlenerek diger satirlarin kontrol edilmesi engelleniyor
                        i = size;
                    }
                }
            }

            if (!check) {
                //guncellenecek kisi dosyada bulunmadiysa yeni personel olusturluyor.
                this.personelOlustur();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        //tablo guncelleme ve form temizleme islemleri gerceklesiyor...
        this.personelTabosuGuncelle();
        this.formTemizle();
        return check;
    }

    public void personelGuncelle(Isci isci) {
        try {
            if (this.personelGuncelle()) {
                /*
                 *  string degiskeni, dosyaya yazilacak yaziyi, name dagiskenini ise personelin fotograf isminin benzersiz
                 *  olmasi icin random bir dosya ismi belirlememizde kullancagiz.
                 * */
                String string;
                /*
                 * fotograf olarak herhangi bir dosya secilmediyse mevcuttaki fotograf adininin degistirilmeden kullanilmasini
                 * saglamak icin dosyaya bu fotograf adiyla kaydediyoruz
                 * */
                if (isci.getFotoName().equals("")) {
                    isci.setFotoName(this.getSelectedPersonel().getFotoName());
                    string = "!" + isci.getAdi() + "@" + isci.getSoyadi() + "%" + isci.getTCKimlik().toString()
                            + "&" + isci.getMaas() + "?" + isci.getDepartman() + "*" + isci.getIseGiris().toString() + "_" +
                            isci.getFotoName() + "<" + isci.getToplamUretim() + "|" + isci.getAylikHedef() + ">";
                } else {
                    isci.setFotoName(this.getName());
                    string = "!" + isci.getAdi() + "@" + isci.getSoyadi() + "%" + isci.getTCKimlik().toString()
                            + "&" + isci.getMaas() + "?" + isci.getDepartman() + "*" + isci.getIseGiris().toString() + "_" +
                            isci.getFotoName() + ".jpg<" + isci.getToplamUretim() + "|" + isci.getAylikHedef() + ">";
                }
                /*
                 * check degiskeni, dosyada degistirilmesi gereken kisinin satiri bulunmadiysa veya kisinin kimlik numarasi
                 * degistiyse yeni personel olusturulmasi icin gerekli islemlerin saglanmasini gercekletirilmesi adina
                 * disarda kontrol edilecek ve yeni personel olusturulacak
                 *
                 * */
                boolean check = false;
                /*
                 * baslangicta dosyadaki satirlar okunuyor
                 * */
                List<String> lines = this.getDosyaYazOku().dosyadanOku("isci");
                int size = lines.size();
                for (int i = 0; i < size; i++) {
                    String str = lines.get(i);
                    int strsize = str.length();
                    for (int j = 0; j < strsize; j++) {
                        String tckimlik = "";
                        /*
                         * formdaki kimlik numarasinin dosyada var olup olmadigini kontrol etmek amaciyla her satirdaki
                         * kimlik numaralari okunuyor
                         * */
                        if (str.charAt(j) == '%') {
                            j++;
                            while (str.charAt(j) != '&') {
                                tckimlik += str.charAt(j++);
                            }
                        }
                        /*
                         * formdaki kimlik numarasi bu satirdaki okunan kimlik numarasi ile eslesiyorsa bu sart icindeki
                         * islemler gerceklesecek
                         * */
                        if (tckimlik.equals(isci.getTCKimlik().toString())) {
                            //guncellenecek satir siliniyoe
                            lines.remove(i);
                            //guncelleme islemi yapildigi isaretleniyor
                            check = true;
                            //yeni bilgi listeye ekleniyor
                            lines.add(string);
                            //yeni bilgilerle dosyaya yazma islemi gerceklestiriliyor.
                            this.getDosyaYazOku().dosyaGuncelle("isci", lines);
                            //daha fazla islem yapilip zaman harcanmamasi adina i size'a esitlenerek diger satirlarin kontrol edilmesi engelleniyor
                            i = size;
                        }
                    }
                }
                if (!check) {
                    //guncellenecek kisi dosyada bulunmadiysa yeni personel olusturluyor.
                    this.personelOlustur(isci);
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    // Yeni personel ekleme islemleri
    public void personelOlustur() {
        String string;
        if (this.getFotoName().getText().equals("")) {
            string = "!" + this.getAdi().getText() + "@" + this.getSoyadi().getText() + "%" + this.getTcKimlikNo().getText()
                    + "&" + this.getMaas().getText() + "?" + this.getDepartman().getText() + "*" + this.getIseGirisTarihi().getValue() + "_unnamed.jpg<";
        } else {
            this.setName(UUID.randomUUID().toString());
            string = "!" + this.getAdi().getText() + "@" + this.getSoyadi().getText() + "%" + this.getTcKimlikNo().getText()
                    + "&" + this.getMaas().getText() + "?" + this.getDepartman().getText() + "*" + this.getIseGirisTarihi().getValue() + "_" + this.getName() + ".jpg<";
            File source = new File(this.getFotoUrl());
            File dest = new File("C:\\Users\\gurkangltekin\\OneDrive - Istanbul Universitesi\\Belgeler\\IdeaProjects\\ikpyo\\src\\images\\" + this.getName() + ".jpg");
            try {
                Files.copy(source.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.getDosyaYazOku().setYazilacakDeger(string);
        try {
            this.getDosyaYazOku().dosyayaYaz("personel");
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.personelTabosuGuncelle();
        this.formTemizle();

    }

    public void personelOlustur(Isci isci) {
        String string;
        if (isci.getFotoName().equals("")) {
            isci.setFotoName("unnamed.jpg");
            string = "!" + isci.getAdi() + "@" + isci.getSoyadi() + "%" + isci.getTCKimlik().toString()
                    + "&" + isci.getMaas() + "?" + isci.getDepartman() + "*" + isci.getIseGiris().toString()
                    + "_" + isci.getFotoName() + "<" + isci.getToplamUretim() + "|" + isci.getAylikHedef() + ">";
        } else {
            isci.setFotoName(UUID.randomUUID().toString());
            string = "!" + isci.getAdi() + "@" + isci.getSoyadi() + "%" + isci.getTCKimlik().toString()
                    + "&" + isci.getMaas() + "?" + isci.getDepartman() + "*" + isci.getIseGiris().toString() + "_"
                    + isci.getFotoName() + ".jpg<" + isci.getToplamUretim() + "|" + isci.getAylikHedef() + ">";
            File source = new File(this.getFotoUrl());
            File dest = new File("C:\\Users\\gurkangltekin\\OneDrive - Istanbul Universitesi\\Belgeler\\IdeaProjects\\ikpyo\\src\\images\\" + isci.getFotoName() + ".jpg");
            try {
                Files.copy(source.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        this.getDosyaYazOku().setYazilacakDeger(string);
        try {
            this.getDosyaYazOku().dosyayaYaz("personel");
            this.getDosyaYazOku().dosyayaYaz("isci");
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.personelTabosuGuncelle();
        this.formTemizle();
    }

    // personeli isten cikarmak icin isten cikar butonuna bastigimizda calisacak kod blogu
    public void personelIstenCikar(ActionEvent e) {
        if (this.getDepartman().getText().equals("İşçi")) {
            Isci isci = new Isci(100, 0);
            isci.setAdi(this.getAdi().getText());
            isci.setSoyadi(this.getSoyadi().getText());
            isci.setTCKimlik(Long.valueOf(this.getTcKimlikNo().getText()));
            isci.setMaas(Integer.parseInt(this.getMaas().getText()));
            isci.setDepartman(this.getDepartman().getText());
            isci.setFotoName(this.getFotoName().getText());
            isci.setIseGiris(this.getIseGirisTarihi().getValue());
            this.istenCikar(isci);
        } else if (this.getDepartman().getText().equals("ITElemanı")) {

        } else if (this.getDepartman().getText().equals("Muhasebeci")) {

        } else if (this.getDepartman().getText().equals("Muhendis")) {

        } else if (this.getDepartman().getText().equals("Pazarlamacı")) {

        } else if (this.getDepartman().getText().equals("Teknisyen")) {

        } else if (this.getDepartman().getText().equals("Teknik Eleman")) {

        } else {
            this.istenCikar();//personel olusturma islemleri farkli bir sinifta gerceklesiyor...
        }
        this.personelTabosuGuncelle();
        this.formTemizle();
    }

    public boolean istenCikar() {
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
                        boolean a = this.getDosyaYazOku().dosyaAra(this.getSelectedPersonel().getFotoName());
                        if (a && !this.getSelectedPersonel().getFotoName().equals("unnamed.jpg")) {
                            Files.delete(Paths.get("C:\\Users\\gurkangltekin\\OneDrive - Istanbul Universitesi\\Belgeler\\IdeaProjects\\ikpyo\\src\\images\\" + this.getSelectedPersonel().getFotoName()));
                        }
                        lines.remove(i);
                        this.getDosyaYazOku().dosyaGuncelle("personel", lines);
                        check = true;
                        i = size;
                    }
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return check;
    }

    public void istenCikar(Isci isci) {
        if (this.istenCikar()) {
            try {
                List<String> lines = this.getDosyaYazOku().dosyadanOku("isci");
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
                            this.getDosyaYazOku().dosyaGuncelle("isci", lines);
                            i = size;
                        }
                    }
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    // form uzerindeki bilgileri temizlemek istedigimizde form temizleme metodunun calismasini saglamak icin ilk bu blok ziyaret edilecektir
    public void formuTemizle(ActionEvent e) {
        this.formTemizle();
    }

    /*form temizleme islemi sadece form temizle butonu araciligiyla degil personel ekle, guncelle, isten cikar
      islemlerinden sonrada gerceklesecegi icin sadece actioneventli bir metodla olmamasi adına ve diger metodlar uzerinden
      de ulasmak istedigimizde rahatca ulasabilmek icin bu metos yazilmistir
    */
    public void formTemizle() {
        this.getTcKimlikNo().setText("");
        this.getAdi().setText("");
        this.getSoyadi().setText("");
        this.getMaas().setText("");
        this.getDepartman().setText("");
        this.getIseGirisTarihi().setValue(LocalDate.now());
        this.getFotograf().setImage(new Image("/images/unnamed.jpg"));
        this.getKisiyiGuncelle_yeniKisiEkle().setText("Yeni Personel Ekle");
        this.getFormuTemizle().setVisible(false);
        this.getIstenCikar().setVisible(false);
        this.getPersonelTable().getSelectionModel().clearSelection();
        this.getFotoName().setText("");
    }


    /*Get - Set Metodlari*/


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
        if (this.main == null)
            this.main = new Main();
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

    private TableView getPersonelTable() {
        return personelTable;
    }

    private void setPersonelTable(TableView personelTable) {
        this.personelTable = personelTable;
    }

    private TableColumn<Personel, Long> getTableTC() {
        return tableTC;
    }

    private void setTableTC(TableColumn<Personel, Long> tableTC) {
        this.tableTC = tableTC;
    }

    private TableColumn<Personel, String> getTableAdi() {
        return tableAdi;
    }

    private void setTableAdi(TableColumn<Personel, String> tableAdi) {
        this.tableAdi = tableAdi;
    }

    private TableColumn<Personel, String> getTableSoyadi() {
        return tableSoyadi;
    }

    private void setTableSoyadi(TableColumn<Personel, String> tableSoyadi) {
        this.tableSoyadi = tableSoyadi;
    }

    private TableColumn<Personel, String> getTableDepartmani() {
        return tableDepartmani;
    }

    private void setTableDepartmani(TableColumn<Personel, String> tableDepartmani) {
        this.tableDepartmani = tableDepartmani;
    }

    private TableColumn<Personel, Date> getTableIsegiris() {
        return tableIsegiris;
    }

    private void setTableIsegiris(TableColumn<Personel, Date> tableIsegiris) {
        this.tableIsegiris = tableIsegiris;
    }

    private ObservableList<Personel> getList() {
        return list;
    }

    private void setList(ObservableList<Personel> list) {
        this.list = list;
    }

    public Button getFotoSec() {
        return fotoSec;
    }

    public void setFotoSec(Button fotoSec) {
        this.fotoSec = fotoSec;
    }

    public Label getFotoName() {
        return FotoName;
    }

    public void setFotoName(Label fotoName) {
        FotoName = fotoName;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }

    public Personel getSelectedPersonel() {
        return selectedPersonel;
    }

    public void setSelectedPersonel(Personel selectedPersonel) {
        this.selectedPersonel = selectedPersonel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}