package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class UretimController {
	@FXML
	private ListView personelListele;
	@FXML
	private Label toplamUretim;
	@FXML
	private Label aylikHedef;
	@FXML
	private DosyaYazma dosyaOkuYaz;
	
	
	
	public ListView getPersonelListele() {
		return personelListele;
	}
	public void setPersonelListele(ListView personelListele) {
		this.personelListele = personelListele;
	}
	public Label getToplamUretim() {
		return toplamUretim;
	}
	public void setToplamUretim(Label toplamUretim) {
		this.toplamUretim = toplamUretim;
	}
	public Label getAylikHedef() {
		return aylikHedef;
	}
	public void setAylikHedef(Label aylikHedef) {
		this.aylikHedef = aylikHedef;
	}
	public DosyaYazma getDosyaOkuYaz() {
		return dosyaOkuYaz;
	}
	public void setDosyaOkuYaz(DosyaYazma dosyaOkuYaz) {
		this.dosyaOkuYaz = dosyaOkuYaz;
	}
	
	
	
}
