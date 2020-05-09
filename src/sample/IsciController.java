package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

import static java.lang.Integer.parseInt;

public class IsciController extends PersonelContoller {
    @FXML
    private TextField aylikHedef;
    @FXML
    private TextField toplamUretim;
    @FXML
    private TableView personelTable;
    @FXML
    private TableColumn<Isci, Long> tableTC;
    @FXML
    private TableColumn<Isci, String> tableAdi;
    @FXML
    private TableColumn<Isci, String> tableSoyadi;
    @FXML
    private TableColumn<Isci, String> tableDepartmani;
    @FXML
    private TableColumn<Isci, Date> tableIsegiris;

    private ObservableList<Isci> list = FXCollections.observableArrayList();

    private Isci selectedIsci;

    @Override
    public void personelTabosuGuncelle() {
        //ilk once mevcut tablomuzda ve listemizde herhangi bir veri varsa onlari sifirliyoruz.
        this.getPersonelTable().getItems().clear();
        this.getList().clear();
        try {
            //dosyadaki tum satirlari okuyarak bir arraylist'e atiyoruz.
            List<String> lines = this.getDosyaYazOku().dosyadanOku("isci");
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
                String aylikHedef = "";
                String toplamUretim = "";
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
                     * satirimizda fotograf adi alani '_' simgesi ile baslayip '<' simgesi ile sona eriyor
                     * satirimizda toplam uretim adi alani '<' simgesi ile baslayip '|' simgesi ile sona eriyor
                     * satirimizda aylik hedef adi alani '|' simgesi ile baslayip '>' simgesi ile sona eriyor
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
                    if (str.charAt(j) == '<') {
                        j++;
                        while (str.charAt(j) != '|') {
                            toplamUretim += str.charAt(j++);
                        }
                    }
                    if (str.charAt(j) == '|') {
                        j++;
                        while (str.charAt(j) != '>') {
                            aylikHedef += str.charAt(j++);
                        }
                    }
                }
                /*
                 * Bir satirin okunma islemi bitti ve personel nesnesi lusturularak tableview listesine
                 * ekleme islemi gerceklestirilecek
                 * */
                Isci isci = new Isci(parseInt(aylikHedef), parseInt(toplamUretim));
                isci.setAdi(ad);
                isci.setSoyadi(soyad);
                isci.setDepartman(depart);
                isci.setMaas(parseInt(maass));
                isci.setTCKimlik(Long.valueOf(tckimlik));
                isci.setIseGiris(LocalDate.parse(isegiris));
                isci.setFotoName(fotoName);
                this.getList().add(isci);
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
        this.getTableTC().setCellValueFactory(new PropertyValueFactory<Isci, Long>("TCKimlik"));
        //Tablomuzun tableAdi sutununa personel nesnemizdeki adi ozelligini aktariyoruz
        this.getTableAdi().setCellValueFactory(new PropertyValueFactory<Isci, String>("adi"));
        //Tablomuzun tableSoyadi sutununa personel nesnemizdeki soyadi ozelligini aktariyoruz
        this.getTableSoyadi().setCellValueFactory(new PropertyValueFactory<Isci, String>("soyadi"));
        //Tablomuzun tableDepartmani sutununa personel nesnemizdeki departman ozelligini aktariyoruz
        this.getTableDepartmani().setCellValueFactory(new PropertyValueFactory<Isci, String>("departman"));
        //Tablomuzun tableIsegiris sutununa personel nesnemizdeki iseGiris ozelligini aktariyoruz
        this.getTableIsegiris().setCellValueFactory(new PropertyValueFactory<Isci, Date>("iseGiris"));
    }

    @Override
    public void personelDetay(MouseEvent e) {
        super.personelDetay(e);
        // tablomuzda secilen verinin alinmiyor
        TableView.TableViewSelectionModel<Isci> selectionModel = this.getPersonelTable().getSelectionModel();

        // tablodam secilecek maks item sayisinin 1 olmasi gerektigini belirliyoruz
        selectionModel.setSelectionMode(SelectionMode.SINGLE);

        /*
         * tablodan secilen itemin bir personel nesnesi oldugu biliniyor ve personel nesnesine cast islemi gercekleserek
         * diger metodlardan da ulasilabilrmasi adina global selectedPersonel nesnesine aktariliyor.
         * */
        this.setSelectedIsci((Isci) selectionModel.getSelectedItem());
        this.getAylikHedef().setText(String.valueOf(this.getSelectedIsci().getAylikHedef()));
        this.getToplamUretim().setText(String.valueOf(this.getSelectedIsci().getToplamUretim()));
    }

    @Override
    public void personelGuncelle(Isci isci) {
        isci.setAylikHedef(parseInt(this.getAylikHedef().getText()));
        isci.setToplamUretim(parseInt(this.getToplamUretim().getText()));
        try {
            if (this.personelGuncelle()) { /*
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

    @Override
    public void personelOlustur(Isci isci) {
        String string;
        isci.setToplamUretim(parseInt(this.getToplamUretim().getText()));
        isci.setAylikHedef(parseInt(this.getAylikHedef().getText()));
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

    @Override
    public void formTemizle() {
        super.formTemizle();
        this.getToplamUretim().setText("");
        this.getAylikHedef().setText("");
    }

    /*Get - Set Metodları*/

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

    public TableView getPersonelTable() {
        return personelTable;
    }

    public void setPersonelTable(TableView personelTable) {
        this.personelTable = personelTable;
    }

    public TableColumn<Isci, Long> getTableTC() {
        return tableTC;
    }

    public void setTableTC(TableColumn<Isci, Long> tableTC) {
        this.tableTC = tableTC;
    }

    public TableColumn<Isci, String> getTableAdi() {
        return tableAdi;
    }

    public void setTableAdi(TableColumn<Isci, String> tableAdi) {
        this.tableAdi = tableAdi;
    }

    public TableColumn<Isci, String> getTableSoyadi() {
        return tableSoyadi;
    }

    public void setTableSoyadi(TableColumn<Isci, String> tableSoyadi) {
        this.tableSoyadi = tableSoyadi;
    }

    public TableColumn<Isci, String> getTableDepartmani() {
        return tableDepartmani;
    }

    public void setTableDepartmani(TableColumn<Isci, String> tableDepartmani) {
        this.tableDepartmani = tableDepartmani;
    }

    public TableColumn<Isci, Date> getTableIsegiris() {
        return tableIsegiris;
    }

    public void setTableIsegiris(TableColumn<Isci, Date> tableIsegiris) {
        this.tableIsegiris = tableIsegiris;
    }

    public ObservableList<Isci> getList() {
        return list;
    }

    public void setList(ObservableList<Isci> list) {
        this.list = list;
    }

    public Isci getSelectedIsci() {
        return selectedIsci;
    }

    public void setSelectedIsci(Isci selectedIsci) {
        this.selectedIsci = selectedIsci;
    }
}
