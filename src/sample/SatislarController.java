package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class SatislarController {
	@FXML
private ListView personelListele;
	@FXML
private Label toplamSatis;
	@FXML
private Label aylikHedef;
	@FXML
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
