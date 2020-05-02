package sample;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class SatislarController {
private ListView personelListele;
private Label toplamSatis;
private Label aylikHedef;
private DosyaYazma dosyaYazOku;
public ListView getPersonelListele() {
	return personelListele;
}
public void setPersonelListele(ListView personelListele) {
	this.personelListele = personelListele;
}
public Label getToplamSatis() {
	return toplamSatis;
}
public void setToplamSatis(Label toplamSatis) {
	this.toplamSatis = toplamSatis;
}
public Label getAylikHedef() {
	return aylikHedef;
}
public void setAylikHedef(Label aylikHedef) {
	this.aylikHedef = aylikHedef;
}
public DosyaYazma getDosyaYazOku() {
	return dosyaYazOku;
}
public void setDosyaYazOku(DosyaYazma dosyaYazOku) {
	this.dosyaYazOku = dosyaYazOku;
}



}
