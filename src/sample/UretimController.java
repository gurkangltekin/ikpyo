package sample;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class UretimController {

	private ListView personelListele;
	private Label toplamUretim;
	private Label aylikHedef;
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
